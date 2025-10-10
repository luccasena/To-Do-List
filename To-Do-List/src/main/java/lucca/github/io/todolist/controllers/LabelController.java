package lucca.github.io.todolist.controllers;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.EntityDTO.LabelDTO;
import lucca.github.io.todolist.services.LabelServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelServices labelServices;

    @GetMapping("/{id}")
    public ResponseEntity<?> getLabelById( @PathVariable Long idUser){
        return ResponseEntity.ok().body(labelServices.getLabelById(idUser));
    }

    @GetMapping
    public ResponseEntity<List<LabelDTO>> getAllLabels(){
        return labelServices.getLabels();
    }

    @PostMapping
    public ResponseEntity<?> createLabel(@RequestBody LabelDTO labelDTO){
        labelServices.createLabel(labelDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLabel(@PathVariable Long id){
        labelServices.deleteLabelById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLabel(@PathVariable Long id, @RequestBody LabelDTO labelDTO){
        labelServices.updateLabel(labelDTO);
        return ResponseEntity.ok().build();
    }
}
