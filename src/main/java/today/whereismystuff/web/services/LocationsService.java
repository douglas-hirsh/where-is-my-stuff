package today.whereismystuff.web.services;

import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.LocationCreateViewModel;
import today.whereismystuff.web.models.LocationViewModel;
import today.whereismystuff.web.models.User;

import java.util.List;

public interface LocationsService {
    List<Location> getAll(User user);
    LocationViewModel getById(User user, long id);

    void saveNewLocation(LocationCreateViewModel newLocation);
}
