package com.example.alert.User.Alert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alert.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class EmergencyNotification extends AppCompatActivity {
    public static String firstN, secondN, thirdN;
    public int flag;
    public EditText edT1;
    public EditText edT2;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_emergency_notification);


        FloatingActionButton fab = ( FloatingActionButton ) findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                //hjh
                Intent i = new Intent ();
                i.setComponent (new ComponentName ("com.android.contacts","com.android.contacts.DialtactsContactsEntryActivity"));
                i.setAction ("android.intent.action.MAIN");
                i.addCategory ("android.intent.category.LAUNCHER");
                i.addCategory ("android.intent.category.DEFAULT");
                Toast.makeText (getApplicationContext (),"Copy the particular contact's number",Toast.LENGTH_LONG).show ();
                startActivity (i);
            }
        });
        final Button serviceB = ( Button ) findViewById (R.id.serviceB);
        flag = 1;
        serviceB.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                if (flag == 1) {
                    Toast.makeText (EmergencyNotification.this,"ACTIVATED!",Toast.LENGTH_LONG).show ();
                    startService (new Intent (getApplicationContext (),ShakeService.class));
                    flag = 0;
                } else {
                    Toast.makeText (EmergencyNotification.this,"DEACTIVATED!",Toast.LENGTH_LONG).show ();
                    stopService (new Intent (getApplicationContext (),ShakeService.class));
                    flag = 1;
                }

            }
        });
        Button doneB = ( Button ) findViewById (R.id.doneButton);
        if (doneB != null) {
            doneB.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    edT1 = ( EditText ) findViewById (R.id.firstNumber);
                    edT2 = ( EditText ) findViewById (R.id.secondNumber);

                    if (edT1.getText () != null) firstN = edT1.getText ().toString ();
                    if (edT2.getText () != null) secondN = edT2.getText ().toString ();
                    try {
                        @SuppressLint("SdCardPath") File myFile = new File ("/sdcard/.emergencyNumbers.txt");
                        myFile.createNewFile ();
                        FileOutputStream fOut = new FileOutputStream (myFile);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter (fOut);
                        myOutWriter.append (firstN);
                        myOutWriter.append ("\n");
                        myOutWriter.append (secondN);
                        myOutWriter.close ();
                        fOut.close ();
                        Toast.makeText (getApplicationContext (),"The emergency contact numbers have been saved.",Toast.LENGTH_SHORT).show ();
                    } catch (Exception e) {
                        Toast.makeText (getApplicationContext (),e.getMessage (),Toast.LENGTH_SHORT).show ();
                    }
                    Log.d (getPackageName (),"Done! button pressed.");
                }
            });
        }
    }
}

