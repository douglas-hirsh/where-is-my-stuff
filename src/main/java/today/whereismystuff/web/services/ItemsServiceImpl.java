package today.whereismystuff.web.services;

import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.Item;
import today.whereismystuff.web.models.ItemViewModel;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.repositories.ItemsRepository;
import today.whereismystuff.web.repositories.LocationsRepository;

import java.util.ArrayList;
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
    public List<ItemViewModel> getAllItems(User user) {
        List<Location> allLocations = locationsRepository.findByUser(user);
        List<Item> items = itemsRepository.findByUser(user);

        return items
                .stream()
                .map(item -> new ItemViewModel(item, allLocations))
                .collect(Collectors.toList());
    }
}
