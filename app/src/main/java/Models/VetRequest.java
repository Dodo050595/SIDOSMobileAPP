package Models;

import java.util.Date;

/**
 * Created by Dominik Deja on 22.05.2017.
 */

public class VetRequest {

    public static enum HealthProblemPriority{
        Low,
        Medium,
        High,
        VeryHigh,
        Extreme
    }

    public enum HealthProblemStatus
    {
        Accepted,
        Rejected,
        Received,
        Solved

    }

    private String id;

    private Pracownik employeeResponsible;

    private Kon horse;

    private User submittedBy;

    private Date requestDate;

    private String descripton;

    private String injuryLocation;

    private String priority;

    private String status;

    public VetRequest(String id, Pracownik employeeResponsible, Kon horse, User submittedBy, Date requestDate, String descripton, String injuryLocation, String priority, String status) {
        this.id = id;
        this.employeeResponsible = employeeResponsible;
        this.horse = horse;
        this.submittedBy = submittedBy;
        this.requestDate = requestDate;
        this.descripton = descripton;
        this.injuryLocation = injuryLocation;
        this.priority = priority;
        this.status = status;
    }

    public VetRequest( Pracownik employeeResponsible, Kon horse, Date requestDate, String descripton, String injuryLocation, String priority) {
        this.employeeResponsible = employeeResponsible;
        this.horse = horse;
        this.requestDate = requestDate;
        this.descripton = descripton;
        this.injuryLocation = injuryLocation;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pracownik getEmployeeResponsible() {
        return employeeResponsible;
    }

    public void setEmployeeResponsible(Pracownik employeeResponsible) {
        this.employeeResponsible = employeeResponsible;
    }

    public Kon getHorse() {
        return horse;
    }

    public void setHorse(Kon horse) {
        this.horse = horse;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getInjuryLocation() {
        return injuryLocation;
    }

    public void setInjuryLocation(String injuryLocation) {
        this.injuryLocation = injuryLocation;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
