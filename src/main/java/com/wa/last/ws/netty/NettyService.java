package com.wa.last.ws.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Netty启动类
 *
 * @author YETA
 * @date 2018/11/25/20:18
 */
@Slf4j
//@Configuration
public class NettyService implements CommandLineRunner {

    @Value("${ws.port}")
    Integer port;

    @Value("${ws.url}")
    String url;

    @Override
    public void run(String... args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("dddd-boss", true));
        EventLoopGroup workerGroup = new NioEventLoopGroup(new DefaultThreadFactory("dddd-worker", true));

        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
//                    .childHandler(new CommonChannelInitializer())
                .childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                        ch.pipeline().addLast(new HttpServerCodec());
                        //以块的方式来写的处理器
                        ch.pipeline().addLast(new ChunkedWriteHandler());
                        //TODO  心跳检测时间，只检测写，不会出现读
                        ch.pipeline().addLast(new IdleStateHandler(0,15,0));
                        ch.pipeline().addLast(new HttpObjectAggregator(65536));
//                        ch.pipeline().addLast(new WebSocketServerProtocolHandler(url, null, true, 1024 * 1024 * 10, false, true, 10000L));
                        ch.pipeline().addLast(new CommonSimpleChannelInboundHandler());//自定义消息处理类
//                        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/webSocket"));
                    }
                });
        sb.bind(port).sync().channel();
        log.info("Netty已启动，端口 : {} ", port);
//        Channel channel = sb.bind(port).sync().channel();
//        channel.closeFuture().sync();
    }
}
