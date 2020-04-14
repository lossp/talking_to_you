package lossp.service;

import org.springframework.stereotype.Component;

@Component
public interface ClientServerCenter {
    public void start() throws Exception;

    public void close();

    public void sendMessage(String message);
}
