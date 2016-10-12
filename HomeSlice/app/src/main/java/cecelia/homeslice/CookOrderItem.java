package cecelia.homeslice;

/**
 *
 */
public class CookOrderItem {
    private long id;
    private boolean done;
    private String item;
    private String subText;

    public CookOrderItem(long id, boolean done, String item, String subText) {
        this.id = id;
        this.done = done;
        this.item = item;
        this.subText = subText;
    }

    public long getId() {
        return id;
    }

    public boolean isDone() {
        return done;
    }

    public String getItem() {
        return item;
    }

    public String getSubText() {
        return subText;
    }

    public void setId(long newId) {
        id = newId;
    }

    public void setDone(boolean newDone) {
        done = newDone;
    }

    public void setItem(String newItem) {
        item = newItem;
    }

    public void setSubText(String newSubText) {
        subText = newSubText;
    }
}
