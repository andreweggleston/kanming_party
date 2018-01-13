package kanming_party.Multiplayer.Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import kanming_party.Multiplayer.Message;
import kanming_party.Multiplayer.UnixTime;

import java.nio.charset.Charset;

/**
 * Created by student on 1/11/18.
 */
public class MessageEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        Message m = (Message) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeCharSequence(m.value(), Charset.defaultCharset());
        ctx.write(encoded, promise);
    }
}
