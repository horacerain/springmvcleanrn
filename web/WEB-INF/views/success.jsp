<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Rain
  Date: 2020/4/26
  Time: 17:35
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
	<h4>success page</h4>
	<fmt:message key="i18n.username"></fmt:message><br/><br/>
	<fmt:message key="i18n.password"></fmt:message><br/><br/>

	time :${requestScope.time}<br/><br/>
	names:${requestScope.names}<br/><br/>
	request user:${requestScope.user}<br/><br/>
	session user:${sessionScope.user}<br/><br/>
	request lover:${requestScope.lover}<br/><br/>
	session lover:${sessionScope.lover}<br/><br/>

</body>
</html>
