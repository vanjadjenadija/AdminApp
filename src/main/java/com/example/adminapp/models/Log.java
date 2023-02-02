package com.example.adminapp.models;

import java.sql.Date;

public class Log {
    private Integer id;
    private String message;
    private String level;
    private Date dateTime;

    private String logger;

    public Log(Integer id, String message, String level, Date dateTime, String logger) {
        this.id = id;
        this.message = message;
        this.level = level;
        this.dateTime = dateTime;
        this.logger = logger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }
}