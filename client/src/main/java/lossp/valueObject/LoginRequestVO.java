package lossp.valueObject;

public class LoginRequestVO {
    private Long userId;
    private String username;

    public LoginRequestVO(String username, Long userId) {
        this.username = username;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginRequestVo: [ userId = " + userId + " ,username = " + username + "]";
    }
}
