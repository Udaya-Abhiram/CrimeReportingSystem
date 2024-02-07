package Entity;

import dao.DatabaseConnector;
import dao.ICrimeAnalysisService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Incidents {
    private static int incidentID;
    private String incidentType;
    private String incidentDate;
    private String location;
    private String description;
    private String status;
    int victimId;
    int suspectId;

    public Incidents(int incidentID, String incidentType, String incidentDate, String location,
                     String description, String status, int victimId, int suspectId){
        this.incidentID = incidentID;
        this.incidentType = incidentType;
        this.incidentDate = incidentDate;
        this.location = location;
        this.description = description;
        this.status = status;
        this.victimId = victimId;
        this.suspectId = suspectId;
    }

//    public Incidents() {
//
//    }

    public static int getIncidentID() {
        return incidentID;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getSuspectId() {
        return suspectId;
    }

    public int getVictimId() {
        return victimId;
    }

    public void enterIncidentDetails() {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Incidents (incidentID, incidentType, incidentDate, location, description, status, victimId, suspectId) VALUES (?, ?, ?, ?,?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, incidentID);
            preparedStatement.setString(2, incidentType);
            preparedStatement.setString(3, incidentDate);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, status);
            preparedStatement.setInt(7, victimId);
            preparedStatement.setInt(8, suspectId);
            preparedStatement.executeUpdate();
            System.out.println("Crime Registered Successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }




}

