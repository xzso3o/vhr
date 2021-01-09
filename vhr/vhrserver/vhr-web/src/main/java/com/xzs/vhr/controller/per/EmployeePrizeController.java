package com.xzs.vhr.controller.per;

import com.xzs.vhr.model.Employeeec;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.model.Salary;
import com.xzs.vhr.service.EmployeeecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnel/ec")
public class EmployeePrizeController {

    @Autowired
    EmployeeecService employeeecService;

    @GetMapping("/")
    public List<Employeeec> getAllEmployeeecs(){
        return employeeecService.getAllEmployeeecs();
    }

    @PostMapping("/")
    public RespBean addEmployeeec(@RequestBody Employeeec employeeec){
        if (employeeecService.addEmployeeec(employeeec) == 1){
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmployeeec(@PathVariable Integer id){
        if (employeeecService.deleteEmployeeec(id) == 1){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateEmployeeecById(@RequestBody Employeeec employeeec){
        if (employeeecService.updateEmployeeecById(employeeec) == 1){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }
}
