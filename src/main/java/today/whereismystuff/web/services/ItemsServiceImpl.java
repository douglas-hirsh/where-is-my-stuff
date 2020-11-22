package today.whereismystuff.web.services;

import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.*;
import today.whereismystuff.web.repositories.ItemsRepository;
import today.whereismystuff.web.repositories.LocationsRepository;
import today.whereismystuff.web.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;
    private final LocationsRepository locationsRepository;
    private final UsersRepository usersRepository;

    public ItemsServiceImpl(ItemsRepository itemsRepository, LocationsRepository locationsRepository, UsersRepository usersRepository) {
        this.itemsRepository = itemsRepository;
        this.locationsRepository = locationsRepository;
        this.usersRepository = usersRepository;
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

    @Override
    public Item createItem(ItemCreateViewModel newItem) {
        User user = usersRepository.findById(newItem.getUserId()).get();
        Location location = locationsRepository.findFirstByUserAndId(user, newItem.getLocationId());

        Item item = new Item(
                newItem.getName(),
                newItem.getDescription(),
                location,
                user);

        Item savedItem = itemsRepository.save(item);

        return savedItem;
    }
}
