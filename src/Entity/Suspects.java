package Entity;

import dao.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Suspects {
    int suspectId;
    String firstName;
    String lastName;
    String dateOfBirth;
    String gender;
    String contactInformation;

    public Suspects(int suspectId,String firstName,String lastName,String dateOfBirth,String gender,String contactInformation){
        this.suspectId = suspectId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInformation = contactInformation;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public int getSuspectId() {
        return suspectId;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void enterSuspectDetails() {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Suspects (suspectId, firstName, lastName, dateOfBirth, gender, contactInformation) VALUES (?, ?, ?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, suspectId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, contactInformation);

            preparedStatement.executeUpdate();
            System.out.println("Suspect Registered Successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
