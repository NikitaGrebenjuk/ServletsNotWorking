package com.DAO.JDBC;

import java.util.List;

public class ParticipantService {
    private ParticipantDAO participantDAO;

    public ParticipantService() {
        this.participantDAO = new JDBCParticipantDAO();
    }

    public List<Participant> getAllParticipants() {
        return participantDAO.getAllParticipants();
    }
    public Participant getParticipantByID(int pid){
        return participantDAO.getParticipantByID(pid);
    }

    public void updateParticipant(int pid, String name, int batchID){ participantDAO.updateParticipant(new Participant(pid,name,batchID));}
    
    public void addParticipant(Participant participant){
    	this.participantDAO.addParticipant(participant);
    }

    // Other business logic methods
}