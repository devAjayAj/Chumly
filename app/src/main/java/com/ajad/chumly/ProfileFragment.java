package com.ajad.chumly;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
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
    ImageView tabIcon;
    CardView cardView;

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
        //Inflate the layout for this fragment
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
        final SmartTabLayout viewPagerTab = getView().findViewById(R.id.viewpagertabprofiletab);
        viewPagerTab.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        FragmentPagerItems pages = FragmentPagerItems.with(getActivity())
                .add("Attendance", AttendanceFragment.class)
                .add("Subject Performance", SubPerformanceFragment.class)
                .add("Details", InstitutionDetailsFragment.class)
                .create();

        ViewPager viewPager = getView().findViewById(R.id.viewpagerprofiletab);

        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        final Resources res = getResources();

        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                View itemView = inflater.inflate(R.layout.layout_tabs_icons_profilefragment, container, false);
//                TextView text = (TextView) itemView.findViewById(R.id.custom_tab_text);
//                text.setText(adapter.getPageTitle(position));
                ImageView icon = (ImageView) itemView.findViewById(R.id.custom_tab_icon);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_account_circle_white_24dp));
                        break;
                    case 1:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_info_outline_white_24dp));
                        break;
                    case 2:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_question_answer_white_24dp));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }

                return itemView;
            }
        });
        final FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPagerTab.setViewPager(viewPager);

        viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = adapter.getCount();
                //Display all tabs cardView with transparent bg in profileFragment
                for (int i = 0; i < count; i++) {
                    cardView = viewPagerTab.getTabAt(i).findViewById(R.id.custom_cv_profile);
                    cardView.setCardBackgroundColor(Color.TRANSPARENT);
                }
                //To Display the selected tab CARD VIEW with dark bg
                cardView = viewPagerTab.getTabAt(position).findViewById(R.id.custom_cv_profile);
                cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.translucentPrimaryDarkForCard));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
