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
 * Adapter used to show menu items in a list with just the food name
 */
public class CookMenuListAdapter extends ArrayAdapter<CookMenuItem> {
    @BindView(R.id.cook_menu_text) TextView itemText;

    public CookMenuListAdapter(Context context, ArrayList<CookMenuItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final CookMenuItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cook_menu_item, parent, false);
        }
        ButterKnife.bind(this, convertView);
        itemText.setText(item.getItem());

        return convertView;
    }
}
