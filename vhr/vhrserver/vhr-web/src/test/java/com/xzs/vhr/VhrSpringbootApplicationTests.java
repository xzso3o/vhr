package com.xzs.vhr;

import com.xzs.vhr.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VhrSpringbootApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        System.out.println(employeeMapper.getEmployeeByPageWithSalaryTable(1, 10));
    }

}
