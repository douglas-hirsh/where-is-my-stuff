package today.whereismystuff.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import today.whereismystuff.web.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
