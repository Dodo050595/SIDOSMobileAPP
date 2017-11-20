package Models;

import java.util.Date;
import java.util.List;

/**
 * Created by dejad on 2017-11-20.
 */

public class Task {

    private Date      acceptationDate;
    private Date      realizationDate;
    private Date      sentDate;
    private int       dosage;
    private String    name;
    private String    description;
    private String    type;
    private String    status;
    private String    cancellationCause;
    private List<Kon> refersTo;
    private Pracownik assignedTo;
    private int       id;

    public Task(Date acceptationDate, Date realizationDate, Date sentDate, int dosage, String name, String description, String type, String status, String cancellationCause, List<Kon> refersTo, Pracownik assignedTo) {
        this.acceptationDate = acceptationDate;
        this.realizationDate = realizationDate;
        this.sentDate = sentDate;
        this.dosage = dosage;
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
        this.cancellationCause = cancellationCause;
        this.refersTo = refersTo;
        this.assignedTo = assignedTo;
    }

    public Date getAcceptationDate() {
        return acceptationDate;
    }

    public void setAcceptationDate(Date acceptationDate) {
        this.acceptationDate = acceptationDate;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancellationCause() {
        return cancellationCause;
    }

    public void setCancellationCause(String cancellationCause) {
        this.cancellationCause = cancellationCause;
    }

    public List<Kon> getRefersTo() {
        return refersTo;
    }

    public void setRefersTo(List<Kon> refersTo) {
        this.refersTo = refersTo;
    }

    public Pracownik getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Pracownik assignedTo) {
        this.assignedTo = assignedTo;
    }
}
