package cecelia.homeslice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment showing the orders that are in progress from users
 */
public class CookOrderFragment extends Fragment {
    @BindView(R.id.order_list) ListView orderList;
    private ArrayList<CookOrderItem> orders;
    private CookOrderListAdapter ordersAdapter;

    public CookOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        orders = new ArrayList<>();
        ordersAdapter = new CookOrderListAdapter(getActivity(), orders);
        ButterKnife.bind(this, view);
        orderList.setAdapter(ordersAdapter);

        // remove item on long press
        orderList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final CookOrderItem item = ordersAdapter.getItem(position);
                item.setDone(2);
                orders.remove(item);
                ordersAdapter.notifyDataSetChanged();
                return false;
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("orders");

        // listener for getting all order items
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                OrderItem orderItem = dataSnapshot.getValue(OrderItem.class);
                orders.add(orderItem);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // TODO remove after testing
        orders.add(new CookOrderItem(0, 1, "Testing item here", "with some testing subtext as well"));
        orders.add(new CookOrderItem(0, 1, "Testing item here", "with some testing subtext as well"));
        orders.add(new CookOrderItem(0, 1, "Testing item here", "with some testing subtext as well"));
        ordersAdapter.notifyDataSetChanged();

        // open detailed view on item tap
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                Fragment specificOrderFragment = new CookSpecificOrderFragment();//the fragment you want to show
                specificOrderFragment.setArguments(savedInstanceState);
                fragmentTransaction.replace(R.id.order_fragment, specificOrderFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
