package lucca.github.io.todolist.models.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    private String title;
    private String description;
    private Boolean done;
    private List<Long> labelIds;
}
