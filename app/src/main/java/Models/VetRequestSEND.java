package Models;

/**
 * Created by Dominik Deja on 11.06.2017.
 */

public class VetRequestSEND {

    private String description;
    private String injuryLocation;
    private String priority;
    private int horseId;
    private String employeeId;
    private String userId;
    private String status;

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

    public VetRequestSEND(String description, String injuryLocation, String priority, String userId, int horseId, String employeeId,String _status) {
        this.description = description;
        this.injuryLocation = injuryLocation;
        this.priority = priority;
        this.userId = userId;
        this.horseId = horseId;
        this.employeeId = employeeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
