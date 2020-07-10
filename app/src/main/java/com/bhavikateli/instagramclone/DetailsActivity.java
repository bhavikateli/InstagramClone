package com.bhavikateli.instagramclone;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailsActivity extends AppCompatActivity {

    public TextView tvUsernameDetails;
    public TextView tvDescriptionDetails;
    public ImageView ivPostImageDetails;
    public Context context;
    public TextView tvTimeStampDetails;
    public TextView tvLocationDetails;
    public ImageView ivProfilePictureDetails;
    public TextView tvLikeCountDetails;

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setPostDetails();
    }

    private void setPostDetails() {
        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        tvUsernameDetails = findViewById(R.id.tvUsernamePostDetails);

        tvDescriptionDetails = findViewById(R.id.tvDescriptionPostDetails);
        ivPostImageDetails = findViewById(R.id.ivImagePostDetails);
        tvTimeStampDetails = findViewById(R.id.tvTimeStampDetails);
        tvLocationDetails = findViewById(R.id.tvLocationDetails);
        ivProfilePictureDetails = findViewById(R.id.ivProfilePictureDetails);
        tvLikeCountDetails = findViewById(R.id.tvLikeCountDetails);

        tvDescriptionDetails.setText(post.getDescription());

        tvUsernameDetails.setText(post.getUser().getUsername());
        Log.i("DetailsActivity", "username: " + post.getUser().getUsername());
        tvUsernameDetails.setPaintFlags(tvUsernameDetails.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        tvLocationDetails.setText(post.getLocation());
        tvLocationDetails.setPaintFlags(tvLocationDetails.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        tvTimeStampDetails.setText(ParseRelativeDate.getRelativeTimeAgo(post.getCreatedAt().toString()));
        Glide.with(this).load(post.getImage().getUrl()).into(ivPostImageDetails);

        tvLikeCountDetails.setText(post.getLike());

        ParseFile profileImage = post.getProfileImage();
        if(profileImage != null) {
            Glide.with(this).load(post.getProfileImage().getUrl())
                    .transform(new RoundedCornersTransformation(40, 25))
                    .into(ivProfilePictureDetails);
        }

    }


}