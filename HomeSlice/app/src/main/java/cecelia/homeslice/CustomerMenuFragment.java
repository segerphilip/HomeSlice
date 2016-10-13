package cecelia.homeslice;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
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

    DatabaseReference firebase;
    CustomerMenuItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_menu, container, false);
        ButterKnife.bind(this, view);

        this.firebase = FirebaseDatabase.getInstance().getReference();

        MenuItem escargot = new MenuItem("escargot");
        MenuItem paella = new MenuItem("paella");
//        Firebase menuRef = firebase.child("menu");
//        menuRef.push().setValue(escargot);
//        menuRef.push().setValue(paella);

        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(escargot);
        menuItems.add(paella);

        this.adapter = new CustomerMenuItemAdapter(this.getActivity(), menuItems);
        menuListView.setAdapter(this.adapter);



        addMenuItemsFromDatabaseToAdapter();

        return view;
    }

    private void addMenuItemsFromDatabaseToAdapter() {
        DatabaseReference menuRef = this.firebase.child("menu");
        final CustomerMenuItemAdapter adapter = this.adapter;
        menuRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    MenuItem item = itemSnapshot.getValue(MenuItem.class);
                    adapter.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }
 }
