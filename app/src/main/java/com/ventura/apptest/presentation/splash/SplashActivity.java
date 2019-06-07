package com.ventura.apptest.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ventura.apptest.R;
import com.ventura.apptest.presentation.home.list_place.PlaceHomeActivity;
import com.ventura.apptest.presentation.login.SigninActivity;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_view);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null)
                {
                    Intent i = new Intent(SplashActivity.this, PlaceHomeActivity.class);
                    startActivity(i);
                    finish();

                } else{
                    Intent i = new Intent(SplashActivity.this, SigninActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 5000);

    }

}
