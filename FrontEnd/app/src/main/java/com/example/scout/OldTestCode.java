package com.example.scout;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.scout.app.MySingleton;
import com.example.scout.utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OldTestCode
{
//    private void backToHome()
//    {
//        Intent in = new Intent(this, MainActivity.class);
//        startActivity(in);
//    }


//    public void addListenerOnButton()
//    {
//        sp = (Spinner) findViewById(R.id.spinner);
//        this.b = (Button) findViewById(R.id.b1);
//        this.b.setOnClickListener(new View.OnClickListener()
//        {
//
//            @Override
//            public void onClick(View v)
//            {
//                // get current package
//                String PNAME = getApplicationContext().getPackageName();
//                PNAME = PNAME + "." + sp.getSelectedItem().toString();
//                Intent in = null;
//                try
//                {
//                    in = new Intent(ProfilePage.this,Class.forName(PNAME));
//                }
//                catch (ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//                startActivity(in);
//            }
//
//        });
//    }


//    public void addJSONObjectRequest(String email, final String iPass){
//        email= email.trim();
//        String url = "http://coms-309-vb-4.cs.iastate.edu:8080/home/users/";
//
//        Log.d("Response", url);
//        JsonObjectRequest jsonobjReq = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
//                {
//                    @Override
//                    public void onResponse(JSONObject response)
//                    {
//                        try
//                        {
//                            Log.d("GET", response.getString("email"));
//                            if(iPass.equals(response.getString("password")))
//                            {
//                                Intent intent = new Intent(MainActivity.this, ProfilePage.class);
//                                startActivity(intent);
//                            }
//                            else
//                            {
//                                Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        catch (JSONException e)
//                        {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error)
//                    {
//                        Log.d("error", "there was an error");
//                        Toast.makeText(MainActivity.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        MySingleton.getInstance(this).addToRequestQueue(jsonobjReq);
//    }


//
//
//    private void strReqLoginVerification(final String email, final String password){
//
//        String url = "http://coms-309-vb-4.cs.iastate.edu:8080/home/login/"+ email + "&" + password;
//        //set parameters
//        Map<String, String> params = new HashMap<>();
//        params.put("email", email);
//        params.put("password", password);
//
//        // create a json object for the parameters
//        JSONObject parameters = new JSONObject(params);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if(response.trim().equals("Success")){
//                    PreferenceUtils.saveEmail(email, MainActivity.this);
//                    PreferenceUtils.savePassword(password, MainActivity.this);
//
//                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT);
//
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println("you got an error");
//            }
//        });
//
//        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
//
//    }
}
