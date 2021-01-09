package com.xzs.vhr.controller.sta;

import com.xzs.vhr.model.Department;
import com.xzs.vhr.model.Employee;
import com.xzs.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics/recored")
public class CountDegreeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/countDegree")
    public List<Employee> getCountDegree(){
        return employeeService.getCountDegree();
    }

    @GetMapping("/countDegreeName")
    public List<Employee> getCountDegreeName(){
        return employeeService.getCountDegreeName();
    }
}
