package com.example.alert.User.About;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alert.R;


import java.util.List;

public class UserAboutListAdapter extends RecyclerView.Adapter< UserAboutListAdapter.ViewHolder> {

    List< UserAboutList > items;

    Context context;

    public UserAboutListAdapter(List<UserAboutList> items,Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAboutListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {




        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.aboutlistlayout,parent,false);

        return new UserAboutListAdapter .ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAboutListAdapter.ViewHolder holder,final int position) {

        final UserAboutList item = items.get(position);


        holder.userabout.setText (item.getDescrip ());




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView useraboutcollapse,useraboutexpand;

        TextView userabout;


        public ViewHolder(View itemView) {
            super(itemView);

            userabout=itemView.findViewById (R.id.Description);

        }
    }
}



