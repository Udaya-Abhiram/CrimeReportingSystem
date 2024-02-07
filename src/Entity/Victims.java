package Entity;

import dao.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Victims {
    int victimId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String address;

    public Victims(int victimId, String firstName, String lastName, String dateOfBirth, String gender,String address){
        this.victimId = victimId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
    }

    public int getVictimId() {
        return victimId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

//    ADDING VICTIMS INTO DATA BASE
    public void enterVictimDetails() {
        try (Connection connection = DatabaseConnector.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Victims (victimId, firstName, LastName, dateOfBirth, gender, Address) VALUES (?, ?, ?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, victimId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, address);
            preparedStatement.executeUpdate();
            System.out.println("Victim details entered successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("some thing went wrong.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
