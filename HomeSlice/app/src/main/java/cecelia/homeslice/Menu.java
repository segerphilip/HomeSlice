package cecelia.homeslice;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Cecelia on 10/11/16.
 */

public class Menu {

    private ArrayList<MenuItem> items
            ;
    DatabaseReference menuRef;

    public Menu() {}

    public Menu(ArrayList<MenuItem> items) {
        this.items = items;
        menuRef = FirebaseDatabase.getInstance().getReference().child("menu");
    }

    public void add(MenuItem item) {
        this.items.add(item);
    }

    public void add(ArrayList<MenuItem> items) {
        this.items.addAll(items);
    }

    public void remove(int index) {
        MenuItem item = this.items.get(index);
        this.items.remove(index);
    }

    public void removeAll() {
        this.items.clear();
    }

    public void remove(MenuItem item) {
        this.items.remove(item);
    }

    public ArrayList<MenuItem> getItems() {
        return this.items;
    }



}
