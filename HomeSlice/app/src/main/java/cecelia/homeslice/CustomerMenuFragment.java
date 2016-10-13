package cecelia.homeslice;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerMenuFragment extends Fragment {

    @BindView(R.id.customer_menu)
    ListView menuListView;

    @BindView(R.id.customer_logout_button)
    Button logoutButton;

    CustomerMenuItemAdapter adapter;
    Menu menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_menu_fragment, container, false);
        ButterKnife.bind(this, view);

        this.menu = new Menu(new ArrayList<MenuItem>());
        addMenuItemsFromDatabase();

        return view;
    }

    private void addMenuItemsFromDatabase() {
        DatabaseReference menuRef = FirebaseDatabase.getInstance().getReference().child("menu");
        final CustomerActivity activity = (CustomerActivity) getActivity();
        final Menu menu = this.menu;
        menuRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                menu.removeAll();
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    MenuItem item = itemSnapshot.getValue(MenuItem.class);
                    menu.add(item);
                }

                createAdapter();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerActivity activity = (CustomerActivity) getActivity();
                activity.logout();
            }
        });
    }

    private void createAdapter() {
        if (this.adapter == null) {
            CustomerMenuItemAdapter adapter = new CustomerMenuItemAdapter(getActivity(), menu.getItems());
            menuListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


 }
