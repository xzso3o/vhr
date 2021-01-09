package com.xzs.vhr.controller.sta;

import com.xzs.vhr.model.Department;
import com.xzs.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics/personnel")
public class CountDepartmentController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/countDepartment")
    public List<Department> getCountDepartment(){
        return employeeService.getCountDepartment();
    }

    @GetMapping("/countDepartmentName")
    public List<Department> getcountDepartmentName(){
        return employeeService.getcountDepartmentName();
    }
}
