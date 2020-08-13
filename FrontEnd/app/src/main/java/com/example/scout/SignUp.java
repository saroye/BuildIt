package com.example.scout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.scout.app.MySingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity
{

    private Button sub;
    private Button createButton;
    private TextView email;
    private TextView phone;
    private TextView password;
    private TextView firstName;
    private TextView lastName;

    private String emailText;
    private String passwordText;
    private String phoneText;
    private String firstText;
    private String lastText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createButton = (Button) findViewById(R.id.sub);
        submit();
    }

    private void sendNewUser(final String email, final String password, final String phone, final String lastName, final String firstName)
    {

        String url = "http://coms-309-vb-4.cs.iastate.edu:8080/home/add";


        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("STR REQ RESPONSE", response);
                Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(SignUp.this, MainActivity.class);
                startActivity(in);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("STR REQ error", error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("firstName", firstName);
                param.put("lastName", lastName);
                param.put("email", email);
                param.put("password", password);
                param.put("phone", phone);

                return param;
            }
        };

        MySingleton.getInstance(SignUp.this).addToRequestQueue(strReq);
    }



    private void submit()
    {
        createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initialize();
                textToString();
                checkLen();
                sendNewUser(emailText, passwordText, phoneText, lastText, firstText);
            }
        });
    }

    private void initialize()
    {
        email =  (TextView) findViewById(R.id.editText);
        password = (TextView) findViewById(R.id.editText2);
        phone = (TextView) findViewById(R.id.PhoneNumber);
        firstName = (TextView) findViewById(R.id.FirstName);
        lastName = (TextView) findViewById(R.id.LastName);
    }

    private void textToString()
    {
        emailText = email.getText().toString();
        passwordText = password.getText().toString();
        phoneText = phone.getText().toString();
        firstText = firstName.getText().toString();
        lastText = lastName.getText().toString();
    }

    private void checkLen()
    {
        if(emailText.length() <=0)
        {
            Toast.makeText(SignUp.this, "Enter an Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwordText.length() <= 0)
        {
            Toast.makeText(SignUp.this, "Enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(phoneText.length()<= 0)
        {
            Toast.makeText(SignUp.this, "Enter a Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }

        if(firstText.length()<=0)
        {
            Toast.makeText(SignUp.this, "Enter your First Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(lastText.length()<=0)
        {
            Toast.makeText(SignUp.this, "Enter your Last Name", Toast.LENGTH_SHORT).show();
            return;
        }

    }


}
