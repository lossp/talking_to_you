package lossp.serviceImp;

import lossp.service.RouteService;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.CountDownLatch;

public class RouteServiceImp implements RouteService {
    Logger logger = LoggerFactory.getLogger(RouteServiceImp.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private ZooKeeper zooKeeper;

    @Value("${server.register.address}")
    private String address;

    public RouteServiceImp() {

    }

    @Override
    public void register(String data) {
        logger.info("进入Route注册中心初始化中");
        if (data != null) {
            zooKeeper = connectRegister();
            if (zooKeeper != null) {
                addRootNode(zooKeeper);
                createNode(zooKeeper, data);
            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return true;
    }

    private ZooKeeper connectRegister() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(address, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        logger.info("zookeeper status is " + Event.KeeperState.SyncConnected);
                        latch.countDown();
                    }
                    if (watchedEvent.getState() == Event.KeeperState.Disconnected) {
                        logger.info("zookeeper status is " + Event.KeeperState.Disconnected);
                    }
                }
            });
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zk;
    }

    private void addRootNode(ZooKeeper zooKeeper) {

    }

    private void createNode(ZooKeeper zooKeeper, String data) {

    }
}
