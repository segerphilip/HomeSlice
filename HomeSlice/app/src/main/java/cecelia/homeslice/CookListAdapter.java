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
public class CookListAdapter extends ArrayAdapter<CookItem> {
    @BindView(R.id.cook_item_text) TextView itemText;
    @BindView(R.id.cook_item_subtext) TextView itemSubText;

    public CookListAdapter(Context context, ArrayList<CookItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final CookItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cook_list_item, parent, false);
        }
        ButterKnife.bind(this, convertView);
        itemText.setText(item.getItem());
        itemSubText.setText(item.getSubText());

        // TODO: set a long click remove feature

        return convertView;
    }
}

//    /**
//     * Creates an alert dialog to edit preexisting values in the listview
//     */
//    public void createAlertDialog(final Item item) {
//        final String prevText = item.getItem();
//        final EditText input = new EditText(getContext());
//        if (prevText != null) {
//            input.setText(prevText);
//            input.setSelection(prevText.length());
//        }
//        input.setSelectAllOnFocus(true);
//
//        AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
//                .setMessage(R.string.editableText)
//                .setView(input)
//                .setCancelable(true)
//                .setPositiveButton(R.string.popupConfirm, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        item.setItem(input.getText().toString());
//                        mHelper.addItem(item);
//                    }
//                });
//
//        AlertDialog visibleAlert = alert.create();
//        visibleAlert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        visibleAlert.show();
//    }
