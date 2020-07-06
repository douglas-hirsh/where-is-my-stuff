package today.whereismystuff.web.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class LocationViewModel {
    private long id;
    private int depth;
    private String path;
    private String name;
    private List<Long> pathIds;
    private User user;
    private List<Item> items;

    public LocationViewModel(long id, int depth, String path, String name, List<Long> pathIds, User user, List<Item> items) {
        this.id = id;
        this.depth = depth;
        this.path = path;
        this.name = name;
        this.pathIds = pathIds;
        this.user = user;
        this.items = items;
    }

    public LocationViewModel(Location location) {
        this.id = location.getId();
        this.depth = location.getDepth();
        this.path = location.getPath();
        this.name = location.getName();
        this.pathIds = location.getPathIds();
        this.user = location.getUser();
        this.items = location.getItems();
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

    public List<Long> getPathIds() {
        return pathIds;
    }

    public void setPathIds(List<Long> pathIds) {
        this.pathIds = pathIds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
