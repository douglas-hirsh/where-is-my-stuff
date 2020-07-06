package today.whereismystuff.web.services;

import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.LocationViewModel;

import java.util.List;

public interface LocationsService {
    List<Location> getAll();
    LocationViewModel getById(long id);
}
