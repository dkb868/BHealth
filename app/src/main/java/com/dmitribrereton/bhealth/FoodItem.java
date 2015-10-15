package com.dmitribrereton.bhealth;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("FoodItem")
public class FoodItem extends ParseObject {
    public FoodItem() {
    }

    public String getTitle(){
        return getString("title");
    }

    public float getCalcium(){
        return (float)(getNumber("calcium"));
    }

    public void setCalcium(float value){
        put("calcium", value);
    }

    public int getCalories(){
        return getInt("calories");
    }

    public void setCalories(float value){
        put("calories", value);
    }

    public double getProtein(){
        return (double)getNumber("protein");
    }

    public double getCarbs(){
        return getDouble("total_carbohydrate");
    }

    public double getFat(){
        return getDouble("total_fat");
    }

}
