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
public class CookMenuFragment extends Fragment {
    @BindView(R.id.menu_list) ListView menuList;
    private ArrayList<CookItem> menus;
    private CookListAdapter menusAdapter;

    public CookMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        menus = new ArrayList<>();
        menusAdapter = new CookListAdapter(getActivity(), menus);
        ButterKnife.bind(this, view);
        menuList.setAdapter(menusAdapter);

        // TODO only for testing, remove
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menusAdapter.add(new CookItem(0, false, "TESTING", "subtesting HERE wow"));
            }
        });

        return view;
    }
}
