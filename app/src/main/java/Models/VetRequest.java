package Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dominik Deja on 22.05.2017.
 */

public class VetRequest implements Serializable{

    public static enum HealthProblemPriority{

        Low{
            @Override
            public String toString() {
                return "Niski";
            }
            public String EnglishVer(){
                return "Low";
            }
        },
        Medium{
            @Override
            public String toString() {
                return "Średni";
            }
            public String EnglishVer(){
                return "Medium";
            }
        },
        High{
            @Override
            public String toString() {
                return "Wysoki";
            }
            public String EnglishVer(){
                return "High";
            }
        },
        VeryHigh{
            @Override
            public String toString() {
                return "Bardzo Wysoki";
            }
            public String EnglishVer(){
                return "VeryHigh";
            }
        },
        Extreme{
            @Override
            public String toString() {
                return "Najwyższy";
            }
            public String EnglishVer(){
                return "Extreme";
            }
        },



    }

//    public static enum HealthProblemPriorityPolish{
//
//        Niski,
//        Średni,
//        Wysoki,
//        Najwyższy,
//        Krytyczny,
//
//
//
//    }

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

    private Date createDate;

    private String noteAboutReport;

    private String descriptionAboutPlaceWhereHorseWasInjured;

    private String priority;

    private String status;

    private String pictureUrl;

    public VetRequest(String id, Pracownik employeeResponsible, Kon horse, User submittedBy, Date createDate, String noteAboutReport, String descriptionAboutPlaceWhereHorseWasInjured, String priority, String status,String pictureUrl) {
        this.id = id;
        this.veterinary = employeeResponsible;
        this.horse = horse;
        this.reportedBy = submittedBy;
        this.createDate = createDate;
        this.noteAboutReport = noteAboutReport;
        this.descriptionAboutPlaceWhereHorseWasInjured = descriptionAboutPlaceWhereHorseWasInjured;
        this.priority = priority;
        this.status = status;
        this.pictureUrl = pictureUrl;
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

    public Date getcreateDate() {
        return createDate;
    }

    public void setcreateDate(Date requestDate) {
        this.createDate = requestDate;
    }

    public String getnoteAboutReport() {
        return ((noteAboutReport==null) ? "" : noteAboutReport) ;
    }

    public void setnoteAboutReport(String noteAboutReport) {
        this.noteAboutReport = noteAboutReport;
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

    public String getPictureUrl() {
        return ((pictureUrl==null) ? pictureUrl : pictureUrl.replace("~", ""));

    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
