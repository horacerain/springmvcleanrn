package com.horace.springmvc.crud.converters;

import com.horace.springmvc.crud.dao.EmployeeDao;
import com.horace.springmvc.crud.entities.Department;
import com.horace.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements Converter<String, Employee> {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee convert(String source) {
        if(source !=null){
            String [] vals = source.split("-");
            if(vals != null && vals.length ==4){
                String lastName = vals[0];
                String email = vals[1];
                Integer gender = Integer.parseInt(vals[2]);
                Department department = new Department();
                department.setId(vals[3]);

                Employee employee = new Employee(null,lastName,email,gender,department);
                System.out.println(source+" --- "+ employee);
                employeeDao.saveEmployee(employee);
                return employee;

            }
        }

        return null;
    }
}
