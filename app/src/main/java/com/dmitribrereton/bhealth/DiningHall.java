package com.dmitribrereton.bhealth;

import java.net.URL;
/**
 * Created by mitri on 10/3/15.
 */
public class DiningHall {
    private String mName;
    private URL mURL;

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public URL getURL(){
        return mURL;
    }

    public void setURL(URL url){
        mURL = url;
    }
}
