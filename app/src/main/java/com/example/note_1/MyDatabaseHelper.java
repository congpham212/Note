package com.example.note_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName = "NoteManager";
    private static final int databaseVersion = 2;

    private static final String tableNote = "Note";
    private static final String idNote = "Id";
    private static final String titleNote = "Title";
    private static final String contentNote = "Content";
    private static final String timeNote = "Time";
    private static final String modeAlarmNote = "ModeAlarm"; // 3 mode 0/1/2
    private static final String alarm = "Alarm"; // true/false on/off
    private static final String done = "Done"; // true/false finish task or yet?

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stringExec = "create table " + tableNote + " ( " + idNote + " integer primary key autoincrement, " + titleNote + " text, "
                + contentNote + " text, " + timeNote + " text, " + modeAlarmNote + " integer, " + alarm + " boolean, " + done + " boolean)";
        sqLiteDatabase.execSQL(stringExec);
        Log.d("noteBase","create table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + tableNote);
        onCreate(sqLiteDatabase);
        Log.d("noteBase","refresh table");
    }

    public void addNote(Note note){
        ContentValues values = new ContentValues();
        values.put(titleNote, note.getTitle());
        values.put(contentNote, note.getContent());
        values.put(timeNote, note.getTime());
        values.put(modeAlarmNote, note.getModeAlarm());
        values.put(alarm, String.valueOf(note.isAlarm()));
        values.put(done, String.valueOf(note.isDone()));

        getWritableDatabase().insert(tableNote, null, values);

        Log.d("noteBase","Add " + note.getTitle());
    }

    public Note getNote(int id){
        Cursor cursor = getReadableDatabase().rawQuery("select * from " + tableNote + " where " + idNote + " = ?",
                new String[] { Integer.toString(id) });
        if (cursor != null) cursor.moveToFirst();
        Note note = new Note(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                Integer.valueOf(cursor.getString(4)), Boolean.valueOf(cursor.getString(5)), Boolean.valueOf(cursor.getString(6)));

        Log.d("noteBase","getNote by id = " + id);
        return note;
    }

    public List<Note> getAllNote(){
        List<Note> noteList = new ArrayList<Note>();
        Cursor cursor = getReadableDatabase().rawQuery("select * from " + tableNote,null);
        if (cursor.moveToFirst())
            do{
                Note note = new Note(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        Integer.valueOf(cursor.getString(4)), Boolean.valueOf(cursor.getString(5)), Boolean.valueOf(cursor.getString(6)));
                noteList.add(note);
            } while (cursor.moveToNext());
        Log.d("noteBase","getAllNote");
        return noteList;
    }

    public void editNote(int id, Note note){
        ContentValues values = new ContentValues();
        values.put(titleNote, note.getTitle());
        values.put(contentNote, note.getContent());
        values.put(timeNote, note.getTime());
        values.put(modeAlarmNote, note.getModeAlarm());
        values.put(alarm, String.valueOf(note.isAlarm()));
        values.put(done, String.valueOf(note.isDone()));

        getWritableDatabase().update(tableNote,values,"id = ?", new String[] { String.valueOf(id) });

        Log.d("noteBase","update note, id = " + id);
    }

    public void deleteNote(int id){

        getWritableDatabase().delete(tableNote,"id = ?",new String[]{ String.valueOf(id)});
        Log.d("noteBase","delete note, id = " + id);
    }

    public void refreshMainAct(RecyclerView rv, Context context){
        rv.setAdapter(new NoteAdapter(getAllNote(), context));
    }

}
