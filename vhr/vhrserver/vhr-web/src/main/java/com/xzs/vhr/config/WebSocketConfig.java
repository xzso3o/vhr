package com.xzs.vhr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//开启Websocket消息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //实现两个方法

    @Override//前端要和服务端连接Websocket的注册点
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();//前端连接的那个点，ws做前缀，与http请求分清开来，前端并需要使用SockJS这个工具
                                            //允许所有的域发送websocket请求
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");//配置一下消息代理，一对一使用queue
    }
}
