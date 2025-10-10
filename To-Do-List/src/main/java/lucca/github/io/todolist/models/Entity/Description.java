package lucca.github.io.todolist.models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Description {
    @Id
    @Column(name = "task_id")
    private Long id;

    private String text;

    @OneToOne
    @MapsId
    @JoinColumn(name = "task_id")
    @JsonIgnore()
    private Task task;

    public Description(String text, Task task){
        this.text = text;
        this.task = task;
    }

}
