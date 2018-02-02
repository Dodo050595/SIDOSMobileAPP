package Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import HelperClasses.HelperMethods;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class Kon implements Serializable{


    public static enum CharacterOfHorses{

    }

    private String character;
    private Boolean isAvailableToRide;
    private Date dateOfBirth;
    private String name;
    private String coatColour;
    private String motherPassport;
    private String fatherPassport;
    private Date sinceWhenInStable;
    private Date untilWhenInStable;
    private String sexType;
    private String healthStatus;
    private String comments;
    private String visibilityForOthers;
    private int height;
    private int id;
    private String mainPicture;
    private List<String> pictureGalleryUrls;

    public Kon(String character, Boolean isAvailableToRide, Date dateOfBirth, String name, String coatColour, String motherPassport, String fatherPassport, Date sinceWhenInStable, Date untilWhenInStable, String sexType, String healthStatus, String comments, String visibilityForOthers, int height, int id,String mainPicture,List<String> pictureGalleryUrls) {
        this.character = character;
        this.isAvailableToRide = isAvailableToRide;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.coatColour = coatColour;
        this.motherPassport = motherPassport;
        this.fatherPassport = fatherPassport;
        this.sinceWhenInStable = sinceWhenInStable;
        this.untilWhenInStable = untilWhenInStable;
        this.sexType = sexType;
        this.healthStatus = healthStatus;
        this.comments = comments;
        this.visibilityForOthers = visibilityForOthers;
        this.height = height;
        this.id = id;
        this.mainPicture = mainPicture;
        this.pictureGalleryUrls = pictureGalleryUrls;
    }

    public List<String> getPictureGalleryUrls() {
        return pictureGalleryUrls;
    }

    public void setPictureGalleryUrls(List<String> pictureGalleryUrls) {
        this.pictureGalleryUrls = pictureGalleryUrls;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Boolean getAvailableToRide() {
        return isAvailableToRide;
    }

    public void setAvailableToRide(Boolean availableToRide) {
        isAvailableToRide = availableToRide;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoatColour() {
        if(coatColour != null) {
            return coatColour;
        }else{
            return "";
        }
    }

    public void setCoatColour(String coatColour) {
        this.coatColour = coatColour;
    }

    public String getMotherPassport() {
        return motherPassport;
    }

    public void setMotherPassport(String motherPassport) {
        this.motherPassport = motherPassport;
    }

    public String getFatherPassport() {
        return fatherPassport;
    }

    public void setFatherPassport(String fatherPassport) {
        this.fatherPassport = fatherPassport;
    }

    public Date getSinceWhenInStable() {
        return sinceWhenInStable;
    }

    public void setSinceWhenInStable(Date sinceWhenInStable) {
        this.sinceWhenInStable = sinceWhenInStable;
    }

    public Date getUntilWhenInStable() {
        return untilWhenInStable;
    }

    public void setUntilWhenInStable(Date untilWhenInStable) {
        this.untilWhenInStable = untilWhenInStable;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVisibilityForOthers() {
        return visibilityForOthers;
    }

    public void setVisibilityForOthers(String visibilityForOthers) {
        this.visibilityForOthers = visibilityForOthers;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return getName() + " ("+getCharacter()+")" + " ("+ HelperMethods.getStringFromDate(this.getDateOfBirth())+")";
    }
}
