package cecelia.homeslice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
 * Fragment which shows a listview of all possible items on a menu
 */
public class CookMenuFragment extends Fragment {
    @BindView(R.id.menu_list) ListView menuList;
    private ArrayList<CookMenuItem> menus;
    private CookMenuListAdapter menusAdapter;

    public CookMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        menus = new ArrayList<>();
        menusAdapter = new CookMenuListAdapter(getActivity(), menus);
        ButterKnife.bind(this, view);
        menuList.setAdapter(menusAdapter);

        // TODO only for testing, remove
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menusAdapter.add(new CookMenuItem(0, "picname", "Mousse", "food1; food2; food3", "Throw that shit in an oven"));
            }
        });

        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                Fragment specificMenuFragment = new CookSpecificMenuFragment();
                specificMenuFragment.setArguments(savedInstanceState);
                fragmentTransaction.replace(R.id.menu_fragment, specificMenuFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
