package com.example.note_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class AddReminderActivity extends AppCompatActivity implements View.OnClickListener {
    
    private EditText et_title;
    private EditText et_content;

    private Context context = this;
    
    private Button btn_once;
    private Button btn_loop;
    private Button btn_everyDay;

    String data[] = new String[]{String.valueOf(R.string.seconds), String.valueOf(R.string.minute), String.valueOf(R.string.hours), String.valueOf(R.string.day),
            String.valueOf(R.string.week), String.valueOf(R.string.month), String.valueOf(R.string.year)};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        anhXa();

        final TabLayout tlModeNotif = findViewById(R.id.tl_mode_notif);
        final ViewPager vpModeNotif = findViewById(R.id.vp_mode_notif);
        ModeNotifPagerAdapter pgAdapter = new ModeNotifPagerAdapter(getSupportFragmentManager());
        vpModeNotif.setAdapter(pgAdapter);
        tlModeNotif.setupWithViewPager(vpModeNotif);
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
        }
        return super .onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
