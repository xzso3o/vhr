package com.xzs.vhr.controller.emp;

import com.xzs.vhr.model.Employee;
import com.xzs.vhr.model.RespPageBean;
import com.xzs.vhr.service.EmployeeAdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/advanced")
public class EmpAdvanceController {
    @Autowired
    EmployeeAdvanceService employeeAdvanceService;

//    @GetMapping("/")
//    public List<Employee> getAllEmployeeAdv(){
//        return employeeAdvanceService.getAllEmployeeAdv();
//    }

    @GetMapping("/")
    public RespPageBean getAllEmployeeAdvByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keywords) {
        return employeeAdvanceService.getEmployeeByPage(page, size,keywords);
    }
}
