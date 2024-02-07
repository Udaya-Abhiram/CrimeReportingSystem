package Entity;

import Entity.Incidents;
import dao.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Evidence {
    int evidenceId;
    String description;
    String locationFound;
    Incidents incidentId;

    public Evidence(int evidenceId, String description, String locationFound, Incidents incidentId) {
        this.evidenceId = evidenceId;
        this.description = description;
        this.locationFound = locationFound;
        this.incidentId = incidentId;
    }

    public String getDescription() {
        return description;
    }

    public Incidents getIncidentId() {
        return incidentId;
    }

    public int getEvidenceId() {
        return evidenceId;
    }
    public String getLocationFound() {
        return locationFound;
    }

    public void enterEvidenceDetails() {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Evidence (evidenceId, description, locationFound, incidentID) VALUES (?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, evidenceId);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, locationFound);
            preparedStatement.setInt(4, Incidents.getIncidentID());
            preparedStatement.executeUpdate();
            System.out.println("Evidence details entered successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
