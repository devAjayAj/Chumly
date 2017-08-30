package com.ajad.chumly;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;


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
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        SmartTabLayout viewPagerTab = getActivity().findViewById(R.id.viewpagertab);
        TextView view = (TextView) viewPagerTab.getTabAt(1).findViewById(R.id.custom_tab_text);
        view.setTextColor(ContextCompat.getColor(getActivity(), R.color.tabTextTint));
        ImageView tabIcon = viewPagerTab.getTabAt(1).findViewById(R.id.custom_tab_icon);
        tabIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_info_outline_tint_24dp));
        recyclerView = (RecyclerView) v.findViewById(R.id.InboxRecyclerView);
//        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(llm);
//        initializeData();
        initializeAdapter();
        return v;
    }

    RecyclerView recyclerView;
    SQLiteDatabase db;
    String dbQueryCategory = "";
    public List<InboxSetterClass> InboxSetterClassList;

    public void initializeData(){
        InboxSetterClassList = new ArrayList<>();
        String pname = "", psprice = "", ppprice = "", pcatgry = "";
        Intent intent = getActivity().getIntent();
        String ctgry = "n";
        System.out.println(ctgry);
        Cursor crsr = db.rawQuery("SELECT * FROM productList WHERE category='"+ ctgry + "' order by productName ASC", null);
        crsr.moveToFirst();
        for(int i = 0; i < crsr.getCount(); i++){
            pname = crsr.getString(0);
            psprice = crsr.getString(1);
            ppprice = crsr.getString(2);
            pcatgry = crsr.getString(4);
            InboxSetterClassList.add(new InboxSetterClass(pname, psprice, ppprice, pcatgry));//constructor to setter class
            crsr.moveToNext();
        }
    }

    public void initializeAdapter(){
        InboxRecyclerViewAdapter padapter = new InboxRecyclerViewAdapter(InboxSetterClassList);
        recyclerView.setAdapter(padapter);
    }
}
