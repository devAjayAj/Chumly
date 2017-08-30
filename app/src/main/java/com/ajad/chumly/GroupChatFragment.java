package com.ajad.chumly;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;


public class GroupChatFragment extends Fragment {

    public GroupChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View v = inflater.inflate(R.layout.fragment_results, container, false);
        SmartTabLayout viewPagerTab = getActivity().findViewById(R.id.viewpagertab);
        TextView view = (TextView) viewPagerTab.getTabAt(2).findViewById(R.id.custom_tab_text);
        view.setTextColor(ContextCompat.getColor(getActivity(), R.color.tabTextTint));
        ImageView tabIcon = viewPagerTab.getTabAt(2).findViewById(R.id.custom_tab_icon);
        tabIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_question_answer_tint_24dp));
        recyclerView = (RecyclerView) v.findViewById(R.id.ChatRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(llm);
//        initializeData();
        initializeAdapter();
        return v;
    }

    RecyclerView recyclerView;
    SQLiteDatabase db;
    String dbQueryCategory = "";
    public List<GroupChatSetterClass> GroupChatSetterClassList;

    public void initializeData(){
        GroupChatSetterClassList = new ArrayList<>();
        String pname = "", psprice = "", ppprice = "", pcatgry = "";
        Intent intent = getActivity().getIntent();
        String ctgry = "a";
        System.out.println(ctgry);
        Cursor crsr = db.rawQuery("SELECT * FROM productList WHERE category='"+ ctgry + "' order by productName ASC", null);
        crsr.moveToFirst();
        for(int i = 0; i < crsr.getCount(); i++){
            pname = crsr.getString(0);
            psprice = crsr.getString(1);
            ppprice = crsr.getString(2);
            pcatgry = crsr.getString(4);
            GroupChatSetterClassList.add(new GroupChatSetterClass(pname, psprice, ppprice));//constructor to setter class
            crsr.moveToNext();
        }
    }

    public void initializeAdapter(){
        GroupChatRecyclerViewAdapter padapter = new GroupChatRecyclerViewAdapter(GroupChatSetterClassList);
        recyclerView.setAdapter(padapter);
    }
}
