package Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dominik Deja on 22.05.2017.
 */

public class VetRequest implements Serializable{

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

    private Pracownik veterinary;

    private Kon horse;

    private User reportedBy;

    private Date requestDate;

    private String descripton;

    private String descriptionAboutPlaceWhereHorseWasInjured;

    private String priority;

    private String status;

    public VetRequest(String id, Pracownik employeeResponsible, Kon horse, User submittedBy, Date requestDate, String descripton, String descriptionAboutPlaceWhereHorseWasInjured, String priority, String status) {
        this.id = id;
        this.veterinary = employeeResponsible;
        this.horse = horse;
        this.reportedBy = submittedBy;
        this.requestDate = requestDate;
        this.descripton = descripton;
        this.descriptionAboutPlaceWhereHorseWasInjured = descriptionAboutPlaceWhereHorseWasInjured;
        this.priority = priority;
        this.status = status;
    }

    public VetRequest( Pracownik employeeResponsible, Kon horse, Date requestDate, String descripton, String descriptionAboutPlaceWhereHorseWasInjured, String priority) {
        this.veterinary = employeeResponsible;
        this.horse = horse;
        this.requestDate = requestDate;
        this.descripton = descripton;
        this.descriptionAboutPlaceWhereHorseWasInjured = descriptionAboutPlaceWhereHorseWasInjured;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pracownik getveterinary() {
        return veterinary;
    }

    public void setEmployeeResponsible(Pracownik employeeResponsible) {
        this.veterinary = employeeResponsible;
    }

    public Kon getHorse() {
        return horse;
    }

    public void setHorse(Kon horse) {
        this.horse = horse;
    }

    public User getreportedBy() {
        return reportedBy;
    }

    public void setreportedBy(User submittedBy) {
        this.reportedBy = submittedBy;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getDescripton() {
        return ((descripton==null) ? "" : descripton) ;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getdescriptionAboutPlaceWhereHorseWasInjured() {
        return ((descriptionAboutPlaceWhereHorseWasInjured==null) ? "" : descriptionAboutPlaceWhereHorseWasInjured);
    }

    public void setdescriptionAboutPlaceWhereHorseWasInjured(String injuryLocation) {
        this.descriptionAboutPlaceWhereHorseWasInjured = injuryLocation;
    }

    public String getPriority() {
        return ((priority==null) ? "" : priority);
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return ((status==null) ? "" : status);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
