package today.whereismystuff.web.services;

import today.whereismystuff.web.models.ItemViewModel;
import today.whereismystuff.web.models.User;

import java.util.List;

public interface ItemsService {
    List<ItemViewModel> getAllItems(User user);
}
