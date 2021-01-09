package com.xzs.vhr.service;

import com.xzs.vhr.mapper.EmployeeMapper;
import com.xzs.vhr.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class EmployeeService {
    @Resource
    EmployeeMapper employeeMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;

    public final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");//计算小数点后两位

    public RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotal(employee, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmp(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        //月份算法：（结束年-起始年）*12 + （结束月份-起始月份）
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
                                // 月份/12即是我们要的合同年限
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        int result = employeeMapper.insertSelective(employee);

        if (result == 1){
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            logger.info(emp.toString());
            rabbitTemplate.convertAndSend("xzs.mail.welcome",emp);
        }
        return result;
    }


    public Integer maxWorkID() {
        return employeeMapper.maxWorkID();
    }

    public Integer deleteEmpByEid(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Integer addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }

    public RespPageBean getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalary(page,size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(list);
        respPageBean.setTotal(employeeMapper.getTotal(null,null));
        return respPageBean;
    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        //如果eid存在就对他进行更新，否则就是一个插入操作
        return employeeMapper.updateEmployeeSalaryById(eid,sid);
    }

    public List<Employee> getPositionCountAndName() {
        return employeeMapper.getPositionCountAndName();
    }

    public List<Employee> getJoblevelCountAndName() {
        return employeeMapper.getJoblevelCountAndName();
    }

    public List<Department> getCountDepartment() {
        return employeeMapper.getCountDepartment();
    }

    public List<Department> getcountDepartmentName() {
        return employeeMapper.getcountDepartmentName();
    }

    public List<Employee> getCountDegree() {
        return employeeMapper.getCountDegree();
    }

    public List<Employee> getCountDegreeName() {
        return employeeMapper.getCountDegreeName();
    }

    public RespPageBean getEmployeeByPageWithSalaryTable(Integer page, Integer size) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalaryTable(page,size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(list);
        respPageBean.setTotal(employeeMapper.getTotal(null,null));
        return respPageBean;
    }

    public Integer updateEmployeeSalaryTableById(Integer eid, Integer stid) {
        return employeeMapper.updateEmployeeSalaryTableById(eid,stid);
    }
}
