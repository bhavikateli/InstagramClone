package com.bhavikateli.instagramclone.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavikateli.instagramclone.Post;
import com.bhavikateli.instagramclone.R;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class UsersPostAdapter extends RecyclerView.Adapter<UsersPostAdapter.ViewHolder> {

    private ImageView userImageView;

    protected Context context;
    List<Post> posts;

    public UsersPostAdapter(Context context,List<Post> posts ){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userImageView = itemView.findViewById(R.id.userImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                }
            });

        }
        public void bind(Post post) {

            ParseFile image = post.getImage();

            if(image != null){
                Glide.with(context).load(image.getUrl()).into(userImageView);
            }
        }
    }
}
