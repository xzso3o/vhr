package com.xzs.vhr.mapper;

import com.xzs.vhr.model.Employeeec;
import com.xzs.vhr.model.Employeetrain;

import java.util.List;

public interface EmployeetrainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeetrain record);

    int insertSelective(Employeetrain record);

    Employeetrain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeetrain record);

    int updateByPrimaryKey(Employeetrain record);

    List<Employeeec> getAllEmployeeTrains();
}