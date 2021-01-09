package com.xzs.vhr.mapper;

import com.xzs.vhr.model.AdjustSalary;
import com.xzs.vhr.model.Employeeec;

import java.util.List;

public interface AdjustSalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdjustSalary record);

    int insertSelective(AdjustSalary record);

    AdjustSalary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdjustSalary record);

    int updateByPrimaryKey(AdjustSalary record);

    List<Employeeec> getAllAdjustSalaries();

}