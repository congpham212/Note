package com.example.note_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main3_1Activity extends AppCompatActivity {

    private RecyclerView rv_note;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        anhXa();
        createNote();

        noteAdapter = new NoteAdapter(noteList, this);
        rv_note.setAdapter(noteAdapter);

//        RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_note.setLayoutManager(linearLayoutManager);
    }

    private void anhXa() {
        rv_note = (RecyclerView) findViewById(R.id.rv_main3_1);
    }

    public void createNote(){
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
