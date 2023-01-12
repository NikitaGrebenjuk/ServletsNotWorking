package com.DAO.JDBC;

public class Participant {
    private int pid;
    private String name;
    private int batchID;

    public Participant(String name, int batchID) {
        this.name = name;
        this.batchID = batchID;
    }
    public Participant(int pid,String name,int batchID){
        this.pid = pid;
        this.name = name;
        this.batchID = batchID;
    }

    public int getpid() {
        return pid;
    }

    public void setpid() {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getbatchID() {
        return batchID;
    }

    public void setbatchID(int batchID) {
        this.batchID = batchID;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                '}';
    }
}
