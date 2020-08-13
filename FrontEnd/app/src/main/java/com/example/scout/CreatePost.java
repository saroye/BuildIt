package com.example.scout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.scout.app.MySingleton;
import com.example.scout.utils.PreferenceUtils;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreatePost extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    EditText projectName;
    EditText projectDescription;
    ImageView projectImage;
    Button bUploadImage;
    Button bCreatePost;
    EditText eCompensation;
    EditText eThumbnailDescription;
    EditText eRoles;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            projectImage.setImageURI(selectedImage);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        projectImage = (ImageView) findViewById(R.id.imageToUpload);
        projectName = (EditText) findViewById(R.id.projectName);
        projectDescription = (EditText) findViewById(R.id.projectDescription);
        bUploadImage = (Button) findViewById(R.id.bUploadImage);
        bCreatePost = (Button) findViewById(R.id.bCreatePost);
        eThumbnailDescription = (EditText) findViewById(R.id.attentionGrabber);
        eCompensation = (EditText) findViewById(R.id.compensation);
        eRoles = (EditText) findViewById(R.id.roles);


        bUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        bCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkAllFields())
                    sendPostToServer();
            }
        });


    }

    public boolean checkAllFields(){
        String cName = projectName.getText().toString();
        String ctnd = eThumbnailDescription.getText().toString();
        String cCompensation = eCompensation.getText().toString();
        String cRoles = eRoles.getText().toString();
        String[] strArr = {cName,ctnd,cCompensation,cRoles};
        //TODO: need to add image

        for(int i =0; i<strArr.length; i++){
            if(strArr[i].length()<=0){
                Toast.makeText(this,"missing field", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;

    }

    public void sendPostToServer()
    {

        String url = "http://coms-309-vb-4.cs.iastate.edu:8080/home/addpost";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CreatePost.this, "Post Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreatePost.this, PublicPosting.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreatePost.this, "Post Not Create", Toast.LENGTH_SHORT).show();
                System.out.println(error.getMessage());
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //TODO add the image part
                Map<String, String> param = new HashMap<>();
                param.put("projectname", projectName.getText().toString());
                param.put("projectdescription",projectDescription.getText().toString());
                param.put("neededroles", eRoles.getText().toString());
                param.put("compensation", eCompensation.getText().toString());
                param.put("thumbnailDescription", eThumbnailDescription.getText().toString());
                param.put("userid", PreferenceUtils.getUserid(CreatePost.this));
                return param;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(strRequest);
    }
}
