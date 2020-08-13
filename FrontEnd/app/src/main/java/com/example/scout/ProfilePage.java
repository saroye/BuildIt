package com.example.scout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfilePage extends AppCompatActivity
{

    private ImageButton editB;
    private ImageView ProPic;
    private TextView summ;
    private TextView Vemail;
    private String Semail;
    private TextView phone;
    private TextView password;
    private TextView name;
    private Switch group;
    private Switch looking;
    private int canEdit = 1;
    private Button logout;


    private JsonObjectRequest profile;
    private JsonObjectRequest edit;
    private String url = "http://coms-309-vb-4.cs.iastate.edu:8080/home/users";
    private String firstName;
    private String lastName;
    private String phoneNum;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Intialize();

        getInfo();
        nav();
        logout();
        enableEdit();
    }

    private void Intialize()
    {
        editB = findViewById(R.id.editB);
        Vemail = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        name = (TextView) findViewById(R.id.name);
        summ = (TextView) findViewById(R.id.summory);
        ProPic = (ImageView) findViewById(R.id.ProPic);
        group = (Switch) findViewById(R.id.group);
        looking = (Switch) findViewById(R.id.looking);
        logout = findViewById(R.id.logoutButton);
        Semail = PreferenceUtils.getEmail(ProfilePage.this);
        url = url + "/"+ Semail;
    }


    public void editPro()
    {

        edit = new JsonObjectRequest(Request.Method.POST, url, null,new Response.Listener<JSONObject>()
        {


            @Override
            public void onResponse(JSONObject response)
            {

                if (response.equals(null)) Log.d("response =", "NULL");



            }
        },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("thing", "4");
                        error.printStackTrace();
                        // requestQueue.stop();
                    }

                });



        MySingleton.getInstance(ProfilePage.this).addToRequestQueue(this.edit);
    }



    public void getInfo()
    {
        Log.d("Click=", "1");
        profile = new JsonObjectRequest(Request.Method.GET,this.url,null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {

                if (response.equals(null)) Log.d("response =", "NULL");
                try
                {

                    Log.d("response", String.valueOf(response.get("email")));
                    Log.d("response", String.valueOf(response.get("phonenumber")));
                    PreferenceUtils.saveFirstName(String.valueOf(response.get("firstname")),ProfilePage.this);
                    PreferenceUtils.saveLastName(String.valueOf(response.get("lastname")),ProfilePage.this);
                    PreferenceUtils.savePhone(String.valueOf(response.get("phonenumber")),ProfilePage.this);
                  //  response.putOpt("firstname","butts");
                    firstName = PreferenceUtils.getFirstName(ProfilePage.this);
                    lastName = PreferenceUtils.getLastName(ProfilePage.this);
                    phoneNum = PreferenceUtils.getPhone(ProfilePage.this);

                    Vemail.setText(String.valueOf(response.get("email")));
                    name.setText(firstName + " " +lastName);
                    phone.setText(phoneNum);


                    Log.d("response", String.valueOf(response.get("firstname")));
                    Log.d("response", String.valueOf(response.get("lastname")));

                 //   PreferenceUtils.saveFirstName("butts",ProfilePage.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("thing", "4");
                       // tv.setText("you messed up");
                        error.printStackTrace();
                        // requestQueue.stop();
                    }

                });

        Log.d("Location", "outside sr");
        MySingleton.getInstance(this).addToRequestQueue(this.profile);
    }



    public void nav()
    {

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {
            switch (menuItem.getItemId()) {
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


    private void enableEdit()
    {
        editB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (canEdit == 0)
                {
                    Vemail.setEnabled(true);
                    phone.setEnabled(true);
                    name.setEnabled(true);
                    summ.setEnabled(true);
                    ProPic.setEnabled(true);
                    group.setEnabled(true);
                    looking.setEnabled(true);

                    canEdit = 1;
                    //    Log.d("Get email",PreferenceUtils.getEmail(ProfilePage.this));
                    //    editPro();
                }

                else
                {
                    Vemail.setEnabled(false);
                    phone.setEnabled(false);
                    name.setEnabled(false);
                    summ.setEnabled(false);
                    ProPic.setEnabled(false);
                    group.setEnabled(false);
                    looking.setEnabled(false);

                    canEdit = 0;
                }
                Log.d("Can Edit = ",  canEdit +"");
            }

        });

    }




    private void logout()
    {
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toLogout();
            }
        });
    }

    public void goToMsg()
    {
        Intent intent = new Intent(ProfilePage.this, MessangerChatBox.class);
        startActivity(intent);
    }


    public void goToHome()
    {
        Intent intent = new Intent(ProfilePage.this, MainActivity.class);
        startActivity(intent);
    }


    public void goToProfile()
    {
        Intent intent = new Intent(ProfilePage.this, ProfilePage.class);
        startActivity(intent);
    }

    private void toLogout()
    {
        Intent in = new Intent(this, TestLogOut.class);
        startActivity(in);
    }



}