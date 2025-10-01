package lucca.github.io.todolist.controllers;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.EntityDTO.CreateTaskRequest;
import lucca.github.io.todolist.models.EntityDTO.LabelDTO;
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
    public ResponseEntity<?> returnTaskById(Long id){
        return taskServices.returnTask(id);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> returnTasks(){
        return ResponseEntity.ok(taskServices.allTasks());
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createTask(@PathVariable Long idUser, @RequestBody CreateTaskRequest taskDTO){
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
        return taskServices.updateStatusTask(taskDTO, id);
    }

}
