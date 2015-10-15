package com.dmitribrereton.bhealth;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Gives the list of food at a particular dining hall
 */
public class FoodListFragment extends ListFragment {
    private static final String TAG = "FoodListFragment";
    private String mQuery = null;

    public FoodListFragment() {
        super();
        setArguments(new Bundle());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAdapter();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), FoodItemActivity.class);
        FoodItem item = (FoodItem) getListAdapter().getItem(position);
        String objectId = item.getObjectId();
        intent.putExtra("id", objectId);
        startActivity(intent);
        // TODO go to specific food item
    }



    private class FoodItemAdapter extends ArrayAdapter<FoodItem>{
        public FoodItemAdapter(List<FoodItem> foodItems){
            super(getActivity(), 0, foodItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(android.R.layout.simple_list_item_1, null);
            }

            FoodItem item = getItem(position);

            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(item.getTitle());
            return convertView;
        }
    }

    public void setupAdapter() {
        int location = getActivity().getIntent().getIntExtra("location", 1); // defaults to best dining hall
        Bundle bundle = getArguments();
        mQuery = bundle.getString("query", null);
        ParseQuery<FoodItem> query = ParseQuery.getQuery(FoodItem.class);
        query.whereEqualTo("location", location);
        if (mQuery!=null) {
            Log.d(TAG, "Query: " + mQuery);
            query.whereStartsWith("title", mQuery);
                // TODO query parse with search
        }
        query.findInBackground(new FindCallback<FoodItem>() {
            @Override
            public void done(List<FoodItem> list, ParseException e) {
                if (e == null) {
                    FoodItemAdapter adapter = new FoodItemAdapter(list);
                    setListAdapter(adapter);
                    Log.d(TAG, "Query is done");
                } else {
                    Log.d("FoodItem", "Error: " + e.getMessage());
                }
            }
        });
    }
}
