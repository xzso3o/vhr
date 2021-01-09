package com.xzs.vhr.controller.per;

import com.xzs.vhr.model.Employeeec;
import com.xzs.vhr.model.Employeetrain;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.service.EmployeeecService;
import com.xzs.vhr.service.EmployeetrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnel/train")
public class EmployeeTrainController {

    @Autowired
    EmployeetrainService employeetrainService;

    @GetMapping("/")
    public List<Employeeec> getAllEmployeeTrains(){
        return employeetrainService.getAllEmployeeTrains();
    }

    @PostMapping("/")
    public RespBean addEmployeeTrain(@RequestBody Employeetrain employeetrain){
        if (employeetrainService.addEmployeeTrain(employeetrain) == 1){
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmployeeTrain(@PathVariable Integer id){
        if (employeetrainService.deleteEmployeeTrain(id) == 1){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateEmployeeTrainById(@RequestBody Employeetrain employeetrain){
        if (employeetrainService.updateEmployeeTrainById(employeetrain) == 1){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }
}


