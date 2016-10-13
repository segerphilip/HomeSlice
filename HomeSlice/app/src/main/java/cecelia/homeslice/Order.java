package cecelia.homeslice;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {

    private ArrayList<OrderItem> items;
    int status;

    public Order() {
        this.status = 0;
    }

    public Order(ArrayList<OrderItem> items) {
        this.items = items;
        this.status = 0;
    }

    public Order(ArrayList<OrderItem> items, int status) {
        this.items = items;
        this.status = status;
    }

    public void add(OrderItem item, DatabaseReference orderRef) {
        this.items.add(item);
        addToOrderDatabaseRef(item, orderRef);
    }

    public void add(ArrayList<OrderItem> items, DatabaseReference orderRef) {

        this.items.addAll(items);
        for (OrderItem item: this.items) {
            addToOrderDatabaseRef(item, orderRef);
        }
    }

    public void remove(int index, DatabaseReference orderRef) {
        OrderItem item = this.items.get(index);
        this.items.remove(index);
        removeFromDatabase(item, orderRef);
    }


    private void removeFromDatabase(OrderItem item, DatabaseReference orderRef) {
        orderRef.child("items").child(item.dbId).removeValue();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("orderitems").child(item.dbId);
        dbRef.removeValue();
    }

    private void addToOrderDatabaseRef(OrderItem item, DatabaseReference orderRef) {
        orderRef.child("items").child(item.dbId).setValue(item);
    }

    public void remove(OrderItem item, DatabaseReference orderRef) {

        this.items.remove(item);
        removeFromDatabase(item, orderRef);
    }

    public void remove(MenuItem item, DatabaseReference orderRef) {
        boolean removed = false;
        int index = 0;

        while (!removed) {
            OrderItem orderItem = this.items.get(index);
            if (orderItem.menuItem.getName() == item.getName()) {
                this.remove(orderItem, orderRef);
                removed = true;
//                Log.d("MenuActivity", "an menuItem has been removed");
            }
            index++;
        }
        if (!removed) {
            throw new IllegalArgumentException("no such menu menuItem to remove from order");
        }

    }

    public void remove(MenuItem item) {
        boolean removed = false;
        int index = 0;

        while (!removed) {
            OrderItem orderItem = this.items.get(index);
            if (orderItem.menuItem.getName() == item.getName()) {
                this.items.remove(orderItem);
                removed = true;
            }
            index++;
        }
        if (!removed) {
            throw new IllegalArgumentException("no such menuitem to remove from order");
        }
    }







    public ArrayList<OrderItem> getItems() {
        return this.items;
    }

    public int getStatus() { return this.status; }

    public void setStatus(int status) { this.status = status; }

    public boolean isInOrder(MenuItem item) {
        for (OrderItem o: this.items) {
            //will this not return true if they are not the same instance??
            if (o.menuItem.getName() == item.getName()) {
                return true;
            }
        }
        return false;
    }

    public OrderItem getItem(MenuItem item) {
        for (OrderItem o: this.items) {
            if (o.menuItem == item) {
                return o;
            }
        }
        throw new Error("that menu menuItem does not exist in the order");
    }

    public static Order getFromDataSnapshot(DataSnapshot dataSnapshot) {
        HashMap<String, Object> dataSnapshotValue = (HashMap<String, Object>) dataSnapshot.getValue();
        ArrayList<OrderItem> items = new ArrayList<OrderItem>();
        if (dataSnapshotValue != null) {
            HashMap<String, Object> itemsMap = (HashMap<String, Object>)dataSnapshotValue.get("items");
            if (dataSnapshotValue.get("items") != null) {
                setOrderItemsfromDataSnapshot(itemsMap, items);
            }
            //pip getting null status
            int status = Integer.valueOf(dataSnapshotValue.get("status").toString());
            Order order = new Order(items, status);
            return order;
        }

        return null;
    }

    public static void setOrderItemsfromDataSnapshot(HashMap<String, Object> itemsMap, ArrayList<OrderItem> items) {
        ArrayList<String> keys = new ArrayList<String>();
        for (String key: itemsMap.keySet()){
            keys.add(key);
        }
        for (String key: keys) {
            itemsMap.get(key);
            items.add(OrderItem.createFromSerial((HashMap<String, Object>)itemsMap.get(key)));
        }
    }
}


