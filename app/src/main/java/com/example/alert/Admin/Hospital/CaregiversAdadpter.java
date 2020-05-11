package com.example.alert.Admin.Hospital;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alert.R;
import com.mikhaellopez.circularimageview.CircularImageView;



import java.util.List;

public class CaregiversAdadpter extends RecyclerView.Adapter<CaregiversAdadpter.ViewHolder> {

    List <Carelist> items;

    Context context;


    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView aname,adescription,aemailid,acontact;

        CircularImageView gobackbtncare;

        public ViewHolder(View itemView) {
            super(itemView);




            adescription=itemView.findViewById ( R.id.ADescription);

            aemailid=itemView.findViewById (R.id.AEmailID);





        }
    }

    public CaregiversAdadpter(List <Carelist> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.activity_caregivers_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Carelist item = items.get ( position );




        holder.adescription.setText (item.getAdescription  ());

        holder.aemailid.setText (item.getAemailid ());





    }
    @Override
    public int getItemCount() {
        return items.size();
    }

}


