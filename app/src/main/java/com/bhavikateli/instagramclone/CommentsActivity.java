package com.bhavikateli.instagramclone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private RecyclerView rvComments;
    List<Comment> comments;
    CommentsAdapter adapter;
    Comment comment;
    public static final String TAG = "CommentsActivity";
    Post post;
    Button btnComment;
    EditText etNewCommentContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         post = Parcels.unwrap(getIntent().getParcelableExtra("postComment"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        setCreate(post);

    }

    private void setCreate(Post post) {

        rvComments = findViewById(R.id.rvComments);
        btnComment = findViewById(R.id.btnComment);
        etNewCommentContent = findViewById(R.id.etNewCommentContent);


        comments = new ArrayList<>();

        adapter = new CommentsAdapter(this, comments, post);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rvComments.setLayoutManager(linearLayoutManager);

        rvComments.setAdapter(adapter);
        queryComment();
        
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Inside comment in button");
                String newComment = etNewCommentContent.getText().toString();
                ParseUser currentUser = ParseUser.getCurrentUser();

                createComment(newComment, currentUser);
            }
        });
    }

    private void createComment(String newComment, ParseUser currentUser) {
        Comment comment = new Comment();
        comment.setUser(currentUser);
        comment.setComment(newComment);
        comment.setPost(post);
        comment.saveInBackground();
    }

    private void queryComment() {
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        query.include(Comment.KEY_USER);
        query.whereEqualTo(Comment.KEY_POST_POINTER, post);
        query.setLimit(20);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> Comments, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                comments.addAll(Comments);
                adapter.notifyDataSetChanged();
            }
        });
    }


}