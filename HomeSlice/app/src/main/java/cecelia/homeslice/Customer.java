package cecelia.homeslice;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

/**
 * Created by Cecelia on 10/9/16.
 */
public class Customer implements Serializable {

    private String email;
    private String userId;
    private Order order;

    public Customer() {}

    public Customer(String email, String id, Order order) {
        this.email = email;
        this.userId = id;
        this.order = order;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserId() {
        return this.userId;
    }

    public Order getOrder() { return this.order; }

    public void setOrder(Order order) {
        this.order = order;
    }

}
