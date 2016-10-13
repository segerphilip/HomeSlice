package cecelia.homeslice;

import android.view.Menu;

import java.util.ArrayList;
import java.util.logging.ErrorManager;

/**
 * Created by Cecelia on 10/9/16.
 */

public class Order {

    private ArrayList<OrderItem> items;
    private Customer customer;

    public Order() {}

    public Order(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public void add(OrderItem item) {
        this.items.add(item);
    }

    public void add(ArrayList<OrderItem> items) {
        this.items.addAll(items);
    }

    public void remove(int index) {
        this.items.remove(index);
    }

    public void remove(OrderItem item) {
        this.items.remove(item);
    }

    public void remove(MenuItem item) {
        boolean removed = false;
        int index = 0;

        while (!removed) {
            OrderItem orderItem = this.items.get(index);
            if (orderItem.item == item) {
                this.remove(orderItem);
                removed = true;
            }
            index++;
        }

    }

    public ArrayList<OrderItem> getItems() {
        return this.items;
    }

    public Customer getCustomer() {
        return  this.customer;
    }

    public boolean isInOrder(MenuItem item) {
        for (OrderItem o: this.items) {
            //will this not return true if they are not the same instance??
            if (o.item == item) {
                return true;
            }
        }
        return false;
    }

    public OrderItem getItem(MenuItem item) {
        for (OrderItem o: this.items) {
            if (o.item == item) {
                return o;
            }
        }
        throw new Error("that menu item does not exist in the order");
    }
}


