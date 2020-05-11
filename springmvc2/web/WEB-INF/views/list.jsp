<%--
  Created by IntelliJ IDEA.
  User: Rain
  Date: 2020/4/28
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<title>Title</title>
	<script src="<%=basePath%>js/vue.js"></script>
	<script src="<%=basePath%>js/jquery-1.8.1.js"></script>
	<script src="<%=basePath%>js/axios.min.js"></script>
	<style type="text/css">
		table {
			margin: 0 auto;
			/*width: 300px;*/
			border:1px solid red;
			border-collapse: collapse;/*关键代码*/
		}
		th,td {
			text-align: center;
			border: 1px solid green;
		}
		h4{
			text-align: center;
			/*margin: auto;*/
		}
		.center{
			margin:auto;
			text-align:center;
		}
	</style>
	<script>
		<%--$(function () {--%>
		<%--	$(".del").click(function(){--%>
        <%--        var href = $(this).attr("name");--%>
        <%--        href = "<%=basePath%>emp/emp/"+href;--%>
        <%--        console.log(href);--%>
        <%--        alert(href);--%>
        <%--        $("#form").attr("action",href).submit();--%>
        <%--        return false;--%>
		<%--	})--%>
        <%--})--%>
	</script>
</head>
<body>
<div id="app">
	<form id="delForm" action="" method="POST">
		<input type="hidden" name="_method" value="DELETE">
	</form>

	<br/><br/>

	<h4>员工信息</h4>
	<br/><br/>
	<h4 style="color:red;" v-if="emps.length==0">没有任何员工信息</h4>
	<br/><br/>
	<p class="center"><a href="emp/emp">Add New Employee</a></p>
	<br/><br/>

	<table>
		<tr>
			<td>ID</td>
			<td>LastName</td>
			<td>Email</td>
			<td>Gender</td>
			<td>Department</td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>
		<tr v-for="(item,index) in emps">
			<td>{{item.id}}</td>
			<td>{{item.lastName}}</td>
			<td>{{item.email}}</td>
			<td>{{item.gender==0?'Female':'Male'}}</td>
			<td>{{item.department.departmentName}}</td>
			<td><a :href="'emp/emp/'+item.id">Edit</a> </td>
<%--			<td><button class="del" :name="item.id" >Delete</button> </td>--%>
			<td><a href="javascript:;" v-on:click="delDepartment(item.id)">Delete</a> </td>
		</tr>
	</table>
</div>

<!--  开发环境版本，包含了有帮助的命令行警告 -->

<script>
    var app = new Vue({
        el: '#app',
        data: {
            emps: []

        },
        methods:{
			getList:function () {
			    var that = this;
                axios.get("emp/getEmpList").then(
                    function(response){
						console.log(response);
						that.emps = response.data;
                    },function(err){}
                )
            },
	        delDepartment:function(id){

                var href = "<%=basePath%>emp/emp/"+id;
                console.log(href);
                $("#delForm").attr("action",href).submit();

	        }
        },
        mounted:function(){
            this.getList();//需要触发的函数
        }
    })


</script>
</body>
</html>
