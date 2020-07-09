package today.whereismystuff.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import today.whereismystuff.web.models.Location;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Location, Long> {
    List<Location> findByPathStartingWithOrPath(String prefix, String path);
}
