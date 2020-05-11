package com.example.alert;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {


    LocationManager locationManager;


    private boolean InternetCheck = true;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        StartAnimations();
        postDelayedMethod();
        postLocationMethod();

    }

    private void postLocationMethod () {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);// Write you code here if permission already given.

    }

    private void postDelayedMethod() {
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                boolean InternetResult = checkConnection();

//                SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
//                if (pref.getBoolean("activity_executed", false)) {
//                    if (InternetResult) {
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        DialogAppear();
//                    }
//                } else {
                Intent intent = new Intent(getApplicationContext(), FrontActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIME_OUT);


    }



    private void DialogAppear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Network Error");
        builder.setMessage("No Internet Connectivity");

        builder.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                });
        builder.setPositiveButton("Retry",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postDelayedMethod();

            }


        });
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    private boolean checkConnection() {

        if (isOnline()){
            Toast.makeText(getApplicationContext(),"connected :"+InternetCheck, Toast.LENGTH_SHORT).show();
            return InternetCheck;
        }
        else {
            return false;
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            Toast.makeText(getApplicationContext(),"connected...", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(),"Disconneted...", Toast.LENGTH_SHORT).show();
            return false;
        }    }



    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.in_le);
//        l.setBackgroundColor(R.color.background);
        //l.setBackgroundResource(R.color.background);

        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView ) findViewById(R.id.loooo);
        iv.clearAnimation();
        iv.startAnimation(anim);
    }


}
