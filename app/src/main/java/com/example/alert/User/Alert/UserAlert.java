package com.example.alert.User.Alert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alert.R;

import static android.content.Context.SENSOR_SERVICE;


public class UserAlert extends Fragment {


    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate ( R.layout.fragment_user_alert,container,false );
        Animation anim = AnimationUtils.loadAnimation(getContext (), R.anim.anim_alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) v.findViewById(R.id.Linearlayout);
//        l.setBackgroundColor(R.color.background);
        //l.setBackgroundResource(R.color.background);

        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(getContext (), R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView ) v.findViewById(R.id.Drawable);
        iv.clearAnimation();
        iv.startAnimation(anim);

        Intent i = new Intent(getContext(), EmergencyNotification.class);
        startActivity(i);
        return v;
    }

}
