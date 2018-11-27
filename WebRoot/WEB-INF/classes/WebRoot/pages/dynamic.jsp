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

<title>项目管理-项目动态</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link 
	href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
   <style type="text/css">
   	.red{color:red}
   	.tdlength{width:30%}
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
			<h1 class="page-header">项目动态</h1>
			<div>
				<form action="${pageContext.request.contextPath}/proj/dynamicList.action">
					<input type="hidden" name="projId" value="${param.projId }"/>
					<input type="date" name="createTime" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="userName" placeholder="操作人"/>
					<button type="submit"  id="selectProjDyna" class="btn btn-warning">查询</button>
					</form>
					<c:if test="${fn:contains(authcodes,'add-dynamic')}">
					<button type="submit"  id="createDynamic" class="btn btn-warning">创建动态</button>
					</c:if>
				</div>
<c:forEach items="${page.resultList}" var="projDyna">
		<div><h3>${projDyna.dynamicDesc}</h3>
			<p>创建时间:${projDyna.createTime} &nbsp; &nbsp; &nbsp;创建人:${projDyna.userName} 
			&nbsp; &nbsp; &nbsp;<input class="reply" type="button" value="回复">
			<input type="hidden" name="dynamicId" value="${projDyna.dynamicId }"/>
			<input class="delete" type="button" value="删除">
			</p>
		</div>
		<table class="table table-hover ">
			<c:forEach items="${projDyna.allComments}" var="from">				
				<c:if test="${not (from.parentId eq projDyna.dynamicId)}">
					<c:forEach items="${projDyna.allComments}"  var="to">
						<c:if test="${from.parentId==to.dynamicId}">
							<tr><td class="tdlength">${from.createTime }&nbsp;${from.userName}回复${to.userName}:
							<td>${from.dynamicDesc}&nbsp;&nbsp;&nbsp;<input class="reply" type="button" value="回复"/><input type="hidden" name="dynamicId" value="${from.dynamicId }"/>
							<input class="delete" type="button" value="删除"></td></tr>
							
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${from.parentId eq projDyna.dynamicId }">
					<tr><td class="tdlength">${from.createTime }&nbsp;${from.userName}回复:
					<td>${from.dynamicDesc }&nbsp;&nbsp;&nbsp;<input class="reply" type="button" value="回复"/><input type="hidden" name="dynamicId" value="${from.dynamicId }"/>
					<input class="delete" type="button" value="删除"></td></tr>
					
				</c:if>
			</c:forEach>
		</table>
</c:forEach>
	<div style="display: none" align="center" class="showTextArea">
		<textarea id="replyTextArea" rows="2" cols="100"></textarea>
		<input type="button" class="submitReply" value="提交" />
	</div>
	<jsp:include page="standard.jsp" />
</div>		
</div>
</body>
<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script type="text/javascript">
	//要回复的项目动态Id或回复Id,也就是将来插入回复的父Id
	var dynamicId="";
	//获取要回复的项目动态Id或回复Id,显示回复框
	$(".reply").click(
		function() {
			$(".showTextArea").toggle();
			dynamicId = $(this).parent().find("input:hidden[name='dynamicId']").val();
		}
	)
	$("#createDynamic").click(
		function() {
			$(".showTextArea").toggle();
			dynamicId = 0;
		}
	)
	//确认回复
	$(".submitReply").click(function(){
		var dynamicDesc = $("#replyTextArea").val();
		var projId=${param.projId};
		$.ajax({
			url:"${pageContext.request.contextPath}/proj/addReply.action",
			type:"POST",
			dataType:"json",
			data:{
				dynamicDesc:dynamicDesc,
				parentId:dynamicId,
				projId:projId				
			},
			success:function(data){
				alert("添加成功");
				location.href="${pageContext.request.contextPath}/proj/dynamicList.action?projId=${param.projId}";
			}
		});
	});
	
	$(".delete").click(function(){
	alert("请问是否删除本条动态！！");
	
	});
</script>
</html>
