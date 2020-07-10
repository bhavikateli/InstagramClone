package com.bhavikateli.instagramclone;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze={Post.class})
@ParseClassName("Post")
public class Post extends ParseObject {

    //empty constructor for parcel
    public Post(){ }

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_PROFILE_IMAGE = "profileImage";
    ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
    public static final String KEY_LIKE = "like";
    public static final String KEY_COMMENT_ARRAY = "comments";
    public static final String KEY_LOCATION = "location";



    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public ParseFile getProfileImage(){
        return getParseUser(KEY_USER).getParseFile(KEY_PROFILE_IMAGE);
    }

    public void setLike(String like){
        Log.i("Post class", "inside setlike");
        put(KEY_LIKE, like);
    }

    public String getLike(){
        Log.i("Post class", "inside getLike");
        return getString(KEY_LIKE);
    }

    public void setLocation(String location){
        put(KEY_LOCATION, location);

    }
    public String getLocation(){
        return getString(KEY_LOCATION);
    }





}
