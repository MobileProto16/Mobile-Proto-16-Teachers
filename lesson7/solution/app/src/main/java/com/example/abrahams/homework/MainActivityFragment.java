package com.example.abrahams.homework;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.editText) EditText editText;
    @BindView(R.id.listView) ListView listView;

    ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.list_view);

        listView.setAdapter(adapter);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchGoogleFinance(editText.getText().toString());
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public void searchGoogleFinance(String search) {

        adapter.clear();

        String[] split = search.split(" ");
        String stocks = "";

        for (String s : split) {
            stocks += s + ",";
        }

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("finance.google.com")
                .appendPath("finance")
                .appendPath("info")
                .appendQueryParameter("client", "iq")
                .appendQueryParameter("q", stocks);
        String myUrl = builder.build().toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String sub = response.substring(3);
                        try {
                            JSONArray json = (JSONArray) new JSONParser().parse(sub);
                            for (int i = 0; i < json.size(); i++) {
                                JSONObject o = (JSONObject) json.get(i);
                                adapter.add(o.get("t") + ": $" + o.get("l"));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Log.d("URL", myUrl);

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this.getActivity()).addToRequestQueue(stringRequest);

    }

}
