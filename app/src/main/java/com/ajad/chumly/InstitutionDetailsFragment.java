package com.ajad.chumly;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InstitutionDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InstitutionDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstitutionDetailsFragment extends Fragment {

    CardView cardView;
    public InstitutionDetailsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        SmartTabLayout viewPagerTab = getActivity().findViewById(R.id.viewpagertabprofiletab);
        cardView = viewPagerTab.getTabAt(2).findViewById(R.id.custom_cv_profile);
        cardView.setCardBackgroundColor(Color.TRANSPARENT);
        return inflater.inflate(R.layout.fragment_institution_details, container, false);

    }

}
