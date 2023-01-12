package com.DAO.JDBC;

import java.util.List;

public interface BatchDAO {
    List<Batch> getAllBatches();
    Batch getBatchById(int id);
    void addBatch(Batch batch);
    void updateBatch(Batch batch);
    void deleteBatch(int bid);
}
