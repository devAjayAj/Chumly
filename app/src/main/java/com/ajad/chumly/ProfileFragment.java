package com.ajad.chumly;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class ProfileFragment extends Fragment {

    Boolean isFragmentLoadedOnce = false;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserVisibleHint(false);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isFragmentLoadedOnce ) {
            isFragmentLoadedOnce = true;
            // Code executes ONLY THE FIRST TIME fragment is viewed.
        }
        //Code executes EVERY TIME user views the fragment
        if(isVisibleToUser && isResumed()){
            onResume();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Set tab TEXT and ICON to white as this is the default tab
        SmartTabLayout viewPagerTab = getActivity().findViewById(R.id.viewpagertab);
        TextView view = (TextView) viewPagerTab.getTabAt(0).findViewById(R.id.custom_tab_text);
        view.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorPrimary));
        ImageView tabIcon = viewPagerTab.getTabAt(0).findViewById(R.id.custom_tab_icon);
        tabIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_account_circle_white_24dp));
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }


    @Override
    public void onResume()
    {
        super.onResume();
        if (!getUserVisibleHint())
        {
            return;
        }
        //INSERT CUSTOM CODE HERE
        //This code will be executed only when the fragment becomes visible to user
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        SmartTabLayout viewPagerTab = getView().findViewById(R.id.viewpagertabprofiletab);
        viewPagerTab.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        FragmentPagerItems pages = FragmentPagerItems.with(getActivity())
                .add("Profile", AttendanceFragment.class)
                .add("Inbox", AttendanceFragment.class)
                .add("GroupChat", AttendanceFragment.class)
                .create();

        ViewPager viewPager = getView().findViewById(R.id.viewpagerprofiletab);

        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        final Resources res = getResources();

        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                View itemView = inflater.inflate(R.layout.custom_tabs_icons_profilefragment, container, false);
                TextView text = (TextView) itemView.findViewById(R.id.custom_tab_text);
                text.setText(adapter.getPageTitle(position));
                ImageView icon = (ImageView) itemView.findViewById(R.id.custom_tab_icon);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(res.getDrawable(R.mipmap.ic_launcher_round));
                        break;
                    case 1:
                        icon.setImageDrawable(res.getDrawable(R.mipmap.ic_launcher_round));
                        break;
                    case 2:
                        icon.setImageDrawable(res.getDrawable(R.mipmap.ic_launcher_round));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }

                return itemView;
            }
        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPagerTab.setViewPager(viewPager);
    }
}
