package lossp.session;

import io.netty.channel.socket.nio.NioSocketChannel;
import lossp.valueObject.UserInfo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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

    public static String printChannelMap() {
        Set<Map.Entry<Long, NioSocketChannel>> entrySet = CHANNEL_MAP.entrySet();
        Iterator<Map.Entry<Long, NioSocketChannel>> iterator = entrySet.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<Long, NioSocketChannel> entry = iterator.next();
            long userId = entry.getKey().longValue();
            NioSocketChannel channel = entry.getValue();
            stringBuilder.append("{ id = " + userId + " , channel = " + channel + "} ");
        }
        return stringBuilder.toString();
    }

    public static String printSessionMap() {
        Set<Map.Entry<Long, String>> entrySet = SESSION_MAP.entrySet();
        Iterator<Map.Entry<Long, String>> iterator = entrySet.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<Long, String> entry = iterator.next();
            long userId = entry.getKey().longValue();
            String username = entry.getValue();
            stringBuilder.append("{id = " + userId + " , username = " + username + "} ");
        }
        return stringBuilder.toString();
    }

    public static void removeChannel(NioSocketChannel socketChannel) {
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
