package com.ajad.chumly;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubPerformanceFragment extends Fragment {

    CardView cardView;

    public SubPerformanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SmartTabLayout viewPagerTab = getActivity().findViewById(R.id.viewpagertabprofiletab);
        cardView = viewPagerTab.getTabAt(1).findViewById(R.id.custom_cv_profile);
        cardView.setCardBackgroundColor(Color.TRANSPARENT);
        return inflater.inflate(R.layout.fragment_sub_performance, container, false);
    }

}
