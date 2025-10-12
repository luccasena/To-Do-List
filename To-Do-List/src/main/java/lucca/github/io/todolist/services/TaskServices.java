package lucca.github.io.todolist.services;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.Description;
import lucca.github.io.todolist.models.Entity.Label;
import lucca.github.io.todolist.models.Entity.Task;
import lucca.github.io.todolist.models.Entity.User;
import lucca.github.io.todolist.models.EntityDTO.DescriptionDTO;
import lucca.github.io.todolist.models.EntityDTO.TaskCreateRequest;
import lucca.github.io.todolist.models.EntityDTO.TaskDTO;
import lucca.github.io.todolist.repositories.LabelRepository;
import lucca.github.io.todolist.repositories.TaskRepository;
import lucca.github.io.todolist.repositories.UserRepository;
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
    private final DescriptionServices descriptionServices;
    private final LabelServices labelServices;

    public Optional<Task> searchTaskById(Long idTask){
        return taskRepository.findById(idTask);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public TaskDTO createTaskDTO(Task task){
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getDone(), task.getLabels());
    }

    public ResponseEntity<TaskDTO> findTaskByID(Long idTask){
        Optional<Task> foundTask = taskRepository.findById(idTask);

        if(foundTask.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        TaskDTO taskDTO = createTaskDTO(foundTask.get());
        return ResponseEntity.ok().body(taskDTO);
    }

    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();

        if(tasks.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<TaskDTO>   taskDTOS = new ArrayList<>();

        for (Task task: tasks){
            TaskDTO taskDTO = createTaskDTO(task);
            taskDTOS.add(taskDTO);
        }

        return ResponseEntity.ok().body(taskDTOS);
    }

    public ResponseEntity<?> createTask(Long idUser, TaskCreateRequest taskDTO){
        Optional<User> foundUser = userServices.searchUserById(idUser);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = foundUser.get();

        List<Label> labels = labelServices.searchAllLabels(taskDTO.labelIds());

        if(labels.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Task task = new Task(user, taskDTO.title(), taskDTO.done(), labels);

        Description description = new Description();

        description.setText(taskDTO.description());
        description.setTask(task);

        task.setDescription(description);
        taskRepository.save(task);

        DescriptionDTO descriptionDTO = new DescriptionDTO(description.getText(), description.getTask().getId());
        descriptionServices.createDescription(descriptionDTO, task);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteTask(Long idTask){
        Optional<Task> taskOptional = taskRepository.findById(idTask);

        if(taskOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        taskRepository.delete(taskOptional.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateTask(TaskCreateRequest taskDTO, Long id){
        Optional<Task> foundTask = taskRepository.findById(id);

        if(foundTask.isEmpty()){
            return ResponseEntity.badRequest().body("No such task");
        }

        Task task = foundTask.get();

        task.setTitle(taskDTO.title());
        task.setDone(taskDTO.done());

        List<Label> labels = labelServices.searchAllLabels(taskDTO.labelIds());

        if(labels.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        task.setLabels(labels);

        taskRepository.save(task);
        return ResponseEntity.ok().build();

    }

}