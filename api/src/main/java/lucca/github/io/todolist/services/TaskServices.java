package lucca.github.io.todolist.services;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.Description;
import lucca.github.io.todolist.models.Entity.Label;
import lucca.github.io.todolist.models.Entity.Task;
import lucca.github.io.todolist.models.Entity.User;
import lucca.github.io.todolist.models.EntityDTO.TaskCreateRequest;
import lucca.github.io.todolist.models.EntityDTO.TaskDTO;
import lucca.github.io.todolist.models.EntityDTO.UserDTO;
import lucca.github.io.todolist.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServices {

    private final TaskRepository taskRepository;
    private final UserServices userServices;
    private final LabelServices labelServices;
    private final DescriptionServices descriptionServices;

    // -----------------------------------------------------------------------------------------------------------------

    public ResponseEntity<TaskDTO> getTask(Long idTask){
        Optional<Task> foundTask = taskRepository.findById(idTask);

        if(foundTask.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Task task = foundTask.get();

        TaskDTO taskDTO = new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getDone(), task.getLabels());
        return ResponseEntity.ok().body(taskDTO);
    }

    public ResponseEntity<List<TaskDTO>> getAllTasks(Long idUser){
        List<Task> tasks;

        if(idUser != null){
            ResponseEntity<UserDTO> user_response = userServices.getUser(idUser);
            UserDTO user = user_response.getBody();
            assert user != null;
            tasks = user.tasks();

        }else{
            tasks = taskRepository.findAll();

        }

        if(tasks.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<TaskDTO>  taskDTOS = new ArrayList<>();

        for (Task task: tasks){
            TaskDTO taskDTO = new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getDone(), task.getLabels());
            taskDTOS.add(taskDTO);
        }

        return ResponseEntity.ok().body(taskDTOS);
    }

    public ResponseEntity<?> createTask(Long idUser, TaskCreateRequest taskDTO){
        User user = userServices.getUserOptional(idUser);
        List<Label> labels = labelServices.searchAllLabels(taskDTO.labelIds());

        if(labels.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Task task = new Task(user, taskDTO.title(), taskDTO.done(), labels);

        Description description = new Description(taskDTO.description(), task);
        descriptionServices.createDescription(description);

        task.setDescription(description);

        taskRepository.save(task);

        return ResponseEntity.ok().build();
    }

    public void deleteTask(Long idTask){
        Optional<Task> taskOptional = taskRepository.findById(idTask);

        if(taskOptional.isEmpty()){
            ResponseEntity.notFound().build();
            return;
        }

        taskRepository.delete(taskOptional.get());
        ResponseEntity.ok().build();
    }

    public void updateTask(TaskCreateRequest taskDTO, Long id){
        Optional<Task> foundTask = taskRepository.findById(id);

        if(foundTask.isEmpty()){
            ResponseEntity.badRequest().body("No such task");
            return;
        }

        Task task = foundTask.get();

        task.setTitle(taskDTO.title());
        task.setDone(taskDTO.done());

        List<Label> labels = labelServices.searchAllLabels(taskDTO.labelIds());

        if(labels.isEmpty()){
            ResponseEntity.badRequest().build();
            return;
        }

        task.setLabels(labels);

        task.getDescription().setText(taskDTO.description());
        task.getDescription().setTask(task);

        taskRepository.save(task);
        ResponseEntity.ok().build();

    }

}