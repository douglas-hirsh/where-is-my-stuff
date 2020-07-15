package today.whereismystuff.web.models;

public class LocationCreateViewModel {
    private String name;
    private Long parentId;
    private Long userId;

    public LocationCreateViewModel() {
    }

    public LocationCreateViewModel(String name, Long parentId, Long userId) {
        this.name = name;
        this.parentId = parentId;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
