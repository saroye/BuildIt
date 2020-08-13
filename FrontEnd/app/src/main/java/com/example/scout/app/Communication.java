package com.example.scout.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Communication {
    private String url;
    Context cont;
    private static Communication instance;

    public Communication(Context c){
        //TODO: add the server url
        url="aba";
        cont = c;

    }
    public void getUserInformation(String email){

        JSONObject jobj = new JSONObject();

        try {
            jobj.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject ans = new JSONObject();

        JsonObjectRequest jsonreq = new JsonObjectRequest(Request.Method.POST, url, jobj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                    }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });

        MySingleton.getInstance(cont).addToRequestQueue(jsonreq);

    }

    public boolean addJSONObjectRequest(String email, String iPass){


        String url = "http://coms-309-vb-4.cs.iastate.edu:8080/demo/email/"+ email;
         String pass = iPass.trim();

         if(email=="hi")
             return false;
//        JsonObjectRequest jsonobjReq = new JsonObjectRequest(
//                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                try {
//                    if(pass.equals(response.getString("password"))) {
//                        t[0] = true;
//
//                    }
//                    else{
//
//                    }
//                } catch (JSONException e) {
//                    //e.printStackTrace();
//
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }
//        );

        return true;
    }

    public static synchronized Communication getInstance(Context context) {
        if (instance == null) {
            instance = new Communication(context);
        }
        return instance;
    }

}
