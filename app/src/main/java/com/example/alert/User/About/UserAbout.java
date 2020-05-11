package com.example.alert.User.About;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserAbout extends Fragment {
    List < UserAboutList > items;

    RecyclerView aboutcard;

    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate ( R.layout.fragment_user_about,container,false );
        aboutcard= v.findViewById ( R.id.AboutRecyclerView );

        aboutcard.setLayoutManager(new LinearLayoutManager (getContext ()));

        items = new ArrayList < UserAboutList > ();

        adapter = new UserAboutListAdapter (items,getContext ());

        aboutcard.setAdapter(adapter);

        loadinfo("https://gayathrimai.000webhostapp.com/User/aboutus.php");
        return v;
    }
    public void loadinfo(String url){


        StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject object = new JSONObject(response);

                    JSONArray information = object.getJSONArray("history");

                    items.clear();

                    for(int i=0;i<information.length();i++){

                        JSONObject info = information.getJSONObject(i);

                        UserAboutList item = new UserAboutList ();

                        item.setName (info.getString("Alert_TIT"));

                        item.setDescrip ( info.getString("Alert_DES"));


                        items.add(item);

                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {

                    Toast.makeText(getContext (), "Database Error", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getContext (), "Server Error", Toast.LENGTH_SHORT).show();

                error.printStackTrace();
            }
        }){
            @Override
            protected Map <String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap <> ();

                return map;
            }
        };

        Volley.newRequestQueue(getContext ()).add(request);
    }

}
