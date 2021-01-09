package com.xzs.vhr.mapper;

import com.xzs.vhr.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    Long getTotal(@Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    Integer maxWorkID();

    Integer addEmps(@Param("list") List<Employee> list);

    Employee getEmployeeById(Integer id);


    List<Employee> getEmployeeByPageWithSalary(@Param("page") Integer page, @Param("size") Integer size);

    Integer updateEmployeeSalaryById(@Param("eid") Integer eid,@Param("sid") Integer sid);

    List<Employee> getPositionCountAndName();

    List<Employee> getJoblevelCountAndName();

    List<Department> getCountDepartment();

    List<Department> getcountDepartmentName();

    List<Employee> getCountDegree();

    List<Employee> getCountDegreeName();

    List<Employee> getEmployeeByPageWithSalaryTable(@Param("page")Integer page,@Param("size") Integer size);

    Integer updateEmployeeSalaryTableById(@Param("eid")Integer eid,@Param("stid") Integer stid);

    List<Employee> getAllEmployeeAdv();

    Long getEmployeeAdvanceTotal();

    List<Employee> getEmployeeAdvanceByPage(@Param("page") Integer page, @Param("size")Integer size, @Param("keywords")String keywords);
}