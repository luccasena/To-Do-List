package lucca.github.io.todolist.models.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucca.github.io.todolist.models.Entity.Task;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    private String name;
    private String lastname;
    private Integer age;

    private String email;
    private String password;

    private List<Task> tasks;

    public UserDTO(Long id, String name, String lastname, Integer age, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
    }

}
