package today.whereismystuff.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import today.whereismystuff.web.models.Location;

public interface LocationsRepository extends JpaRepository<Location, Long> {
}
