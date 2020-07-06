package today.whereismystuff.web.services;

import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.LocationViewModel;
import today.whereismystuff.web.repositories.LocationsRepository;

import java.util.List;

@Service
public class LocationsServiceImpl implements LocationsService {
    private final LocationsRepository locationsRepository;

    public LocationsServiceImpl(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public List<Location> getAll() {
        return locationsRepository.findAll();
    }

    @Override
    public LocationViewModel getById(long id) {
        return new LocationViewModel(locationsRepository.getOne(id));
    }
}
