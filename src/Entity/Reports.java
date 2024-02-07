package Entity;

import Entity.Incidents;
import Entity.Officers;
import dao.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Reports {
    int reportId;
    Incidents incidentId;
    Officers reportingOfficerId;
    private String reportDate;
    private String reportDetails;
    private String status;

    public Reports(int reportId,Incidents incidentId, Officers reportingOfficerId, String reportDate, String reportDetails, String status){
        this.reportId = reportId;
        this.incidentId = incidentId;
        this.reportingOfficerId = reportingOfficerId;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.status = status;
    }

    public Incidents getIncidentId() {
        return incidentId;
    }

    public int getReportId() {
        return reportId;
    }

    public Officers getReportingOfficer() {
        return reportingOfficerId;
    }

    public String getStatus() {
        return status;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void enterReportDetails() {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Reports (reportId, incidentId, reportingOfficerId, reportDate, reportDetails, status) VALUES (?, ?, ?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, reportId);
            preparedStatement.setInt(2, Incidents.getIncidentID());
            preparedStatement.setInt(3, Officers.getOfficerId());
            preparedStatement.setString(4, reportDate);
            preparedStatement.setString(5, reportDetails);
            preparedStatement.setString(6, status);
            preparedStatement.executeUpdate();
            System.out.println("Report details entered successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
