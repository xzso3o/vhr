package com.xzs.vhr.controller.sta;

import com.xzs.vhr.model.Employee;
import com.xzs.vhr.model.JobLevel;
import com.xzs.vhr.model.Position;
import com.xzs.vhr.service.EmployeeService;
import com.xzs.vhr.service.JobLevelService;
import com.xzs.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics/all")
public class CountPositionAndNameController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    PositionService positionService;

    @GetMapping("/countAndname")
    public List<Employee> getPositionCountAndName(){
        return employeeService.getPositionCountAndName();
    }

    @GetMapping("/positionNames")
    public List<Position> getAllPositionName(){
        return positionService.getAllPositionNames();
    }
}
