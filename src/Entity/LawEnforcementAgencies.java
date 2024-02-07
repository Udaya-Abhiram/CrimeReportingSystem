package Entity;

import dao.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class LawEnforcementAgencies {
    static int agencyId;
    private String agencyName;
    private String jurisdiction;
    private String contactInfo;
    private int officerId;

    public LawEnforcementAgencies(int agencyId,String agencyName,String jurisdiction, String contactInfo,int officerId){
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.contactInfo = contactInfo;
        this.jurisdiction = jurisdiction;
        this.officerId = officerId;
    }

    public static int getAgencyId() {
        return agencyId;
    }

    public int getOfficerId() {
        return officerId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void enterAgencyEnforcement() {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO LawEnforcementAgency (agencyId, agencyName, jurisdiction, contactInfo, officerId) VALUES (?, ?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, agencyId);
            preparedStatement.setString(2, agencyName);
            preparedStatement.setString(3, jurisdiction);
            preparedStatement.setString(4, contactInfo);
            preparedStatement.setInt(5, officerId);
            preparedStatement.executeUpdate();
            System.out.println("Law details entered successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
