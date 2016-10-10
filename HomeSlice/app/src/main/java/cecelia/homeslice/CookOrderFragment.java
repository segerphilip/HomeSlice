package cecelia.homeslice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
        ordersAdapter = new CookListAdapter(getActivity(), orders);
        ButterKnife.bind(this, view);
        orderList.setAdapter(ordersAdapter);

        // fab used to add completely new notes
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordersAdapter.add(new CookItem(0, false, "TESTING", "subtesting HERE wow"));
            }
        });

//        ordersAdapter.notifyDataSetChanged();
        return view;
    }
}
