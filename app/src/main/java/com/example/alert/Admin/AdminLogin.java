package com.example.alert.Admin;

import android.os.Build;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alert.R;



public class AdminLogin extends AppCompatActivity implements View.OnClickListener {
    EditText email,password;
    Button login;
    ProgressDialog progressDialog;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_admin_login);
        setTitle("Admin");


        progressDialog =new ProgressDialog(AdminLogin.this);
        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));

        email = (EditText)findViewById(R.id.AdminEmailId);
        password = (EditText)findViewById(R.id.AdminPassword);

        login = (Button)findViewById(R.id.AdminLogin);



        login.setOnClickListener(this);

        progressDialog.show();

    }


    @Override
    public void onClick(View v) {

        if(v == login) {


            String remail = email.getText().toString();
            String rpass = password.getText().toString();
            if(email.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
            {
                email.setError("Enter Valid Email Id");


            }
            else if (password.getText().toString().isEmpty() || password.getText().toString().length() < 6) {
                password.setError("Enter Valid Password");

            }
            else if (email.getText().toString().equals("admin@gmail.com") && password.getText().toString().equals("admin123")) {
                progressDialog.setMessage("logging please wait");
                progressDialog.show();
                Intent a=new Intent(getApplicationContext(),AdminDashBoard.class);
                startActivity(a);
                Toast.makeText(AdminLogin.this, "successfully logged in", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(AdminLogin.this, " Invalid email id and password ", Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        }

    }
    public int validate(){
        int k =1;
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("enter email id");
            k=0;
        }
        else if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("enter password");
            k=0;
        }
        else if (!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            Toast.makeText(AdminLogin.this, "enter valid email address", Toast.LENGTH_SHORT).show();
            k=0;
        }
        else if (password.getText().toString().length() < 8) {
            password.setError("password must be atleast 8 characters");
            k=0;
        }
        return k;
    }
}

