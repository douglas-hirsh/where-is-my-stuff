package today.whereismystuff.web.services;

import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.Item;
import today.whereismystuff.web.models.ItemViewModel;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.repositories.ItemsRepository;
import today.whereismystuff.web.repositories.LocationsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;
    private final LocationsRepository locationsRepository;

    public ItemsServiceImpl(ItemsRepository itemsRepository, LocationsRepository locationsRepository) {
        this.itemsRepository = itemsRepository;
        this.locationsRepository = locationsRepository;
    }

    @Override
    public List<ItemViewModel> getAllItems() {
        List<Location> allLocations = locationsRepository.findAll();
        return itemsRepository
                .findAll()
                .stream()
                .map(item -> new ItemViewModel(item, allLocations))
                .collect(Collectors.toList());
    }
}
