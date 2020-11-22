package today.whereismystuff.web.models;

public class ItemCreateViewModel {
    private String name;
    private String description;
    private Long locationId;
    private Long userId;

    public ItemCreateViewModel() {
    }

    public ItemCreateViewModel(String name, String description, Long locationId, Long userId) {
        this.name = name;
        this.description = description;
        this.locationId = locationId;
        this.userId = userId;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user) {
        this.userId = user;
    }
}
