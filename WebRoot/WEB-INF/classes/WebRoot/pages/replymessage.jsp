<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<body style="background-color: #C0E0E0; ">

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<c:if test="${mark!='allreply' }">
					<h1 class="page-header">单个回复</h1>
				</c:if>
				<c:if test="${mark=='allreply' }">
					<h1 class="page-header">全部回复</h1>
				</c:if>

				<div class="row placeholders">
					<center>
						<form action="${pageContext.request.contextPath}/platfrom/replymessage.action" enctype="multipart/form-data" method="post">
							<table>
								<c:if test="${mark!='allreply' }">
									<tr>
										<td>
											<h3>内容</h3>
										</td>
									</tr>
									<tr>
										<td>
									 <c:forEach items="${message }" var="message">
		 标题:${message.msgtitle} <br>
		 内容:${message.msgcontext }<br>
		 附件:${message.fileurl }<br>
		时间:${message.createtime }<br>
						             </c:forEach>
						           
										</td>
									</tr>
								</c:if>
								<tr>
									<td>
										<h3>${UserInfo.userCode }回复:${touser }<input type="hidden" value="${touser }" name="touser">
										</h3>
									</td>
								</tr>
								<tr>
									<td>
										<h5>
											标题:
											<input type="text" name="msgtitle">
										</h5>
									</td>
								</tr>
								<tr>
									<td>
										<h5>
											附件:
											<input type="file" name="file" accept="image/*">
										</h5>
									</td>
								</tr>
								<tr>
									<td>
										<textarea rows="6" cols="60" name="msgcontext"></textarea>
									</td>
								</tr>
								<tr>
									<td>
										<input type="submit">
									</td>
								</tr>
							</table>
						</form>
					</center>
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
