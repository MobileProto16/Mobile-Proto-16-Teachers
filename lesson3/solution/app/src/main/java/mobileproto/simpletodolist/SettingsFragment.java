package mobileproto.simpletodolist;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by nmohamed on 8/15/2016.
 */
public class SettingsFragment extends Fragment {

    String TAG = "SettingsFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings_fragment_layout, container, false);

        Button changeColorButton1 = (Button) view.findViewById(R.id.changeColorButton1);
        changeColorButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to fragment
                Log.d(TAG, "Clicked change color button");
                View activityView = getActivity().findViewById(R.id.mainLayout);
                activityView.setBackgroundColor(Color.argb(255, 0, 255, 255));
            }
        });

        Button changeColorButton2 = (Button) view.findViewById(R.id.changeColorButton2);
        changeColorButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to fragment
                Log.d(TAG, "Clicked change color button");
                View activityView = getActivity().findViewById(R.id.mainLayout);
                activityView.setBackgroundColor(Color.argb(255, 255, 0, 255));
            }
        });

        Button changeColorButton3 = (Button) view.findViewById(R.id.changeColorButton3);
        changeColorButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to fragment
                Log.d(TAG, "Clicked change color button");
                View activityView = getActivity().findViewById(R.id.mainLayout);
                activityView.setBackgroundColor(Color.argb(0, 0, 255, 255));
            }
        });
        return view;
    }


}