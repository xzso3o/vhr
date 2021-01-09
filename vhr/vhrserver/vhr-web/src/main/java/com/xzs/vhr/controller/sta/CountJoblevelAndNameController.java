package com.xzs.vhr.controller.sta;

import com.xzs.vhr.model.Employee;
import com.xzs.vhr.model.JobLevel;
import com.xzs.vhr.service.EmployeeService;
import com.xzs.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics/score")
public class CountJoblevelAndNameController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/countAndname")
    public List<Employee> getJoblevelCountAndName(){
        return employeeService.getJoblevelCountAndName();
    }

    @GetMapping("/joblevelsName")
    public List<JobLevel> getAllJoblevelsName(){
        return jobLevelService.getAllJoblevelsName();
    }
}
