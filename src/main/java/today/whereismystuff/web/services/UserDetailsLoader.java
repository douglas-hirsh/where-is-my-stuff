package today.whereismystuff.web.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.models.UserWithRoles;
import today.whereismystuff.web.repositories.UsersRepository;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepository users;

    public UserDetailsLoader(UsersRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsernameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);
    }
}
