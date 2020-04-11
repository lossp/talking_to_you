package lossp.serviceImp;

import lossp.service.DiscoveryService;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DiscoveryServiceImp implements DiscoveryService {
    Logger logger = LoggerFactory.getLogger(DiscoveryService.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private List<String> addressCache = new CopyOnWriteArrayList<>();
    private ZooKeeper zooKeeper;

    @Value("${server.register.address}")
    private String address;

    @Value("${server.register.path}")
    private String registerPath;


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

    @Override
    public String discovery(String name) {
        logger.info("进入服务发现 ... ... ...");
        String serverPath = registerPath;
        zooKeeper = connectRegister();
        try {
            if (zooKeeper != null) {
                // 首先从本地获取
                String discoveryAddress;
                int addressSize = addressCache.size();
                if (addressSize > 0) {
                    if (addressSize == 1) {
                        discoveryAddress = addressCache.get(0);
                    } else {
                        discoveryAddress = addressCache.get(ThreadLocalRandom.current().nextInt(addressSize));
                    }
                    logger.info(">>> get address node from local: " + discoveryAddress);
                    // 否则从服务端获取
                } else {
                    List<String> addressList = zooKeeper.getChildren(serverPath, true);
                    addressCache.addAll(addressList);


                    if (CollectionUtils.isEmpty(addressList)) {
                        throw new RuntimeException(">>>can't find any address node on path " + serverPath);
                    }

                    if (addressList.size() ==1) {
                        discoveryAddress = addressCache.get(0);
                    } else {
                        discoveryAddress = addressCache.get(ThreadLocalRandom.current().nextInt(addressList.size()));
                    }

                    System.out.println(">>>get address node:" + discoveryAddress);
                    return discoveryAddress;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
