package com.example.cs425.models;

import com.github.tlaabs.timetableview.Time;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int startHour(String start){
        return Integer.parseInt(start.substring(11, 13));
    }
    public int startMinute(String start){
        return Integer.parseInt(start.substring(14, 16));
    }
    public int getDay(String date) throws ParseException {
        String newDate = "" + date.substring(8, 10) + "/" +  date.substring(5, 7) + "/" + date.substring(0, 4) ;
        Calendar c = Calendar.getInstance();
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(newDate);
        c.setTime(date1);
        return c.get(Calendar.DAY_OF_WEEK)-2;
    }
}
