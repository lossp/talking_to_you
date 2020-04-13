package lossp.valueObject;

import java.io.Serializable;

public class ServerInfoResVO implements Serializable {
    private String code;
    private String message;
    private Object requestNo;
    private ServerInfo serverInfo;


    public static class ServerInfo {
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
            return "ServerInfo: [ ip = " + ip + " ,server port = " +serverPort + " ,http port = " + httpPort + "]";
        }
    }

    @Override
    public String toString() {
        return "ServerInfoResVO:[ code = " + code + " ,message = " + message + " ,requestNo = " + requestNo + " ,server info = " + serverInfo + "]";
    }
}
