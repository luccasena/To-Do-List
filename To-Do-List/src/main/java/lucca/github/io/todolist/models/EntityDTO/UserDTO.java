package lucca.github.io.todolist.models.EntityDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lucca.github.io.todolist.models.Entity.Task;

import java.util.List;


public record UserDTO (Long id,
                       @NotBlank(message = "Nome não pode ser vazio.")
                       @Size(min = 3,max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
                       String name,
                       String lastname,
                       Integer age,
                       @Email
                       String email,
                       String password,
                       List<Task> tasks) { }
