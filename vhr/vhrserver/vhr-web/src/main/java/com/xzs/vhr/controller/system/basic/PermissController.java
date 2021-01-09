package com.xzs.vhr.controller.system.basic;

import com.xzs.vhr.model.Menu;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.model.Role;
import com.xzs.vhr.service.MenuService;
import com.xzs.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRoles(Integer rid, Integer[] mids){
        if (menuService.updateMenuRoles(rid,mids)){
            return RespBean.ok("更新成功!");
        }else {
            return RespBean.error("更新失败！");
        }
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if (roleService.addRole(role) == 1){
            return RespBean.ok("添加成功!");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid){
        if (roleService.deleteRoleById(rid) == 1){
            return RespBean.ok("删除成功!");
        }else {
            return RespBean.error("删除失败！");
        }
    }
}

