package lossp.service;

import lossp.valueObject.ServerResponseVO;
import org.springframework.stereotype.Component;

@Component
public interface ClientServerCenter {
    public void logout();

    public void sendMessage(String message, Long receiveUserId);

    public ServerResponseVO userLogin(String username, Long userId);

    public boolean getConnectServer();
}
