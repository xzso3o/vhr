package com.xzs.vhr.controller.per;

import com.xzs.vhr.model.AdjustSalary;
import com.xzs.vhr.model.Employeeec;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.service.AdjustSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnel/salary")
public class EmployeeAdjustSalaryController {
    @Autowired
    AdjustSalaryService adjustSalaryService;

    @GetMapping("/")
    public List<Employeeec> getAllAdjustSalaries(){
        return adjustSalaryService.getAllAdjustSalaries();
    }

    @PostMapping("/")
    public RespBean addAdjustSalary(@RequestBody AdjustSalary adjustSalary){
        if (adjustSalaryService.addAdjustSalary(adjustSalary) == 1){
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteAdjustSalary(@PathVariable Integer id){
        if (adjustSalaryService.deleteAdjustSalary(id) == 1){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateAdjustSalaryById(@RequestBody AdjustSalary adjustSalary){
        if (adjustSalaryService.updateAdjustSalaryById(adjustSalary) == 1){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }
}
