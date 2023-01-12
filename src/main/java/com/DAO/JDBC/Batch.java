package com.DAO.JDBC;

public class Batch {
    private int batchID;
    private String batchName;
    public Batch() {
        super();
    }

    public Batch(int id, String batchName) {
        this.batchName = batchName;
        this.batchID = id;
    }

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchName='" + batchName + '\'' +
                '}';
    }
}