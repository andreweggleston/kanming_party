package kanming_party.Multiplayer.Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import kanming_party.Multiplayer.Message;
import kanming_party.Multiplayer.UnixTime;

import java.nio.charset.Charset;

/**
 * Created by student on 1/11/18.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        ctx.writeAndFlush(in.retain());
        System.out.println(in.refCnt());
        while (in.isReadable()) {
            System.out.print((char) in.readByte());
        }
        in.release();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        final ChannelFuture f = ctx.writeAndFlush(new Message());

        f.addListener((ChannelFutureListener) channelFuture -> {
            assert f == channelFuture;
            ctx.close();
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
