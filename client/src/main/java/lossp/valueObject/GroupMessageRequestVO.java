package lossp.valueObject;

public class GroupMessageRequestVO {
    private long userId;
    private String message;

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
        return "GroupMessageRequestVO: [ userId = " + userId + " ,message = " + message + "]";
    }
}
