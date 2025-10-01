package lucca.github.io.todolist.models.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lucca.github.io.todolist.models.EntityDTO.UserDTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Task(User user,String title, String description, Boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.user = user;
    }
}