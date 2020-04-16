package lossp.valueObject;

public class ServerResponseVO {
    private String ip;
    private Integer serverPort;
    private Integer httpPort;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public Integer getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    @Override
    public String toString() {
        return "ServerResponseVO: [ ip = " + ip + " ,server port = " + serverPort + " , http port = " + httpPort + "]";
    }
}
