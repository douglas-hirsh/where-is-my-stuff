package today.whereismystuff.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import today.whereismystuff.web.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("FROM User u WHERE u.email = :name OR u.username = :name")
    User findByUsernameOrEmail(@Param("name") String usernameOrEmail);
}
