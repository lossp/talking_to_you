package lossp.valueObject;

public class ChatMessageRequestVO {
    private Long userId;
    private String message;
    private Long receivedUserId;
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ChatMessageRequestVO(Long userId, String message, Long receivedUserId, String username) {
        this.userId = userId;
        this.message = message;
        this.receivedUserId = receivedUserId;
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

    @Override
    public String toString() {
        return "ChatMessageRequestVO:[ userId = " + userId + " ,username = " + username +" ,received user id = " + receivedUserId + " ,message = " + message + "]";
    }
}
