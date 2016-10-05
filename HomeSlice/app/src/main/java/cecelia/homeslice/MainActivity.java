package cecelia.homeslice;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMainPageFragment();
    }

    public void addMainPageFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Add the MainActivityFragment as the starting Fragment when the app is opened
        fragmentTransaction.replace(R.id.fragment_holder, new LoginFragment(), "CURRENT_FRAGMENT");
        fragmentTransaction.commit();
    }
}
