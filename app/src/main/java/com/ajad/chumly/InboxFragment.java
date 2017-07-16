package com.ajad.chumly;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;


public class InboxFragment extends Fragment {
    TextView message;
    public InboxFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = (TextView)getActivity().findViewById(R.id.custom_tab_text);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Set tab TEXT and ICON to blue tint
        SmartTabLayout viewPagerTab = getActivity().findViewById(R.id.viewpagertab);
        TextView view = (TextView) viewPagerTab.getTabAt(1).findViewById(R.id.custom_tab_text);
        view.setTextColor(ContextCompat.getColor(getActivity(), R.color.tabTextTint));
        ImageView tabIcon = viewPagerTab.getTabAt(1).findViewById(R.id.custom_tab_icon);
        tabIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_info_outline_tint_24dp));
        return inflater.inflate(R.layout.fragment_message, container, false);
    }
}
