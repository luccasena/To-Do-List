package lucca.github.io.todolist.services;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.Description;
import lucca.github.io.todolist.models.Entity.Task;
import lucca.github.io.todolist.models.EntityDTO.DescriptionDTO;
import lucca.github.io.todolist.repositories.DescriptionRepository;
import lucca.github.io.todolist.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DescriptionServices {

    private final DescriptionRepository descriptionRepository;
    private final TaskRepository taskRepository;

    public void createDescription(DescriptionDTO descriptionDTO){
        Optional<Task> foundTask = taskRepository.findById(descriptionDTO.taskId());

        if(foundTask.isEmpty()){
            ResponseEntity.badRequest().body("");
            return;
        }

        Task task = foundTask.get();
        Description description = new Description(descriptionDTO.text(), task);

        descriptionRepository.save(description);

        ResponseEntity.ok().build();
    }

    public DescriptionDTO createDescriptionDTO(Description description){
        return new DescriptionDTO(description.getText(),  description.getTask().getId());
    }

    public ResponseEntity<List<DescriptionDTO>> findAllDescriptions(){
        List<Description> descriptions = descriptionRepository.findAll();

        if(descriptions.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        List<DescriptionDTO> descriptionDTOS = new ArrayList<>();

        for(Description description: descriptions){
            DescriptionDTO descriptiondto = createDescriptionDTO(description);
            descriptionDTOS.add(descriptiondto);
        }
        return ResponseEntity.ok(descriptionDTOS);

    }

    public void deleteDescription(Long idDescription){
        Optional<Description> foundDescription = descriptionRepository.findById(idDescription);

        if(foundDescription.isEmpty()){
            ResponseEntity.notFound().build();
        }

        Description description = foundDescription.get();
        descriptionRepository.delete(description);
        ResponseEntity.ok().build();

    }

    public void updateDescription(Long idDescription, DescriptionDTO descriptionDTO){
        Optional<Description> foundDescription = descriptionRepository.findById(idDescription);

        if(foundDescription.isEmpty()){
            ResponseEntity.notFound().build();

        }
        Description description = foundDescription.get();
        description.setText(descriptionDTO.text());
        descriptionRepository.save(description);
    }

}
