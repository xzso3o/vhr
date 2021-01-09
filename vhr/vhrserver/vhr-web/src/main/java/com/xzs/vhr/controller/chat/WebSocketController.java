package com.xzs.vhr.controller.chat;

import com.xzs.vhr.model.ChatMsg;
import com.xzs.vhr.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;

@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    //处理前端发送消息过来的方法
    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg){//用来获取当前登录的用户,消息就是从这个用户来的，收到的消息就是ChatMsg
        Hr hr = (Hr) authentication.getPrincipal();
        chatMsg.setFrom(hr.getUsername());
        chatMsg.setFromNickname(hr.getName());
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);//第一个参数为你要发给谁，第二个参数就是前端的队列，我们将在前端监听这个队列的内容,第三个参数为你要发的消息对象
    }
}
