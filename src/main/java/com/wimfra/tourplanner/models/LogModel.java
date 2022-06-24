package com.wimfra.tourplanner.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class LogModel {

    private SimpleIntegerProperty tour_id;

    private SimpleIntegerProperty log_id;

    private SimpleStringProperty date;

    private SimpleStringProperty time;

    private SimpleStringProperty comment;

    private SimpleStringProperty difficulty;

    private SimpleIntegerProperty rating;

    private SimpleStringProperty total_time;

    private SimpleStringProperty tourname;

    public int getLogID() {
        return log_id.get();
    }

    public void setLogID(int logID) {
        this.log_id = new SimpleIntegerProperty(logID);
    }

    public int getTourID() {
        return tour_id.get();
    }

    public void setTourID(int tourID) {
        this.tour_id = new SimpleIntegerProperty(tourID);
    }

    public int getRating() {
        return rating.get();
    }

    public void setRating(int rating) {
        this.rating = new SimpleIntegerProperty(rating);
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = new SimpleStringProperty(difficulty);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time = new SimpleStringProperty(time);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.comment = new SimpleStringProperty(date);
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment = new SimpleStringProperty(comment);
    }

    public String getTotalTime() {
        return total_time.get();
    }

    public void setTotalTime(String total_time) {
        this.total_time = new SimpleStringProperty(total_time);
    }

    public String getTourName() {
        return tourname.get();
    }

    public void setTourname(String tourname) {
        this.tourname = new SimpleStringProperty(tourname);
    }


    public LogModel(int log_id, int tour_id, String tourname, String date, String time, String difficulty, int rating, String comment, String total_time) {
        this.tour_id = new SimpleIntegerProperty(tour_id);
        this.log_id = new SimpleIntegerProperty(log_id);
        this.tourname = new SimpleStringProperty(tourname);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.difficulty = new SimpleStringProperty(difficulty);
        this.rating = new SimpleIntegerProperty(rating);
        this.comment = new SimpleStringProperty(comment);
        this.total_time = new SimpleStringProperty(total_time);


    }
}
