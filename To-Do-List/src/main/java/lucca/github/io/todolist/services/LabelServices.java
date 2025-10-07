package lucca.github.io.todolist.services;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.Label;
import lucca.github.io.todolist.models.EntityDTO.LabelDTO;
import lucca.github.io.todolist.repositories.LabelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LabelServices {

    private final LabelRepository labelRepository;

    public LabelDTO createLabelDTO(LabelDTO labelDTO){
        return new LabelDTO(labelDTO.id(), labelDTO.name(), labelDTO.tasks());
    }

    public void createLabel(LabelDTO labelDTO){
        Label label = new Label(labelDTO.id(),labelDTO.name(), labelDTO.tasks());
        labelRepository.save(label);
    }

    public ResponseEntity<List<LabelDTO>> getLabels(){
        List<Label> labels = labelRepository.findAll();

        if(labels.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<LabelDTO> labelDTOs = new ArrayList<>();
        for(Label label : labels){
            labelDTOs.add(new LabelDTO(label.getId(),label.getName(),label.getTasks()));
        }
        return ResponseEntity.ok(labelDTOs);
    }

    public ResponseEntity<?> getLabelById(Long id){
        Optional<Label> label = labelRepository.findById(id);
        if(label.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new LabelDTO(label.get().getId(),label.get().getName(),label.get().getTasks()));
    }



}
