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
</head>
<body>
<div id="app">
	<%--${pageContext.request.scheme} <br/>
${pageContext.request.serverName} <br/>
${pageContext.request.serverPort} <br/>
	${pageContext.request.contextPath} <br/>--%>

	<br/><br/>
	<h4>员工信息</h4>
	<br/><br/>
		<form class="center" action="testConversionServiceConverer" method="POST">
			<%-- lastname-email-gender-department.id  例如： GG-gg@163.com-0-105--%>
			Employee: <input type="text" name="employee">
			<button type="submit">submit</button>
		</form>
	<br/><br/>

	<form class="center" action="emp/emp" method="post">
<%--		<input type="hidden" name="id" :value="emp.id">--%>
		<input type="hidden" name="id" >
		<p v-if="emp.id==null">LastName:<input type="text" name="lastName"  v-model="emp.lastName"/><br/></p>
		<p>Email:<input type="text" name="email" v-model="emp.email"/><br/></p>
		<p>Gender:<input type="radio" name="gender" value="1"  v-model="emp.gender" />&nbsp;Male
		<input type="radio" name="gender" value="0" v-model="emp.gender"  />&nbsp;Female<br/></p>
		<p>Department:{{depId}}
		<select name="department.id" v-model="depId">
			<option v-for="(item,index) in departments" :value="item.id" >
				{{item.departmentName}}
			</option>
		</select><br/></p>
<%--		<p>birth: <input type="text" name="birth" vv-model="emp.birth"><br/></p>--%>
		<button type="submit">提交</button>
		<br/>
	</form>
	<br/><br/>
	{{emp}}
</div>

<!--  开发环境版本，包含了有帮助的命令行警告 -->

<script>
    var app = new Vue({
        el: '#app',
        data: {
            departments: [],
			emp:{id:null,lastName:'',email:'',gender:null, department:{'id':'','departmentName':''},birth:''},
	        id:'${employeeId}',
	        depId:''
        },
        methods:{
			getDepartments:function () {
			    var that = this;
                axios.get("emp/getDepartments").then(
                    function(response){
						console.log(response);
						that.departments = response.data;
                    },function(err){}
                )
            },
	        getEmp:function (id) {
		        var that = this;
		        var paramData = {"id":id};
                axios.post("emp/getEmp",paramData).then(
                    function(response){
                        console.log(response);
                        that.emp = response.data;
                        that.depId = response.data.department.id;
                    },function(err){}
                )
            }
        },
        mounted:function(){

            this.getDepartments();//需要触发的函数
	        // console.log(this.id);
	        if(this.id != null && this.id !=""){
                this.getEmp(this.id);//需要触发的函数
            }
        }
    })


</script>
</body>
</html>
