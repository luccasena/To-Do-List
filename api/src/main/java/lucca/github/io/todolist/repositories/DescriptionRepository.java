package lucca.github.io.todolist.repositories;

import lucca.github.io.todolist.models.Entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description,Long> {
}
