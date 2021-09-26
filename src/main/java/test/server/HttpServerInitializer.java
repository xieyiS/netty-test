package test.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: xieyi
 * @create: 2021/9/23 14:13
 * @conent:
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //处理http消息的编解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //我们替换成如下两行也是可以的
        /*pipeline.addLast("httpResponseEndcoder", new HttpResponseEncoder());
        pipeline.addLast("HttpRequestDecoder", new HttpRequestDecoder());*/
        //将请求转换为单一的 FullHttpReques
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        //添加自定义的ChannelHandler
        //pipeline.addLast("httpServerHandler", new HttpServerChannelHandler());
        pipeline.addLast("httpServerHandler", new HttpServerChannelHandler2());
    }
}
