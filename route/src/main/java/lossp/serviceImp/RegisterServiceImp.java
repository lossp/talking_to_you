package lossp.serviceImp;

import lossp.service.RegisterService;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

@Component
public class RegisterServiceImp implements RegisterService {
    Logger logger = LoggerFactory.getLogger(RegisterServiceImp.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private ZooKeeper zooKeeper;
    private ZooKeeper discoveryZooKeeper;
    private final List<String> addressCache = new CopyOnWriteArrayList<>();

    @Value("${server.register.address}")
    private String address;

    @Value("${server.register.path}")
    private String registerPath;

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
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    private ZooKeeper connectRegister() {
        System.out.println("the address = " + address);
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
        try {
            System.out.println("registerPath = " + registerPath);
            Stat stat = zooKeeper.exists(registerPath, false);
            if (stat == null) {
                logger.info("adding root node... ... ...");
                zooKeeper.create(registerPath, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createNode(ZooKeeper zooKeeper, String data) {
        try {
            byte[] infoBytes = data.getBytes();
            String path = zooKeeper.create(registerPath + "/child", infoBytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.info("after create node, the path = " + path);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
