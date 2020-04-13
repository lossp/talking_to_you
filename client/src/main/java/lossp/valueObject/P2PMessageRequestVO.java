package lossp.valueObject;

public class P2PMessageRequestVO {
    private long userId;
    private long receivedUserId;
    private String message;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(long receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "P2PMessageRequestVO: [ userId = " + userId + " ,receiveUserId = " + receivedUserId + " ,message = " + message + "]";
    }
}
