package com.example.alert.User.Privacy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alert.R;
import com.mikhaellopez.circularimageview.CircularImageView;



import java.util.List;

public class UserPrivacyListAdapter extends RecyclerView.Adapter< UserPrivacyListAdapter.ViewHolder> {

    List< UserPrivacyList > items;

    Context context;

    public UserPrivacyListAdapter(List<UserPrivacyList> items,Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public UserPrivacyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {




        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.privacylistlayout,parent,false);

        return new UserPrivacyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserPrivacyListAdapter.ViewHolder holder,final int position) {

        final UserPrivacyList item = items.get(position);


        holder.privacytitle.setText (item.getPtitle ());

        holder.privacydescription.setText (item.getPdescription ());



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView privacyview;

        TextView privacytitle,privacydescription;

        CircularImageView gobackbtnprivacy;


        public ViewHolder(View itemView) {
            super(itemView);

            privacytitle = itemView.findViewById( R.id.PrivacyTitle);

            privacydescription=itemView.findViewById (R.id.PrivacyDescription);

            privacyview=itemView.findViewById (R.id.PrivacyCardView);


        }
    }
}




