package com.cvsr.photobank.photobank;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomePage extends Fragment {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.ptusha,R.drawable.nirupama,R.drawable.mcmarrycom,R.drawable.kiranbedi,R.drawable.kalpana};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    Timer swipeTimer = new Timer();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_home_page, container, false);
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(new HomePageAdapter(getActivity(),XMENArray));
        CircleIndicator indicator = (CircleIndicator) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

    return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        swipeTimer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        swipeTimer.cancel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        swipeTimer.cancel();
    }
}