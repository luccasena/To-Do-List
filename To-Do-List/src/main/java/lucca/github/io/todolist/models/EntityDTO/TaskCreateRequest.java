package lucca.github.io.todolist.models.EntityDTO;

import java.util.List;

public record TaskCreateRequest(String title, Boolean done, List<Long> labelIds){

}