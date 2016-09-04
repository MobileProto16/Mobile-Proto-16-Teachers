package mobileproto.simpletodolist;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    String settingsText = "Settings";
    String todoListText = "Back";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainFragment mainFrag = new MainFragment();
        // set initial fragment
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.fragment_holder, mainFrag).commit();
        }

        final Button switchFragmentButton = (Button) this.findViewById(R.id.switchFragmentButton);
        switchFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked switch fragment button");

                if (switchFragmentButton.getText() == settingsText){
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_holder, new SettingsFragment())
                            .addToBackStack(null).commit();
                    switchFragmentButton.setText(todoListText);
                } else {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_holder,mainFrag)
                            .addToBackStack(null).commit();
                    switchFragmentButton.setText(settingsText);
                }

            }
        });
    }
}
