package com.example.alert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.alert.Admin.AdminLogin;
import com.example.alert.Hospital.HospitalMapsActivity;
import com.example.alert.User.UserLogin;


public class FrontActivity extends AppCompatActivity {
    TextView admin,hospital,users;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_front );
        admin=(TextView)findViewById (R.id.btnAdmin);
        hospital=(TextView)findViewById (R.id.btnHospital);
        users=(TextView)findViewById (R.id.btnUser);
        progressDialog = new ProgressDialog( FrontActivity.this);

        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));
        admin.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                progressDialog.setMessage("Loading");
                progressDialog.show();
                progressDialog.dismiss();
                Intent i=new Intent (FrontActivity.this, AdminLogin.class);
                startActivity (i);
            }
        } );
        hospital.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                progressDialog.setMessage("Loading");
                progressDialog.show();
                progressDialog.dismiss();
                Intent in=new Intent (FrontActivity.this, HospitalMapsActivity.class);
                startActivity (in);
            }
        } );
        users.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                progressDialog.setMessage("Loading");
                progressDialog.show();
                progressDialog.dismiss();
                Intent inn=new Intent (FrontActivity.this, UserLogin.class);
                startActivity (inn);
            }
        } );
    }
}


