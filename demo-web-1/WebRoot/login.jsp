<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 导入数据库相关的包 -->
<%@page import="java.sql.*" %>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <style>
    *{margin:0;padding:0;}
    body{height:100vh;display:flex;justify-content:center;align-items:center;}
    .form-wrapper{ padding:20px; text-align:center; border:1px solid #ddd;}
    .form-wrapper .line{ width:243px; margin:20px 10px; height:20px; position:relative; }
    .form-wrapper .line > label{ position:absolute; left:0; width:28%; }
    .form-wrapper .line > input{ position:absolute; right:0; }
    .form-wrapper input[name="submit"]{height:26px;width:60px;margin-left:24%;margin-right:10%}
    .form-wrapper input[name="reset"]{height:26px;width:60px;}
  </style>
  <script type="text/javascript">
              function validate() {
                  var pwd1 = document.getElementById("pwd1").value;
                  var pwd2 = document.getElementById("pwd2").value;
					
                  if(pwd1 == pwd2) {
                      /* document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>"; */
                      //document.getElementById("submit").disabled = true;
                  }
                  else {
                      document.getElementById("tishi").innerHTML="<font color='red'>不一致</font>";
                  		document.getElementById("submit").disabled = true;
                        alert("代码不一致!!");
                  }
              }
              function clear(){
            	  document.getElementById("username").innerHTML="";
            	  document.getElementById("pwd1").innerHTML="";
            	  document.getElementById("pwd2").innerHTML="";
              }
  </script>
  </head>
  
  <body>
	<div class="form-wrapper">
    <h1>欢迎登录</h1>
    <form id="check" action="check" method="post">
      <div class="line">
        <label>用户名</label>
        <input type="text" name="username" id="username">
      </div>
      <div class="line">
        <label>密码</label>
        <input type="password" name="password" id="pwd1">
      </div>
        <div class="line">
        <label>确认</label>
        <input type="password" name="" id="pwd2"  ><span id="tishi" ></span>
      </div>
      <input type="submit" name="submit" value="确定" id="submit" onclick="validate()">
      <input type="reset" name="reset" value="重置" >
    </form>
  </div>
  </body>
</html>
