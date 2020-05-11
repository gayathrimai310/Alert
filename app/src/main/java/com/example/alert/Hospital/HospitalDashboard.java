package com.example.alert.Hospital;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.alert.R;
import com.mikhaellopez.circularimageview.CircularImageView;



public class HospitalDashboard extends AppCompatActivity {
    CardView hospitalmanagement,caregivers,ambulance,alertdetails;
    CircularImageView hospitalgobackbtn;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_hospital_dashboard );
        hospitalmanagement=findViewById ( R.id.HospitalManagement);
        caregivers=findViewById (R.id.CareGivers);
        hospitalgobackbtn=findViewById (R.id.HospitalGobackbutn);
        progressDialog =new ProgressDialog(HospitalDashboard.this);
        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));
        hospitalmanagement.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {

                progressDialog.setMessage("Loading");
                progressDialog.show();
                Intent i=new Intent(HospitalDashboard.this,HospitalMapsActivity.class);
                startActivity (i);
            }
        } );
        caregivers.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                progressDialog.setMessage("Loading");
                progressDialog.show();
                Intent in=new Intent(HospitalDashboard.this,CareGivers.class);
                startActivity (in);
            }
        } );


        hospitalgobackbtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder ( HospitalDashboard.this );
                builder.setTitle ( "Sorry!" );
                builder.setMessage ( "Current Account To Be SignOut After That Only You Have Access To Another Account.......!" );
                builder.setNegativeButton ( "No",new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        Toast.makeText ( getApplication (),"no",Toast.LENGTH_LONG ).show ();
                    }
                } );
                builder.setPositiveButton ( "yes",new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {

                        Toast.makeText ( getApplicationContext (),"Thank You",Toast.LENGTH_LONG ).show ();
                    }
                } );
                builder.show ();

            }
        } );
    }
}
