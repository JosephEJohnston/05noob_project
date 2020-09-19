package com.noob.service.impl;

import com.noob.dao.EmployeeDAO;
import com.noob.domain.Employee;
import com.noob.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;


    public void register(int employeeID, String employeeName, boolean employeeSex, Date employeeBirth,
                         String employeePhone, String employeePlace, String password, boolean isLead) {
        Employee employee = new Employee();

        employee.setEmployeeID(employeeID);
        employee.setEmployeeName(employeeName);
        employee.setEmployeeSex(employeeSex);
        employee.setEmployeeBirth(employeeBirth);
        employee.setEmployeePhone(employeePhone);
        employee.setEmployeePlace(employeePlace);
        employee.setPassword(password);
        employee.setLead(isLead);

        employeeDAO.addEmployee(employee);
    }

    public Employee findUserByID(int employeeID) {
        return employeeDAO.findEmployeeById(employeeID);
    }

    @Override
    public Employee findUserByName(String username) {
        return null;
    }

    @Override
    public Employee checkUser(String username, String password) {
        return employeeDAO.findByUsernameAndPassword(username, password);
    }


    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
