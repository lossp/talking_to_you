package lossp.valueObject;

public class GroupMessage {
    private long userId;
    private String message;

    public GroupMessage(long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

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

    @Override
    public String toString() {
        return "GroupMessage:[ userId = " + userId + " ,message = " + message + "]";
    }
}
