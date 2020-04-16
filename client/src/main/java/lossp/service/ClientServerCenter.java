package lossp.service;

import lossp.valueObject.ServerInfoResVO;
import org.springframework.stereotype.Component;

@Component
public interface ClientServerCenter {
    public void start() throws Exception;

    public void close();

    public void sendMessage(String message);

    public ServerInfoResVO.ServerInfo userLogin(String username, Long userId);
}
