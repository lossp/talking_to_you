package lossp.session;

import io.netty.channel.socket.nio.NioSocketChannel;
import lossp.valueObject.UserInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionHolder {
    private static final Map<Long, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final Map<Long, String> SESSION_MAP = new ConcurrentHashMap<>();

    public static NioSocketChannel getChannel(Long userId) {
        return CHANNEL_MAP.get(userId);
    }

    public static Map<Long, NioSocketChannel> getChannelMap() {
        return CHANNEL_MAP;
    }

    public static Map<Long, String> getSessionMap() {
        return SESSION_MAP;
    }

    public static void saveSession(Long userId, String username) {
        SESSION_MAP.put(userId, username);
    }

    public static void removeSession(Long userId) {
        SESSION_MAP.remove(userId);
    }

    public static void saveChannel(Long userId, NioSocketChannel socketChannel) {
        CHANNEL_MAP.put(userId, socketChannel);
    }

    public static void removeChannle(NioSocketChannel socketChannel) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == socketChannel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }

    public static UserInfo getUserInfo(NioSocketChannel socketChannel) {
        for (Map.Entry<Long, NioSocketChannel> entry : CHANNEL_MAP.entrySet()) {
            NioSocketChannel value = entry.getValue();
            if (socketChannel == value) {
                Long key = entry.getKey();
                String name = SESSION_MAP.get(key);
                UserInfo userInfo = new UserInfo(key, name);
                return userInfo;
            }
        }
        return null;
    }


}
