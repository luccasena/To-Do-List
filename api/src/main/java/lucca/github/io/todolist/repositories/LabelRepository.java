package lucca.github.io.todolist.repositories;

import lucca.github.io.todolist.models.Entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {
}
