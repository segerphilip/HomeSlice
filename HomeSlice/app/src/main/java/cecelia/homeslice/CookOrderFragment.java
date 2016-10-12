package cecelia.homeslice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
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
                item.setDone(true);
                orders.remove(item);
                ordersAdapter.notifyDataSetChanged();
                return false;
            }
        });

        // TODO remove after testing
        orders.add(new CookOrderItem(0, false, "Testing item here", "with some testing subtext as well"));
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
