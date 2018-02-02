package Models;

import java.io.Serializable;
import java.util.Date;

import HelperClasses.Utils;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class Pracownik implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private Date hireDate;
    private String employeeType;
    private String userName;
    private String emailAddress;
    private Date dateOfBirth;
    private String phoneNumber;
    private String profilePicture;

    public Pracownik(String id, String firstName, String lastName, Date hireDate, String employeeType, String userName, String emailAddress, Date dateOfBirth, String phoneNumber,String profilePicture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.employeeType = employeeType;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return (firstName==null) ? "" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return (lastName==null) ? "" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getUserName() {
        return (userName==null) ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getemailAddress() {
        return (emailAddress==null) ? "" : emailAddress;
    }

    public void setemailAddress(String email) {
        this.emailAddress = email;
    }

    public Date getdateOfBirth() {
        return dateOfBirth;
    }

    public void setdateOfBirth(Date birthDate) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getphoneNumber() {
        return (phoneNumber==null) ? "" : phoneNumber;
    }

    public void setphoneNumber(String phoneNmber) {
        this.phoneNumber = phoneNmber;
    }

    public String toString(){
        return getFirstName() + " " + getLastName();
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
