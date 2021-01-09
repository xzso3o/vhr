package com.xzs.vhr.service;

import com.xzs.vhr.mapper.EmployeetrainMapper;
import com.xzs.vhr.model.Employeeec;
import com.xzs.vhr.model.Employeetrain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeetrainService {
    @Resource
    EmployeetrainMapper employeetrainMapper;

    public List<Employeeec> getAllEmployeeTrains() {
        return employeetrainMapper.getAllEmployeeTrains();
    }

    public Integer addEmployeeTrain(Employeetrain employeetrain) {
        return employeetrainMapper.insert(employeetrain);
    }

    public Integer deleteEmployeeTrain(Integer id) {
        return employeetrainMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmployeeTrainById(Employeetrain employeetrain) {
        return employeetrainMapper.updateByPrimaryKeySelective(employeetrain);
    }
}
