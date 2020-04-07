package lossp.services;

public interface Server {
    // 停止服务
    public void stop();

    // 启动服务
    public void start() throws Exception;

    // 判断服务是否在运行中
    public boolean isRunning();

}
