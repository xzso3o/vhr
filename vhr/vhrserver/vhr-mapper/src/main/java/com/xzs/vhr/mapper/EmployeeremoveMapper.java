package com.xzs.vhr.mapper;

import com.xzs.vhr.model.Employeeremove;

import java.util.List;

public interface EmployeeremoveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeremove record);

    int insertSelective(Employeeremove record);

    Employeeremove selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeremove record);

    int updateByPrimaryKey(Employeeremove record);

    List<Employeeremove> getAllEmployeeRemoves();
}