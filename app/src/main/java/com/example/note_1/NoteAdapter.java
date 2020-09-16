package com.example.note_1;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
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

        noteViewHolder.tv_date.setText(note.getDate());
        noteViewHolder.tv_title.setText(note.getTitle());
        noteViewHolder.tv_content.setText(note.getContent());
        noteViewHolder.tv_remind.setText(note.getRemind());

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cb_id;
        private TextView tv_date;
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_remind;
        private CheckedTextView ctv;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content_main3_1);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date_main3_1);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_main3_1);
            tv_remind = (TextView) itemView.findViewById(R.id.tv_remind_main3_1);
            cb_id = (CheckBox) itemView.findViewById(R.id.cb_main3_1);
//            ctv = (CheckedTextView) itemView.findViewById(R.id.ctv_main3_1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = noteList.get(getAdapterPosition());
                    Toast.makeText(context, note.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            cb_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        Note note = noteList.get(getAdapterPosition());
                        Toast.makeText(context,"id: " + note.getId() + " --- Title: " + note.getTitle(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder delete = new AlertDialog.Builder(context);
                    delete.setTitle(R.string.remove_reminder)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    return false;
                }
            });
        }


    }
}
