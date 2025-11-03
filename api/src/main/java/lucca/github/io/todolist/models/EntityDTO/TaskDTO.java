package lucca.github.io.todolist.models.EntityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lucca.github.io.todolist.models.Entity.Description;
import lucca.github.io.todolist.models.Entity.Label;

import java.util.List;

public record TaskDTO (
                        Long id,
                       @NotBlank(message = "The title field must not be empty.")
                       @Size(min = 3, max = 40, message = "The title field must contain between 3 and 40 characters.")
                       String title,

                       Description description,

                       @NotNull(message = "The done field must not be empty.")
                       Boolean done,

                       @NotEmpty(message = "The task must contain some label.")
                       List<Label> labels){ }
