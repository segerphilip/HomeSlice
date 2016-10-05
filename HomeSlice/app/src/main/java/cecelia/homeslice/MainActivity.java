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

        //this line of code is needed for every activity that uses firebase
        //firebase resource: https://www.firebase.com/docs/android/quickstart.html
        Firebase.setAndroidContext(this);
    }

    public void addMainPageFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Add the MainActivityFragment as the starting Fragment when the app is opened
        fragmentTransaction.replace(R.id.fragment_holder, new LoginFragment(), "CURRENT_FRAGMENT");
        fragmentTransaction.commit();
    }
}
