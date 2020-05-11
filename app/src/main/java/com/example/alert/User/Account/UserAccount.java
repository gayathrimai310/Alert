package com.example.alert.User.Account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alert.R;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class UserAccount extends Fragment {
    TextView name,age,address,bld,email,phone,aphone;
    String m;
    ImageView edit;
    Button editprofile;

    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate ( R.layout.fragment_user_account,container,false );
        name=v.findViewById(R.id.UName);
        age=v.findViewById(R.id.UAge);
        address=v.findViewById(R.id.UAddress);
        editprofile=v.findViewById(R.id.edit);
        bld=v.findViewById(R.id.UBlood);
        email=v.findViewById(R.id.UEmailId);
        phone=v.findViewById(R.id.UContact);
        aphone=v.findViewById(R.id.UAlterContact);
        edit=v.findViewById(R.id.EditBtn);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("details",MODE_PRIVATE );
        final String emailid = sharedPreferences.getString("Email", "");
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Editprofile.class);
                startActivity(intent);
            }
        });
        StringRequest sr = new StringRequest ( Request.Method.POST,"https://gayathrimai.000webhostapp.com/User/editprofile.php",new Response.Listener < String > () {
            @Override
            public void onResponse (String response) {

                try {
                    JSONObject obj = new JSONObject ( response );
                    JSONArray array = obj.getJSONArray ( "history" );
                    for (int i = 0; i < array.length (); i++) {
                        JSONObject o = array.getJSONObject ( i );
                        name.setText ( o.getString ( "Alert_NAME" ) );
                        age.setText ( o.getString ( "Alert_AGE" ) );
                        address.setText ( o.getString ( "Alert_ADDRESS" ) );
                        bld.setText ( o.getString ( "Alert_BLD" ) );
                        email.setText ( o.getString ( "Alert_EMAILID" ) );
                        phone.setText ( o.getString ( "Alert_PH_NUM" ) );
                        aphone.setText ( o.getString ( "Alert_ALTER_PH_NUM" ) );


                    }
                } catch (Exception e) {

                }
            }
        },new Response.ErrorListener () {
            @Override
            public void onErrorResponse (VolleyError error) {
                Toast.makeText ( getContext (),error.toString (),Toast.LENGTH_SHORT ).show ();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> n = new HashMap<String, String>();
                n.put("em_id", emailid);


                return (n);

            }

        };
        RequestQueue r = Volley.newRequestQueue ( getContext () );
        r.add ( sr );
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getContext(), Editprofile.class);
                a.putExtra("Name",name.getText().toString());
                a.putExtra("Age",age.getText().toString());
                a.putExtra("Address",address.getText().toString());
                a.putExtra("Blood",bld.getText().toString());
                a.putExtra("Email",email.getText().toString());
                a.putExtra("Phone",phone.getText().toString());
                a.putExtra("AlterPhone",aphone.getText().toString());
                startActivity(a);
            }
        });
        return v;
    }

}
