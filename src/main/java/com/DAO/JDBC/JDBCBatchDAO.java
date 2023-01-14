package com.DAO.JDBC;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCBatchDAO implements BatchDAO{

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // code to load the configuration and establish a connection
        String url = "";
        String username = "";
        String password = "";
        String driver = "";

        url = "jdbc:mysql://localhost/devDB";
        username = "root";
        password = "admin123!";
        driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);

    }

    public CachedRowSet executeSQLQuery(String sqlQuery) {
        Connection conn = null;
        Statement stmt = null;
        CachedRowSet rowSet = null;
        // Create a CachedRowSet from the ResultSet

        try {
            // Load the JDBC driver and establish a connection
            conn = getConnection();

            // Execute a SELECT statement to get the participants
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.populate(rs);
            // Process the result set
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                // nothing we can do
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return rowSet;
    }

    public void executeSQLUpdate(String sql){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null){
                    stmt.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException se){
                //nothing we can do
            }
        }
    }
    @Override
    public List<Batch> getAllBatches() {
        ArrayList<Batch> batches = new ArrayList<Batch>();
        String sql = "SELECT * FROM Batch";
        CachedRowSet rs = null;
        try {
            rs = executeSQLQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                batches.add(new Batch(id,name));
            }
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se2) {
                // nothing we can do
            }
        }
        return batches;
    }

    @Override
    public Batch getBatchById(int id) {
        Batch batch = null;
        String sql = "SELECT * FROM Batch where BatchID = "+ id;
        ResultSet rs = null;
        try {
            rs = executeSQLQuery(sql);
            // Process the result set
            if(rs.next()){
                batch = new Batch(rs.getInt("BatchID"),rs.getString("BatchName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException se2) {
                // nothing we can do
            }
        }
        return batch;

    }

    @Override
    public void addBatch(Batch batch) {
        String sql = "INSERT INTO Batch (BatchName) VALUES ('"+ batch.getBatchName() +"')";
        executeSQLUpdate(sql);

    }

    @Override
    public void updateBatch(Batch batch) {
        String sql = "UPDATE Batch SET BatchName = '"+ batch.getBatchName() +"' WHERE BatchID = "+ batch.getBatchID();
        executeSQLUpdate(sql);
    }

    @Override
    public void deleteBatch(int bid) {
        String sql = "DELETE FROM Batch WHERE BatchID = "+bid;
        executeSQLUpdate(sql);
    }
}
