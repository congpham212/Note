package com.example.note_1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    ListView my_listView;
    CheckBox checkBox_all;

    private static final String TAG = "ListViewMultiple";

    private static final String[] items = {"lorem", "ipsum", "dolor",
            "sit", "amet", "consectetuer",
            "adipiscing", "elit", "morbi",
            "vel", "ligua", "vitae",
            "arcu", "aliquet", "mollis",
            "eiam", "vel", "erat",
            "placerat", "ante", "porttitor",
            "sodales", "pellentesque", "augue",
            "purus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        anhXa();

        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);

        my_listView.setAdapter(myArrayAdapter);
        my_listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        control_checkbox(this, myArrayAdapter);
        my_listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNohingSelected: ");
            }
        });
//        my_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, "onItemClick: " + position);
//                CheckedTextView v = (CheckedTextView) view;
//                boolean currentCheck = v.isChecked();
//            }
//
//        });


    }

    private void control_checkbox(final Activity main3, final ArrayAdapter<String> myArrayAdapter) {
        checkBox_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(TAG, "OnCheckedChange: " + isChecked);

//                Toast.makeText(main3, my_listView.getCheckedItemPosition(), Toast.LENGTH_SHORT).show();
                for(int i = 0; i < myArrayAdapter.getCount(); i++){
                    my_listView.setItemChecked(i, isChecked);
                }
            }
        });
    }

    private void anhXa() {
        my_listView = (ListView) findViewById(R.id.listView_main3);
        checkBox_all = (CheckBox) findViewById(R.id.cb_all_main3);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super .onOptionsItemSelected(item);
    }
}
