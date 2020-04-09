package lossp.message;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private long userId;
    private String message;
    private int delay;
    private Date time;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message = " + message + " ,userId = " + userId + " ,delay = " + delay + " ,time = " + time;
    }
}
