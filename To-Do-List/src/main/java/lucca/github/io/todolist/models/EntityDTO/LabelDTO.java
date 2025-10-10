package lucca.github.io.todolist.models.EntityDTO;

import lucca.github.io.todolist.models.Entity.Task;

import java.util.List;

public record LabelDTO(Long id, String name, List<Task> tasks) { }
