package lossp.service;

import lossp.valueObject.GroupMessage;
import lossp.valueObject.RegisterInfoResponse;

public interface AccountService {
    public RegisterInfoResponse register(RegisterInfoResponse info);

    public void messagePush(String url, long userId, GroupMessage groupMessage) throws Exception;

    public void offLine(long userId) throws Exception;
}
