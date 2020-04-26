package lossp.valueObject;

public class P2PMessageRequestVO {
    private Long userId;
    private String message;
    private Long receivedUserId;

    public P2PMessageRequestVO(Long userId, String message, Long receivedUserId) {
        this.userId = userId;
        this.message = message;
        this.receivedUserId = receivedUserId;
    }

    public Long getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(Long receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
        return "P2PMessageRequestVO = [ message = " + message + " ,receive user id = " + receivedUserId + " ,user id = " + userId + " ]";
    }
}
