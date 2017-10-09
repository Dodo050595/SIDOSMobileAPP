package Models;

/**
 * Created by Dominik Deja on 11.06.2017.
 */

public class VetRequestSEND {

    private String NoteAboutReport;
    private String injuryLocation;
    private String descriptionAboutPlaceWhereHorseWasInjured;
    private int horseId;
    private String veterinaryId;
    private String userId;
    private String status;
    private String picture;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public VetRequestSEND(String NoteAboutReport, String injuryLocation, String descriptionAboutPlaceWhereHorseWasInjured, int horseId, String veterinaryId,String _status,String picture) {
        this.NoteAboutReport = NoteAboutReport;
        this.injuryLocation = injuryLocation;
        this.descriptionAboutPlaceWhereHorseWasInjured = descriptionAboutPlaceWhereHorseWasInjured;
        this.horseId = horseId;
        this.veterinaryId = veterinaryId;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return NoteAboutReport;
    }

    public void setDescription(String description) {
        this.NoteAboutReport = description;
    }

    public String getInjuryLocation() {
        return injuryLocation;
    }

    public void setInjuryLocation(String injuryLocation) {
        this.injuryLocation = injuryLocation;
    }

    public String getdescriptionAboutPlaceWhereHorseWasInjured() {
        return descriptionAboutPlaceWhereHorseWasInjured;
    }

    public void setdescriptionAboutPlaceWhereHorseWasInjured(String descriptionAboutPlaceWhereHorseWasInjured) {
        this.descriptionAboutPlaceWhereHorseWasInjured = descriptionAboutPlaceWhereHorseWasInjured;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public String getEmployeeId() {
        return veterinaryId;
    }

    public void setveterinaryId(String employeeId) {
        this.veterinaryId = employeeId;
    }
}
