package com.DAO.JDBC;

import java.util.List;

public interface ParticipantDAO {
    List<Participant> getAllParticipants();
    Participant getParticipantByID(int pid);
    void updateParticipant(Participant participant);
    void addParticipant(Participant participant);
    void deleteParticipant(int pid);

}
