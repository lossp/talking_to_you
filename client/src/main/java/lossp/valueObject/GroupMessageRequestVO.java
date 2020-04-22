package lossp.valueObject;

public class GroupMessageRequestVO {
    private Long userId;
    private String message;

    public GroupMessageRequestVO(Long userId, String message) {
        this.message = message;
        this.userId = userId;
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
        return "GroupMessageRequestVO: [ userId = " + userId + " ,message = " + message + "]";
    }
}
