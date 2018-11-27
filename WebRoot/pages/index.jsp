<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>demo</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/layout.css" rel="stylesheet">
  </head>

  <body>
	<!-- 头部 -->
    <jsp:include page="header.jsp"/>

    
    <div class="container-fluid">
      <div class="row">用户管理
        <div class="col-sm-3 col-md-2 sidebar">
          <jsp:include page="navibar.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">欢迎使用</h1>

          <div class="row placeholders">
 				 <p><h2>用户名:${UserInfo.userName}</h2></p> 
                <p><h3>用户code:${UserInfo.userCode}</h3></p>
                <p><h3>创建时间:${UserInfo.createTime}</h3></p>
              
          </div> 
          <div style="position: absolute; left:800px;top:0px">
          <a href="${pageContext.request.contextPath}/bug/noBug.action">
          有${noBug}条bug未解决
          </a><br>
          <a href="${pageContext.request.contextPath}/platfrom/myplatfromlist.action?readstate=0">
          有${nearOutMessage}条未读消息
          </a><br>
          <a href="${pageContext.request.contextPath}/projtask/projtasklist.action?outtimemark=out">
          有${nearOutTask}条快过期任务
          </a><br>
          </div>
        </div>
      </div>
      
      
      
    
    </div>

    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
  </body>
</html>
