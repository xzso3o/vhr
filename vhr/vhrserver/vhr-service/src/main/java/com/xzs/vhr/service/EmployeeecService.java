package com.xzs.vhr.service;

import com.xzs.vhr.mapper.EmployeeecMapper;
import com.xzs.vhr.mapper.EmployeetrainMapper;
import com.xzs.vhr.model.Employeeec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeecService {

    @Resource
    EmployeeecMapper employeeecMapper;
    @Resource
    EmployeetrainMapper employeetrainMapper;

    public List<Employeeec> getAllEmployeeecs() {
        return employeeecMapper.getAllEmployeeecs();
    }

    public Integer addEmployeeec(Employeeec employeeec) {
        return employeeecMapper.insert(employeeec);
    }

    public Integer deleteEmployeeec(Integer id) {
        return employeeecMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmployeeecById(Employeeec employeeec) {
        return employeeecMapper.updateByPrimaryKeySelective(employeeec);
    }

}
