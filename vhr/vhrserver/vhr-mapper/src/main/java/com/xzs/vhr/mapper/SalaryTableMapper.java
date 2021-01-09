package com.xzs.vhr.mapper;

import com.xzs.vhr.model.SalaryTable;

import java.util.List;

public interface SalaryTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalaryTable record);

    SalaryTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalaryTable record);

    List<SalaryTable> getAllSalaryTable();
}
