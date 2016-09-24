package com.david.lab1;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Customer> customers;
    private ArrayList<com.david.lab1.MenuItem> menuItems;
    private ArrayList<Order> orders;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private S3Util s3util;

    private s3Callback orderCallback = new s3Callback() {
        @Override
        public void receiveObjects(ArrayList o) {
            orders = o;
            Log.d(tag, "Orders received: " + o);
        }
    };

    private s3Callback customerCallback = new s3Callback() {
        @Override
        public void receiveObjects(ArrayList o) {
            customers = o;
            Log.d(tag, "Customers received: " + o);
        }
    };

    private s3Callback menuItemCallback = new s3Callback() {
        @Override
        public void receiveObjects(ArrayList o) {
            menuItems = o;
            Log.d(tag, "Menu Items received: " + o);
        }
    };

    private static final String tag = MainActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s3util = new S3Util(this, S3Credentials.COGNITO_POOL_ID);
        loadCustomersFromS3();
//        loadMenuItemsFromS3();
//        loadOrdersFromS3();
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            Fragment firstFragment = new LoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        orders = new ArrayList<>();
        menuItems = new ArrayList<>();
    }

    public void switchFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goBack() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void loadCustomersFromS3() {
        customers = new ArrayList<>();
        s3util.loadObjectFromS3(S3Util.CUSTOMER_KEY, this.customerCallback);
    }

    public void loadOrdersFromS3() {
        orders = new ArrayList<>();
        s3util.loadObjectFromS3(S3Util.ORDER_KEY, this.orderCallback);
    }

    public void loadMenuItemsFromS3() {
        orders = new ArrayList<>();
        s3util.loadObjectFromS3(S3Util.MENU_KEY, this.menuItemCallback);
    }

    public void saveCustomersToS3() {
        Log.d(tag, customers.toString());
        s3util.saveObjectToS3(customers, S3Util.CUSTOMER_KEY);
    }

<<<<<<< 4b06dd687633c42b8280c74197ee44b2ddcfb546
    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }


    public void addMenuItem(com.david.lab1.MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public ArrayList<com.david.lab1.MenuItem> getMenuItems() {
        return menuItems;
=======
    public void saveOrdersToS3() {
        Log.d(tag, orders.toString());
        s3util.saveObjectToS3(orders, S3Util.ORDER_KEY);
    }

    public void saveMenuItemsToS3() {
        Log.d(tag, menuItems.toString());
        s3util.saveObjectToS3(menuItems, S3Util.MENU_KEY);
>>>>>>> saving and loading utils
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
