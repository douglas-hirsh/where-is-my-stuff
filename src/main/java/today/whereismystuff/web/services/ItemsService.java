package today.whereismystuff.web.services;

import today.whereismystuff.web.models.ItemViewModel;

import java.util.List;

public interface ItemsService {
    List<ItemViewModel> getAllItems();
}
