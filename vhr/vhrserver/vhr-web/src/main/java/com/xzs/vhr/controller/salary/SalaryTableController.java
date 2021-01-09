package com.xzs.vhr.controller.salary;

import com.xzs.vhr.model.RespBean;
import com.xzs.vhr.model.RespPageBean;
import com.xzs.vhr.model.SalaryTable;
import com.xzs.vhr.service.EmployeeService;
import com.xzs.vhr.service.SalaryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/table")
public class SalaryTableController {

    @Autowired
    SalaryTableService salaryTableService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAll")
    public List<SalaryTable> getAllSalaryTable(){
        return salaryTableService.getAllSalaryTable();
    }

    @PostMapping("/")
    public RespBean addSalaryTable(@RequestBody SalaryTable salaryTable){
        if (salaryTableService.addSalaryTable(salaryTable) == 1){
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalaryTable(@PathVariable Integer id){
        if (salaryTableService.deleteSalaryTable(id) == 1){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateSalaryTable(@RequestBody SalaryTable salaryTable){
        if (salaryTableService.updateSalaryTable(salaryTable) == 1){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }

    @GetMapping("/")
    public RespPageBean getEmployeeByPageWithSalaryTable(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")Integer size){
        return employeeService.getEmployeeByPageWithSalaryTable(page,size);
    }

    @PutMapping("/update")
    public RespBean updateEmployeeSalaryTableById(Integer eid,Integer stid){
        Integer result = employeeService.updateEmployeeSalaryTableById(eid, stid);
        if (result == 1 || result == 2){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }

}
