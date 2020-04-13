package lossp.valueObject;

public class ChatMessageRequestVO {
    private long userId;
    private String message;

    public ChatMessageRequestVO(long userId, String message) {
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
        return "ChatMessageRequestVO:[ userId = " + userId + " ,message = " + message + "]";
    }
}
