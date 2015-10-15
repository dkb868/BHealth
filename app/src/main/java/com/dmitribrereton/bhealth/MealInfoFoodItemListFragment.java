package com.dmitribrereton.bhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * List of food items that the user has added to their meal
 * allows user to adjust portions, remove food, etc.
 */
public class MealInfoFoodItemListFragment  extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> list = new ArrayList<>();
        list.add("Test1");
        list.add("test2");
        MealInfoFoodItemAdapter adapter = new MealInfoFoodItemAdapter(list);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), FoodItemActivity.class);
        startActivity(intent);
    }

    private class MealInfoFoodItemAdapter extends ArrayAdapter<String> {
        public MealInfoFoodItemAdapter(List<String> foodItems){
            super(getActivity(), 0, foodItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_meal_info_food, null);
            }

            String item = getItem(position);

            TextView textView = (TextView) convertView.findViewById(R.id.foodTitleTextView);
            textView.setText(item);

            Button addButton = (Button)convertView.findViewById(R.id.foodAddButton);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            Button minusButton = (Button)convertView.findViewById(R.id.foodMinusButton);
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO
                }
            });
            return convertView;
        }
    }
}


