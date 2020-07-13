package today.whereismystuff.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import today.whereismystuff.web.models.Item;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.User;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser(User user);
    List<Item> findByLocationIn(List<Location> locations);
    List<Item> findByUserAndLocationIn(User user, List<Location> locations);
}
