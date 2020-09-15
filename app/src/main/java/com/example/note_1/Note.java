package com.example.note_1;

public class Note {
    private String id;
    private String date;
    private String title;
    private String content;
    private String remind;

    public Note(){

    }

    public Note(String id, String date, String title, String content, String remind) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.remind = remind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
