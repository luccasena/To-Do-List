package lucca.github.io.todolist.models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "task_labels",
                joinColumns = @JoinColumn(name = "task_id"),
                inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore()
    private User user;

    public Task(User user,String title, String description, Boolean done, List<Label> labels) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.user = user;
        this.labels = labels;
    }
}