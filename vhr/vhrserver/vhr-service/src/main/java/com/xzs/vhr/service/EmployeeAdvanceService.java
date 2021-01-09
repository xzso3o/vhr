package com.xzs.vhr.service;

import com.xzs.vhr.mapper.EmployeeMapper;
import com.xzs.vhr.model.Employee;
import com.xzs.vhr.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeAdvanceService {
    @Resource
    EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployeeAdv() {
        return employeeMapper.getAllEmployeeAdv();
    }

    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keywords) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeAdvanceByPage(page, size,keywords);
        Long total = employeeMapper.getEmployeeAdvanceTotal();
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
