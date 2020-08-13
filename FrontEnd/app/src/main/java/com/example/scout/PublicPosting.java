package com.example.scout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.scout.app.MySingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class PublicPosting extends AppCompatActivity implements RecyclerViewAdapter.OnNoteListener {
    private static final String TAG = "publicPostActivity";

    ImageButton bCreatePost;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Post> mPosts = new ArrayList<>();
    private  BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_posting);
        Log.d(TAG, "onCreate: started");

        bCreatePost = (ImageButton) findViewById(R.id.imageButton);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        navagation();
        jsonRequestPosts();
        createPost();

    }

    private void initRecycleView()
    {
        Log.d(TAG, "initRecyclerView: init recylcerview.");
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mPosts, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void jsonRequestPosts()
    {
        final JSONArray[] jsonArray = {null};
        String url = "http://coms-309-vb-4.cs.iastate.edu:8080/home/allposts";
        JsonArrayRequest jreq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response.length());
                jsonArray[0] = response;
                int sizeofarr = response.length();
                for(int i=0; i<sizeofarr; i++) {
                    try {
                        JSONObject postObj = response.getJSONObject(i);
                        Date pdate = null;
                        String pCompensation = (String) postObj.get("compensation");
                        String pRoles = (String) postObj.get("neededRoles");
                        Integer picture = R.drawable.scouticon;
                        String pDescription = (String) postObj.get("projectDescription");
                        String pName = (String) postObj.get("projectName");
                        String pThumbnaildescription = postObj.getString("thumbNail");
                        String ownerName = (String) postObj.get("ownerName");
                        String ownerId = postObj.getString("ownerId");
                        Post post = new Post(picture, pName, pDescription, pRoles, pThumbnaildescription, pCompensation,ownerId, ownerName);
                        mPosts.add(post);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                initRecycleView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jreq);


    }

    @Override
    public void onNoteClick(int position) {
        Intent in = new Intent(this, PostView.class);
//        in.putExtra("id", mPosts.get(position).getDate());
//        in.putExtra("tnd",mPosts.get(position).getmThumbnailDescription());
//        in.putExtra("roles",mPosts.get(position).getNeedRoles());
//        in.putExtra("compensation",mPosts.get(position).getmCompensation());
//        in.putExtra("name", mPosts.get(position).getPostName() );
//        in.putExtra("description", mPosts.get(position).getDescrip());
        in.putExtra("post", mPosts.get(position));
        startActivity(in);
    }


    private void navagation()
    {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        goToHome();
                        break;
                    case R.id.navigation_account:
                        goToProfile();
                        break;
                    case R.id.navigation_message:
                        goToMsg();
                        break;
                }
                return false;
            }
        });
    }

    private void createPost()
    {
        bCreatePost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent in = new Intent(view.getContext(), CreatePost.class);
                startActivity(in);
            }
        });
    }

     public void goToMsg()
     {
         Intent intent = new Intent(PublicPosting.this, MessangerChatBox.class);
         startActivity(intent);
     }


    public void goToHome()
    {
        Intent intent = new Intent(PublicPosting.this, PublicPosting.class);
        startActivity(intent);
    }


    public void goToProfile()
    {
        Intent intent = new Intent(PublicPosting.this, ProfilePage.class);
        startActivity(intent);
    }



}
