package com.xzs.vhr.service;

import com.xzs.vhr.mapper.DepartmentMapper;
import com.xzs.vhr.mapper.EmployeeremoveMapper;
import com.xzs.vhr.mapper.JobLevelMapper;
import com.xzs.vhr.model.Department;
import com.xzs.vhr.model.Employeeremove;
import com.xzs.vhr.model.JobLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeRemoveService {

    @Resource
    EmployeeremoveMapper employeeremoveMapper;
    @Resource
    DepartmentMapper departmentMapper;
    @Resource
    JobLevelMapper jobLevelMapper;

    public List<Employeeremove> getAllEmployeeRemoves() {
        List<Employeeremove> employeeRemoves = employeeremoveMapper.getAllEmployeeRemoves();
        for (int i = 0; i < employeeRemoves.size(); i++) {
            Department department = departmentMapper.selectByPrimaryKey(employeeRemoves.get(i).getAfterDepId());
            employeeRemoves.get(i).setDepartment(department);
            JobLevel jobLevel = jobLevelMapper.selectByPrimaryKey(employeeRemoves.get(i).getAfterJobId());
            employeeRemoves.get(i).setJobLevel(jobLevel);
        }
        return employeeRemoves;
    }

    public Integer addRemove(Employeeremove employeeremove) {
        return employeeremoveMapper.insert(employeeremove);
    }

    public Integer deleteRemoveTrain(Integer id) {
        return employeeremoveMapper.deleteByPrimaryKey(id);
    }

    public Integer updateRemoveById(Employeeremove employeeremove) {
        return employeeremoveMapper.updateByPrimaryKeySelective(employeeremove);
    }
}
