package DataModels;

public class Category {
    private int id;
    private String title;
    private String icon;

    public Category(int id, String title, String icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
