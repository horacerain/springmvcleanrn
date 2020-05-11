package com.horace.springmvc.crud.handler;

import com.horace.springmvc.crud.dao.DepartmentDao;
import com.horace.springmvc.crud.dao.EmployeeDao;
import com.horace.springmvc.crud.entities.Department;
import com.horace.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeHandler {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false) String id,
                            Map<String,Object> map){
        if(id !=null && !"".equals(id)){
            map.put("employee",employeeDao.getEmployee(id));
        }
    }

    @RequestMapping(value = "/emp/",method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.saveEmployee(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    public String input(Map<String,Object> map){
        System.out.println("/emp input");
        map.put("departments",departmentDao.getDepatments());
        map.put("employee",new Employee());
        return "input3";
    }
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String inptut(@PathVariable("id") String id,Map<String,Object> map){
        System.out.println("/emp  UPDATE");
        map.put("employee",employeeDao.getEmployee(id));
        map.put("employeeId",id);
        return "input";
    }

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    public String save(@Valid Employee employee){
        System.out.println("/emp  save:"+employee);
        employeeDao.saveEmployee(employee);
        return "list";
    }
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id){
        System.out.println("/emp  delete");
        employeeDao.delete(id);
        return "list";
    }


    @ResponseBody
    @RequestMapping("/getDepartments")
    public Collection<Department> getDepartments(){
        System.out.println("getDepartments");
        return  departmentDao.getDepatments();
//        return  new ArrayList<Employee>();
    }

    @ResponseBody
    @RequestMapping(value = "/getEmp")
    public Employee getEmp(@RequestBody Map dataMap){
        System.out.println("/getEmp  id="+dataMap.get("id"));
        String id = (String)dataMap.get("id");
        if(id==null)
            id="1005";
        Employee emp = employeeDao.getEmployee(id);
        return  emp;
    }
    @ResponseBody
    @RequestMapping("/getEmpList")
    public Collection<Employee> getList(){
        System.out.println("/getEmpList");
        return  employeeDao.getAllEmployees();
    }

    @RequestMapping("/emps")
    public String list(Map<String,Object> map){
//        map.put("employees",employeeDao.getAllEmployees());

        return "list";
    }

//    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields("lastName");
    }
}
