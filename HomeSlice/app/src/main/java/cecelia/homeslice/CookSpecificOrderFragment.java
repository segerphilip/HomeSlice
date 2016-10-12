package cecelia.homeslice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class CookSpecificOrderFragment extends Fragment {
    // using same adapters as menu because convenince (and want similar view)
    @BindView(R.id.specific_list) ListView orderList;
    private ArrayList<CookMenuItem> orders;
    private CookMenuListAdapter ordersAdapter;

    public CookSpecificOrderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cook_specific_order, container, false);
        orders = new ArrayList<>();
        ordersAdapter = new CookMenuListAdapter(getActivity(), orders);
        ButterKnife.bind(this, view);
        orderList.setAdapter(ordersAdapter);

        orders.add(new CookMenuItem(0, "TESTING TESTING"));
        ordersAdapter.notifyDataSetChanged();

        return view;
    }
}
