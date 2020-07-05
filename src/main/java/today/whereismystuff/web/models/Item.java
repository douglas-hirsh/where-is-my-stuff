package today.whereismystuff.web.models;


import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn (name = "location_id")
    private Location location;

    @OneToOne
    private User user;

    public Item() {
    }

    public Item(long id, String name, String description, Location location, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.user = user;
    }

    public Item(String name, String description, Location location, User user) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
