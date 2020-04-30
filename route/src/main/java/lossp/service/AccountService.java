package lossp.service;

import lossp.valueObject.ChatMessageRequestVO;
import lossp.valueObject.RegisterInfoResponse;
import lossp.valueObject.ServerResponseVO;

public interface AccountService {
    public RegisterInfoResponse register(RegisterInfoResponse info);

    public String messagePush(String url, Long userId, ChatMessageRequestVO groupMessage) throws Exception;

    public void offLine(Long userId) throws Exception;

    public ServerResponseVO loadServerByUserId(Long userId);
}
