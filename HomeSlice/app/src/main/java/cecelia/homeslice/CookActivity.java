package cecelia.homeslice;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Main activity which starts all fragments for Cooks
 * Includes two tabs to different fragments, orders and menu
 * Orders is controlled by CookOrderFragment
 * Menu is controlled by CookMenuFragment
 */
public class CookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        // logic for tab and fragment viewpager learned from here:
        // http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ORDERS"));
        tabLayout.addTab(tabLayout.newTab().setText("MENU"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),
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
    }

    public DatabaseReference getOrderRef() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("orders").child(firebaseAuth.getCurrentUser().getUid());
        return orderRef;
    }
}
