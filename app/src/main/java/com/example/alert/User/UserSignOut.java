package com.example.alert.User;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alert.FrontActivity;
import com.example.alert.R;



import java.util.HashMap;
import java.util.Map;

public class UserSignOut extends AppCompatActivity {
    TextView singoutemail;
    Button signout;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_sign_out );
        singoutemail=(TextView)findViewById ( R.id.Singoutmail);
        signout=(Button)findViewById (R.id.Signout);
        progressDialog =new ProgressDialog(UserSignOut.this);
        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));
        sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
        singoutemail.setText (sharedPreferences.getString ( "Email", "" ) );
        AlertDialog.Builder builder = new AlertDialog.Builder ( UserSignOut.this );
        builder.setTitle ( "Alert!" );
        builder.setMessage ( "Are You Sure To Exit.......!" );
        builder.setNegativeButton ( "No",new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog,int which) {
                Toast.makeText ( getApplication (),"no",Toast.LENGTH_LONG ).show ();
            }
        } );
        builder.setPositiveButton ( "yes",new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog,int which) {
                Toast.makeText ( getApplicationContext (),"Thank you",Toast.LENGTH_LONG ).show ();
            }
        } );
        builder.show ();
        signout.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                progressDialog.setTitle("Please Wait.....");
                progressDialog.setMessage("Thank You Visit Again.........");
                progressDialog.show();
                StringRequest tt = new StringRequest( Request.Method.POST, "https://gayathrimai.000webhostapp.com/User/usersignout.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //     Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                        if (response.equals(" Logout ")) {
                            Toast.makeText(UserSignOut.this, "Logout successful", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(UserSignOut.this, FrontActivity.class);
                            startActivity(in);

                        } else {

                            Toast.makeText(UserSignOut.this, "Logout unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //           Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map <String, String> getParams() throws AuthFailureError {
                        Map<String, String> n = new HashMap <String, String> ();
                        n.put("User", singoutemail.getText().toString());
                        return (n);
                    }
                };
                RequestQueue re = Volley.newRequestQueue(UserSignOut.this);
                re.add(tt);
            }

        } );
    }
}


