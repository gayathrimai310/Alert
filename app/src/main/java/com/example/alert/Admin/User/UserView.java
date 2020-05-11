package com.example.alert.Admin.User;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alert.R;
import com.mikhaellopez.circularimageview.CircularImageView;



public class UserView extends AppCompatActivity {
    TextView username,userage,useraddress,usercontact,useraltercontact,useremail;
    CircularImageView view;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_view );
        username=findViewById ( R.id.viewuser);
        userage=findViewById (R.id.Userage);
        useraddress=findViewById (R.id.Useraddress);
        usercontact=findViewById (R.id.Usercontact);
        useraltercontact=findViewById (R.id.Useraltercontact);
        useremail=findViewById (R.id.Useremailid);
        view=findViewById (R.id.GobackbutnView);

        String sname=getIntent ().getStringExtra ("Name");
        String sage=getIntent ().getStringExtra ("Age");
        String saddress=getIntent ().getStringExtra ("Address");
        String scontact=getIntent ().getStringExtra ("Contact");
        String saltercontact=getIntent ().getStringExtra ("Alter");
        String semailid=getIntent ().getStringExtra ("Email");


        username.setText (sname);
        userage.setText (sage);
        useraddress.setText (saddress);
        usercontact.setText (scontact);
        useraltercontact.setText (saltercontact);
        useremail.setText (semailid);

        view.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent in=new Intent (UserView.this,UserList.class);
                startActivity (in);
            }
        } );

    }
}

