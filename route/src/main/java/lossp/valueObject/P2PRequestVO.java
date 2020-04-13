package lossp.valueObject;

public class P2PRequestVO {
    private Long userId;
    private Long receivedUserId;
    private String message;

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
        return "P2PRequestVO: [ userId = " + userId + " ,receive userId = " + receivedUserId + " ,message = " +message +"]";
    }
}
