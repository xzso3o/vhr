package com.xzs.vhr.controller.per;

import com.xzs.vhr.model.Employeeremove;
import com.xzs.vhr.model.Employeetrain;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.service.EmployeeRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnel/remove")
public class EmployeeRemoveController {
    @Autowired
    EmployeeRemoveService employeeRemoveService;

    @GetMapping("/")
    public List<Employeeremove> getAllEmployeeRemoves(){
        return employeeRemoveService.getAllEmployeeRemoves();
    }

    @PostMapping("/")
    public RespBean addRemove(@RequestBody Employeeremove employeeremove){
        if (employeeRemoveService.addRemove(employeeremove) == 1){
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteRemoveTrain(@PathVariable Integer id){
        if (employeeRemoveService.deleteRemoveTrain(id) == 1){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateRemoveById(@RequestBody Employeeremove employeeremove){
        if (employeeRemoveService.updateRemoveById(employeeremove) == 1){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }
}
