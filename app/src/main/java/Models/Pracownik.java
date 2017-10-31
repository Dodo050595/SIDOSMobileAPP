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
    private String email;
    private Date birthDate;
    private String phoneNmber;
    private String profilePicture;

    public Pracownik(String id, String firstName, String lastName, Date hireDate, String employeeType, String userName, String email, Date birthDate, String phoneNmber,String profilePicture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.employeeType = employeeType;
        this.userName = userName;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNmber = phoneNmber;
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNmber() {
        return phoneNmber;
    }

    public void setPhoneNmber(String phoneNmber) {
        this.phoneNmber = phoneNmber;
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
