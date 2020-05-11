package com.horace.springmvc.handlers;

import com.horace.springmvc.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@SessionAttributes(value = {"user"},types = {String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
    private static final String SUCCESS="success";

    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("test redirect");
        return "redirect:/index.html";
    }

    @RequestMapping("/testView")
    public String testView(){
        System.out.println("testView");
        return "helloView";
    }

    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver(){
        System.out.println("testViewAndViewResolver");
        return SUCCESS;
    }

    /**
     * 1，由@ModelAttribute 标记的方法，会再每个目标方法执行之前被SpringMVC调用！
     * 2，@ModelAttribute 追加二也可以来修饰目标方法的POJO 类型的入参，其 value 属性值有
     * 如下的作用：
     *      1）SpringMVC 会使用 value 属性值 在implicitModel 中查找对应的对象，若存在则会直接传入到目标方法的入参中
     *      2）SpringMVC 会以 value 为 key , POJO 类型的对象为 value,存入到request 中
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required=false) Integer id ,
                        Map<String,Object> map){
        System.out.println("testModelAttribute methoed");
        if(id !=null){
            //模拟从数据库中获取对象
            User user = new User("1","Tom","123456","tom@163.com",12);
            System.out.println("从数据库中获取一个对象："+user);
            map.put("user",user);
        }
    }

    /**
     * 运行流程：
     * 1，执行 @ModelAttribute 注解修饰的方法： 从数据库中取出对象，把对象放入到了Map中，键位user
     * 2,SpringMVC 从 Map 中取出 User 对象， 并把表单的请求参数赋给该 User 对象的对应属性
     * 3， SpringMVC 把上述对象传入目标方法的参数。
     *
     * 注意： 在 @ModelAttribute 修饰的方法中，放入到 MAP 时的键，需要和目标方法入参类型的第一个字母小写的字符串一致！
     *
     * SpringMVC 确定目标方法POJO 类型的入参过程
     * 1，确定一个key:
     *      1） 若目标方法的 POJO类型的参数没有使用 @ModelAttribute 作为修饰，则 key 为 POJO 类型首字母小写
     *      2） 若使用了 @ModelAttribute 来修饰，则key 为 @Model Attribute 追加二的value 属性值。
     * 2, 在implicitModel 中查找key对应的对象，若存在，则作为入参传入
     *      1）若在 @ModelAttribute 标记的方法中在 Map 中保存过， 且 key 和 1,确定的 key  一致， 则会获取到。
     * 3，若implicitModel 中不存在key 对应的对象，则检查当前的Handler 是否使用@SessionAttributes注解修饰，
     * 若使用了该注解，且@SessionAttributes 注解的value属性值中包含了 key, 则会从 httpSession 中来获取key 所对应
     * 的value 值，若存在则直接传入到目标方法的入参中，若不存在，则将抛出异常。
     * 4，若 Handler  没有表示 @SessionAttributes 或 @SessionAttributes 注解value值中不包含key,则会通过反射来创建
     * POJO 类型的参数，传入为目标方法的参数
     * 5，SpringMVC 会把 key 和 value 保存到 implicitModel 中，进而保存到 request 中。
     *
     * 源码分析的流程
     * 1，调用@ModelAttribut 注解修饰的方法实际上把@ModelAttribute 方法中Map中的数据放在了 implictModel 中
     *  2，解析请求处理器的目标参数，来自WebDataBinder 对象的target 属性
     *      1），创建WebDataBinder对象：
     *          ①确定ObjectName属性 若传入的attrName 为 "" , 则 objectName 为类名的首字母小写
     *             注意：attrName 若目标方法的POJO 属性使用的@ModelAttribute来修饰,则值即为@ModelAttribute的value属性值
     *          ②确定target属性
     *              1.>  在implicitModel中查找 attrName 对应的属性值,若存在，ok
     *              2.>  若不存在，则验证当前Handler 是否使用了 @SessionAttributes 进行修饰，若使用了，则尝试从Session中获取 attrName 所对应的属性值
     *                  若Session中没有对应的属性值，则抛出了异常。
     *              3.> 若 Handler 没有使用@SessionAttributes 进行修饰，或@SessionAttributes 中没有value值指定 key
     *                  和 attrName 相匹配，则通过反射创建了POJO对象
     *      2）. SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性
     *      3）. SpringMVC 会把 WebDataBinder 的attrName 和 target 给到implicitModel 进而传到 request 域对象中。
     *      4).  把 WebDataBinder 的 target 作为参数传递给目标方法的入参
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user){
        System.out.println(user);
        return SUCCESS;
    }

    /**
     *value中放指定对象名，types中放对象类型，两者是 || 关系，符合其一即可
     *@SessionAttributes 注解 会将Map中的参数放到session中
     *这个注解只能放在类的上面
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String,Object> map){
        User user = new User("horace","123456","horace@163.com",18);
        map.put("lover","Dorcas");
        map.put("user",user);
        return SUCCESS;
    }

    /**
     * 目标方法可以添加map类型（实际上也可以是Model类型或者ModelMap类型）的参数
     * @param map
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        map.put("names", Arrays.asList("tom","jerry"));
        System.out.println(map.getClass().getName());
        return SUCCESS;
    }

    /**
     * 目标方法的返回值可以是 ModelAndView 类型，
     * 包含视图信息和模型信息
     * springmvc 会把 ModelAndView 的model 中数据放到request 域对象中。
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView  (){
        String viewName = SUCCESS;
        ModelAndView modelAndView = new ModelAndView(viewName);

        //添加模型数据到 ModelAndView
        modelAndView.addObject("time",new Date());
        return modelAndView;
    }

    /**
     * 可以使用Servlet 原生的 API 作为目标方法的参数
     * 包括：
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Local
     * InputStream
     * OutputStream
     * Reader
     * Writer
     */
    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request, Writer out,
                                 HttpServletResponse response) throws IOException {
        System.out.println("testServletAPI :"+request+" , "+response);
        out.write("hello springmvc testServletAPI");
//        return SUCCESS;
    }
    /**
     * Spring MVC 会被请求参数名和POJO 属性名进行自动匹配，
     * 自动为该对象填充属性值，支持级联属性
     */
    @RequestMapping("/testPojo")
    public String testPojo(User user){
        System.out.println(user);
        return SUCCESS;
    }

    /**
     * @CookieValue 映射一个Cookie 值， 属性同@RequestParam
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId){
        System.out.println("testCookieValue: sessionId" + sessionId);
        return SUCCESS;
    }

    /**
     * 映射请求头信息
     * 用法同 RequestParam
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
        System.out.println("testRequestHeader Accept-Language: "+ al );
        return SUCCESS;
    }

    /**
     * @RequestParam 来映射请求参数
     * value值即请求参数的参数名
     * required 该参数是否必须，默认为true
     * defaultValue 请求参数的默认值
     * @param username
     * @param age
     * @return
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(value = "username") String username,
        @RequestParam(value ="age",required = false,defaultValue = "0") Integer age){
        System.out.println("testRequestParam: name: "+username +"  age: "+age);
        return SUCCESS;
    }

    //post请求模拟 REST put delete
    /**
     * Rest风格的url.
     * 以crud为例
     * 新增：/order POST
     * 修改：/order/1 PUT
     * 获取：/order/1 GET
     * 删除：/order/1 DELETE
     * 如何发送PUT 和 DELETE 请求
     * 1，需要配置 hidden httpMethodFilter
     * 2,需要发送POST 请求
     * 3， 需要发送post 请求时， 携带一个name=_method 的隐藏域，值为DELETE 或 PUT
     * 在 SpringMVC 的目标方法中如何得到 id 呢？
     * 使用 @PathVariable 注解
     */
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.GET)
    public String testRest(@PathVariable Integer id){
        System.out.println("testRest GET: "+id);
        return SUCCESS;
    }

    //post请求模拟 REST put delete
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.POST)
    public String testRest(){
        System.out.println("testRest POST: ");
        return SUCCESS;
    }

    //post请求模拟 REST put delete
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id){
        System.out.println("testRest PUT: "+id);
        return SUCCESS;
    }

    //post请求模拟 REST put delete
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.DELETE)
    public String testResDelete(@PathVariable Integer id){
        System.out.println("testRest DELETE: "+id);
        return SUCCESS;
    }



    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id")
                                   Integer id){
        System.out.println("testPathVariable id : "+ id);
        return "success";
    }

    @RequestMapping("/testAntPath/*/abc")
    public String testAntPath(){
        System.out.println("testAnt");
        return "success";
    }

    /**
     * 了解： 可以使用params 和 headers  可以更加景区的映射请求，
     * params 和 headers 支持简单的表达式
     * @return
     */
    @RequestMapping(value = "testParamsAndHeaders",
        params={"username","age!=10"})
    public String testParamsAndHeader(){
        System.out.println("testParamsAndHeader");
        return "success";
    }

    @RequestMapping(value = "/testMethod",
        method = RequestMethod.POST)
    public String testMethod(){
        System.out.println("testMethod");
        return "success";
    }

    /**
     * 1,@TequestMapping除了修饰方法，还可以修饰类
     * 修饰类：提供初步的请求映射信息，相对于WEB应用的根目录
     * 修饰方法：提供进一步细分映射信息
     */
    @RequestMapping("/springmvctest")
    public String springmvctest(){
        System.out.println("springmvctest");
        return "success";
    }

}
