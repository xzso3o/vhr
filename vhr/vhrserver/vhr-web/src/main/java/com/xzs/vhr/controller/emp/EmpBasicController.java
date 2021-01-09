package com.xzs.vhr.controller.emp;

import com.xzs.vhr.model.*;
import com.xzs.vhr.service.*;
import com.xzs.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          Employee employee,
                                          Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page, size, employee, beginDateScope);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee){
        if (employeeService.addEmp(employee) == 1){
            return RespBean.ok("添加成功！");
        }else {
            return RespBean.error("添加失败！");
        }
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.getAllNations();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/JobLevels")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean maxWorkID(){
        RespBean respBean = RespBean.build().setStatus(200)
                .setObj(String.format("%08d",employeeService.maxWorkID() + 1));
        return respBean;
    }

    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmpByEid(@PathVariable Integer id){
        if ( employeeService.deleteEmpByEid(id) == 1){
            return RespBean.ok("删除成功！");
        }else {
            return RespBean.error("删除失败！");
        }
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if ( employeeService.updateEmp(employee) == 1){
            return RespBean.ok("更新成功！");
        }else {
            return RespBean.error("更新失败！");
        }
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        //查询出页面所有数据
        List<Employee> list = (List<Employee>) employeeService.getEmployeeByPage(null, null, new Employee(), null).getData();
        //生成Excel然后返回ResponseEntity
        return POIUtils.employeeToExcel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        //拿到list集合
        List<Employee> list = POIUtils.excelToEmployee(file,nationService.getAllNations(),politicsstatusService.getAllPoliticsstatus(),
                departmentService.getAllDepartmentsWithOutChildren(),positionService.getAllPositions(),jobLevelService.getAllJobLevels());
        if (employeeService.addEmps(list) == list.size()){
            return RespBean.ok("上传成功！");
        }
        return RespBean.error("上传失败！");
    }
}
