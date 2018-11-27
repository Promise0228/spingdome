<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>项目管理-项目详情</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link 
	href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
   <style type="text/css">
   	.red{color:red}
   	.tdlength{width:20%}
   </style>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="header.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">${proj.projName }</h1>
			<div class="row placeholders">
			</div>
			<table class="table table-hover ">
			<tr><td class="tdlength">项目编码:</td><td>${proj.projNum }</td></tr>
			<tr><td class="tdlength">项目描述:</td><td>${proj.projDesc }</td></tr>
			<tr><td class="tdlength">访问类型:</td><td>${proj.vistorType eq '0'?'公开':''}
													${proj.vistorType eq '1'?'项目组':''}
													${proj.vistorType eq '2'?'白名单':''}
												</td></tr>
			<tr><td class="tdlength">项目组成员:</td><td>
			<c:forEach items="${projUsers}" var="projUser" >
			${projUser.userName }&nbsp;&nbsp;
			</c:forEach>
			</td></tr>
			<c:if test="${proj.vistorType eq '2'}">
			<tr><td class="tdlength">白名单:</td><td>
			<c:forEach items="${projWhite }" var="projWhiteMember">
			${projWhiteMember.userName }&nbsp;&nbsp;
			</c:forEach>
			</td></tr>
			</c:if>
			<tr><td class="tdlength">创建时间:</td><td>${proj.createTime }</td></tr>
			<tr><td class="tdlength">创建人:</td><td>${projCreater.userName }</td></tr>
			<tr><td class="tdlength">开始时间:</td><td>${proj.startTime }</td></tr>
			<tr><td class="tdlength">结束时间:</td><td>${proj.endTime }</td></tr>
			<tr><td class="tdlength">项目状态:</td><td>${proj.projState }</td></tr>
			<tr><td class="tdlength">可用工时:</td><td>${usableTime }小时</td></tr>
			<tr><td class="tdlength">已消耗时间:</td><td>${expendTime }小时</td></tr>
			<tr><td class="tdlength">项目进度:</td><td>${process}%</td></tr>
			<tr><td class="tdlength">项目负责人:</td><td>${projChief.userName }</td></tr>
			<tr><td class="tdlength">备注:</td><td>${proj.remark }</td></tr>
			</table>
			<div class="row placeholders">
			</div>
		</div>
	</div>		
</body>
<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</html>
