package Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dominik Deja on 28.05.2017.
 */

public class User implements Serializable{
    private String id;
    private String userName;
    private String email;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(String id, String userName, String email, Date birthDate, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return getFirstName() + " " + getLastName();
    }
}
