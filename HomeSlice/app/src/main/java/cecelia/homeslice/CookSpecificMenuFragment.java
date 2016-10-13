package cecelia.homeslice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment showing all the details about a specific menu item
 */
public class CookSpecificMenuFragment extends Fragment {
    @BindView(R.id.menu_title) TextView menuTitle;
    @BindView(R.id.menu_ingredients) TextView menuIngredients;
    @BindView(R.id.menu_instructions) TextView menuList;

    public CookSpecificMenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cook_specific_menu, container, false);

        ButterKnife.bind(this, view);
        ImageView menuImage = (ImageView) view.findViewById(R.id.imageView);

        menuImage.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
        menuTitle.setText("Mousse");
        menuIngredients.setText("1 Canadian moose\n50 mL pure maple syrup\nWow");
        menuList.setText("Insert food\nDone\nWell done");

        return view;
    }
}
