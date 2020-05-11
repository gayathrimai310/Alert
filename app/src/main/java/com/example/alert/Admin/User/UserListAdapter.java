package com.example.alert.Admin.User;

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
import com.example.alert.Admin.AdminDashBoard;
import com.example.alert.R;
import com.mikhaellopez.circularimageview.CircularImageView;



import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    List <UserDetailsList> items;

    Context context;


    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView name;

        LinearLayout linear;


        public ViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById ( R.id.Uusername);

            linear=itemView.findViewById (R.id.Linearlayout);


        }
    }

    public UserListAdapter(List <UserDetailsList> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_details_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final UserDetailsList item = items.get ( position );

        holder.name.setText (item.getUname  ());

        holder.linear.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent=new Intent (context,UserView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra ("Name",item.getUname ());
                intent.putExtra ("Age",item.getUage ());
                intent.putExtra ( "Address",item.getUaddress ());
                intent.putExtra ( "Contact",item.getUcontact ());
                intent.putExtra ( "Alter",item.getUaltercontact ());
                intent.putExtra ( "Email",item.getUemail ());
                context.startActivity ( intent );
            }
        } );



    }
    @Override
    public int getItemCount() {
        return items.size();
    }

}



