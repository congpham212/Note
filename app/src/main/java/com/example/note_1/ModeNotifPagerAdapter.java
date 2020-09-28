package com.example.note_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;


import android.widget.NumberPicker;
import android.widget.TimePicker;

public class ModeNotifPagerAdapter extends FragmentPagerAdapter {
    private int numOfTab = 3;
    mode_notif_1 tab1 = new mode_notif_1();
    mode_notif_2 tab2 = new mode_notif_2();
    mode_notif_3 tab3 = new mode_notif_3();
    public ModeNotifPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return tab1;
            case 1:
                return tab2;
            case 2:
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
            //Log.d("addReminderActivity", "tab1");

            return view;
        }

        public String getHour(){
            String minuteFisrt = timePickerMode_1.getMinute() / 10 == 0 ? "0" : "";
            String hourFisrt = timePickerMode_1.getHour() / 10 == 0 ? "0" : "";
            return hourFisrt + String.valueOf(timePickerMode_1.getHour()) + ":"
                    + minuteFisrt + String.valueOf(timePickerMode_1.getMinute());
        }

        public void setHour(int hour){
            timePickerMode_1.setHour(hour);
        }

        public String getDay(){
            return String.valueOf(datePickerMode_1.getDayOfMonth() + "/"
                    + String.valueOf(datePickerMode_1.getMonth() + 1)) + "/"
                    + String.valueOf(datePickerMode_1.getYear());
        }
    }

    public static class mode_notif_2 extends Fragment {
        TimePicker timePicker;
        CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.chon_gio_va_thu, container, false);
            timePicker = view.findViewById(R.id.timePicker1);
            cbMonday = view.findViewById(R.id.cb_monday);
            cbTuesday = view.findViewById(R.id.cb_tuesday);
            cbWednesday = view.findViewById(R.id.cb_wednesday);
            cbThursday = view.findViewById(R.id.cb_thursday);
            cbFriday = view.findViewById(R.id.cb_friday);
            cbSaturday = view.findViewById(R.id.cb_saturday);
            cbSunday = view.findViewById(R.id.cb_sunday);
            //Log.d("addReminderActivity", "tab2");

            return view;
        }

        public String getHour(){
            String minuteFisrt = timePicker.getMinute() / 10 == 0 ? "0" : "";
            String hourFisrt = timePicker.getHour() / 10 == 0 ? "0" : "";
            return hourFisrt + String.valueOf(timePicker.getHour()) + ":"
                    + minuteFisrt + String.valueOf(timePicker.getMinute());
        }

        public void setHour(int hour){
            timePicker.setHour(hour);
        }

        public String getDay(){
            String day = cbMonday.isChecked()? "2/" : "";
            day += cbTuesday.isChecked()? "3/" : "";
            day += cbWednesday.isChecked()? "4/" : "";
            day += cbThursday.isChecked()? "5/" : "";
            day += cbFriday.isChecked()? "6/" : "";
            day += cbSaturday.isChecked()? "7/" : "";
            day += cbSunday.isChecked()? "CN/" : "";
            day = day.substring(0, day.length() > 1 ? day.length() - 1 : 0);
            return day;
        }

    }
    public static class mode_notif_3 extends Fragment {
        View view;
        public EditText time_repeat;
        public NumberPicker time_type;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.chon_thoi_gian_lap_lai, container, false);
            String listLoopMode[] = {"Phút", "Giờ", "Ngày", "Tuần", "Tháng", "Năm"};
            time_type = view.findViewById(R.id.number_Picker);
            time_type.setValue(0);
            time_type.setWrapSelectorWheel(false);
            time_type.setMaxValue(listLoopMode.length-1);
            time_type.setMinValue(0);
            time_type.setDisplayedValues(listLoopMode);

            time_repeat = view.findViewById(R.id.et_thoi_gian_loop);
            time_type = view.findViewById(R.id.number_Picker);

            this.time_repeat = view.findViewById(R.id.et_thoi_gian_loop);
            this.time_type = view.findViewById(R.id.number_Picker);
            //Log.d("addReminderActivity", "tab3");

            return view;
        }

        public String getTimeRepeat() {
            return String.valueOf(time_repeat.getText());
        }

        public void setTime_repeat(EditText time_repeat) {
            this.time_repeat = time_repeat;
        }

        public String getTime_type() {
            return String.valueOf(time_type.getDisplayedValues()[time_type.getValue()]);
        }

        public void setTime_type(NumberPicker time_type) {
            this.time_type = time_type;
        }
    }
}
