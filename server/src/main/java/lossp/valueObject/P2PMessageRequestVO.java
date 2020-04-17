package lossp.valueObject;

public class P2PMessageRequestVO {
    private String username;
    private Long userId;
    private String message;

    public P2PMessageRequestVO(String username, Long userId, String message) {
        this.userId = userId;
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "P2PMessageRequestVO = [ message = " + message + " ,user = " + username + " ,user id = " + userId + " ]";
    }
}
