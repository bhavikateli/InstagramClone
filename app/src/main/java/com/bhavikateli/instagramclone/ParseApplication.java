package com.bhavikateli.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Comment.class);


        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("bhavi-parstagram") // should correspond to APP_ID env variable
                .clientKey("yolo")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://bhavi-parstagram.herokuapp.com/parse").build());
    }
}
