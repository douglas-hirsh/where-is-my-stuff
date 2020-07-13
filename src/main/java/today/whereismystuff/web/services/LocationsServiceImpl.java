package today.whereismystuff.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.LocationViewModel;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.repositories.LocationsRepository;

import java.util.List;

@Service
public class LocationsServiceImpl implements LocationsService {
    private final LocationsRepository locationsRepository;

    public LocationsServiceImpl(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public List<Location> getAll(User user) {
        return locationsRepository.findByUser(user);
    }

    @Override
    public LocationViewModel getById(User user, long id) {
        return new LocationViewModel(locationsRepository.findFirstByUserAndId(user, id));
    }
}
