package today.whereismystuff.web.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int depth;

    private String path;

    private String name;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Item> items;

    public Location() {

    }

    public Location(long id, int depth, String path, String name, User user, List<Item> items) {
        this.id = id;
        this.depth = depth;
        this.path = path;
        this.name = name;
        this.user = user;
        this.items = items;
    }

    public Location(int depth, String path, String name, User user, List<Item> items) {
        this.depth = depth;
        this.path = path;
        this.name = name;
        this.user = user;
        this.items = items;
    }

    public List<Long> getPathIds() {
        // shred the path ids.
        List<Long> parsedPathIds = Arrays
                                    .stream(this.path.split("/"))
                                    .filter(Predicate.not(String::isBlank))
                                    .map(Long::parseLong)
                                    .collect(Collectors.toList());

        return parsedPathIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Location> getParentLocations(List<Location> allLocations) {
        return this.getPathIds()
                .stream()
                .map(
                    locationId ->
                        allLocations
                            .stream()
                            .filter(location -> location.getId() == locationId)
                            .findFirst()
                            .get()
                )
                .collect(Collectors.toList());
    }
}
