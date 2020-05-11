package com.example.alert.User.Support;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.alert.R;
import com.example.alert.User.UserMenu;
import com.mikhaellopez.circularimageview.CircularImageView;



import java.util.Objects;


public class UserSupport extends Fragment {
    CardView emailus,contactus;
    ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate ( R.layout.fragment_user_support,container,false );
        emailus=v.findViewById (R.id.SupportEmail);
        contactus=v.findViewById (R.id.SupportContact);
        progressDialog=new ProgressDialog (getContext ());

        progressDialog.setIndeterminate(true);
        //here is the trick:
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.anim1, null));

        emailus.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                progressDialog.setTitle ("Please Wait");
                progressDialog.setMessage ("Loading....");
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "support@plummet.com", null));
                startActivity(Intent.createChooser(emailIntent, null));

            }
        } );
        contactus.setOnClickListener ( new View.OnClickListener () {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick (View v) {
                progressDialog.setTitle ("Please Wait");
                progressDialog.setMessage ("Loading....");
                AlertDialog.Builder mBuilder = new AlertDialog.Builder( Objects.requireNonNull ( getContext () ) );
                mBuilder.setTitle("Are you sure to make a call to our support team?");
                mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String num="8838587063";
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(num.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });
            }
        } );
        return  v;
    }
}
