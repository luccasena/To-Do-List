package lucca.github.io.todolist.models.EntityDTO;

import lucca.github.io.todolist.models.Entity.Label;

import java.util.List;

public record TaskDTO (Long id, String title, String description, Boolean done, List<Label> labels){ }
