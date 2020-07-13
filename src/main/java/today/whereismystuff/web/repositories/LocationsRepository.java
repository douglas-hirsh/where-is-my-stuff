package today.whereismystuff.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.User;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Location, Long> {
    List<Location> findByUser(User user);
    List<Location> findByPathStartingWithOrPath(String prefix, String path);
    List<Location> findByUserAndPathStartingWithOrPath(User user, String prefix, String path);

    @Query("FROM Location l WHERE l.user = :user AND (l.path like concat(:path, '/%') escape '%' OR l.path = :path)")
    List<Location> findAllByPathAndUser(@Param("user") User user, @Param("path") String path);
}
