package cecelia.homeslice;

/**
 *
 */
public class CookMenuItem {
    private long id;
    private String item;

    public CookMenuItem(long id, String item) {
        this.id = id;
        this.item = item;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
