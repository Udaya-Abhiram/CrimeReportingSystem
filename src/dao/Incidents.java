package dao;

import CustomExceptions.CrimeNotFoundException;
import CustomExceptions.DateNotFoundException;
import CustomExceptions.IncidentNumberNotFoundException;
import CustomExceptions.UpdateImpossibleException;

import java.sql.*;
import java.util.*;

public class Incidents implements ICrimeAnalysisService {
    private static int incidentID;
    private String incidentType;
    private String incidentDate;
    private String location;
    private String description;
    private String status;
    private int victimId;
    private int suspectId;

    // Constructor, getters, and setters remain unchanged
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

    public Incidents(Incidents existingIncident) {

    }

   

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getVictimId() {
        return victimId;
    }

    public static int getIncidentID() {
        return incidentID;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public int getSuspectId() {
        return suspectId;
    }

    public String getIncidentType() {
        return incidentType;
    }

    @Override
    public boolean createIncident(Incidents incident) {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Incidents (incidentID, incidentType, incidentDate, location, description, status, victimId, suspectId) VALUES (?, ?, ?, ?,?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, incident.getIncidentID());
            preparedStatement.setString(2, incident.getIncidentType());
            preparedStatement.setString(3, incident.getIncidentDate());
            preparedStatement.setString(4, incident.getLocation());
            preparedStatement.setString(5, incident.getDescription());
            preparedStatement.setString(6, incident.getStatus());
            preparedStatement.setInt(7, incident.getVictimId());
            preparedStatement.setInt(8, incident.getSuspectId());
            preparedStatement.executeUpdate();
            System.out.println("Crime Registered Successfully!");
            return true; // Indicate successful incident creation
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Something went wrong.");
            return false; // Indicate failure due to constraint violation
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false; // Indicate failure
        }
    }
//    incident Number retrieve
    public boolean retrieveIncidentNumber(int incidentID) throws IncidentNumberNotFoundException{
        List<Incidents> data = new ArrayList<>();
        try(Connection connection = DatabaseConnector.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Incidents where incidentId=?"
            )){
            preparedStatement.setInt(1,incidentID);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    data.add(new Incidents(
                            resultSet.getInt(incidentID),
                            resultSet.getString("incidentType"),
                            resultSet.getString("incidentDate"),
                            resultSet.getString("location"),
                            resultSet.getString("description"),
                            resultSet.getString("status"),
                            resultSet.getInt("victimId"),
                            resultSet.getInt("suspectId")
                    ));
                }

                if(!data.isEmpty()) {
                    System.out.println("Data is found based on incident");
                    return true;
                }
                else{
                    throw new IncidentNumberNotFoundException("Incident not found");
                }
            }
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return false;
    }

    public boolean updateIncidentStatus(String newStatus, int incidentId)throws UpdateImpossibleException {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Incidents SET status = ? WHERE incidentID = ?"
             )) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, incidentId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Incident status updated successfully!");
                return true; // Indicate successful status update
            } else {
                throw new UpdateImpossibleException("update not possible because incidentID not exists");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            return false; // Indicate failure
        }
    }

//Update Incidenet Description

    public boolean updateIncidentDescription(int incidentId, String description){
        try (Connection connection = DatabaseConnector.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "Update Incidents SET description = ? where incidentId = ? "
            )){
            preparedStatement.setString(1,description);
            preparedStatement.setInt(2,incidentId);

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected>0){
                System.out.println("Description Updated successfully");
                return true;
            }
            else{
                System.out.println("Description updation failed");
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Collection<Incidents> getIncidentsInDateRange(String startDate, String endDate) throws DateNotFoundException {
        List<Incidents> incidents = new ArrayList<>();

        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Incidents WHERE incidentDate BETWEEN ? AND ?"
             )) {
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    incidents.add(new Incidents(
                            resultSet.getInt("incidentID"),
                            resultSet.getString("incidentType"),
                            resultSet.getString("incidentDate"),
                            resultSet.getString("location"),
                            resultSet.getString("description"),
                            resultSet.getString("status"),
                            resultSet.getInt("victimId"),
                            resultSet.getInt("suspectId")
                    ));
                }
            }

            if(incidents.isEmpty())
                throw new DateNotFoundException("The data range is not found");

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return incidents;
    }

    public Collection<Incidents> searchIncidents(String incidentType) throws CrimeNotFoundException{
        List<Incidents> matchingIncidents = new ArrayList<>();

        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Incidents WHERE incidentType = ?"
             )) {
            preparedStatement.setString(1, incidentType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    matchingIncidents.add(new Incidents(
                            resultSet.getInt("incidentID"),
                            resultSet.getString("incidentType"),
                            resultSet.getString("incidentDate"),
                            resultSet.getString("location"),
                            resultSet.getString("description"),
                            resultSet.getString("status"),
                            resultSet.getInt("victimId"),
                            resultSet.getInt("suspectId")
                    ));
                }
            }

            if(matchingIncidents.isEmpty()){
               throw new CrimeNotFoundException("This type of incident type is not found");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return matchingIncidents;
    }

//    public Case createCase(String caseDescription, Collection<Incidents> associatedIncidents) {
//        try (Connection connection = DatabaseConnector.openConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "INSERT INTO Cases (caseDescription) VALUES (?)",
//                     PreparedStatement.RETURN_GENERATED_KEYS
//             )) {
//            preparedStatement.setString(1, caseDescription);
//            preparedStatement.executeUpdate();
//
//            // Retrieve the auto-generated case ID
//            int caseId;
//            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    caseId = generatedKeys.getInt(1);
//                } else {
//                    throw new SQLException("Failed to retrieve auto-generated case ID.");
//                }
//            }
//
//            // Associate incidents with the case
//            associateIncidentsWithCase(caseId, associatedIncidents);
//
//            return new Case(caseDescription, associatedIncidents);
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle or log the exception as needed
//            return null; // Indicate failure
//        }
//    }


    @Override
    public boolean createIncident(Entity.Incidents incident) {
        return false;
    }
}
