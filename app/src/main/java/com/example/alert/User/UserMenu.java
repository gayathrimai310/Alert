package com.example.alert.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;

import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.alert.R;
import com.example.alert.User.About.UserAbout;
import com.example.alert.User.Account.Editprofile;
import com.example.alert.User.Account.UserAccount;
import com.example.alert.User.Alert.UserAlert;
import com.example.alert.User.Privacy.UserPrivacyPolicy;
import com.example.alert.User.Support.UserSupport;
import com.example.alert.User.TermsConditions.TermsAndCondition;
import com.example.alert.User.Upgrade.UserUpgrade;
import com.google.android.material.navigation.NavigationView;



public class UserMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    boolean url;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_menu );
        Toolbar toolbar = findViewById ( R.id.usertoolbar );
        setSupportActionBar ( toolbar );

        DrawerLayout drawer = findViewById ( R.id.drawer_layout );

        NavigationView navigationView = findViewById ( R.id.nav_view );
        TextView suri=(TextView)navigationView.getHeaderView(0).findViewById(R.id.te1);

        Intent w=getIntent();
        String e=w.getStringExtra("Email");
        suri.setText(e);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle ( this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ();
        navigationView.setNavigationItemSelectedListener ( this );
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, new UserAbout()).commit();



    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = findViewById ( R.id.drawer_layout );
        if (drawer.isDrawerOpen ( GravityCompat.START )) {
            drawer.closeDrawer ( GravityCompat.START );
        } else {
            super.onBackPressed ();
        }
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate ( R.menu.user_menu,menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            Intent in=new Intent (UserMenu.this,FeedBackList.class);
            startActivity (in);
            return true;
        }
        if (id == R.id.action_editpro) {
            Intent i=new Intent (UserMenu.this, Editprofile.class);
            startActivity (i);
            return true;
        }
        if (id == R.id.action_signout) {
            Intent innn=new Intent (UserMenu.this,UserSignOut.class);
            startActivity (innn);
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId ();
        FragmentManager fragmentManager=getSupportFragmentManager();
        if (id == R.id.nav_upgrade) {
            setTitle("Upgrade");
            fragmentManager.beginTransaction().replace(R.id.frame,new UserUpgrade()).commit();
        } else if (id == R.id.nav_refer) {
            setTitle("Invite Friends");
            Intent share = new Intent (Intent.ACTION_SEND);
            Intent chooser = Intent.createChooser(share, "Plummet recommendation");
            share.setType("I have improved my safety best praticies by using Plummet application and plummet services." +
                    "You Can learn about more plumet at https://www.fallsafetyapp.com");
            share.putExtra(Intent.EXTRA_TEXT, url);
            startActivity(chooser);
        } else if (id == R.id.nav_support) {
            setTitle("Support Team");
            fragmentManager.beginTransaction().replace(R.id.frame, new UserSupport()).commit();
        }
            else if (id == R.id.nav_acc_us) {
                setTitle("Account");
                fragmentManager.beginTransaction().replace(R.id.frame,new UserAccount()).commit();
        } else if (id==R.id.nav_privacy){
            setTitle("Privacy");
            fragmentManager.beginTransaction().replace(R.id.frame,new UserPrivacyPolicy()).commit();
        } else if(id==R.id.nav_terms){
            setTitle("Terms And Conditions");
            fragmentManager.beginTransaction().replace(R.id.frame,new TermsAndCondition()).commit();
        } else if(id==R.id.nav_about_us){
            setTitle("About Us");
            fragmentManager.beginTransaction().replace(R.id.frame,new UserAbout()).commit();
        }else if(id==R.id.nav_alert){
            setTitle("Alert");
            fragmentManager.beginTransaction().replace(R.id.frame,new UserAlert ()).commit();
        }

        DrawerLayout drawer = findViewById ( R.id.drawer_layout );
        drawer.closeDrawer ( GravityCompat.START );
        return true;
    }
}

