package today.whereismystuff.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import today.whereismystuff.web.models.Item;

public interface ItemsRepository extends JpaRepository<Item, Long> {
}
