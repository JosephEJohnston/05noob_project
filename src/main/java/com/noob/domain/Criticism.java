package com.noob.domain;

import java.io.Serializable;
import java.util.Date;

public class Criticism implements Serializable {
    private int criticismID;
    private String criticismContent;
    private int employeeID;
    private Date criticismTime;
    private int messageID;

    public int getCriticismID() {
        return criticismID;
    }

    public void setCriticismID(int criticismID) {
        this.criticismID = criticismID;
    }

    public String getCriticismContent() {
        return criticismContent;
    }

    public void setCriticismContent(String criticismContent) {
        this.criticismContent = criticismContent;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Date getCriticismTime() {
        return criticismTime;
    }

    public void setCriticismTime(Date criticismTime) {
        this.criticismTime = criticismTime;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    @Override
    public String toString() {
        return "Criticism{" +
                "criticismID=" + criticismID +
                ", criticismContent='" + criticismContent + '\'' +
                ", employeeID=" + employeeID +
                ", criticismTime=" + criticismTime +
                ", messageID=" + messageID +
                '}';
    }
}
