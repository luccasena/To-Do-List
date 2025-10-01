package lucca.github.io.todolist.models.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean done;

}
