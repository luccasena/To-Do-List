package lucca.github.io.todolist.models.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucca.github.io.todolist.models.Entity.Task;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LabelDTO {
    private Long id;
    private String name;
    private List<Task> tasks;
}
