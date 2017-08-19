package com.ajad.chumly;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {
    ImageView tabIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Inbox", InboxFragment.class)
                .add("Profile", ProfileFragment.class)
                .add("Group", GroupChatFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1, true);
        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        final LayoutInflater inflater = LayoutInflater.from(this);
        final Resources res = getResources();
        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                View itemView = inflater.inflate(R.layout.layout_tab_icon_main, container, false);
                TextView text = itemView.findViewById(R.id.custom_tab_text);
                text.setText(adapter.getPageTitle(position));
                ImageView icon = itemView.findViewById(R.id.custom_tab_icon);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_account_circle_white_24dp));
                        break;
                    case 1:
                        icon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_info_outline_white_24dp));
                        break;
                    case 2:
                        icon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_question_answer_white_24dp));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }

                return itemView;
            }
        });

        viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = adapter.getCount();
                //Display all tabs TEXT and ICON with blue tint
                for (int i = 0; i < count; i++) {
                    TextView view = viewPagerTab.getTabAt(i).findViewById(R.id.custom_tab_text);
                    view.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.tabTextTint));
                }
                tabIcon = viewPagerTab.getTabAt(0).findViewById(R.id.custom_tab_icon);
                tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_account_circle_tint_24dp));
                tabIcon = viewPagerTab.getTabAt(1).findViewById(R.id.custom_tab_icon);
                tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_info_outline_tint_24dp));
                tabIcon = viewPagerTab.getTabAt(2).findViewById(R.id.custom_tab_icon);
                tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_question_answer_tint_24dp));
                //To Display the selected tab TEXT and ICON in white
                TextView tabTextView = viewPagerTab.getTabAt(position).findViewById(R.id.custom_tab_text);
                tabIcon = viewPagerTab.getTabAt(position).findViewById(R.id.custom_tab_icon);
                tabTextView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.textColorPrimary));
                if(position == 0)
                    tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_account_circle_white_24dp));
                else if(position == 1)
                    tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_info_outline_white_24dp));
                else if(position == 2)
                    tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_question_answer_white_24dp));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPagerTab.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        viewPagerTab.setViewPager(viewPager);
    }

}
