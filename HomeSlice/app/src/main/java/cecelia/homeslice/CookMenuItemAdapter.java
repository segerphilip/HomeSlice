package cecelia.homeslice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter used to display ingredients and cooking instructions for a food
 */
public class CookMenuItemAdapter extends ArrayAdapter<CookMenuItem> {
    @BindView(R.id.cook_item_text) TextView itemText;
    @BindView(R.id.cook_item_subtext) TextView itemSubText;

    public CookMenuItemAdapter(Context context, ArrayList<CookMenuItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final CookMenuItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cook_specific_menu, parent, false);
        }
        ButterKnife.bind(this, convertView);
        itemText.setText(item.getItem() + "BASLDFHAS");

        return convertView;
    }
}
