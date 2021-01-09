package com.xzs.vhr.service;


import com.xzs.vhr.mapper.MenuMapper;
import com.xzs.vhr.mapper.MenuRoleMapper;
import com.xzs.vhr.model.Hr;
import com.xzs.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    MenuMapper menuMapper;
    @Resource
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenusByHrId() {
                                    //获取当前登录的用户信息对象，再强转成Hr
        return menuMapper.getMenusByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

//    @Cacheable
    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }


    @Transactional//添加事务注解，否则你删除完没添加成功就是有错
    public boolean updateMenuRoles(Integer rid, Integer[] mids) {
        //先去删除该rid所有的mids
        menuRoleMapper.deleteByRid(rid);
        if(mids == null || mids.length == 0){
            return true;
        }
        Integer result =menuRoleMapper.insertRecord(rid,mids);
        return result == mids.length;
    }

}
