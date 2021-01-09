package com.xzs.vhr.service;

import com.xzs.vhr.mapper.SalaryTableMapper;
import com.xzs.vhr.model.SalaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SalaryTableService {

    @Resource
    SalaryTableMapper salaryTableMapper;

    public List<SalaryTable> getAllSalaryTable() {
        return salaryTableMapper.getAllSalaryTable();
    }

    public Integer addSalaryTable(SalaryTable salaryTable) {
        return salaryTableMapper.insert(salaryTable);
    }

    public Integer deleteSalaryTable(Integer id) {
        return salaryTableMapper.deleteByPrimaryKey(id);
    }

    public Integer updateSalaryTable(SalaryTable salaryTable) {
        return salaryTableMapper.updateByPrimaryKeySelective(salaryTable);
    }
}
