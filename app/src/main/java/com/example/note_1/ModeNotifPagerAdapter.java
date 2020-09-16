package com.example.note_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ModeNotifPagerAdapter extends FragmentPagerAdapter {
    private int numOfTab = 3;
    public ModeNotifPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                mode_notif_1 tab1 = new mode_notif_1();
                return tab1;
            case 1:
                mode_notif_2 tab2 = new mode_notif_2();
                return tab2;
            case 2:
                mode_notif_3 tab3 = new mode_notif_3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTab;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Một lần";
            case 1:
                return "Hằng ngày";
            case 2:
                return "Chu kỳ";
        }
        return null;
    }
    public static class mode_notif_1 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.chon_gio, container, false);
        }
    }

    public static class mode_notif_2 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.chon_gio_va_thu, container, false);
        }
    }
    public static class mode_notif_3 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.chon_thoi_gian_lap_lai, container, false);
        }
    }
}
