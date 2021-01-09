package com.xzs.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzs.vhr.model.Hr;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    HrService hrService;
    @Autowired
    MyFilter myFilter;
    @Autowired
    MyDecisionManager myDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    //防止登录被拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.anyRequest().authenticated()//所有请求都需要认证后才能访问
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(myDecisionManager);
                        object.setSecurityMetadataSource(myFilter);
                        return object;
                    }
                })
                .and()
                .formLogin()  //配置登陆页面
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {//登录成功后的回调
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");//返回一个json字符串
                        PrintWriter out = resp.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();  //获取登录成功的Hr对象
                        hr.setPassword(null);   //登录后不显示密码
                        RespBean ok = RespBean.ok("登陆成功！", hr); //转换成我们设置的对应实体类
                        String s = new ObjectMapper().writeValueAsString(ok);//把该信息转换成一个字符串
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {//登录失败后的回调
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RespBean error = RespBean.error("登陆失败！");
                        if(exception instanceof LockedException){
                            error.setMsg("账户被锁定，请联系管理员！");
                        }else if (exception instanceof CredentialsExpiredException){
                            error.setMsg("密码过期，请联系管理员！");
                        }else if (exception instanceof AccountExpiredException){
                            error.setMsg("账户过期，请联系管理员！");
                        }else if (exception instanceof DisabledException){
                            error.setMsg("账户被禁用，请联系管理员！");
                        }else if (exception instanceof BadCredentialsException){
                            error.setMsg("用户名或密码输入错误，请重新输入！");
                        }
                        String s = new ObjectMapper().writeValueAsString(error);//把该信息转换成一个字符串
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()//跟这里相关的接口都能直接地返回
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        String s = new ObjectMapper().writeValueAsString(RespBean.ok("注销成功！"));
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()////使用Postman要关掉这个csrf攻击
                .csrf().disable().exceptionHandling()
                //没有认证时，在这里处理结果，不要重定向
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(401);
                        RespBean error = RespBean.error("访问失败！");
                        if(exception instanceof InsufficientAuthenticationException){
                            error.setMsg("请求失败，请联系管理员！");
                        }
                        String s = new ObjectMapper().writeValueAsString(error);//把该信息转换成一个字符串
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                });
        //多端登录自动踢下线
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse resp = event.getResponse();
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(RespBean.error("您已在另一台设备登录，本次登录已下线!")));
            out.flush();
            out.close();
        }), ConcurrentSessionFilter.class);
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Hr hr = (Hr) authentication.getPrincipal();
                    hr.setPassword(null);
                    RespBean ok = RespBean.ok("登录成功!", hr);
                    String s = new ObjectMapper().writeValueAsString(ok);
                    out.write(s);
                    out.flush();
                    out.close();
                }
        );
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    RespBean respBean = RespBean.error(exception.getMessage());
                    if (exception instanceof LockedException) {
                        respBean.setMsg("账户被锁定，请联系管理员!");
                    } else if (exception instanceof CredentialsExpiredException) {
                        respBean.setMsg("密码过期，请联系管理员!");
                    } else if (exception instanceof AccountExpiredException) {
                        respBean.setMsg("账户过期，请联系管理员!");
                    } else if (exception instanceof DisabledException) {
                        respBean.setMsg("账户被禁用，请联系管理员!");
                    } else if (exception instanceof BadCredentialsException) {
                        respBean.setMsg("用户名或者密码输入错误，请重新输入!");
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                }
        );
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/doLogin");
        //多端登录自动踢下线
//        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
//        sessionStrategy.setMaximumSessions(1);//最大session数为1
//        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;
    }
}
