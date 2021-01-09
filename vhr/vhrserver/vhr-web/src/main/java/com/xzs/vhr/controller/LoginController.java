package com.xzs.vhr.controller;

import com.xzs.vhr.config.VerificationCode;
import com.xzs.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {

    //在用户直接访问一些接口时，我们后端不返回一个登陆页面让他登录，而只返回一个提示
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登陆，请登陆！");
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }
}
