package com.bhavikateli.instagramclone;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {

    public TextView tvUsernameDetails;
    public TextView tvDescriptionDetails;
    public ImageView ivPostImageDetails;
    public Context context;
    public TextView tvTimeStamp;

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
         setPostDetails();
    }

    private void setPostDetails() {
        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        tvUsernameDetails = findViewById(R.id.tvUsernameDetails);
        tvDescriptionDetails = findViewById(R.id.tvDescriptionDetails);
        ivPostImageDetails = findViewById(R.id.ivPostImageDetails);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);

        tvDescriptionDetails.setText(post.getDescription());
        tvUsernameDetails.setText(post.getUser().getUsername());
        tvTimeStamp.setText(ParseRelativeDate.getRelativeTimeAgo(post.getCreatedAt().toString()));
        Glide.with(this).load(post.getImage().getUrl()).into(ivPostImageDetails);
    }


}
