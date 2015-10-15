package com.dmitribrereton.bhealth;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

/**
 * Gives information about a specific food item
 * allows the user to add this to their current meal
 */
public class FoodItemFragment extends Fragment {
    private String mId;
    private FoodItem mFoodItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mId = getActivity().getIntent().getStringExtra("id");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_food_item, container, false);
        ParseQuery<FoodItem> query = ParseQuery.getQuery(FoodItem.class);
        query.getInBackground(mId, new GetCallback<FoodItem>() {
            @Override
            public void done(final FoodItem foodItem, ParseException e) {
                TextView foodItemTitleTextView = (TextView) view.findViewById(R.id.foodTitleTextView);
                foodItemTitleTextView.setText(foodItem.getTitle());

                TextView foodCaloriesTextView = (TextView) view.findViewById(R.id.foodCaloriesTextView);
                foodCaloriesTextView.setText(Integer.toString(foodItem.getCalories()));

                TextView foodProteinTextView = (TextView) view.findViewById(R.id.foodProteinTextView);
                foodProteinTextView.setText(Double.toString(foodItem.getProtein()));

                TextView foodCarbsTextView = (TextView) view.findViewById(R.id.foodCarbsTextView);
                foodCarbsTextView.setText(Double.toString(foodItem.getCarbs()));

                TextView foodFatTextView = (TextView) view.findViewById(R.id.foodFatTextView);
                foodFatTextView.setText(Double.toString(foodItem.getFat()));

                Button foodItemCancelButton = (Button) view.findViewById(R.id.foodItemCancelButton);
                foodItemCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), FoodListActivity.class);
                        startActivity(intent);
                    }
                });
                Button foodItemDoneButton = (Button) view.findViewById(R.id.foodItemDoneButton);
                foodItemDoneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), MealInfoActivity.class);
                        DiningHallsActivity.USER_CALORIES += foodItem.getCalories();
                        DiningHallsActivity.USER_PROTEIN += foodItem.getProtein();
                        DiningHallsActivity.USER_CARBS += foodItem.getCarbs();
                        DiningHallsActivity.USER_FAT += foodItem.getFat();
                        // getCurrentUser
                        // Add this to their current meal
                        startActivity(intent);

                    }
                });
            }
        });


        return view;
    }
}
