package today.whereismystuff.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import today.whereismystuff.web.models.Location;
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
        List<Location> allLocations = locationsService.getAll();
        model.addAttribute("allLocations", allLocations);
        return "locations/index";
    }
}
