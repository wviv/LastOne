package com.wa.last.ws.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 接收/处理/响应浏览器WebSocket请求的核心业务处理类
 *
 * @author YETA
 * @date 2018/11/25/19:27
 */
@Slf4j
public class CommonSimpleChannelInboundHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Value("${ws.url}")
    private String url;


    //TODO  需要配置nginx保持链接 以及心跳检测
    //TODO  心跳检测

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            //处理浏览器向服务器发起HTTP握手请求
            FullHttpRequest request = (FullHttpRequest) msg;

            handleHttpRequest(ctx, request);

            //TODO  绑定时关联用户和channel

            ConcurrentMap<String, String> urlParams = urlParams(request.uri());

            //TODO 这里可以将id 换成机构id和key
            if (!urlParams.containsKey(ChannelHandlerPool.key)) {
                throw new RuntimeException("没有用户标识");
            } else {
                Channel channel = ctx.channel();
                channel.attr(ChannelHandlerPool.attributeKey).set(urlParams.get(ChannelHandlerPool.key));
                ChannelHandlerPool.channelGroup.add(channel);
            }

        } else if (msg instanceof WebSocketFrame) {
            //处理WebSocket连接
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }


    /**
     * 读取url 中的 参数
     *
     * @param url
     * @return
     */
    private static ConcurrentMap<String, String> urlParams(String url) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }


    /**
     * 服务器处理浏览器WebSocket请求的核心方法
     *
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
//    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //处理浏览器向服务器发起HTTP握手请求
        if (o instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        } else if (o instanceof WebSocketFrame) {       //处理WebSocket连接
            handleWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    /**
     * 处理浏览器向服务器发起HTTP握手请求
     *
     * @param ctx
     * @param req
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        //  判断是否是ws 请求，好像用不到
        if (!req.decoderResult().isSuccess() || !("websocket").equals(req.headers().get("Upgrade"))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //握手协议
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
                url,
                null,
                false);
        handshaker = factory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 服务器向浏览器发送消息
     *
     * @param ctx
     * @param request
     * @param res
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, DefaultFullHttpResponse res) {
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        //发送数据
        ChannelFuture future = ctx.channel().writeAndFlush(res);
        if (res.status().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }


//TODO  不会跨域？？？？还是以及在外层处理了跨域？？？？？
        // 返回应答给客户端 跨域和其他处理
//        if (response.status().code() != 200) {
//            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
//            response.content().writeBytes(buf);
//            buf.release();
//            //允许跨域访问 设置头部信息
//            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE");
//            response.headers().set(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept");
//            HttpHeaders.setContentLength(response, response.content().readableBytes());
//        }
//
//        // 如果是非Keep-Alive，关闭连接 保持Keep-Alive
//        ChannelFuture f = ctx.channel().writeAndFlush(response);
//        if (!HttpHeaders.isKeepAlive(request) || response.status().code() != 200) {
//            f.addListener(ChannelFutureListener.CLOSE);
//        }
    }

    /**
     * 处理WebSocket连接
     *
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断是否关闭WebSocket消息
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
            return;
        }
        //判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame());
            return;
        }
        //判断是否是二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            log.info("收到二进制消息");
            throw new RuntimeException(this.getClass().getName() + " 不支持二进制消息");
        }
    }

    /**
     * 出现异常时调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 浏览器与服务器创建连接时调用
     * @param ctx
     * @throws Exception
     */
//    用不到，登录时绑定了
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        //TODO  重写
//        super.channelActive(ctx);
//    }

    /**
     * 浏览器与服务器断开连接时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    /**
     * 服务器接收浏览器发送过来的数据结束之后调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 心跳检测
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        log.info("pong 客户端 : {}",ctx.channel().id());
        //超时事件
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleEvent = (IdleStateEvent) evt;
            if (idleEvent.state() == IdleState.READER_IDLE) {//读
                log.trace("read 超时 : {}",ctx.channel().id());
            } else if (idleEvent.state() == IdleState.WRITER_IDLE) {//写
                log.trace("write 超时 : {}", ctx.channel().id());
                ctx.channel().writeAndFlush(new PongWebSocketFrame());
            } else if (idleEvent.state() == IdleState.ALL_IDLE) {//全部

            }

        }
        //长时间没有操作，并且没有链接断开，发送一个pong包
//        ctx.channel().writeAndFlush(new PongWebSocketFrame());
        super.userEventTriggered(ctx, evt);
    }
}
