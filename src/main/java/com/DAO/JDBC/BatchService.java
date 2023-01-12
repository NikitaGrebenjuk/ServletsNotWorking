package com.DAO.JDBC;

import java.util.List;

public class BatchService{
    private BatchDAO batchDAO;
    public BatchService() {
        this.batchDAO = new JDBCBatchDAO();
    }
    public List<Batch> getAllBatches(){
        return batchDAO.getAllBatches();
    }
    public Batch getBatchById(int id){
        return this.batchDAO.getBatchById(id);
    }
    public void addBatch(Batch batch){
        this.batchDAO.addBatch(batch);
    }

    public void updateBatch(Batch batch) {
        this.batchDAO.updateBatch(batch);
    }

    public void deleteBatch(int bid) {
        this.batchDAO.deleteBatch(bid);
    }
}