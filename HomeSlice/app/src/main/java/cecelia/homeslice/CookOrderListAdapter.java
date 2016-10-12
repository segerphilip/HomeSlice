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
 *
 */
public class CookOrderListAdapter extends ArrayAdapter<CookOrderItem> {
    @BindView(R.id.cook_item_text) TextView itemText;
    @BindView(R.id.cook_item_subtext) TextView itemSubText;

    public CookOrderListAdapter(Context context, ArrayList<CookOrderItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final CookOrderItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cook_list_item, parent, false);
        }
        ButterKnife.bind(this, convertView);
        itemText.setText(item.getItem());
        itemSubText.setText(item.getSubText());

        return convertView;
    }
}
