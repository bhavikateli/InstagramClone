package com.bhavikateli.instagramclone;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public ProfileAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post =  posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private ImageView ivProfilePicture;
        private TextView tvTimeStamp;
        private TextView tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescriptionPost);
            tvUsername = itemView.findViewById(R.id.tvUsernamePost);
            ivImage = itemView.findViewById(R.id.ivImagePost);
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            tvLocation = itemView.findViewById(R.id.tvLocation);
        }

        public void bind(Post post) {
            //bind the post data to view elements
            tvDescription.setText(post.getDescription());

            tvUsername.setText(post.getUser().getUsername());
            tvUsername.setPaintFlags(tvUsername.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

            tvLocation.setText(post.getLocation());
            tvLocation.setPaintFlags(tvLocation.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);


            tvTimeStamp.setText(ParseRelativeDate.getRelativeTimeAgo(post.getCreatedAt().toString()));


            ParseFile image = post.getImage();
            if(image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }
            ParseFile profileImage = post.getProfileImage();
            if(profileImage != null) {
                Glide.with(context).load(post.getProfileImage().getUrl())
                        .transform(new RoundedCornersTransformation(80, 30))
                        .into(ivProfilePicture);
            }
        }
    }

}