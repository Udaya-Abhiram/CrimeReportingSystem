package dao;

import CustomExceptions.CrimeNotFoundException;
import CustomExceptions.DateNotFoundException;
import CustomExceptions.UpdateImpossibleException;
import Entity.Incidents;
import java.util.*;

public interface ICrimeAnalysisService {

    boolean createIncident(dao.Incidents incident);

    // Create a new incident
    boolean createIncident(Incidents incident);

    boolean updateIncidentStatus(String status, int incidentId)throws UpdateImpossibleException;

    Collection<dao.Incidents> getIncidentsInDateRange(String startDate, String endDate) throws DateNotFoundException;

    // Search incidents based on criteria
    Collection<dao.Incidents> searchIncidents(String incidentType) throws CrimeNotFoundException;

//    Case createCase(String caseDescription, Collection<Incidents> associatedIncidents);
}
