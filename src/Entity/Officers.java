package Entity;

import Entity.LawEnforcementAgencies;
import dao.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Officers {
    static int officerId;
    private static String firstName;
    private String lastName;
    private int badgeNumber;
    private int rankings;
    private String contactInfo;
    LawEnforcementAgencies agencyId;

    public  Officers(int officerId, String firstName, String lastName, int badgeNumber, int rankings, String contactInfo, LawEnforcementAgencies agencyId){
        this.officerId = officerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rankings = rankings;
        this.badgeNumber = badgeNumber;
        this.contactInfo = contactInfo;
        this.agencyId = agencyId;
    }

    public static int getOfficerId() {
        return officerId;
    }

    public String getLastName() {
        return lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public int getRank() {
        return rankings;
    }

    public LawEnforcementAgencies getAgencyId() {
        return agencyId;
    }

    public void enterOfficerDetails() {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Officers (officerId, firstName, lastName, badgeNumber, rankings, contactInfo, agencyID) VALUES (?, ?, ?, ?,?, ?, ?)"
             )) {
            preparedStatement.setInt(1, officerId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, badgeNumber);
            preparedStatement.setInt(5, rankings);
            preparedStatement.setString(6, contactInfo);
            preparedStatement.setInt(7, LawEnforcementAgencies.getAgencyId());
            preparedStatement.executeUpdate();
            System.out.println("Officer Registered Successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
