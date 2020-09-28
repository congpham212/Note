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

    public ModeNotifPagerAdapter(FragmentManager fm, int modeAlarm, String timeString) {
        super(fm);
        switch (modeAlarm){
            case 0:
                tab1 = mode_notif_1.newInstance(timeString);
                break;
            case 1:
                tab2 = mode_notif_2.newInstance(timeString);
                break;
            case 2:
                tab3 = mode_notif_3.newInstance(timeString);
                break;
            default:
                break;
        }
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

        public static mode_notif_1 newInstance(String timeString) {

            Bundle args = new Bundle();
            args.putString("timeString", timeString);

            mode_notif_1 fragment = new mode_notif_1();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.chon_gio, container, false);
            timePickerMode_1 = view.findViewById(R.id.timePicker1);
            datePickerMode_1 = view.findViewById(R.id.datePicker);
            Bundle arg = getArguments();
            String timeString = "";
            if (arg != null) {
                timeString = arg.getString("timeString");
                int hour = Integer.parseInt(timeString.substring(0,2)),
                        minute = Integer.parseInt(timeString.substring(3,5)),
                        day = Integer.parseInt(timeString.substring(7,9)),
                        month = Integer.parseInt(timeString.substring(10,12)),
                        year = Integer.parseInt(timeString.substring(13,17));
                timePickerMode_1.setHour(hour);
                timePickerMode_1.setMinute(minute);
                datePickerMode_1.updateDate(year, month, day);
            }
            //Log.d("addReminderActivity", "tab1 " + timeString);

            return view;
        }

        public String getHour(){
            String minuteFisrt = timePickerMode_1.getMinute() / 10 == 0 ? "0" : "";
            String hourFisrt = timePickerMode_1.getHour() / 10 == 0 ? "0" : "";
            return hourFisrt + String.valueOf(timePickerMode_1.getHour()) + ":"
                    + minuteFisrt + String.valueOf(timePickerMode_1.getMinute());
        }

        public String getDay(){
            String dayFirst = datePickerMode_1.getDayOfMonth() / 10 == 0 ? "0" : "";
            String monthFisrt = (datePickerMode_1.getMonth() + 1)/ 10 == 0 ? "0" : "";
            return dayFirst + String.valueOf(datePickerMode_1.getDayOfMonth() + "/"
                    + monthFisrt + String.valueOf(datePickerMode_1.getMonth() + 1)) + "/"
                    + String.valueOf(datePickerMode_1.getYear());
        }
    }

    public static class mode_notif_2 extends Fragment {
        TimePicker timePicker;
        CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday;

        public static mode_notif_2 newInstance(String timeString) {

            Bundle args = new Bundle();
            args.putString("timeString", timeString);

            mode_notif_2 fragment = new mode_notif_2();
            fragment.setArguments(args);
            return fragment;
        }

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

            Bundle arg = getArguments();
            String timeString = "";
            if (arg != null) {
                timeString = arg.getString("timeString");
                int hour = Integer.parseInt(timeString.substring(0,2)),
                        minute = Integer.parseInt(timeString.substring(3,5));
                int index = 11; // thu dau tien duoc chon trong string
                do{
                    switch (timeString.charAt(index)){
                        case '2':
                            cbMonday.setChecked(true);
                            break;
                        case '3':
                            cbTuesday.setChecked(true);
                            break;
                        case '4':
                            cbWednesday.setChecked(true);
                            break;
                        case '5':
                            cbThursday.setChecked(true);
                            break;
                        case '6':
                            cbFriday.setChecked(true);
                            break;
                        case '7':
                            cbSaturday.setChecked(true);
                            break;
                        case 'C':
                            cbSunday.setChecked(true);
                            break;
                        default:
                            break;
                    }
                    index++;
                } while (index < timeString.length());
                timePicker.setHour(hour);
                timePicker.setMinute(minute);
            }
            Log.d("addReminderActivity", "tab2 " + timeString);

            //Log.d("addReminderActivity", "tab2");

            return view;
        }

        public String getHour(){
            String minuteFisrt = timePicker.getMinute() / 10 == 0 ? "0" : "";
            String hourFisrt = timePicker.getHour() / 10 == 0 ? "0" : "";
            return hourFisrt + String.valueOf(timePicker.getHour()) + ":"
                    + minuteFisrt + String.valueOf(timePicker.getMinute());
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
            return "Thứ " + day;
        }

    }
    public static class mode_notif_3 extends Fragment {
        View view;
        private TimePicker time_start;
        private EditText time_repeat;
        private NumberPicker time_type;

        public static mode_notif_3 newInstance(String timeString) {

            Bundle args = new Bundle();
            args.putString("timeString", timeString);

            mode_notif_3 fragment = new mode_notif_3();
            fragment.setArguments(args);
            return fragment;
        }

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
            time_start = view.findViewById(R.id.timePicker);

            Bundle arg = getArguments();
            String timeString = "";
            if (arg != null) {
                timeString = arg.getString("timeString");
                int hour = Integer.parseInt(timeString.substring(0,2)),
                        minute = Integer.parseInt(timeString.substring(3,5));
                time_start.setHour(hour);
                time_start.setMinute(minute);
                String timeRepeatString = timeString.substring(11, timeString.indexOf(" ", 11));
                String timeTypeString = timeString.substring(timeString.indexOf(" ", 11) + 1);
                int valueType = 0;
                switch (timeTypeString){
                    case "Phút":
                        valueType = 0;
                        break;
                    case "Giờ":
                        valueType = 1;
                        break;
                    case "Ngày":
                        valueType = 2;
                        break;
                    case "Tuần":
                        valueType = 3;
                        break;
                    case "Tháng":
                        valueType = 4;
                        break;
                    case "Năm":
                        valueType = 5;
                        break;
                    default:
                        break;
                }
                time_type.setValue(valueType);
                time_repeat.setText(timeRepeatString);
            }

            //Log.d("addReminderActivity", "tab3");

            return view;
        }

        public String getTimeStart(){
            String minuteFisrt = time_start.getMinute() / 10 == 0 ? "0" : "";
            String hourFisrt = time_start.getHour() / 10 == 0 ? "0" : "";
            return hourFisrt + String.valueOf(time_start.getHour()) + ":"
                    + minuteFisrt + String.valueOf(time_start.getMinute());
        }

        public String getTimeRepeat() {
            return String.valueOf(time_repeat.getText());
        }

        public String getTime_type() {
            return String.valueOf(time_type.getDisplayedValues()[time_type.getValue()]);
        }

    }
}
