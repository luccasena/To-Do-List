package lucca.github.io.todolist.models.EntityDTO;

import java.util.List;

public record TaskCreateRequest(String title, String description, Boolean done, List<Long> labelIds){

}