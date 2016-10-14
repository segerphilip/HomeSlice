package cecelia.homeslice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Cecelia on 10/9/16.
 */
public class OrderListAdapter extends ArrayAdapter<OrderItem> {


    public OrderListAdapter(Context context, ArrayList<OrderItem> items) {
        super(context, 0, items);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final OrderItem orderItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.order_list_item, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.item_name);
        nameView.setText(orderItem.menuItem.name);
        TextView numberView = (TextView) convertView.findViewById(R.id.item_number);
        numberView.setText(String.valueOf(orderItem.amount));
        TextView addntlCommentsView = (TextView) convertView.findViewById(R.id.item_additional_comments);
        addntlCommentsView.setText(orderItem.additionalComments);

        return convertView;
    }
}
