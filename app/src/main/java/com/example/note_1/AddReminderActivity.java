package com.example.note_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class AddReminderActivity extends AppCompatActivity implements View.OnClickListener {
    
    private EditText et_title;
    private EditText et_content;

    private Context context = this;
    
    private boolean isModeAdd;
    private int idNote;

    EditText et;

    ModeNotifPagerAdapter pgAdapter;
    TabLayout tlModeNotif;
    ViewPager vpModeNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        anhXa();

        tlModeNotif = findViewById(R.id.tl_mode_notif);
        vpModeNotif = findViewById(R.id.vp_mode_notif);

        Intent intent = getIntent();
        idNote = intent.getExtras().getInt("idNote");
        if (idNote != -1)
        {
            isModeAdd = false;
            MyDatabaseHelper db = new MyDatabaseHelper(this);
            Note note = db.getNote(idNote);
            et_title.setText(note.getTitle());
            et_content.setText(note.getContent());

            int modeAlarm = note.getModeAlarm();
            pgAdapter = new ModeNotifPagerAdapter(getSupportFragmentManager(), modeAlarm, note.getTime());
            vpModeNotif.setAdapter(pgAdapter);
            vpModeNotif.setCurrentItem(modeAlarm);
        }
        else {
            isModeAdd = true;
            pgAdapter = new ModeNotifPagerAdapter(getSupportFragmentManager());
            vpModeNotif.setAdapter(pgAdapter);
        }
        tlModeNotif.setupWithViewPager(vpModeNotif);

        vpModeNotif.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                /*if (pgAdapter.tab1 == null)
                    Log.d("addReminderActivity", "tab1");
                if (pgAdapter.tab2 == null)
                    Log.d("addReminderActivity", "tab2");
                if (pgAdapter.tab3 == null)
                    Log.d("addReminderActivity", "tab3");*/

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

//        et.setText("10");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_actionbar_add_reminder, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void anhXa() {
        et_title = (EditText) findViewById(R.id.et_tile_main2);
        et_content = (EditText) findViewById(R.id.et_content_main2);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            case R.id.save_reminder:
                String titleNote = String.valueOf(et_title.getText());
                String contentNote = String.valueOf(et_content.getText());
                String hour, day, timeRepeat, type, timeString = "";

                if (titleNote.equals("") && contentNote.equals("")) {
                    finish();
                    return true;
                }

                // Lay thoi gian thong bao de luu vao database
                int position = vpModeNotif.getCurrentItem();
                if(position == 0){
                    hour = pgAdapter.tab1.getHour();
                    day = pgAdapter.tab1.getDay();
                    timeString = hour + ", " + day;
                } else
                if(position == 1){
                    hour = pgAdapter.tab2.getHour();
                    day = pgAdapter.tab2.getDay();
                    timeString = hour + ", " + day;
                } else
                if(position == 2){
                    hour = pgAdapter.tab3.getTimeStart();
                    timeRepeat = pgAdapter.tab3.getTimeRepeat();
                    type = pgAdapter.tab3.getTime_type();
                    timeString = hour + ", Mỗi " + timeRepeat + " " + type;
                }

                MyDatabaseHelper db = new MyDatabaseHelper(this);
                if (isModeAdd)
                    db.addNote(new Note(titleNote, contentNote, timeString, position, true));
                else db.editNote(idNote, new Note(titleNote, contentNote, timeString, position, true));

                finish();

                return true;
        }
        return super .onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }

}
