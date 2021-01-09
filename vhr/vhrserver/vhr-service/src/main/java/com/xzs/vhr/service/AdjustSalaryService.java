package com.xzs.vhr.service;

import com.xzs.vhr.mapper.AdjustSalaryMapper;
import com.xzs.vhr.model.AdjustSalary;
import com.xzs.vhr.model.Employeeec;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdjustSalaryService {
    @Resource
    AdjustSalaryMapper adjustSalaryMapper;

    public List<Employeeec> getAllAdjustSalaries() {
        return adjustSalaryMapper.getAllAdjustSalaries();
    }

    public Integer addAdjustSalary(AdjustSalary adjustSalary) {
        return adjustSalaryMapper.insert(adjustSalary);
    }

    public Integer deleteAdjustSalary(Integer id) {
        return adjustSalaryMapper.deleteByPrimaryKey(id);
    }

    public Integer updateAdjustSalaryById(AdjustSalary adjustSalary) {
        return adjustSalaryMapper.updateByPrimaryKeySelective(adjustSalary);
    }
}
