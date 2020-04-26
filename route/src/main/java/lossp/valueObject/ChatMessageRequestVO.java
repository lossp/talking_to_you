package lossp.valueObject;

public class ChatMessageRequestVO {
    private Long userId;
    private String message;
    private Long receivedUserId;


    public ChatMessageRequestVO(Long userId, String message, Long receivedUserId) {
        this.userId = userId;
        this.message = message;
        this.receivedUserId = receivedUserId;
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
        return "ChatMessageRequestVO:[ userId = " + userId + " ,received user id = " + receivedUserId + " ,message = " + message + "]";
    }
}
