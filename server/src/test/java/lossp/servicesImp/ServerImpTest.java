package lossp.servicesImp;

public class ServerImpTest {
    public static void main(String[] args) throws InterruptedException{
        ServerImp serverImp = new ServerImp();
        serverImp.start();
        System.out.println("服务启动完成");
        System.out.println("服务关闭中");
        serverImp.stop();
    }
}
