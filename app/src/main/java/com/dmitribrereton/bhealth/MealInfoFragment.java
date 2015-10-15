package com.dmitribrereton.bhealth;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Gives nutrient info for current meal thus far
 * allows user to modify portions, add more of the same,
 * or add new items(from the same dining hall I would think)
 */
public class MealInfoFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_info, container, false);
        TextView mealCaloriesTextView = (TextView)view.findViewById(R.id.mealCaloriesTextView);
        mealCaloriesTextView.setText(Double.toString(DiningHallsActivity.USER_CALORIES));
        TextView mealProteinTextView = (TextView)view.findViewById(R.id.mealProteinTextView);
        mealProteinTextView.setText(Double.toString(DiningHallsActivity.USER_PROTEIN));
        TextView mealCarbsTextView = (TextView)view.findViewById(R.id.mealCarbsTextView);
        mealCarbsTextView.setText(Double.toString(DiningHallsActivity.USER_CARBS));
        TextView mealFatTextView = (TextView)view.findViewById(R.id.mealFatTextView);
        mealFatTextView.setText(Double.toString(DiningHallsActivity.USER_FAT));

        Button moreButton = (Button)view.findViewById(R.id.addMore);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FoodListActivity.class));
            }
        });
        Button button = (Button)view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
