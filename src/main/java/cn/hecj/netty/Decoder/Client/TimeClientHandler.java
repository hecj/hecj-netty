package cn.hecj.netty.Decoder.Client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * by hecj
 */
public class TimeClientHandler extends ChannelHandlerAdapter{

    // 写日志
    private static final Logger logger =
            Logger.getLogger(TimeClientHandler.class.getName());

    private int counter;
    public TimeClientHandler(){}

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg)
            throws Exception{
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("Now is : " + body+";the countor is : "+ (++counter));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        // 当客户端和服务端建立tcp成功之后，Netty的NIO线程会调用channelActive
        // 发送查询时间的指令给服务端。
        // 调用ChannelHandlerContext的writeAndFlush方法，将请求消息发送给服务端
        // 当服务端应答时，channelRead方法被调用
        ByteBuf message = null;
        for (int i=0;i<100;i++){
            byte[] req = ("我要查询服务器时间"+i).getBytes();
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        logger.warning("message from:"+cause.getMessage());
        ctx.close();
    }
}
