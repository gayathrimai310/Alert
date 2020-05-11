package com.example.alert.User.Privacy;

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


public class UserPrivacyPolicy extends Fragment {
    List < UserPrivacyList > items;

    RecyclerView privacycard;

    RecyclerView.Adapter adapter;
    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate ( R.layout.fragment_user_privacy_policy,container,false );
        privacycard= v.findViewById ( R.id.PrivacyRecyclerView );

        privacycard.setLayoutManager(new LinearLayoutManager (getContext ()));

        items = new ArrayList < UserPrivacyList > ();

        adapter = new UserPrivacyListAdapter (items,getContext ());

        privacycard.setAdapter(adapter);

        loadinfo("https://gayathrimai.000webhostapp.com/User/privacy.php");
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

                        UserPrivacyList item = new UserPrivacyList ();

                        item.setPtitle (info.getString("Alert_PTIT"));

                        item.setPdescription ( info.getString("Alert_PDES"));


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
