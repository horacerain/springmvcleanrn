package com.horace.springmvc.crud.dao;

import com.horace.springmvc.crud.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {

    private static Map<String, Department> depatments = null;

    static {
        depatments = new HashMap<String,Department>();
        depatments.put("101",new Department("101","D-AA"));
        depatments.put("102",new Department("102","D-BB"));
        depatments.put("103",new Department("103","D-CC"));
        depatments.put("104",new Department("104","D-DD"));
        depatments.put("105",new Department("105","D-EE"));
    }

    public Collection<Department> getDepatments(){
        return depatments.values();
    }

    public  Department getDepartment(String id){

        return depatments.get(id);
    }
}
