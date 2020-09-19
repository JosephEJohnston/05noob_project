package com.noob.service;

import com.noob.domain.Employee;

import java.util.Date;

public interface EmployeeService {

    //注册
    void register(int employeeID, String employeeName, boolean employeeSex,
                  Date employeeBirth, String employeePhone, String employeePlace,
                  String password, boolean isLead);

    //以 ID 查找用户
    Employee findUserByID(int employeeID);

    Employee findUserByName(String username);

    Employee checkUser(String username, String password);
}
