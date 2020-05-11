<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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

    </script>
  </head>
  <body>
    <div id="app">
        <fmt:message key="i18n.username"></fmt:message>
      <br/><br/>
      <a href="i18n?locale=zh_CH">中文 PAGE</a><br/><br/>
      <a href="i18n?locale=en_US">English PAGE</a><br/><br/>
      <a href="i18n2">I18N2 PAGE</a><br/><br/>

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
