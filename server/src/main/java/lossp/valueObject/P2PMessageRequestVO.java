package lossp.valueObject;

public class P2PMessageRequestVO {
    private Long userId;
    private String message;

    public P2PMessageRequestVO(String username, Long userId, String message) {
        this.userId = userId;
        this.message = message;
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
        return "P2PMessageRequestVO = [ message = " + message + " ,user id = " + userId + " ]";
    }
}
