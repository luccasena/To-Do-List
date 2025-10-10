package lucca.github.io.todolist.models.EntityDTO;

import lucca.github.io.todolist.models.Entity.Description;
import lucca.github.io.todolist.models.Entity.Label;

import java.util.List;

public record TaskDTO (Long id, String title, Description description, Boolean done, List<Label> labels){ }
