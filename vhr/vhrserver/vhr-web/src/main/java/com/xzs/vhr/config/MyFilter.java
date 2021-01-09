package com.xzs.vhr.config;

import com.xzs.vhr.model.Menu;
import com.xzs.vhr.model.Role;
import com.xzs.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

//根据你的请求地址分析出来需要哪些角色，匹配上就返回需要的角色，没匹配上返回个默认值
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;
    //用来匹配路径
    AntPathMatcher antpathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //菜单里面的pattern就定义了路径规则
        List<Menu> menus = menuService.getAllMenusWithRole();
        //遍历menus，进行路径匹配，返回路径匹配成功所需要的角色
        for (Menu menu : menus) {
            if(antpathMatcher.match(menu.getUrl(),requestUrl)){
                List<Role> roles = menu.getRoles();
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
                //返回的是Collection<ConfigAttribute>，可以通过SecurityConfig.createList()创造
                //又因为需要的是一个字符串数组，所以还得自己遍历roles放入一个字符串数组里
            }
        }
        //如果路径都匹配不上，给他返回一个默认资源
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
