package com.example.alert.User.TermsConditions;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alert.R;
import com.mikhaellopez.circularimageview.CircularImageView;



import java.util.List;

public class UserTermsListAdapter extends RecyclerView.Adapter< UserTermsListAdapter.ViewHolder> {

    List< UserTermsList > items;

    Context context;

    public UserTermsListAdapter(List<UserTermsList> items,Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public UserTermsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {




        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.termslistlayout,parent,false);

        return new UserTermsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserTermsListAdapter.ViewHolder holder,final int position) {

        final UserTermsList item = items.get(position);


        holder.privacytitle.setText (item.getTtitle ());

        holder.privacydescription.setText (item.getTdescription ());




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView privacyview;

        TextView privacytitle,privacydescription;

        CircularImageView gobackbtnterms;


        public ViewHolder(View itemView) {
            super(itemView);

            privacytitle = itemView.findViewById( R.id.TermsTitle);

            privacydescription=itemView.findViewById (R.id.TermsDescription);

            privacyview=itemView.findViewById (R.id.TermsCardView);


        }
    }
}




