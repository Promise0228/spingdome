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

<title>发送站内信</title>

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
				<h1 class="page-header">发送站内信</h1>
				<div class="row placeholders">
					<div style="position: absolute; width:95%; height:20%; top:80%; left:0%; ">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="单发" id="one-send" />
						<input type="button" value="多发" id="more-send" />
						<input type="button" value="群发" id="group-send" />
						<input type="button" value="全发" id="all-send" />
						<br>
					</div>

					<div style="position: absolute; width:65%; height:320%; top:100%; left:0%;color: blue; ">
						<form action="${pageContext.request.contextPath}/platfrom/sendmessage.action" enctype="multipart/form-data" method="post">
							<br>
							<h5>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收件人:
										<input type="text" size="85" id="touser" name="touser" />
							</h5>
							<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="添加抄送" /> -->
							<!-- <br> -->
							<h5>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标题:&nbsp;&nbsp;&nbsp;
								<input type="text" size="85" name="msgtitle" />
								<input type="hidden" name="sendtype" id="sendtype" value="1" />
							</h5>
							<table>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td>
										<input type="file" name="file" accept="image/*">
									</td>
								</tr>
							</table>
							<h5>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发送内容:&nbsp;&nbsp;
								<textarea name="msgcontext" cols="80" rows="10"></textarea>
							</h5>
							<div style="text-align:right; ">
								<c:choose>
									<c:when test="${mark==1 }">
										<input type="hidden" value="1" name="one-reply">
										<input type="hidden" value="${msgid }" name="msgid">
										<input type="submit" value="发送" id="send" />
									</c:when>
									<c:otherwise>
										<input type="submit" value="发送" id="send" />
									</c:otherwise>
								</c:choose>
							</div>
					</div>
					</form>
					<div style="position: absolute; width:32%; height:320%; top:100%; left:65%;  text-align: center;">
						<b> 通讯录</b>
						<!-- <br>
						<br>
						<input type="text" size="30" id="usercode" />
						<input type="button" value="查询" id="find-users" />
						<br>
						<br>
						<a>最近联系人</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a>所有联系人</a> -->
						<div style=" overflow:scroll; height: 100%; " id="more-user" >
							<c:forEach items="${allUsers }" var="user">
								<input name="musercode" class="user-code" type="checkbox" value="${user.userCode }">${user.userName }<br>
							</c:forEach>
						</div>
						<div style="overflow:scroll; height: 100%;" id="one-user">
							<c:forEach items="${allUsers }" var="user">
								<input name="ousercode" class="user-code" type="radio" value="${user.userCode }">${user.userName }<br>
							</c:forEach>
						</div>
						<div style="overflow:scroll; height: 100%;" id="team-user">
							<c:forEach items="${team }" var="team">
								<input name="tusercode" class="user-code" type="radio" value="${team.teamCode }">${team.teamName }<br>
							</c:forEach>
						</div>
						<div style="overflow:scroll; height: 100%;" id="find-user">
							<c:forEach items="${allUsers }" var="user">
								<input name="fusercode" class="user-code" type="checkbox" value="${user.userCode }">${user.userName }<br>
							</c:forEach>
						</div>
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
<script language="JavaScript" type="text/javascript">
	$("#more-user").hide();
	$("#team-user").hide();
	$("#find-user").hide();
	$("#one-send").click(function() {
		$("#sendtype").val("1");
		$("#touser").val("");
		$("#one-user").show();
		$("#more-user").hide();
		$("#team-user").hide();
	})
	$("#more-send").click(function() {
		$("#sendtype").val("2");
		$("#touser").val("");
		$("#more-user").show();
		$("#team-user").hide();
		$("#one-user").hide();
	})
	$("#group-send").click(function() {
		$("#sendtype").val("3");
		$("#touser").val("");
		$("#team-user").show();
		$("#one-user").hide();
		$("#more-user").hide();
	})
	$("#find-users")
			.click(
					function() {
						var usercode = $("#usercode").val();
						alert(usercode);
						$
								.ajax({
									url : "${pageContext.request.contextPath}/platfrom/findusers.action",
									dataType : "json",
									data : {
										usercode : usercode,
									},
									type : "post",
									success : function(data) {
										
										$("#more-user").hide();
										$("#team-user").hide();
										$("#one-user").hide();
										$("#find-user").show();
									},
									error : function() {

									}

								})

					})
	$("#more-user").change(function() {
		var uc = $("input[name='musercode']:checked");
			var usercode = "";
			if(uc.length>0){
				$.each(uc, function(i, u) {
					usercode += u.value + ",";
				});
			}
		$("#touser").val(usercode);
	})
	$("#find-user").change(function() {
		var fc = $("input[name='fusercode']:checked");
			var usercode = "";
			if(fc.length>0){
				$.each(fc, function(i, u) {
					usercode += u.value + ",";
				});
			}
		$("#touser").val(usercode);
	})
	$("#one-user").change(function() {
		var oc = $("input[name='ousercode']:checked");
			var usercode = "";
			if(oc.length>0){
				$.each(oc, function(i, u) {
					usercode += u.value + ",";
				});
			}
		$("#touser").val(usercode);
	})
	$("#team-user").change(function() {
		
		var tc = $("input[name='tusercode']:checked");
			var usercode = "";
			if(tc.length>0){
				$.each(tc, function(i, u) {
					usercode += u.value + ",";
				});
			}
		$("#touser").val(usercode);
	})
	$("#all-send")
			.click(
					function() {
						$("#more-user").hide();
						$("#team-user").hide();
						$("#find-user").hide();
						$("#one-user").hide();
						$("#touser").val("");
						$("#sendtype").val("4");
						$
								.ajax({
									url : "${pageContext.request.contextPath}/platfrom/allsend.action",
									dataType : "json",
									data : {
									},
									type : "post",
									success : function(data) {
										$("#touser").val(data.usercode);
									}
								})
					})
</script>

</html>
