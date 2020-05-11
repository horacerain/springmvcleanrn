<%--
  Created by IntelliJ IDEA.
  User: Rain
  Date: 2020/5/6
  Time: 16:37
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
	<h4>ERROR PAGE</h4>
	<br/><br/>
	${exception}
	<br/><br/>
	${stack[0]}
</body>
</html>
