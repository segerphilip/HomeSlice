package cecelia.homeslice;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Fragment showing all the details about a specific menu item
 */
public class CookSpecificMenuFragment extends Fragment {
    @BindView(R.id.imageView) ImageView menuImage;
    @BindView(R.id.menu_title) TextView menuTitle;
    @BindView(R.id.menu_ingredients) TextView menuIngredients;
//    @BindView(R.id.menu_instructions) ListView menuInstructions;

}
