package lucca.github.io.todolist.controllers;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.EntityDTO.TaskCreateRequest;
import lucca.github.io.todolist.models.EntityDTO.TaskDTO;
import lucca.github.io.todolist.services.TaskServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskServices taskServices;

    @GetMapping("/{idTask}")
    public ResponseEntity<?> getTaskById(@PathVariable Long idTask){
        return taskServices.findTaskByID(idTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        return taskServices.getAllTasks();
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createTask(@PathVariable Long idUser, @RequestBody TaskCreateRequest taskDTO){
        return taskServices.createTask(idUser, taskDTO);
    }

    @DeleteMapping("/{idTask}")
    public ResponseEntity<?> deleteTask(@PathVariable Long idTask){
        taskServices.deleteTask(idTask);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idTask}")
    public ResponseEntity<?> updateTask(@PathVariable Long idTask, @RequestBody TaskDTO taskDTO){
        taskServices.updateTask(taskDTO, idTask);
        return ResponseEntity.ok().build();
    }

}
