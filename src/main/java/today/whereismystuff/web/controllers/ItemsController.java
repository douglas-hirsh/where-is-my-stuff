package today.whereismystuff.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import today.whereismystuff.web.models.ItemCreateViewModel;
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

    @PostMapping("/items")
    public String createItem(@AuthenticationPrincipal User user, @ModelAttribute ItemCreateViewModel newItem) {
        newItem.setUserId(user.getId());
        itemsService.createItem(newItem);
        return "redirect:/items";
    }
}
