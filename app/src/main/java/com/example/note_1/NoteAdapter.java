package com.example.note_1;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private static final String TAG = "Note_Adapter";
    private List<Note> noteList;
    private Context context;
    private LayoutInflater layoutInflater;

    public NoteAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.test_row_item_note, viewGroup,false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        Note note = noteList.get(i);

        noteViewHolder.tv_time.setText(note.getTime());
        noteViewHolder.tv_title.setText(note.getTitle());
        //noteViewHolder.tv_mode_alarm.setText(Integer.toString(note.getModeAlarm()));
        noteViewHolder.ib_alarm.setImageResource(note.isAlarm() ? R.drawable.ic_on_alarm_24 : R.drawable.ic_off_alarm_24);
        noteViewHolder.cb_done.setChecked(note.isDone());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cb_done;
        private TextView tv_time;
        private TextView tv_title;
        private TextView tv_mode_alarm;
        private ImageButton ib_alarm;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time_reminder);
            tv_title = itemView.findViewById(R.id.tv_title_reminder);
            //tv_mode_alarm = itemView.findViewById(R.id.tv_mode_alarm);
            cb_done = itemView.findViewById(R.id.cb_done_reminder);
            ib_alarm = itemView.findViewById(R.id.ib_alarm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = noteList.get(getAdapterPosition());

                    Intent intent = new Intent(context, AddReminderActivity.class);
                    int id = noteList.get(getAdapterPosition()).getId();
                    intent.putExtra("idNote", id);
                    context.startActivity(intent);

                    //Toast.makeText(context, note.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            cb_done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Note note = noteList.get(getAdapterPosition());
                    if(isChecked){
                        //Toast.makeText(context,"id: " + note.getId() + " --- Title: " + note.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                    note.setDone(isChecked);
                    MyDatabaseHelper db = new MyDatabaseHelper(context);
                    db.editNote(note.getId(),note);

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
                    deleteDialog.setTitle(R.string.remove_reminder)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MyDatabaseHelper db = new MyDatabaseHelper(context);
                                    db.deleteNote(noteList.get(getAdapterPosition()).getId());
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.putExtra("finish","finish");
                                    context.startActivity(intent);

                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    deleteDialog.show();
                    return true; // tat su kien on click khi on long click
                }
            });

            ib_alarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note note = noteList.get(getAdapterPosition());
                    if (note.isAlarm()) {
                        note.setAlarm(false);
                        ib_alarm.setImageResource(R.drawable.ic_off_alarm_24);
                        Toast.makeText(context,"F", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        note.setAlarm(true);
                        ib_alarm.setImageResource(R.drawable.ic_on_alarm_24);
                        Toast.makeText(context,"T", Toast.LENGTH_SHORT).show();
                    }
                    MyDatabaseHelper db = new MyDatabaseHelper(context);
                    db.editNote(note.getId(),note);
                }
            });
        }


    }
}
