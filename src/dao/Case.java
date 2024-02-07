package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;

import dao.DatabaseConnector;

public class Case {
    private int caseId;
    private String caseDescription;
    private String caseDateRecieved;
    private String caseStatus;

    public Case(int caseId,String caseDescription,String caseDateRecieved, String caseStatus) {
       this.caseId = caseId;
       this.caseDescription = caseDescription;
       this.caseDateRecieved = caseDateRecieved;
       this.caseStatus = caseStatus;
    }

    public int getCaseId(){
        return caseId;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public String getCaseDateRecieved(){
        return caseDateRecieved;
    }

    public String getCaseStatus(){
        return caseStatus;
    }




    public void createCase(int caseId, String caseDescription, String caseDateRecieved, String caseStatus) {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO cases (caseId,caseDescription,caseDateRecieved, caseStatus) VALUES (?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, caseId);
            preparedStatement.setString(2, caseDescription);
            preparedStatement.setString(3,caseDateRecieved);
            preparedStatement.setString(4, caseStatus);
            System.out.println("Cases are created");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

    private void associateIncidentsWithCase(int caseId, Collection<Incidents> associatedIncidents) {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Incidents SET caseId = ? WHERE incidentID = ?"
             )) {
            for (Incidents incident : associatedIncidents) {
                preparedStatement.setInt(1, caseId);
                preparedStatement.setInt(2, incident.getIncidentID());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

}



