package today.whereismystuff.web.controllers;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.services.LocationsService;

import java.util.List;

@Controller
public class LocationsController {
    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public String getAll(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Location> allLocations = locationsService.getAll(currentUser);
        model.addAttribute("allLocations", allLocations);
        return "locations/index";
    }

    @GetMapping("/locations/{id}")
    public String getLocation(@PathVariable Long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("location", locationsService.getById(currentUser, id));
        return "locations/show";
    }
}
