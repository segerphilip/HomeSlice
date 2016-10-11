package cecelia.homeslice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private ArrayList<CookItem> orders;
    private CookListAdapter ordersAdapter;

    public CookOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        orders = new ArrayList<>();
        ordersAdapter = new CookListAdapter(getActivity(), orders);
        ButterKnife.bind(this, view);
        orderList.setAdapter(ordersAdapter);

        // remove item on long press
        orderList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final CookItem item = ordersAdapter.getItem(position);
                item.setDone(true);
                orders.remove(item);
                ordersAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return view;
    }
}
