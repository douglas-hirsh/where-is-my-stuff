package today.whereismystuff.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import today.whereismystuff.web.models.ItemViewModel;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.services.ItemsService;

import java.util.List;

@Controller
public class ItemsController {
    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("/items")
    public String getAll(@AuthenticationPrincipal User user, Model model) {
        List<ItemViewModel> allItems = itemsService.getAllItems(user);
        model.addAttribute("allItems", allItems);
        return "items/index";
    }
}
