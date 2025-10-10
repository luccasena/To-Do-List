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

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        return taskServices.returnTask(id);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> returnTasks(){
        return ResponseEntity.ok(taskServices.getTasks());
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createTask(@PathVariable Long idUser, @RequestBody TaskCreateRequest taskDTO){
        taskServices.createTask(idUser,taskDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskServices.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatusTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        taskServices.updateStatusTask(taskDTO, id);
        return ResponseEntity.ok().build();
    }

}
