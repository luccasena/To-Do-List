package lucca.github.io.todolist.models.EntityDTO;


import lucca.github.io.todolist.models.Entity.Task;

import java.util.List;


public record UserDTO (Long id, String name, String lastname, Integer age, String email, String password,List<Task> tasks) { }
