package lossp.valueObject;

public class P2PMessageRequestVO {
    private Long userId;
    private Long receivedUserId;
    private String message;

    public P2PMessageRequestVO(Long userId, Long receivedUserId, String message) {
        this.userId = userId;
        this.receivedUserId = receivedUserId;
        this.message = message;
    }

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
