package cecelia.homeslice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CustomerMenuItemAdapter extends ArrayAdapter<MenuItem> {


    public CustomerMenuItemAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        MenuItem menuItem = this.getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.customer_menu_item, parent, false);
        }

        initializeText(menuItem, convertView);
        initializeButtons(menuItem, convertView);

        return convertView;
    }

    private void initializeText(MenuItem menuItem, View convertView) {
        TextView itemNameView = (TextView) convertView.findViewById(R.id.customer_menu_item_name);
        itemNameView.setText(menuItem.name);
    }

    private void initializeButtons(MenuItem menuItem, View convertView) {
        ImageButton addButton = (ImageButton) convertView.findViewById(R.id.add_to_order);
        ImageButton removeButton = (ImageButton) convertView.findViewById(R.id.remove_from_order);
        ImageButton editButton = (ImageButton) convertView.findViewById(R.id.edit_order_item);

        //from here: http://stackoverflow.com/questions/20541821/get-listview-item-position-on-button-click
        addButton.setTag(menuItem);
        removeButton.setTag(menuItem);
        editButton.setTag(menuItem);

        setButtonVisibility(convertView, menuItem);
        setButtonClickListeners(menuItem, convertView);
    }

    private void setButtonClickListeners(final MenuItem menuItem, final View itemView) {
        ImageButton addButton = (ImageButton) itemView.findViewById(R.id.add_to_order);
        ImageButton removeButton = (ImageButton) itemView.findViewById(R.id.remove_from_order);
        ImageButton editButton = (ImageButton) itemView.findViewById(R.id.edit_order_item);

        //from here: http://stackoverflow.com/questions/20541821/get-listview-item-position-on-button-click
        addButton.setTag(menuItem);
        removeButton.setTag(menuItem);
        editButton.setTag(menuItem);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                createAddOrderItemAlertDialog(menuItem, button, itemView);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                removeFromOrder(menuItem);
                setButtonVisibility(itemView, menuItem);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                createEditOrderItemAlertDialog(menuItem, itemView, getCurrentOrder().getItem(menuItem));
            }
        });
    }


    private void addToOrder(final MenuItem menuItem, View dialogView) {
        EditText amountInput = (EditText)dialogView.findViewById(R.id.amount);
        EditText additionalCommentsInput = (EditText)dialogView.findViewById(R.id.additional_comments);

        int amount = Integer.valueOf(amountInput.getText().toString());
        String additionalComments = additionalCommentsInput.getText().toString();

        OrderItem item = createOrderItemInDatabse(menuItem, amount, additionalComments);
        getCurrentOrder().add(item, getOrderRef());
    }

    private
    void removeFromOrder(MenuItem menuItem) {

        getCurrentOrder().remove(menuItem, getOrderRef());

    }

    public void createAddOrderItemAlertDialog(MenuItem menuItem, View button, View itemView) {
        createEditOrderItemAlertDialog(menuItem, itemView, null);
    }

    public void createEditOrderItemAlertDialog(MenuItem menuItem, View itemView, OrderItem orderItem) {
        AlertDialog.Builder builder = createBuilder();
        View dialogView = setCustomBuilderLayout(builder);
        TextView nameView = (TextView) dialogView.findViewById(R.id.add_item_title);
        nameView.setText(menuItem.getName());

        if (orderItem != null) {
            EditText amountInput = (EditText) dialogView.findViewById(R.id.amount);
            amountInput.setText(String.valueOf(orderItem.amount));
            EditText additionalCommentInput = (EditText) dialogView.findViewById(R.id.additional_comments);
            additionalCommentInput.setText(orderItem.additionalComments);
        }

        setDialogPositiveNegativeButtons(builder, itemView, dialogView, menuItem, orderItem);
        AlertDialog alert = builder.create();
        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        alert.show();
    }

    private void setDialogPositiveNegativeButtons(AlertDialog.Builder builder, final View itemView, final View dialogView, final MenuItem menuItem, final OrderItem orderItem) {
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (orderItem != null) {
                    getCurrentOrder().remove(orderItem, getOrderRef());
                }
                addToOrder(menuItem, dialogView);
                setButtonVisibility(itemView, menuItem);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
    }

    private AlertDialog.Builder createBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        return builder;
    }

    private View setCustomBuilderLayout(AlertDialog.Builder builder){
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View layout = inflater.inflate(R.layout.add_order_item_dialog, null);
        builder.setView(layout);
        return layout;
    }

    private void setButtonVisibility(View itemView, MenuItem menuItem) {
        ImageButton addButton = (ImageButton) itemView.findViewById(R.id.add_to_order);
        ImageButton removeButton = (ImageButton) itemView.findViewById(R.id.remove_from_order);
        ImageButton editButton = (ImageButton) itemView.findViewById(R.id.edit_order_item);

        if (checkMenuIteminOrder(menuItem)) {
            addButton.setVisibility(addButton.GONE);
            editButton.setVisibility(editButton.VISIBLE);
            removeButton.setVisibility(editButton.VISIBLE);
        } else {
            removeButton.setVisibility(removeButton.GONE);
            editButton.setVisibility(editButton.GONE);
            addButton.setVisibility(addButton.VISIBLE);
        }
    }

    private Order getCurrentOrder() {
        CustomerActivity ca = (CustomerActivity) this.getContext();
        return ca.getCustomer().getOrder();
    }

    private boolean checkMenuIteminOrder(MenuItem menuItem) {
        return getCurrentOrder().isInOrder(menuItem);
    }

    private DatabaseReference getOrderRef() {
        CustomerActivity activity = (CustomerActivity) getContext();
        return activity.getOrderRef();
    }

    private OrderItem createOrderItemInDatabse(MenuItem menuItem, int amount, String additionalComments) {
        DatabaseReference newItemRef = FirebaseDatabase.getInstance().getReference().child("orderitems").push();
        OrderItem item = new OrderItem(menuItem, amount, additionalComments, newItemRef.getKey());
        newItemRef.setValue(item);
        return item;
    }

}
