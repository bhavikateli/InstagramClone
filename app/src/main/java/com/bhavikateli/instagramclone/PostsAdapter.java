package com.bhavikateli.instagramclone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    public interface ProfileImageListener {
        void switchFragment(String fragmentName, Post post);
    }

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
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

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        ImageView ivProfilePicture;
        TextView tvTimeStamp;
        TextView tvLocation;
        TextView tvLikeCount;
        ImageView ivLike;
        ImageView ivComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescriptionPost);
            tvUsername = itemView.findViewById(R.id.tvUsernamePost);
            ivImage = itemView.findViewById(R.id.ivImagePost);
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            ivLike = itemView.findViewById(R.id.ivLike);
            ivComment = itemView.findViewById(R.id.ivComment);

        }

        public void bind(final Post post) {
            //bind the post data to view elements
            tvDescription.setText(post.getDescription());

            tvUsername.setText(post.getUser().getUsername());
            tvUsername.setPaintFlags(tvUsername.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

            tvLocation.setText(post.getLocation());
            tvLocation.setPaintFlags(tvLocation.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

            tvTimeStamp.setText(ParseRelativeDate.getRelativeTimeAgo(post.getCreatedAt().toString()));

            tvLikeCount.setText(post.getLike());


            ParseFile image = post.getImage();
            if(image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }
            ParseFile profileImage = post.getProfileImage();
            if(profileImage != null) {
                Glide.with(context).load(post.getProfileImage().getUrl())
                        .transform(new RoundedCornersTransformation(40, 25))
                        .into(ivProfilePicture);
            }
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("PostsAdapter", "username clicked inside Posts Adapter" );
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("post", Parcels.wrap(post));
                    context.startActivity(i);
                }
            });

            ivComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("PostsAdapter", "username clicked comments " );
                    Intent i = new Intent(context, CommentsActivity.class);
                    i.putExtra("postComment", Parcels.wrap(post));
                    context.startActivity(i);

                }
            });

            ivProfilePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//
                    ((MainActivity)context).switchFragment("UserFragment", post);
                }
            });

            ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ivLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_vector_heart));
                    String likes = post.getLike();
                    Log.i("PostsAdapter", "this is now the likes: " + likes);
                    int intLike = Integer.parseInt(likes);
                    intLike++;
                    likes = String.valueOf(intLike);
                    post.setLike(likes);
                    Log.i("PostsAdapter", "this is now the likes: " + post.getLike());
                    post.saveInBackground();
                }
            });
        }
    }
}