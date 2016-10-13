package cecelia.homeslice;

/**
 * Created by Cecelia on 10/9/16.
 */

public class OrderItem {

    MenuItem item;
    String additionalComments;
    int amount;

    public OrderItem() {}

    public OrderItem(MenuItem item, int amount, String additionalComments) {
        this.item = item;
        this.amount = amount;
        this.additionalComments = additionalComments;
    }

    public MenuItem getItem() {
        return this.item;
    }

    public String getAdditionalComments() {
        return this.additionalComments;
    }

    public int getAmount() {
        return this.amount;
    }
}
