package com.dmitribrereton.bhealth;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(FoodItem.class);
        Parse.initialize(this, "nQSrnTCGHDRXttSDhZDDNGP9ED5C4Ch2gdy5J27u", "UDWPyN3hBevxvOxOOPkvXrybyVTbupCEXU7ydZxI");
    }
}
