package com.example.alert.Admin.Hospital;

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

public class CareGiversList extends AppCompatActivity {


    List <Carelist> items;

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_care_givers_list );

        recyclerView =findViewById(R.id.CareRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager (getApplicationContext()));

        items = new ArrayList <> ();

        adapter = new CaregiversAdadpter (items,getApplicationContext());

        recyclerView.setAdapter(adapter);

        getUsers ();
    }


    public void getUsers(){


        StringRequest request = new StringRequest( Request.Method.POST, "https://gayathrimai.000webhostapp.com/Admin/caregiverslist.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText ( CareGiversList.this, response, Toast.LENGTH_SHORT ).show ();


                try {

                    JSONObject object = new JSONObject(response);

                    JSONArray array = object.getJSONArray("history");

                    for(int i=0;i<array.length();i++){

                        JSONObject object1 = array.getJSONObject(i);

                        Carelist item = new Carelist ();



                        item.setAdescription (object1.getString("Alert_FD_BK"));

                        item.setAemailid (object1.getString("Alert_REG_ID"));




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

