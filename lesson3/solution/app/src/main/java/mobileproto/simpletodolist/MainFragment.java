package mobileproto.simpletodolist;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nmohamed on 8/15/2016.
 */
public class MainFragment extends Fragment {

    String TAG = "MainFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);
        TextView item1 = (TextView) view.findViewById(R.id.item1);
        TextView item2 = (TextView) view.findViewById(R.id.item2);
        TextView item3 = (TextView) view.findViewById(R.id.item3);
        TextView item4 = (TextView) view.findViewById(R.id.item4);
        TextView item5 = (TextView) view.findViewById(R.id.item5);
        item1.setOnClickListener(onItemClick);
        item2.setOnClickListener(onItemClick);
        item3.setOnClickListener(onItemClick);
        item4.setOnClickListener(onItemClick);
        item5.setOnClickListener(onItemClick);

        return view;
    }

    public View.OnClickListener onItemClick = new View.OnClickListener(){
        @Override
        public void onClick(final View v) {
            Log.d(TAG, "Clicked an item");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
            alertDialogBuilder.setTitle("Edit item: ");
            final EditText input = new EditText(v.getContext());
            alertDialogBuilder.setView(input);

            //alert buttons
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String text = input.getText().toString();
                    ((TextView) v).setText(text);
                }
            });
            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialogBuilder.show();
        }
    };
}