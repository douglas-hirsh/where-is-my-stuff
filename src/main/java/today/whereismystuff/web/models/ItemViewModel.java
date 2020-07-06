package today.whereismystuff.web.models;


import java.util.List;

public class ItemViewModel {
    private long id;
    private String name;
    private String description;
    private Location location;
    private List<Location> parents;
    private User user;

    public ItemViewModel(long id, String name, String description, Location location, List<Location> parents, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.parents = parents;
        this.user = user;
    }

    public ItemViewModel(Item item, List<Location> allLocations) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.location = item.getLocation();
        this.parents = item.getLocation().getParentLocations(allLocations);
        this.user = item.getUser();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getParents() {
        return parents;
    }

    public void setParents(List<Location> parents) {
        this.parents = parents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
