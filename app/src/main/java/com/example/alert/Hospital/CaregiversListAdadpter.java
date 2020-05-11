package com.example.alert.Hospital;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.alert.R;

import java.util.List;

public class CaregiversListAdadpter extends RecyclerView.Adapter< CaregiversListAdadpter.ViewHolder> {

    List < CareView > items;

    Context context;


    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView ename,eaddress,econtact,eemail;




        public ViewHolder(View itemView) {
            super(itemView);



            ename=itemView.findViewById (R.id.name);
            eaddress=itemView.findViewById(R.id.address);
            econtact=itemView.findViewById(R.id.contact);
            eemail=itemView.findViewById(R.id.emailid);




        }
    }

    public CaregiversListAdadpter (List < CareView > items,Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_care_givers_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,int position) {

        final CareView item = items.get ( position );


        holder.ename.setText (item.getName ());

        holder.eaddress.setText (item.getAddress ());

        holder.econtact.setText (item.getContact ());

        holder.eemail.setText (item.getEmail ());





    }
    @Override
    public int getItemCount() {
        return items.size();
    }

}


