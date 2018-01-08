package Models;

import java.io.Serializable;

/**
 * Created by dejad on 2017-11-27.
 */

public class TaskChangeStatusDto implements Serializable{
    private int id;
    private String Status;
    //private String CancellationCause;

    public TaskChangeStatusDto(int id, String status) {
        this.id = id;
        Status = status;
    }

//    public TaskChangeStatusDto(int id, String status, String cancellationCause) {
//        this.id = id;
//        Status = status;
//        CancellationCause = cancellationCause;
//    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

//    public String getCancellationCause() {
//        return CancellationCause;
//    }
//
//    public void setCancellationCause(String cancellationCause) {
//        CancellationCause = cancellationCause;
//    }
}
