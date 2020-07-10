package com.bhavikateli.instagramclone.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavikateli.instagramclone.Post;
import com.bhavikateli.instagramclone.R;
import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class UserFragment extends Fragment {

    public static final String TAG = "UserFragment";

    public UsersPostAdapter adapter;
    private RecyclerView rvUserPosts;
    protected List<Post> allUserPosts;
    private ParseUser user;
    private TextView tvUserUsername;
    private ImageView ivUserProfileImage;

    public UserFragment(ParseUser user) {
        // Required empty public constructor
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUserUsername = view.findViewById(R.id.tvUserUsername);
        ivUserProfileImage = view.findViewById(R.id.ivUserProfileImage);
        rvUserPosts = view.findViewById(R.id.rvUserPosts);
        allUserPosts = new ArrayList<>();
        adapter = new UsersPostAdapter(getContext(), allUserPosts);

        tvUserUsername.setText(user.getUsername());

        ParseFile profileImage = user.getParseFile("profileImage");
        if(profileImage != null) {
            Glide.with(getContext()).load(profileImage.getUrl())
                    .transform(new RoundedCornersTransformation(80, 30))
                    .into(ivUserProfileImage);
        }


        rvUserPosts.setAdapter(adapter);

        rvUserPosts.setLayoutManager(new GridLayoutManager(getContext(), 3));
        queryPosts();
    }

    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        //Need to filter out only that specific user
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, user);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allUserPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}