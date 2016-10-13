package cecelia.homeslice;

/**
 * Order specifics, which contains the item name, its state, and other information as subtext
 */
public class CookOrderItem {
    long id;
    int done;
    String item;
    String subText;

    public CookOrderItem(long id, int done, String item, String subText) {
        this.id = id;
        this.done = done;
        this.item = item;
        this.subText = subText;
    }

    public long getId() {
        return id;
    }

    public int getDone() {
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

    public void setDone(int newDone) {
        done = newDone;
    }

    public void setItem(String newItem) {
        item = newItem;
    }

    public void setSubText(String newSubText) {
        subText = newSubText;
    }
}
