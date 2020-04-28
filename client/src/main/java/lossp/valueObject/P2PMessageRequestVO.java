package lossp.valueObject;

public class P2PMessageRequestVO {
    private Long userId;
    private Long receivedUserId;
    private String message;
    private String username;

    public P2PMessageRequestVO(Long userId, Long receivedUserId, String message, String username) {
        this.userId = userId;
        this.receivedUserId = receivedUserId;
        this.message = message;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(Long receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "P2PMessageRequestVO: [ userId = " + userId + " ,username = " + username + " ,receiveUserId = " + receivedUserId + " ,message = " + message + "]";
    }
}
