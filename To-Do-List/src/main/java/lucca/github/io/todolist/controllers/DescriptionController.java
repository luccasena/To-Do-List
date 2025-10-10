package lucca.github.io.todolist.controllers;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.Description;
import lucca.github.io.todolist.models.EntityDTO.DescriptionDTO;
import lucca.github.io.todolist.services.DescriptionServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/description")
@RequiredArgsConstructor
public class DescriptionController {

    private final DescriptionServices descriptionServices;

    @GetMapping
    public ResponseEntity<List<DescriptionDTO>> getAllDescriptions(){
        return descriptionServices.findAllDescriptions();
    }

    @PostMapping
    public ResponseEntity<?> createDescription(@RequestBody DescriptionDTO descriptionDTO){
        descriptionServices.createDescription(descriptionDTO);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping
    public ResponseEntity<?> deleteDescription(@PathVariable Long idDescription){
        descriptionServices.deleteDescription(idDescription);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idDescription}")
    public ResponseEntity<?> updateDescription(@PathVariable Long idDescription, @RequestBody DescriptionDTO descriptionDTO){
        descriptionServices.updateDescription(idDescription, descriptionDTO);
        return ResponseEntity.ok().build();
    }

}
