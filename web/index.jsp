<%--
  Created by IntelliJ IDEA.
  User: Rain
  Date: 2020/4/26
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Title</title>
</head>
<body>
	<a href="helloworld">hello</a>
	<br/><br/>
	<a href="springmvc/springmvctest">springmvctest</a>
	<br/><br/>
	<a href="springmvc/testMethod">testMethod</a>
	<br/><br>
	<form action="springmvc/testMethod" method="post">
		<button type="submit">提交</button>
	</form>
	<br/><br>
	<form action="springmvc/testParamsAndHeaders" method="post">
		username:<input name="username" value="horace">
		age:<input name="age" value="10">
		<button type="submit">提交</button>
	</form>
	<br/><br/>
	<a href="springmvc/testAntPath/123/abc">测试通配符映射url</a>


</body>
</html>
