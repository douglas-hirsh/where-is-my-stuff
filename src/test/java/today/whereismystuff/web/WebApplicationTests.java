package today.whereismystuff.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import today.whereismystuff.web.models.Item;
import today.whereismystuff.web.models.Location;
import today.whereismystuff.web.models.User;
import today.whereismystuff.web.repositories.ItemsRepository;
import today.whereismystuff.web.repositories.LocationsRepository;
import today.whereismystuff.web.repositories.UsersRepository;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WebApplicationTests {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private LocationsRepository locationsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MockMvc mvc;

    private User testUser;
    private Location testRootLocation;

    @Test
    void contextLoads() {
        assertNotNull(mvc);
    }

    @BeforeEach
    void before() {
        this.testUser = usersRepository.save(new User("tester", "t@t.com", "test"));
        this.testRootLocation = locationsRepository.save(new Location(1, "/", "House", this.testUser, null));
        testRootLocation.setPath(testRootLocation.getPath() + testRootLocation.getId());
        locationsRepository.save(testRootLocation);
    }

    @Test
    void canGetItemsIndexWithNoItemsFound() throws Exception {
        mvc.perform(get("/items"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Items")))
            .andExpect(content().string(containsString("No items found.")));
    }

    @Test
    void canGetItemsIndexWithItemsFound() throws Exception {
        Location location = locationsRepository.getOne(this.testRootLocation.getId());
        //Not using testLocation because it doesn't run postLoad because we didn't get it from the database.
        Item testItem = new Item("AAA Batteries", "Batteries", location, testUser);
        Item savedItem = itemsRepository.save(testItem);

        mvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Items")))
                .andExpect(content().string(containsString(savedItem.getName())))
                .andExpect(content().string(containsString(savedItem.getLocation().getName())));

    }

}
