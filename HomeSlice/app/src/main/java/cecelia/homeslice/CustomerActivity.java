package cecelia.homeslice;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;

public class CustomerActivity extends AppCompatActivity {

    final String TAG = CustomerActivity.class.getSimpleName();

    Customer customer;
    Boolean tabsDone;

    @BindView(R.id.customer_logout_button)
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        this.tabsDone = false;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        this.customer = new Customer(firebaseUser.getEmail(), firebaseUser.getUid(), new Order());
        final Customer customer = this.customer;
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("orders").child(customer.getUserId());

        orderRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Order order = Order.getFromDataSnapshot(dataSnapshot);
                if (order != null) {
                    customer.setOrder(order);
                } else {
                    createNewCustomerOrder();
                }
                setUpTabs();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                createNewCustomerOrder();
                setUpTabs();
                Log.d(TAG, databaseError.toString());
            }
        });

    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        returnToLogin();
    }

    public void returnToLogin() {
        Toast.makeText(this, "RETURN TO LOGIN", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public Customer getCustomer() {
        return this.customer;
    }

    private void createNewCustomerOrder() {
        //fuck this code
        FirebaseDatabase.getInstance().getReference().child("orders");
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("orders").child(customer.getUserId());;
        Order newOrder = new Order(new ArrayList<OrderItem>());
        orderRef.setValue(newOrder);
        this.customer.setOrder(newOrder);
    }

    private void setUpTabs() {
        if (!this.tabsDone) {
//          // logic for tab and fragment viewpager learned from here:
            // http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
            TabLayout tabLayout = (TabLayout) findViewById(R.id.customer_tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("MENU"));
            tabLayout.addTab(tabLayout.newTab().setText("YOUR ORDER"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            final ViewPager viewPager = (ViewPager) findViewById(R.id.customer_pager);
            final CustomerTabAdapter adapter = new CustomerTabAdapter(getSupportFragmentManager(),
                    tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            this.tabsDone = true;
        }

    }

    public DatabaseReference getOrderRef() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("orders").child(firebaseAuth.getCurrentUser().getUid());
        return orderRef;
    }


}
