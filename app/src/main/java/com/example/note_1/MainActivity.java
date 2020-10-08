package com.example.note_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amitshekhar.DebugDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAddNote;
    private Intent intent;
    private RecyclerView rv_note;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.d("addr", DebugDB.getAddressLog());
        Intent intentReceiver = getIntent();
        if (intentReceiver.hasExtra("finish")) finish();
        anhXa();
        controlButton(this);

        /*createNote();*/

        DividerItemDecoration dividerHorizontal = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv_note.addItemDecoration(dividerHorizontal);

        // Lay du lieu note tu database
        MyDatabaseHelper db =new MyDatabaseHelper(this);
        noteList = db.getAllNote();

        // do du lieu ra man hinh
        noteAdapter = new NoteAdapter(noteList, this);
        rv_note.setAdapter(noteAdapter);

//        RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_note.setLayoutManager(linearLayoutManager);

        RecyclerViewSwipeListener recyclerViewSwipeListener = new RecyclerViewSwipeListener(false) {

            @Override
            public void onSwipeUp() {
                Toast.makeText(MainActivity.this, "Vuốt lên", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity","asd");
            }

            @Override
            public void onSwipeDown() {
                Toast.makeText(MainActivity.this, "Vuốt xuống", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity","asd");
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "Vuốt sang trai", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity","asd");
            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "Vuốt sang phai", Toast.LENGTH_SHORT).show();
            }
        };

        rv_note.setOnFlingListener(recyclerViewSwipeListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh(){
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        noteList = db.getAllNote();
        noteAdapter = new NoteAdapter(noteList, this);
        rv_note.setAdapter(noteAdapter);
    }
    /*private void createNote() {
        noteList = new ArrayList<>();
        noteList.add(new Note(1,"shoping","8:00",1,false));
        noteList.add(new Note(2,"cbvcb","11:00",1,false));
        noteList.add(new Note(3,"shcvboping","2:00",1,false));
        noteList.add(new Note(4,"ytu","8:00",1,false));
        noteList.add(new Note(5,"eqr","8:00",1,false));
        noteList.add(new Note(6,"lik","8:00",1,false));
    }*/


    private void controlButton(final Activity main) {
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(main, AddReminderActivity.class);
                intent.putExtra("idNote",-1);
                startActivity(intent);


//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
//                builder.setTitle("Bạn có chắc muốn thoát khỏi app");
//                builder.setMessage("Hãy lựa chọ bên dưới để xác nhận");
//                builder.setIcon(android.R.drawable.ic_dialog_alert);
//                builder.setCancelable(false);
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        onBackPressed();
//                    }
//                });
//
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                builder.show();
            }
        });
    }

    private void anhXa() {
        btnAddNote = findViewById(R.id.btn_add);
        rv_note = findViewById(R.id.rv_main3_1);

    }




}
