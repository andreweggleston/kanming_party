package kanming_party.Multiplayer.Client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import kanming_party.Multiplayer.Message;
import kanming_party.Multiplayer.UnixTime;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by student on 1/11/18.
 */
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 4) {
            return;
        }

        out.add(new Message(new String(ByteBufUtil.getBytes(in), Charset.defaultCharset())));
    }
}
