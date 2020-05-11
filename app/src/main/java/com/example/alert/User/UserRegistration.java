package com.example.alert.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class UserRegistration extends AppCompatActivity {
    Button signup_btn;
    EditText name, age,address, mobile_number,alter_mobile_number, email, password,repass,blood;
    SharedPreferences sharedPreferences;
    Spinner ebloodgroup;
    ArrayAdapter<String> bloodgroup;
    ArrayAdapter <String> desg;
    String aspin1= null,spin1=null;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_registration );
        setTitle ( "Register  " );

        name = findViewById ( R.id.name );
        age = findViewById ( R.id.age );
        address=findViewById (R.id.address);
        mobile_number = findViewById ( R.id.mobile_number );
        alter_mobile_number=findViewById (R.id.alter_mobile_number);
        email = findViewById ( R.id.input_email );
        password = findViewById ( R.id.password );
        repass=findViewById(R.id.repassword);
        ebloodgroup=findViewById(R.id.BLDG);
        signup_btn = findViewById ( R.id.btn_signup );
        final String[] nocounts={"Choose Blood type...","0+","A+","B+","o-","A_"};
        bloodgroup = new ArrayAdapter(getApplicationContext(),R.layout.spinnerdesign,nocounts);
        bloodgroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ebloodgroup.setAdapter(bloodgroup);
        ebloodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
                if(ebloodgroup != null && ebloodgroup.getSelectedItem() !=null ) {
                    aspin1 = (String)ebloodgroup.getSelectedItem();
                    //     Toast.makeText(getApplicationContext(), as, Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(getApplicationContext(), "Choose the field", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup_btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
              if (name.getText().toString().isEmpty()  &&  name.getText().toString().length()<32){
                  name.setError("Enter Name");
              }else if (age.getText().toString().isEmpty() ){
                  age.setError("Enter Age");
              }else if (address.getText().toString().isEmpty()   && address.getText().toString().length()<50){
                  address.setError("Enter Address");
              }else if (mobile_number.getText().toString().isEmpty() && mobile_number.getText().toString().length()<10){
                  mobile_number.setError("Enter Mobile Number");
              }else if(alter_mobile_number.getText().toString().isEmpty() && alter_mobile_number.getText().toString().length()<10){
                  alter_mobile_number.setError("Enter Alter Mobile Number");
              }else if (email.getText().toString().matches(emailPattern)  && email.getText().toString().isEmpty()){
                  email.setError("Invalid Pattern");
              }else if (password.getText().toString().length()<8){
                    password.setError("Maximum 8 Characters");
                }else if (repass.getText().toString().equals(password.getText().toString())  && repass.getText().toString().isEmpty()){
                  repass.setError("Does Not Match");
              }else{
                  final ProgressDialog progressDialog = new ProgressDialog ( UserRegistration.this );
                  progressDialog.setTitle ( "Please Wait" );
                  progressDialog.setMessage ( "Creating Account" );
                  progressDialog.show ();

                  StringRequest stringRequest = new StringRequest ( Request.Method.POST,"https://gayathrimai.000webhostapp.com/User/userregister.php",new Response.Listener < String > () {
                      @Override
                      public void onResponse(String response) {
                          progressDialog.dismiss ();
                          finish ();
                          Intent i = new Intent ( UserRegistration.this,UserLogin.class );
                          startActivity ( i );
                          Toast.makeText ( UserRegistration.this,response.toString (),Toast.LENGTH_SHORT ).show ();
                      }
                  },new Response.ErrorListener () {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          //         Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                      }
                  } ) {
                      @Override
                      protected Map < String, String > getParams() throws AuthFailureError {
                          Map < String, String > par = new HashMap <> ();
                          par.put ( "Alert_NAME",name.getText ().toString () );
                          par.put ( "Alert_AGE",age.getText ().toString () );
                          par.put ( "Alert_ADDRESS",address.getText ().toString () );
                          par.put ( "Alert_PH_NUM",mobile_number.getText ().toString () );
                          par.put ( "Alert_ALTER_PH_NUM",alter_mobile_number.getText ().toString () );
                          par.put ( "Alert_EMAILID",email.getText ().toString () );
                          par.put ( "Alert_PASS",password.getText ().toString () );
                          par.put ( "Alert_BLD",aspin1 );


                          return par;
                      }
                  };
                  RequestQueue rq = Volley.newRequestQueue ( getApplicationContext () );
                  rq.add ( stringRequest );
              }

            }
        } );

    }
}
