package com.example.scout;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.scout.app.MySingleton;
import com.example.scout.utils.Constants;
import com.example.scout.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button CA;
    Button login;
    TextView username;
    TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         initViews();
         Log.d("after","init");
         login = findViewById(R.id.loginButton);
         CA = findViewById(R.id.createAccountButton);
         toSignUp();

         login();

    }

    private void loginVerification(final String email, final String password)
    {
        String url = "http://coms-309-vb-4.cs.iastate.edu:8080/home/login/"+ email + "&" + password;
        Log.d("in","verifiy");
        //set parameters
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        // create a json object for the parameters
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("in","verifiy");
                    if (response.getBoolean("success")){
                        PreferenceUtils.saveEmail(email, MainActivity.this);
                        PreferenceUtils.savePassword(password, MainActivity.this);
                        PreferenceUtils.saveUserid(response.getString("userId"), MainActivity.this);
                        startPublicPosting();
                         Log.d("Responce", String.valueOf(response.getBoolean("success")));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonReq);
    }

    private void login()
    {
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Log.d("in","login");
                username = (TextView) findViewById(R.id.EmailText);
                password = (TextView) findViewById(R.id.passwordText);
                String usertext = username.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                if(usertext.length() <=0){
                    Toast.makeText(MainActivity.this, "Enter a email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwordText.length() <=0 ){
                    Toast.makeText(MainActivity.this, "Enter a Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(usertext.equals("message"))
                    startActivity(new Intent(MainActivity.this, MessangerChatBox.class));

                //addJSONObjectRequest(usertext, passwordText);
                loginVerification(usertext, passwordText);

            }
        });
    }


    private void initViews()
    {
        PreferenceUtils utils = new PreferenceUtils();

        if(utils.getEmail(this) != null )
        {
            loginVerification(utils.getEmail(this), utils.getPassword(this));
        }
    }

    private void SignUpPage()
    {
        Intent in = new Intent(this, SignUp.class);
        startActivity(in);
    }


    private void toSignUp()
    {
        CA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SignUpPage();
            }

        });

    }

    private void startPublicPosting()
    {

        Intent intent = new Intent(MainActivity.this, PublicPosting.class);
        startActivity(intent);
    }



}