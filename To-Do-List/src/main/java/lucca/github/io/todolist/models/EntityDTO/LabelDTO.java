package lucca.github.io.todolist.models.EntityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lucca.github.io.todolist.models.Entity.Task;

import java.util.List;

public record LabelDTO(Long id,
                       @NotBlank(message = "The name field must not be empty.")
                       @Size(min = 3,max = 25, message = "The name field must contain between 3 and 25 characters.")
                       String name,

                       List<Task> tasks) { }
