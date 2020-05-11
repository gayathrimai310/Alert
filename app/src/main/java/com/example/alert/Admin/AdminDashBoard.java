package com.example.alert.Admin;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.alert.Admin.Hospital.CareGiversList;
import com.example.alert.Admin.User.UserList;
import com.example.alert.FrontActivity;
import com.example.alert.R;


public class AdminDashBoard extends AppCompatActivity {
    CardView userpage,hospitalpage;
    Button logout;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_admin_dash_board );
        userpage=findViewById (R.id.UserPage);

        hospitalpage=findViewById (R.id.HospitalPage);
        logout=findViewById (R.id.SignoutAdmin);
        userpage.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {

                Intent i=new Intent( AdminDashBoard.this, UserList.class);
                startActivity (i);
            }
        } );
        hospitalpage.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {


                Intent i=new Intent( AdminDashBoard.this, CareGiversList.class);
                startActivity (i);
            }
        } );
        logout.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View view) {

                Intent i=new Intent( AdminDashBoard.this, FrontActivity.class);
                startActivity (i);
            }
        } );
    }
}
