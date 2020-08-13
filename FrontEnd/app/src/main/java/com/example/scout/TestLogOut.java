package com.example.scout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scout.utils.PreferenceUtils;

public class TestLogOut extends AppCompatActivity
{

    Button logout;
    Button cont;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_log_out);
        logout = findViewById(R.id.logoutButton);;
        cont = findViewById(R.id.cont);
        logout();
        cont();
    }

    public void cont()
    {
        cont.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toPosting();
            }
        });

    }

    public void logout()
    {
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PreferenceUtils.logOut(TestLogOut.this);
                toLogin();
            }
        });
    }


    private void toLogin()
    {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    private void toPosting()
    {
        Intent in = new Intent(this, PublicPosting.class);
        startActivity(in);
    }


}
