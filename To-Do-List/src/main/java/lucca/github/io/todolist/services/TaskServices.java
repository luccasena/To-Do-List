package lucca.github.io.todolist.services;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.Task;
import lucca.github.io.todolist.models.Entity.User;
import lucca.github.io.todolist.models.EntityDTO.TaskDTO;
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
    private final UserRepository userRepository;

    // --------------------------------------------------

    public Optional<Task> findTaskByID(Long id){
        return taskRepository.findById(id);
    }

    public TaskDTO createTaskDTO(Task task){
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getDone());
    }

    // --------------------------------------------------

    public ResponseEntity<?> returnTask(Long id){
        Optional<Task> taskOptional = findTaskByID(id);

        if(taskOptional.isEmpty()){
            return ResponseEntity.badRequest().body("No such task");
        }

        return ResponseEntity.ok(createTaskDTO(taskOptional.get()));

    }

    public List<TaskDTO> allTasks(){
        List<Task>      tasks    = taskRepository.findAll();
        List<TaskDTO>   taskDTOS = new ArrayList<>();

        for (Task task: tasks){
            TaskDTO taskDTO = createTaskDTO(task);
            taskDTOS.add(taskDTO);
        }

        return taskDTOS;
    }

    // --------------------------------------------------

    public ResponseEntity<?> createTask(Long idUser, TaskDTO taskDTO){
        Optional<User> foundUser = userRepository.findById(idUser);

        if(foundUser.isEmpty()){
            return ResponseEntity.badRequest().body("");
        }
        User user = foundUser.get();

        Task task = new Task(user, taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDone());
        taskRepository.save(task);
        return ResponseEntity.ok(task);
    }

    // --------------------------------------------------

    public ResponseEntity<?> deleteTask(Long id){
        Optional<Task> taskOptional = findTaskByID(id);

        if(taskOptional.isEmpty()){
            return ResponseEntity.badRequest().body("No such task");
        }
        taskRepository.delete(taskOptional.get());

        return ResponseEntity.status(204).build();
    }

    // --------------------------------------------------

    public ResponseEntity<?> updateStatusTask(TaskDTO taskDTO, Long id){
        Optional<Task> foundTask = findTaskByID(id);

        if(foundTask.isEmpty()){
            return ResponseEntity.badRequest().body("No such task");
        }

        Task task = foundTask.get();
        task.setDone(taskDTO.getDone());
        taskRepository.save(task);

        return ResponseEntity.ok().body("Task has been updated");

    }

}
