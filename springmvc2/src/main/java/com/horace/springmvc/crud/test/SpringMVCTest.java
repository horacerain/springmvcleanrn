package com.horace.springmvc.crud.test;

import com.horace.springmvc.crud.dao.EmployeeDao;
import com.horace.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Controller
public class SpringMVCTest {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @ResponseStatus(reason = "测试",value = HttpStatus.NOT_FOUND)
    @RequestMapping("testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(
            @RequestParam("i") int i
    ){
        if(i==13){
            throw new UserNameNotMatchPasswordException();
        }
        System.out.println("... testResponseStatusExceptionResolver");
        return "success";
    }

//    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleArithmeticException2(Exception ex){
        System.out.println("[出异常了] runtimeException："+ex);
//        map.put("exception",ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }
    /**
     * 1，在@ExceptionHandler 修饰的方法的入参中，加入Exception类型的参数
     * 该参数即对应发生的异常对象
     * 2，@ExceptionHandler 方法的如惨重不饿能传入 Map,若希望把异常信息传到页面上，需使用ModelandView作为返回值
     * 3, @ExceptionHandler 方法标记的异常有优先级的问题。
     * 4，@ExceptionHandler 在handler 中标记的方法，只能捕获当前handler 中的方法抛出的异常；
     * 5, 如果在当前 handler 中找不到 @ExceptionHandler 方法中找不到当前方法出现的异常，
     * 则去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常
     * */
//    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView handleArithmeticException(Exception ex){
        System.out.println("出异常了："+ex);
//        map.put("exception",ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }

    @RequestMapping("testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(
            @RequestParam("i") int i
    ){
        System.out.println("result: 10/i = "+(10/i));
        return "success";
    }


    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc
    , @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("desc: "+ desc);
        System.out.println("OriginalFileName : "+file.getOriginalFilename());
        System.out.println("fileInputStream : "+file.getInputStream());

        return "redirect:/success.jsp";
    }

    @RequestMapping("/i18n")
    public String testI18n(Locale locale){
        String val = messageSource.getMessage("i18n.username",null,locale);
        System.out.println(val);
        return "i18n";
    }
    //文件下载
    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/hosts.txt");
        body = new byte[in.available()];
        in.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=hosts.txt");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body,headers,statusCode);
        return response;
    }
    @RequestMapping("/testResponseEntityString")
    public ResponseEntity<String> testResponseEntityString(HttpSession session) throws IOException {
        String body = "testResponseEntityString + 中文";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/html;charset=utf-8");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<String> response = new ResponseEntity<>(body,headers,statusCode);
        return response;
    }

    @ResponseBody
    @RequestMapping("/getEmpList")
    public Collection<Employee> getList(){
        System.out.println("/getEmpList");
        return  employeeDao.getAllEmployees();
    }

    @RequestMapping("/testConversionServiceConverer")
    public String  testConverter(@RequestParam("employee") Employee employee){
        System.out.println("save employee:" +employee);
        return "list";
    }

    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println(body);
        return "helloWorld!" + new Date();
    }
}
