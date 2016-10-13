package cecelia.homeslice;

import java.util.HashMap;

/**
 * Created by Cecelia on 10/9/16.
 */

public class OrderItem {

    MenuItem menuItem;
    String additionalComments;
    int amount;
    String dbId;

    public OrderItem() {}

    public OrderItem(MenuItem item, int amount, String additionalComments, String dbId) {
        this.menuItem = item;
        this.amount = amount;
        this.additionalComments = additionalComments;
        this.dbId = dbId;
    }

    public MenuItem getMenuItem() {
        return this.menuItem;
    }

    public String getAdditionalComments() {
        return this.additionalComments;
    }

    public int getAmount() {
        return this.amount;
    }

    private String getDbId() {
        return this.dbId;
    }

    public static OrderItem createFromSerial(HashMap<String, Object> serial) {
        Integer amount = Integer.valueOf(serial.get("amount").toString());
        String additionalComments = (String) serial.get("additionalComments");
        String dbId = (String) serial.get("dbId");
        MenuItem item = MenuItem.getFromHashMap(((HashMap<String, String>)serial.get("menuItem")));
        return new OrderItem(item, amount, additionalComments, dbId);
    }


}
