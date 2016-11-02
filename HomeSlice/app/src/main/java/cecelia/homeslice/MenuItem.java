package cecelia.homeslice;

import android.view.Menu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cecelia on 10/6/16.
 */

public class MenuItem {

    String name;
//    ArrayList<String> ingredients;
// unused?
    public MenuItem() {}

//    public MenuItem(String name, ArrayList<String> ingredients) {
    public MenuItem(String name) {
            this.name = name;
//        this.ingredients = ingredients;
    }

    public String getName() {
        return this.name;
    }

//    public ArrayList<String> getIngredients() {
//        return this.ingredients;
//    }

    public static MenuItem getFromHashMap(HashMap<String, String> map) {
        String name = map.get("name");
        return new MenuItem(name);
    }
}
