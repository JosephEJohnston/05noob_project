package com.noob.dao;

import com.noob.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 报错报得孩子人傻了
@Mapper // 配了 xml 也得配置这里的注解，否则报错
public interface EmployeeDAO {
    //添加员工信息
    void addEmployee(Employee employee);

    //定义修改员工信息
    void updateEmployee(Employee employee);

    //定义删除员工信息
    void deleteEmployee(int employeeID);

    //定义查询所有员工信息
    List<Employee> findAllEmployee();

    //按 ID 查询员工信息
    Employee findEmployeeById(int employeeID);

    //@Select("select * from tb_employee where employeeName=#{username} AND password=#{password}")
    Employee findByUsernameAndPassword(String username, String password);
}
