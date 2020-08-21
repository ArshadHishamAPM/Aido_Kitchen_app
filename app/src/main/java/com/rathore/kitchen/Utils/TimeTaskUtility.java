package com.rathore.kitchen.Utils;

/**
 * Created by Ravi Archi on 1/4/2017.
 */
public class TimeTaskUtility {
    public String timetaskresukt,taskname,minut,status;
    public int id;
    public TimeTaskUtility(String timeTaskResult, String taskName, String minit, String status) {

        this.timetaskresukt = timeTaskResult;
        this.taskname = taskName;
        this.minut = minit;
        this.status = status;
    }

    public TimeTaskUtility() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimetaskresukt() {
        return timetaskresukt;
    }

    public void setTimetaskresukt(String timetaskresukt) {
        this.timetaskresukt = timetaskresukt;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getMinut() {
        return minut;
    }

    public void setMinut(String minut) {
        this.minut = minut;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public int getId() {
        return id;
    }
}
