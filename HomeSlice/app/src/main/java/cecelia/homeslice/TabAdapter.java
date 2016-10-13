package cecelia.homeslice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * A beautiful tab adapter view
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    // tutorial followed for designing tabs:
    // http://stackoverflow.com/questions/7723964/replace-fragment-inside-a-viewpager

    public TabAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
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
