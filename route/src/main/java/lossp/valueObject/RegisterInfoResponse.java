package lossp.valueObject;

import java.io.Serializable;

public class RegisterInfoResponse implements Serializable {
    private long userId;
    private String username;

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
        return "RegisterInfoResponse: [" + "userId = " + userId + ", username = " + username + "]";
    }
}
