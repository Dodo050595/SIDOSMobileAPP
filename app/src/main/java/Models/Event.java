package Models;

import java.io.Serializable;
import java.util.Date;

import HelperClasses.Utils;

/**
 * Created by dejad on 2017-12-10.
 */

public class Event implements Serializable{

    private String event;
    private String description;
    private Date dateStart;
    private Date dateEnd;
    private String url;
    private String eventColor;
    private int Id;

    public Event(String event, String description, Date dateStart, Date dateEnd, String url,String eventColor, int id) {
        event = event;
        description = description;
        dateStart = dateStart;
        dateEnd = dateEnd;
        url = url;
        eventColor = eventColor;
        Id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getUrl() {
        return Utils.URLFORAPI + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEventColor() {
        return eventColor;
    }

    public void setEventColor(String eventColor) {
        this.eventColor = eventColor;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
