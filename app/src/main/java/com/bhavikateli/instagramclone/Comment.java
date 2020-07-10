package com.bhavikateli.instagramclone;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze={Comment.class})
@ParseClassName("Comment")

public class Comment extends ParseObject{

    public Comment(){ }

    public static final String KEY_USER = "user";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_POST_POINTER = "postPointer";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public String getComment(){
        return getString(KEY_CONTENT);
    }

    public void setComment(String content){
        put(KEY_CONTENT, content);
    }

    public void  setPost(Post post){
        put(KEY_POST_POINTER, post);
    }

    public ParseObject getPost(){
        return getParseObject(KEY_POST_POINTER);
    }



}
