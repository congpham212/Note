package com.example.note_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAddNote;
    private Intent intent;
    private Context context;
    private RecyclerView rv_note;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        controlButton(this);

        createNote();

        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        rv_note.addItemDecoration(dividerHorizontal);

        noteAdapter = new NoteAdapter(noteList, this);
        rv_note.setAdapter(noteAdapter);

//        RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_note.setLayoutManager(linearLayoutManager);

    }

    private void createNote() {
        noteList = new ArrayList<>();
        noteList.add(new Note("1","5:00", "Dạy đi", "dậy tập thể dục", "hàng ngày"));
        noteList.add(new Note("2","6:00", "Ăn sáng ", "dậy ăn sáng", "hằng ngày"));
        noteList.add(new Note("3","6:30", "Dạy đi học", "đi học", "hàng ngày"));
        noteList.add(new Note("5","7:00", "Vào học ", "dậy ăn sáng", "hằng ngày"));
        noteList.add(new Note("7","7:30", "Dạy đi", "dậy tập thể dục", "hàng ngày"));
        noteList.add(new Note("8","8:00", "Ăn sáng ", "dậy ăn sáng", "hằng ngày"));
        noteList.add(new Note("9","8:30", "Dạy đi", "dậy tập thể dục", "hàng ngày"));
        noteList.add(new Note("10","9:00", "Ăn sáng ", "dậy ăn sáng", "hằng ngày"));
        noteList.add(new Note("11","9:30", "Dạy đi", "dậy tập thể dục", "hàng ngày"));
        noteList.add(new Note("12","10:00", "Ăn sáng ", "dậy ăn sáng", "hằng ngày"));
        noteList.add(new Note("9","8:30", "Dạy đi", "dậy tập thể dục", "hàng ngày"));
        noteList.add(new Note("10","9:00", "Ăn sáng ", "dậy ăn sáng", "hằng ngày"));
        noteList.add(new Note("11","9:30", "Dạy đi", "dậy tập thể dục", "hàng ngày"));
        noteList.add(new Note("12","10:00", "Ăn sáng ", "dậy ăn sáng", "hằng ngày"));
    }

    private void controlButton(final Activity main) {
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(main, Main2Activity.class);
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
        btnAddNote = (Button) findViewById(R.id.btn_add);
        rv_note = (RecyclerView) findViewById(R.id.rv_main3_1);

    }




}
