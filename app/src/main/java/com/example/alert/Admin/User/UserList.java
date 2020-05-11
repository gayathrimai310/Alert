package com.example.alert.Admin.User;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alert.R;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList extends AppCompatActivity {


    List <UserDetailsList> items;

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_list );

        recyclerView =findViewById( R.id.RecyclerViewUser);

        recyclerView.setLayoutManager(new LinearLayoutManager (getApplicationContext()));

        items = new ArrayList <> ();

        adapter = new UserListAdapter (items,getApplicationContext());

        recyclerView.setAdapter(adapter);

        getUsers ();
    }


    public void getUsers(){


        StringRequest request = new StringRequest( Request.Method.POST, "https://gayathrimai.000webhostapp.com/Admin/userlist.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText ( UserList.this, response, Toast.LENGTH_SHORT ).show ();


                try {

                    JSONObject object = new JSONObject(response);

                    JSONArray array = object.getJSONArray("history");

                    for(int i=0;i<array.length();i++){

                        JSONObject object1 = array.getJSONObject(i);

                        UserDetailsList item = new UserDetailsList ();

                        item.setUname (object1.getString("Alert_NAME"));

                        item.setUage (object1.getString("Alert_AGE"));

                        item.setUaddress (object1.getString ("Alert_ADDRESS"));

                        item.setUcontact (object1.getString("Alert_PH_NUM"));

                        item.setUaltercontact (object1.getString("Alert_ALTER_PH_NUM"));

                        item.setUemail (object1.getString("Alert_EMAILID"));


                        items.add(item);

                        adapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext (), "Database Error", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext (), "Connection Error", Toast.LENGTH_SHORT).show();

                error.printStackTrace();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();

                return map;
            }
        };

        Volley.newRequestQueue(getApplicationContext ()).add(request);
    }
}

