package main;

import CustomExceptions.CrimeNotFoundException;
import CustomExceptions.DateNotFoundException;
import CustomExceptions.IncidentNumberNotFoundException;
import CustomExceptions.UpdateImpossibleException;
import Entity.*;
import dao.*;
import dao.Incidents;

import java.util.Collection;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IncidentNumberNotFoundException, UpdateImpossibleException, CrimeNotFoundException, DateNotFoundException {
//        Incidents id = new Incidents(1,"Robbery","2024-01-12","43",
//                "Chain Stolen","Opend",1,1);
//         Incidents id1 = new Incidents(2,"Theft","2024-01-12","24","money stolen","opened",2,2);
////        Incidents id2 = new Incidents(3,"Robbery","2024-01-15","72","money stolen","opened",3,3);
//
//        id1.enterIncidentDetails();
//        id2.enterIncidentDetails();

//        Victims vc = new Victims(1,"Abhi","kona","2004-10-21","Male","123 street,AP");
//        vc.enterVictimDetails();

//        Victims vc1 = new Victims(2,"sandeep","sett","2002-10-2","Male","456 street,AP");
//        Victims vc2 = new Victims(3,"hari","de","2001-11-18","female","4521 street,AP");
//
//        vc1.enterVictimDetails();
//        vc2.enterVictimDetails();

//        Suspects sp1 = new Suspects(1,"sam","pale","2012-01-2","female","831281838");
//        Suspects sp2 = new Suspects(2,"pal","luther","2013-01-3","male","8342894");
//
//        sp1.enterSuspectDetails();
//        sp2.enterSuspectDetails();
//        sp3.enterSuspectDetails();

//         LawEnforcementAgencies la = new LawEnforcementAgencies(1,"xyz","check","8123712",1);
//////        la.enterAgencyEnforcement();
////
//          Officers of = new Officers(1,"abh","kon",21,2,"32132113",la);
//          of.enterOfficerDetails();

//        Evidence ed = new Evidence(1,"xyz","rew",id);
//        ed.enterEvidenceDetails();

//        Reports rp = new Reports(1,id,of,"2024-10-1","report hand","completed");
//        rp.enterReportDetails();


        Incidents newIncident = new Incidents(
                4, "Robbery", "2024-01-16", "Some Location",
                "Description of the incident", "Open", 4, 4
        );

        Incidents newIncident1 = new Incidents(
                5, "Robbery", "2024-01-16", "Some Location",
                "Description of the incident", "Open", 5, 5
        );

        Incidents existingIncident = new Incidents(
                1, "Robbery", "2024-01-16", "Some Location",
                "Description of the incident", "Open", 123, 456
        );

        // Create an instance of the Incidents class using the constructor
//        Incidents existingIncidentInstance = new Incidents(existingIncident);
//        boolean statusUpdated = existingIncidentInstance.updateIncidentStatus("Closed", 1);

//        // Display the updated incident details
//        if (statusUpdated) {
//            System.out.println("Incident status updated successfully!");
//            System.out.println("Updated Incident Details:");
//        } else {
//            System.out.println("Failed to update incident status.");
//        }

        Incidents incidentsService = new Incidents(existingIncident);
//
//
//        Collection<Incidents> incidentsInRange = incidentsService.getIncidentsInDateRange("22-12-10","22-11-10");
//
//
//        // Print the details of incidents in the date range
//        System.out.println("Incidents in Date Range:");
//        for (Incidents incident : incidentsInRange) {
//            System.out.println("Incident ID: " + incident.getIncidentID());
//            System.out.println("Incident Type: " + incident.getIncidentType());
//            System.out.println("Incident Date: " + incident.getIncidentDate());
//            System.out.println("Location: " + incident.getLocation());
//            System.out.println("Description: " + incident.getDescription());
//            System.out.println("Status: " + incident.getStatus());
//            System.out.println("Victim ID: " + incident.getVictimId());
//            System.out.println("Suspect ID: " + incident.getSuspectId());
//            System.out.println();
//        }

        Incidents sampleIncident1 = new Incidents(1, "Robbery", "2024-01-16", "Location A", "Description 1", "Open", 123, 456);
        Incidents sampleIncident2 = new Incidents(2, "Theft", "2024-01-17", "Location B", "Description 2", "Open", 789, 101);

//            Incidents id = new Incidents(5,"fa","fahhfa","41","chain","pending",5,5);
//////            id.updateIncidentStatus("completed",10);
////
////            id.searchIncidents("pay");
////
//            id.updateIncidentDescription(1,"necklace stolen");

            Scanner sc = new Scanner(System.in);

            System.out.println("1. Enter Incident Details");
            System.out.println("2. Enter Victim Details");
            System.out.println("3. Enter Suspect Details");
            System.out.println("4. Update Incident Status");
            System.out.println("5. Update Incident Description");
            System.out.println("6. Search Incidents");

            int choice;

        do {
            System.out.println("Enter your choice (1-6, 0 to exit): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Incidents sampleIncident3 = new Incidents(5, "Theft", "2024-01-17", "Location B", "Description 2", "Open", 789, 101);
                    Incidents sampleIncident = new Incidents(4, "Robbery", "2024-01-16", "Location A", "Description 1", "Open", 123, 456);
                    sampleIncident.createIncident(sampleIncident3);
                    break;

                case 2:
                    Victims v = new Victims(5, "Abhi", "RAm", "291", "male", "fjdsjfa");
                    v.enterVictimDetails();
                    break;

                case 3:
                    Suspects sp3 = new Suspects(4, "arjun", "kumar", "2010-01-4", "female", "38283");
                    sp3.enterSuspectDetails();
                    break;

                case 4:
                    Incidents sampleIncidentx = new Incidents(4, "Robbery", "2024-01-16", "Location A", "Description 1", "Open", 123, 456);
                    sampleIncidentx.updateIncidentStatus("Completed", 4);
                    break;

                case 5:
                    // Assuming sampleIncident1 is an instance of Incidents
                    sampleIncident1.updateIncidentDescription(1, "Killing");
                    break;

                case 6:
                    Collection<Incidents> incidentsInRange1 = incidentsService.searchIncidents("Robbery");
                    // Handle incidentsInRange1 as needed
                    break;

                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 6.");
            }
        } while (choice != 0);
    }

}



