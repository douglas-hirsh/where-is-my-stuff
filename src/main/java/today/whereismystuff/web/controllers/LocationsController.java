package today.whereismystuff.web.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.LocationCreateViewModel;
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

    @GetMapping(path={"/locations/create", "/locations/{id}/create"})
    public String showCreateLocationForm(@PathVariable(value = "id", required = false) Long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(id != null) {
            model.addAttribute("location", locationsService.getById(currentUser, id));
        }

        model.addAttribute("newLocation", new LocationCreateViewModel("", id, currentUser.getId()));

        return "locations/create";
    }

    @GetMapping("/locations/{id}")
    public String getLocation(@PathVariable Long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("location", locationsService.getById(currentUser, id));
        return "locations/show";
    }

    @PostMapping("locations/create")
    public String saveLocation(@ModelAttribute LocationCreateViewModel newLocation) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newLocation.setUserId(currentUser.getId());

        locationsService.saveNewLocation(newLocation);
        return "redirect:/locations";
    }
}
