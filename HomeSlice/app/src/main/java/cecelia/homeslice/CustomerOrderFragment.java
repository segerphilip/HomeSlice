package cecelia.homeslice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cecelia on 10/9/16.
 */
public class CustomerOrderFragment extends Fragment {

    final String TAG = CustomerOrderFragment.class.getSimpleName();

    @BindView(R.id.order_list)
    ListView orderList;

    @BindView(R.id.customer_logout_button)
    Button logoutButton;
    OrderListAdapter adapter;

    public CustomerOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_order_fragment, container, false);
        ButterKnife.bind(this, view);

        getOrderFromDatabase();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerActivity activity = (CustomerActivity) getActivity();
                activity.logout();
            }
        });

        return view;
    }

    public void getOrderFromDatabase() {

        final CustomerActivity activity = (CustomerActivity)this.getActivity();
        this.adapter = new OrderListAdapter(activity, new ArrayList<OrderItem>());
        orderList.setAdapter(adapter);

        final DatabaseReference orderRef = getOrderRef();

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Object> orderSnapshot = (HashMap<String, Object>)dataSnapshot.getValue();
                HashMap<String, Object> allItems = (HashMap<String, Object> ) orderSnapshot.get("items");
                adapter.clear();
                if (allItems != null) {
                    for (String key : allItems.keySet()) {
                        HashMap<String, Object> itemValue = (HashMap<String, Object>) allItems.get(key);
                        OrderItem item = OrderItem.createFromSerial((HashMap<String, Object>) itemValue);
                        adapter.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });
    }

    private DatabaseReference getOrderRef() {
        CustomerActivity activity = (CustomerActivity) getActivity();
        return activity.getOrderRef();
    }
}
