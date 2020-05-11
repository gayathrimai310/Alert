package com.example.alert.User.Upgrade;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.alert.R;
import com.example.alert.User.Privacy.UserPrivacyPolicy;
import com.example.alert.User.TermsConditions.TermsAndCondition;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;



public class UserUpgrade extends Fragment {
    SliderView sliderView;
    TextView uterms,uprivacy;
    Button upgrade;
    @Override
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate ( R.layout.fragment_user_upgrade,container,false );
        sliderView = v.findViewById( R.id.imageSlider);
        uterms=v.findViewById (R.id.UTerms);
        uprivacy=v.findViewById (R.id.UPrivacy);
        upgrade=v.findViewById (R.id.UpgradeBtn);

        final SliderAdapterExample adapter = new SliderAdapterExample(getContext());
        adapter.setCount(5);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation( IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation( SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor( Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
        upgrade.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent in=new Intent (getActivity (),PaymentActivity.class );
                startActivity (in);
            }
        } );
        uprivacy.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent i=new Intent (getActivity (), UserPrivacyPolicy.class );
                startActivity (i);
            }
        } );
        uterms.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent i=new Intent (getActivity (), TermsAndCondition.class );
                startActivity (i);
            }
        } );

        return  v;
    }

}
