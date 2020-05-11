package com.example.alert.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alert.R;



import java.util.HashMap;
import java.util.Map;

public class UserLogin extends AppCompatActivity {
    EditText e1, e2;
    Button b1;
    TextView t1;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_login );
        e1 = findViewById ( R.id.login_name );
        e2 = findViewById ( R.id.login_password );
        b1 = findViewById ( R.id.signin_btn );
        t1 = findViewById ( R.id.create_account );
        progressDialog = new ProgressDialog( UserLogin.this);
        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));

        b1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (e1.getText ().toString ().isEmpty () || (!e1.getText ().toString ().matches ( emailPattern ))) {
                    e1.setError ( "Enter Correct Mailid" );
                } else if (e2.getText().toString().isEmpty() || e2.getText().toString().length() < 8) {
                    e2.setError("Enter correctpassword");
                } else {

                    StringRequest tt = new StringRequest ( Request.Method.POST,"https://gayathrimai.000webhostapp.com/User/userlogin.php",new Response.Listener < String > () {
                        @Override
                        public void onResponse (String response) {

                                 Toast.makeText(UserLogin.this, response, Toast.LENGTH_SHORT).show();
                            if (response.equals ( " Login " )) {

                                sharedPreferences = getSharedPreferences ( "details",MODE_PRIVATE );
                                SharedPreferences.Editor edit = sharedPreferences.edit ();
                                edit.putString ( "Email",e1.getText ().toString () );
                                edit.commit ();
                                Intent i = new Intent (getApplicationContext(),UserMenu.class );
                                startActivity ( i );
                                Toast.makeText ( UserLogin.this ,"Login successful",Toast.LENGTH_SHORT ).show ();

                            } else {

                                Toast.makeText ( UserLogin.this,"Login unsuccessful",Toast.LENGTH_SHORT ).show ();
                            }
                        }
                    },new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            //           Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } ) {
                        @Override
                        protected Map < String, String > getParams () throws AuthFailureError {
                            Map < String, String > n = new HashMap < String, String > ();
                            n.put ( "user",e1.getText ().toString () );
                            n.put ( "pass",e2.getText ().toString () );

                            return (n);
                        }
                    };
                    RequestQueue re = Volley.newRequestQueue ( UserLogin.this );
                    re.add ( tt );

                }
            }

        } );
        t1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent inn = new Intent ( UserLogin.this,UserRegistration.class );
                startActivity ( inn );
            }
        } );
    }
}

