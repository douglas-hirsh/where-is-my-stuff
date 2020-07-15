package today.whereismystuff.web.services;

import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.LocationCreateViewModel;
import today.whereismystuff.web.models.LocationViewModel;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.repositories.LocationsRepository;
import today.whereismystuff.web.repositories.UsersRepository;

import java.util.List;

@Service
public class LocationsServiceImpl implements LocationsService {
    private final LocationsRepository locationsRepository;
    private final UsersRepository usersRepository;

    public LocationsServiceImpl(LocationsRepository locationsRepository, UsersRepository usersRepository) {
        this.locationsRepository = locationsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Location> getAll(User user) {
        return locationsRepository.findByUser(user);
    }

    @Override
    public LocationViewModel getById(User user, long id) {
        return new LocationViewModel(locationsRepository.findFirstByUserAndId(user, id));
    }

    @Override
    public void saveNewLocation(LocationCreateViewModel newLocation) {
        Location parentLocation;

        if(newLocation.getParentId() != null) {
            parentLocation = locationsRepository.getOne(newLocation.getParentId());
        } else {
            //If we don't have a parent then fill in the data to make the create work as a root location.
            parentLocation = new Location();
            parentLocation.setDepth(0);
            parentLocation.setPath("");
        }

        User user = usersRepository.getOne(newLocation.getUserId());

        Location location = new Location(parentLocation.getDepth() + 1, parentLocation.getPath() + "/", newLocation.getName(), user, null);

        Location savedLocation = locationsRepository.save(location);
        savedLocation.setPath(savedLocation.getPath() + savedLocation.getId());

        locationsRepository.save(savedLocation);
    }
}
