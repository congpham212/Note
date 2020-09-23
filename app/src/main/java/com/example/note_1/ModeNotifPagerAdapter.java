package com.example.note_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.EditText;


import android.widget.NumberPicker;
import android.widget.TimePicker;

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
        View view;
        TimePicker timePickerMode_1;
        DatePicker datePickerMode_1;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.chon_gio, container, false);
            timePickerMode_1 = view.findViewById(R.id.timePicker1);
            datePickerMode_1 = view.findViewById(R.id.datePicker);
            // Inflate the layout for this fragment
            return view;
        }
    }

    public static class mode_notif_2 extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.chon_gio_va_thu, container, false);
            // Inflate the layout for this fragment
            return view;
        }
    }
    public static class mode_notif_3 extends Fragment {
        View view;
        public EditText time_repeat;
        public NumberPicker time_type;

        public EditText getTime_repeat() {
            return time_repeat;
        }

        public void setTime_repeat(EditText time_repeat) {
            this.time_repeat = time_repeat;
        }

        public NumberPicker getTime_type() {
            return time_type;
        }

        public void setTime_type(NumberPicker time_type) {
            this.time_type = time_type;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.chon_thoi_gian_lap_lai, container, false);
            String listLoopMode[] = {"Phút", "Giờ", "Ngày", "Tuần", "Tháng", "Năm"};
            NumberPicker numberPicker = view.findViewById(R.id.number_Picker);
            numberPicker.setValue(0);
            numberPicker.setWrapSelectorWheel(false);
            numberPicker.setMaxValue(listLoopMode.length-1);
            numberPicker.setMinValue(0);
            numberPicker.setDisplayedValues(listLoopMode);

            anhXa();

//            this.time_repeat = view.findViewById(R.id.et_thoi_gian_loop);
//            this.time_type = view.findViewById(R.id.number_Picker);
//            Spinner spLoopMode = (Spinner) view.findViewById(R.id.spLoopMode);

//            ArrayAdapter adapterSensorName = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, listLoopMode);
//            adapterSensorName.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//            spLoopMode.setAdapter(adapterSensorName);
            return view;
        }

        public void anhXa() {
            time_repeat = view.findViewById(R.id.et_thoi_gian_loop);
            time_type = view.findViewById(R.id.number_Picker);
        }
    }
}
