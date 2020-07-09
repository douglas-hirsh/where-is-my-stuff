package today.whereismystuff.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import today.whereismystuff.web.models.Item;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.repositories.ItemsRepository;
import today.whereismystuff.web.repositories.LocationsRepository;
import today.whereismystuff.web.repositories.UsersRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationServiceTests {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private EntityManager entityManager;

    private User testUser;
    private Location homeLocation;
    private Location bedroomLocation;
    private Location closetLocation;
    private Location shelfLocation;

    @BeforeEach
    void before() {
        this.testUser = usersRepository.save(new User("tester", "t@t.com", "test"));

        this.homeLocation = locationsRepository.save(new Location(1, "/", "House", this.testUser, null));
        homeLocation.setPath(homeLocation.getPath() + homeLocation.getId());
        locationsRepository.save(homeLocation);

        this.bedroomLocation = this.addLocationToLocation(homeLocation, "Bedroom");
        this.closetLocation = this.addLocationToLocation(bedroomLocation, "Closet");
        this.shelfLocation = this.addLocationToLocation(closetLocation, "shelf");

        Item item1 = new Item("Bed", "A Bed", this.bedroomLocation, testUser);
        Item item2 = new Item("Shirt", "A Shirt", closetLocation, testUser);
        Item item3 = new Item("Shirt 2", "A Shirt 2", closetLocation, testUser);
        Item item4 = new Item("Book", "A book", shelfLocation, testUser);

        itemsRepository.saveAll(List.of(item1, item2, item3, item4));
    }

    private Location addLocationToLocation(Location parent, String name) {
        Location newLocation = new Location(parent.getDepth() + 1, parent.getPath() + "/", name, parent.getUser(), null);
        newLocation = locationsRepository.save(newLocation);
        newLocation.setPath(newLocation.getPath() + newLocation.getId());
        locationsRepository.save(newLocation);
        return newLocation;
    }

    @Test
    void canGetAllParentsDescendantLocations() {

        List<Location> locations = locationsRepository.findByPathStartingWithOrPath(closetLocation.getPath() + "/", closetLocation.getPath());
        List<Item> items =  itemsRepository.findByLocationIn(locations);


        //System.out.println(this.closetLocation.getParentLocations(locations));
        items.stream().forEach(s -> System.out.println(s.getName()));

    }
}
