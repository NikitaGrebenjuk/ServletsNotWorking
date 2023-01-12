package com.DAO.JDBC;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCParticipantDAO implements ParticipantDAO {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // code to load the configuration and establish a connection
        String url = "";
        String username = "";
        String password = "";
        String driver = "";
        Properties config = new Properties();
 /*
        try {
            // Load the configuration file
            config.load(new FileInputStream("/config/config.properties"));
            url = config.getProperty("database.url");
            username = config.getProperty("database.username");
            password = config.getProperty("database.password");
            driver = config.getProperty("database.driver");
        } catch (IOException e) {
            // Handle errors reading the configuration file
            e.printStackTrace();
        }
*/
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("WEB-INF/config.properties")) {
            config.load(in);
            url = config.getProperty("database.url");
            username = config.getProperty("database.username");
            password = config.getProperty("database.password");
            driver = config.getProperty("database.driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public List<Participant> getAllParticipants() {
        ArrayList<Participant> participants = new ArrayList<Participant>();
        String sql = "SELECT * FROM participants";
        CachedRowSet rs = null;
        try {
            rs = executeSQLQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int batchID = rs.getInt(4);
                participants.add(new Participant(id,name,batchID));
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
        return participants;
    }

    @Override
    public Participant getParticipantByID(int pid) {
        Participant participant = null;
        String sql = "SELECT p.* FROM participants p where p.id="+ pid;
        ResultSet rs = null;
        try {
            rs = executeSQLQuery(sql);
            // Process the result set
            if(rs.next()){
                participant = new Participant(rs.getInt("id"),rs.getString("name"), rs.getInt("batchID"));
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
        return participant;
    }

    @Override
    public void updateParticipant(Participant participant) {
        String sql = "UPDATE Participants " + "SET name = '"+ participant.getName() +"', batchID = "+ participant.getbatchID()  +
                " WHERE ID = "+ participant.getpid();
        //String sql = "UPDATE Participants SET name = 'karl', batchID = 2 WHERE id = 3";
        executeSQLUpdate(sql);
    }

    @Override
    public void addParticipant(Participant participant) {
        String sql = "insert into Participants (name,batchname,) VALUES ('"+ participant.getName() +"', "+ participant.getbatchID() + ")";
        executeSQLUpdate(sql);
    }

    @Override
    public void deleteParticipant(int pid) {
        String sql = "DELETE FROM Participants WHERE id = "+pid;
        executeSQLUpdate(sql);
    }
}
