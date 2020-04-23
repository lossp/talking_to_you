package lossp.valueObject;

public class StringRequestVO {
    private String message;
    private Long receiveUserId;

    public Long getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
