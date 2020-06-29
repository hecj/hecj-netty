package cn.hecj.netty.Decoder2.Client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
/**
 * by hecj
 */
public class TimeClientHandler extends ChannelHandlerAdapter{

    private int counter;
    public TimeClientHandler(){}

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg)
            throws Exception{
        String body = (String)msg;
        System.out.println("Now is : " + body+";the countor is : "+ (++counter));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        // 分隔符 System.getProperty("line.separator")
        byte[] req =("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
        ByteBuf message = null;
        for (int i=0;i<100;i++){
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        ctx.close();
    }
}
