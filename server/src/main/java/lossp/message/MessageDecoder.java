package lossp.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.util.SerializationUtils;

import java.util.List;

@Deprecated
public class MessageDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;
    public MessageDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void decode(ChannelHandlerContext context, ByteBuf in, List<Object> out) {
        System.out.println("Server decoding...");
        System.out.println(in.readableBytes());
        if (in.readableBytes() < 4) { return; }

        // 标记一下当前的readIndex的位置
        in.markReaderIndex();

        int length = in.readInt();
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Object obj = SerializationUtils.deserialize(bytes);

        out.add(obj);
    }
}
