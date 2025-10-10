package lucca.github.io.todolist.controllers;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{idTask}")
    public ResponseEntity<DescriptionDTO> getDescriptionById(@PathVariable Long idTask){
        return descriptionServices.findDescriptionById(idTask);
    }

    @GetMapping
    public ResponseEntity<List<DescriptionDTO>> getAllDescriptions(){
        return descriptionServices.getAllDescriptions();
    }

    @PostMapping
    public ResponseEntity<?> createDescription(@RequestBody DescriptionDTO descriptionDTO){
        return descriptionServices.createDescription(descriptionDTO);

    }
    @DeleteMapping("/{idDescription}")
    public ResponseEntity<?> deleteDescription(@PathVariable Long idDescription){
        return descriptionServices.deleteDescription(idDescription);
    }

    @PutMapping("/{idDescription}")
    public ResponseEntity<?> updateDescription(@PathVariable Long idDescription, @RequestBody DescriptionDTO descriptionDTO){
        return descriptionServices.updateDescription(idDescription, descriptionDTO);
    }

}
