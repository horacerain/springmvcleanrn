<%--
  Created by IntelliJ IDEA.
  User: Rain
  Date: 2020/4/28
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" 
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>crud</title>
    <script src="js/jquery-1.8.1.js"></script>
    <script>
      $(function(){
        $("#testJson").click(function(){
          var url = "emp/getEmpList";
          var args = {};
          $.post(url,args,function(data){
            console.log(data);
            for(var i = 0; i<data.length; i++){
              console.log(data[i]);
            }
          })
          return;
        })
      })
    </script>
  </head>
  <body>
    <div id="app">
      <ul>
        <li>
          <form action="testFileUpload" method="POST" enctype="multipart/form-data">
            File:<input type="file" name="file" >
            Desc:<input type="text" name="desc">
            <input type="submit" value="submit">
          </form>
        </li>
        <li>
          <a href="emp/emps">List All Employees </a>
          <br/><br/>
          <a href="javascript:;" id="testJson">testJson</a>
        </li>
        <li>
          <form action="testHttpMessageConverter" method="POST" enctype="multipart/form-data">
            File:<input type="file" name="file" >
            Desc:<input type="text" name="desc">
            <input type="submit" value="submit">
          </form>
        </li>
        <li>
          <a href="testResponseEntity">testResponseEntity</a>
        </li>
        <li>
          <a href="testResponseEntityString">testResponseEntityString</a>
        </li>
        <%--
            关于国际化：
            1，在页面上能够根据浏览器语言设置的情况对文本（不是内容），时间，数值进行本地化处理
            2，可以在 bean 中获取国际化资源文件 Locale 对应的消息
            3，可以通过超链接切换 Locale ,而不再依赖于浏览器的语言设置情况

            解决：
            1， 使用 jstl 的 fmt 标签
            2，在 bean 中侏儒 ResourceBundleMessageSource 的实例， 使用其对应的 getMessage 方法即可
            3，配置 LocalResolver 和LocaleChangeInterceptor
        --%>
        <li><a href="i18n">I18N PAGE</a> </li>
        <li><a href="testExceptionHandlerExceptionResolver?i=10">testExceptionHandlerExceptionResolver</a> </li>
        <li><a href="testResponseStatusExceptionResolver?i=13">testResponseStatusExceptionResolver</a> </li>
      </ul>
    </div>

    <!--  开发环境版本，包含了有帮助的命令行警告 -->
    <script src="js/vue.js"></script>
    <script src="js/axios.min.js"></script>
    <script>
      var app = new Vue({
        el: '#app',
        data: {
          message: '',
          urlList:[
            "springmvc/testRedirect",
          ],
          formUrlList:[
            {url:"springmvc/testModelAttribute",method:"post",sign:"update"}
          ]
        },
        methods:{

        }
      })


    </script>
  </body>
</html>
