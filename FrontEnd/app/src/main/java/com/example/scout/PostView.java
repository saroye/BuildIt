package com.example.scout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostView extends AppCompatActivity {

    TextView mName;
    ImageView mImage;
    TextView mdescription;
    Post post;
    TextView post_username;
    CircleImageView postUserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        mName = findViewById(R.id.projectName);
        mImage = findViewById(R.id.projectImage);
        mdescription = findViewById(R.id.projectDescription);
        post_username = findViewById(R.id.post_username);
        postUserImage = findViewById(R.id.profile_image);

        getinfo();
    }

    public void getinfo(){

        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("post");
        mName.setText(post.getPostName());
        mdescription.setText(post.getDescrip());
        post_username.setText(post.getmOwnerName());

    }
}
