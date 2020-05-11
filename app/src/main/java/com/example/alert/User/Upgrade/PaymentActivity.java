package com.example.alert.User.Upgrade;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    EditText name,mobilenumber,date,amounttransfer,reference;
    Button transfer;



    private String TAG = "MainActivity";
    String payeeAddress = "roshanarchu97@okaxis";
    String payeeName = "Roshan";
    String transactionNote = "Test for Deeplinking";
    String amount = "1";
    String currencyUnit = "INR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_payment);
        setTitle("Money Transfer");
        name=(EditText) findViewById(R.id.Name);
        mobilenumber=(EditText) findViewById(R.id.Mobilenumber);
        date=(EditText)findViewById (R.id.SendingDate);
        amounttransfer=(EditText)findViewById (R.id.SendingAmount);
        reference=(EditText)findViewById (R.id.Reference);
        transfer=(Button) findViewById(R.id.Submit);


        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(MilkPayment.this, "Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("upi://pay?pa="+payeeAddress+"&pn="+payeeName+"&tn="+amounttransfer.getText().toString()+
                        "&am="+amounttransfer.getText().toString()+"&cu="+currencyUnit);
                Log.d(TAG, "onClick: uri: "+uri);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivityForResult(intent,1);
            }
        });

//        transfer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MilkPayment.this);
//
//                // Setting Dialog Title
//                alertDialog.setTitle("Confirm transfer amount...");
//
//                // Setting Dialog Message
//                alertDialog.setMessage("Are you sure you want Transfer the amount?");
//
//                // Setting Icon to Dialog
//
//
//                // Setting Positive "Yes" Button
//                alertDialog.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int which) {
//
//                        // Write your code here to invoke YES event
//                        transfer();
//                        //Toast.makeText(getApplicationContext(), "Money Transmitted Successfully", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                // Setting Negative "NO" Button
//                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Write your code here to invoke NO event
//                        Toast.makeText(getApplicationContext(), "Transaction cancelled", Toast.LENGTH_SHORT).show();
//                        dialog.cancel();
//                    }
//                });
//
//                // Showing Alert Message
//                alertDialog.show();
//
//            }
//        });
//
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);
        Log.d(TAG, "onActivityResult: resultCode: " + resultCode);
        //txnId=UPI20b6226edaef4c139ed7cc38710095a3&responseCode=00&ApprovalRefNo=null&Status=SUCCESS&txnRef=undefined
        //txnId=UPI608f070ee644467aa78d1ccf5c9ce39b&responseCode=ZM&ApprovalRefNo=null&Status=FAILURE&txnRef=undefined

        if (data != null) {
            Log.d(TAG, "onActivityResult: data: " + data.getStringExtra("response"));
            String res = data.getStringExtra("response");
            String search = "SUCCESS";
            if (res.toLowerCase().contains(search.toLowerCase())) {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }
}



