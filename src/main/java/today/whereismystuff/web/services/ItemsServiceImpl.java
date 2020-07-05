package today.whereismystuff.web.services;

import org.springframework.stereotype.Service;
import today.whereismystuff.web.models.Item;
import today.whereismystuff.web.repositories.ItemsRepository;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsServiceImpl(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }
}
