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

    @GetMapping("/{idLabel}")
    public ResponseEntity<?> getLabelById(@PathVariable Long idLabel){
        return labelServices.findLabelByID(idLabel);
    }

    @GetMapping
    public ResponseEntity<List<LabelDTO>> getAllLabels(){
        return labelServices.getAllLabels();
    }

    @PostMapping
    public ResponseEntity<?> createLabel(@RequestBody LabelDTO labelDTO){
        labelServices.createLabel(labelDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idLabel}")
    public ResponseEntity<?> deleteLabel(@PathVariable Long idLabel){
        return labelServices.deleteLabel(idLabel);
    }

    @PutMapping("/{idLabel}")
    public ResponseEntity<?> updateLabel(@PathVariable Long idLabel, @RequestBody LabelDTO labelDTO){
        return labelServices.updateLabel(idLabel, labelDTO);
    }

}
