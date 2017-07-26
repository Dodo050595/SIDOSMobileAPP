package Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import HelperClasses.HelperMethods;

/**
 * Created by Dominik Deja on 07.05.2017.
 */

public class Kon implements Serializable{

    private int id;
    private String name;
    private Date birthdate;
    private String character;
    private String height;

    public Kon(int id, String name, Date birthdate, String character, String height) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.character = character;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String toString(){
        return getName() + " ("+getCharacter()+")" + " ("+ HelperMethods.getStringFromDate(this.getBirthdate())+")";
    }
}
