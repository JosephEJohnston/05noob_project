package com.noob;

import com.noob.domain.Employee;
import com.noob.service.EmployeeService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class ApplicationTests {


    @Autowired
    EmployeeService employeeService;


    @Test
    void contextLoads() {
        Employee employee = employeeService.checkUser("张三", "123");
        System.out.println(employee);
    }

}
