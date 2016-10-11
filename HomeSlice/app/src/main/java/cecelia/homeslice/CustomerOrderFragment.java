package cecelia.homeslice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cecelia on 10/9/16.
 */
public class CustomerOrderFragment extends Fragment {

    @BindView(R.id.order_list)
    ListView orderList;

    DatabaseReference firebase;
    OrderListAdapter orderAdapter;

    public CustomerOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_order_fragment, container, false);
        ButterKnife.bind(this, view);

        this.firebase = FirebaseDatabase.getInstance().getReference();

        CustomerActivity activity = (CustomerActivity)this.getActivity();
        ArrayList<OrderItem> orderItems = activity.currentOrder.getItems();
        this.orderAdapter = new OrderListAdapter(this.getActivity(), orderItems);
        orderList.setAdapter(this.orderAdapter);

        return view;
    }

}
