package cecelia.homeslice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

/**
 *
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    // http://stackoverflow.com/questions/7723964/replace-fragment-inside-a-viewpager
    private final FragmentManager mFragmentManager;
    private Fragment mFragment;

    public TabAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        mFragmentManager = fm;
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                final CookOrderFragment newFragment = new CookOrderFragment();
//                FragmentTransaction transaction = mFragmentManager.beginTransaction();
//                transaction.replace(R.id.fragment, newFragment, "orderFragment");
//                transaction.commit();
//                return newFragment;
                CookOrderFragment tab1 = new CookOrderFragment();
                return tab1;
            case 1:
                CookMenuFragment tab2 = new CookMenuFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
