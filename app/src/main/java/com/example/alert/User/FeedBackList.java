package com.example.alert.User;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class FeedBackList extends AppCompatActivity {
    EditText registeremail,feedbackform;
    Button submit;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_feed_back_list );
        registeremail=(EditText)findViewById ( R.id.UserEmailfeedback);
        feedbackform=(EditText)findViewById (R.id.UserFeedbackform);
        submit=(Button)findViewById (R.id.UserSubmit);

        progressDialog =new ProgressDialog(FeedBackList.this);
        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));
        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(registeremail.getText ().toString ().isEmpty ()  ||  (!registeremail.getText ().toString ().matches (emailPattern))){
                    registeremail.setError ("Enter Correct Mailiid");
                }else if (feedbackform.getText ().toString ().isEmpty ()){
                    feedbackform.setError ("Fields Required");
                }else {
                    StringRequest sr = new StringRequest ( Request.Method.POST,"https://gayathrimai.000webhostapp.com/User/feedbacklist.php",new Response.Listener < String > () {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText ( getApplicationContext (),response,Toast.LENGTH_SHORT ).show ();
                        }
                    },new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_SHORT ).show ();
                        }
                    } ) {
                        @Override
                        protected Map < String, String > getParams() throws AuthFailureError {
                            Map < String, String > mp = new HashMap <> ();
                            mp.put ( "Alert_REG_ID",registeremail.getText ().toString () );
                            mp.put ( "Alert_FD_BK",feedbackform.getText ().toString () );
                            return mp;
                        }
                    };
                    RequestQueue r = Volley.newRequestQueue ( getApplicationContext () );
                    r.add ( sr );

                }
            }
        } );
    }
}


