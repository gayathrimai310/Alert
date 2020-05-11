package com.example.alert.User.Account;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alert.R;
import com.example.alert.User.UserMenu;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Editprofile extends AppCompatActivity {

    Button msignup_btn;
    TextView memail,mblood;
    EditText mname, mage,maddress, mmobile_number,malter_mobile_number, mpassword;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    ArrayAdapter<String> bloodgroup;
    ArrayAdapter <String> desg;
    String aspin1= null,spin1=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        mname = findViewById ( R.id.Ename );
        mage = findViewById ( R.id.Eage );
        maddress = findViewById ( R.id.Eaddress );
        mmobile_number = findViewById ( R.id.Emobile_number );
        malter_mobile_number = findViewById ( R.id.Ealter_mobile_number );
        memail = findViewById ( R.id.Eemail );
        mpassword = findViewById ( R.id.Epassword );
        msignup_btn = findViewById ( R.id.Ebtn_signup );
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("details",MODE_PRIVATE );
        final String emailid = sharedPreferences.getString("Email", "");
        msignup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
                signupRequest ();
            }
        });

        progressDialog =new ProgressDialog(Editprofile.this);
        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));

        Intent intent=getIntent();
        String b=intent.getStringExtra("Name");
        String c=intent.getStringExtra("Age");
        String d=intent.getStringExtra("Address");
        String f=intent.getStringExtra("Email");
        String g=intent.getStringExtra("Phone");
        String h=intent.getStringExtra("Alter");

        mname.setText(b);
        mage.setText(c);
        maddress.setText(d);
        memail.setText(f);
        mmobile_number.setText(g);
        malter_mobile_number.setText(h);


        StringRequest sr = new StringRequest ( Request.Method.POST,"https://gayathrimai.000webhostapp.com/User/editprofile.php",new Response.Listener < String > () {
            @Override
            public void onResponse (String response) {

                try {
                    JSONObject obj = new JSONObject ( response );
                    JSONArray array = obj.getJSONArray ( "history" );
                    for (int i = 0; i < array.length (); i++) {
                        JSONObject o = array.getJSONObject ( i );

                        mpassword.setText ( o.getString ( "Alert_PASS" ) );


                    }
                } catch (Exception e) {

                }
            }
        },new Response.ErrorListener () {
            @Override
            public void onErrorResponse (VolleyError error) {
                Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_SHORT ).show ();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> n = new HashMap<String, String>();
                n.put("em_id", emailid);


                return (n);

            }

        };
        RequestQueue r = Volley.newRequestQueue ( getApplicationContext () );
        r.add ( sr );

    }

    private void validation() {

        final String ename = mname.getText ().toString ().trim ();
        final String eage = mage.getText ().toString ().trim ();
        final String eaddress = maddress.getText ().toString ().trim ();
        final String emobilenumber = mmobile_number.getText ().toString ().trim ();
        final String ealtermobilenumber = malter_mobile_number.getText ().toString ().trim ();
        final String epassword = mpassword.getText ().toString ().trim ();


        if (validateName ( ename ) && validateAddress ( eaddress ) && validateMobilenumber ( emobilenumber )  && validateAlterMobilenumber ( ealtermobilenumber )  && validatePassword ( epassword ) && validateAge ( eage )) {
            signupRequest();

        } else {
            Toast.makeText ( getApplicationContext (),"All fields Required",Toast.LENGTH_LONG ).show ();
        }

    }

    //    Address
    private boolean validateAddress(String eaddress) {
        if (eaddress.equals ( "" )) {
            maddress.setError ( "Enter Your Address" );
            return false;
        } else if (eaddress.length () > 80) {
            maddress.setError ( "Maximum 50 Characters" );
            return false;
        }

        return true;
    }
    //   Age
    private boolean validateAge(String eage) {
        if (eage.equals ( "" )) {
            mage.setError ( "Enter Your Age" );
            return false;
        } else if (eage.length () > 10) {
            mage.setError ( "Maximum 10 Characters" );
            return false;
        }

        return true;
    }
    //   Password
    private boolean validatePassword(String epassword) {
        if (epassword.equals ( "" )) {
            mpassword.setError ( "Enter Your Password" );
            return false;
        } else if (epassword.length () > 32) {
            mpassword.setError ( "Maximum 32 Characters" );
            return false;
        } else if (epassword.length () < 8) {
            mpassword.setError ( "Minimum 8 Characters" );
            return false;
        }

        return true;
    }
    //  Mobile Number
    private boolean validateMobilenumber(String emobilenumber) {
        if (emobilenumber.equals ( "" )) {
            mmobile_number.setError ( "Enter Your Mobile Number" );
            return false;
        }
        if (emobilenumber.length () != 10) {
            mmobile_number.setError ( "Enter A Valid Mobile Number" );
            return false;
        }

        return true;
    }
    //  Alternate Mobile Number
    private boolean validateAlterMobilenumber(String ealtermobilenumber) {
        if (ealtermobilenumber.equals ( "" )) {
            mmobile_number.setError ( "Enter Your Alter Mobile Number" );
            return false;
        }
        if (ealtermobilenumber.length () != 10) {
            mmobile_number.setError ( "Enter A Valid Alter Mobile Number" );
            return false;
        }

        return true;
    }

    //   Name
    private boolean validateName(String ename) {
        if (ename.equals ( "" )) {
            mname.setError ( "Enter Your Name" );
            return false;
        } else if (ename.length () > 50) {
            mname.setError ( "Maximum 50 Characters" );
            return false;
        }

        return true;
    }

    private void signupRequest() {

        final ProgressDialog progressDialog = new ProgressDialog ( Editprofile.this );
        progressDialog.setTitle ( "Please Wait" );
        progressDialog.setMessage ( "Applying Changes" );
        progressDialog.show ();

        StringRequest stringRequest = new StringRequest ( Request.Method.POST,"https://gayathrimai.000webhostapp.com/User/updateprofile.php",new Response.Listener < String > () {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss ();
                finish ();
                Toast.makeText (Editprofile.this,"Changes Successfully",Toast.LENGTH_SHORT).show ();
            }
        },new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                //         Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        } ) {
            @Override
            protected Map< String, String > getParams() throws AuthFailureError {
                Map < String, String > par = new HashMap<>();
                par.put ( "Alert_NAME",mname.getText ().toString () );
                par.put ( "Alert_AGE",mage.getText ().toString () );
                par.put ( "Alert_ADDRESS",maddress.getText ().toString () );
                par.put ( "Alert_PH_NUM",mmobile_number.getText ().toString () );
                par.put ( "Alert_ALTER_PH_NUM",malter_mobile_number.getText ().toString () );
                par.put ( "Alert_EMAILID",memail.getText ().toString () );
                par.put ( "Alert_PASS",mpassword.getText ().toString () );


                return par;
            }
        };
        RequestQueue rq = Volley.newRequestQueue ( getApplicationContext () );
        rq.add ( stringRequest );
        Toast.makeText (Editprofile.this,"Your Details Are Updated",Toast.LENGTH_SHORT).show ();



    }
}
