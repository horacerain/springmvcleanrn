package com.horace.springmvc.crud.dao;

import com.horace.springmvc.crud.entities.Department;
import com.horace.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    @Autowired
    private DepartmentDao departmentDao;
    private String initId = "1005";
    private static Map<String, Employee> employees = null;

    static{
        employees = new HashMap<String,Employee>();
        employees.put("1001",new Employee("1001","E-AA","horace@163.com",1,new Department("101","D-AA")));
        employees.put("1002",new Employee("1002","E-BB","horace@163.com",0,new Department("102","D-BB")));
        employees.put("1003",new Employee("1003","E-CC","horace@163.com",1,new Department("103","D-CC")));
        employees.put("1004",new Employee("1004","E-DD","horace@163.com",0,new Department("104","D-DD")));
        employees.put("1005",new Employee("1005","E-EE","horace@163.com",1,new Department("105","D-EE")));
    }
    public void saveEmployee( Employee employee){
        if(employee.getId()==null||employee.getId().equals("")) {
            initId = Integer.parseInt(initId) + 1 + "";
            employee.setId(initId);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee> getAllEmployees(){
        return employees.values();
    }
    public Employee getEmployee(String id){
        Employee employee = employees.get(id);
        return employee;
    }
    public void delete(String id){
        employees.remove(id);
    }
}
