package com.ajad.chumly;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendence, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        CircleDisplay cd = getActivity().findViewById(R.id.circleDisplay);
        cd.setColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        cd.setAnimDuration(2000);
        cd.setValueWidthPercent(22f);
        cd.setTextSize(36f);
        cd.setDrawText(true);
        cd.setDrawInnerCircle(true);
        cd.setFormatDigits(1);
        cd.setTouchEnabled(false);
        cd.setUnit("%");
        cd.setStepSize(0.5f);
        cd.showValue(65f, 100f, true);
    }

}
