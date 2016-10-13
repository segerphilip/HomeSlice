package cecelia.homeslice;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {

    Order currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.customer_toolbar);
        setSupportActionBar(toolbar);

        // logic for tab and fragment viewpager learned from here:
        // http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
        TabLayout tabLayout = (TabLayout) findViewById(R.id.customer_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("MENU"));
        tabLayout.addTab(tabLayout.newTab().setText("YOUR ORDER"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.customer_pager);
        final CustomerTabAdapter adapter = new CustomerTabAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

//        addMainPageFragment();

        //firebase resource: https://www.firebase.com/docs/android/quickstart.html
//        Firebase.setAndroidContext(this);

        this.currentOrder = new Order(new ArrayList<OrderItem>()); //NOTE: this should get the current order from either shared pref or the DB
    }
//
//    public void addMainPageFragment() {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        //Add the MainActivityFragment as the starting Fragment when the app is opened
//        fragmentTransaction.replace(R.id.fragment_holder, new CustomerMenuFragment(), "CURRENT_FRAGMENT");
//        fragmentTransaction.commit();
//    }
}
