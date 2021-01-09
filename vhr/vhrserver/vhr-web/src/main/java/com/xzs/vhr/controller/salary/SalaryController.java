package com.xzs.vhr.controller.salary;

import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.model.Salary;
import com.xzs.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        if (salaryService.addSalary(salary) == 1){
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id){
        if (salaryService.deleteSalary(id) == 1){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateSalaryById(@RequestBody Salary salary){
        if (salaryService.updateSalaryById(salary) == 1){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }
}
