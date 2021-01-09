package com.xzs.vhr.controller.salary;

import com.xzs.vhr.mapper.EmployeeMapper;
import com.xzs.vhr.model.Employee;
import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.model.RespPageBean;
import com.xzs.vhr.model.Salary;
import com.xzs.vhr.service.EmployeeService;
import com.xzs.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")Integer size){
        return employeeService.getEmployeeByPageWithSalary(page,size);
    }

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    @PutMapping("/")
    public RespBean updateEmployeeSalaryById(Integer eid,Integer sid){
        Integer result = employeeService.updateEmployeeSalaryById(eid, sid);
        if (result == 1 || result == 2){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }
}
