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

<title>项目管理 - 添加白名单</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
	  <style type="text/css">
   	.red{color:red}
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
			<h1 class="page-header">添加白名单</h1>
			<div class="row placeholders">
				<div>
				<form action="${pageContext.request.contextPath}/proj/whiteSheet.action">
					<input type="text" name="userName" value="${param.userName}" maxlength="16" placeholder="用户名"> 
					&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="hidden" name="projId" value="${param.projId}"/>
					<button type="submit"  id="selectAll">查询</button>
				</form>
				</div>
				<br />
				<!-- 添加所选 -->
				<button type="button" class="btn btn-warning add-white-sheet"
						data-toggle="modal" data-target="#add-confirm-dialog">添加所选</button>
				<!-- 修改白名单 -->
				<button type="button" class="btn btn-warning select-white-sheet"
						data-toggle="modal" data-target="#update-confirm-dialog"
				>修改白名单</button>
				<div class="space-div"></div>
				<!-- 确认添加对话框 -->
				<div class="modal fade " id="add-confirm-dialog" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">提醒</h4>
								</div>
								<div class="modal-body">确认添加所选用户吗</div>
								<div id="addUser"></div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button"
										class="btn btn-primary add-selected-confirm">确认</button>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade " id="update-confirm-dialog" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">提醒</h4>
								</div>
								<div class="modal-body">确认修改所选用户吗</div>
								<div id="updateUser"></div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button"
										class="btn btn-primary update-selected-confirm">确认</button>
								</div>
							</div>
						</div>
					</div>
				<table class="table table-hover table-bordered user-list">
					<tr>
						<td><input type="checkbox" class="select-all-btn" /></td>
						<td>ID</td>
						<td>用户组</td>
						<td>用户名</td>
						<td>昵称</td>
						<td>用户状态</td>
					</tr>
					<c:forEach items="${page.resultList }" var="user">
						<tr>
							<td><input type="checkbox" name="userIds" value="${user.userId }" /></td>
							<td class="userid">${user.userId }</td>
							<td class="groupid">${user.groupId }</td>
							<td class="username">${user.userName }</td>
							<td class="nickName">${user.nickName }</td>						
							<td class="userState">
								${user.userState==1?"启用":"禁用"}
							</td>							
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="standard.jsp" />
			</div>
		</div>
	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
	
	<script>
		$(function(){
			//将cookie中存在的复选框选中
			$(".user-list input[name='userIds']").each(function(i){
				var userId=$(this).val();
				var userIds=$.cookie("userIds");
				if(userIds&&userIds.indexOf(userId)!=-1){
					$(this).prop("checked",true);
				}
			})
			//存入cookie
			$(".user-list input[name='userIds']").change(function(){
				var userIds=",";
				if($.cookie("userIds")){
					userIds="";
					userIds += $.cookie("userIds");
				}
				$(".user-list input[name='userIds']").each(function(i){
					var userId=$(this).val();
					var flag = $(this).prop("checked");
					//将选中的checkbox加入cookie
					if(flag&&userIds.indexOf(","+userId+",")==-1){
						userIds+=userId+",";
					}
					//将未选中的checkbox从cookie中剔除
					if(!flag&&userIds.indexOf(","+userId+",")!=-1){
						userIds=userIds.replace(","+userId,"");
				}
			})
			$.cookie("userIds",userIds,{path:"/"});
		})
		
		})
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
		
		
		$(".select-all-btn").change(function() {
					if ($(this).is(":checked")) {//$(this).prop("checked")
						$(".user-list input[type='checkbox']:gt(0)").prop(
								"checked", true);
					} else {
						$(".user-list input[type='checkbox']:gt(0)").prop(
								"checked", false);
					}
					//对选中的其他复选框加入cookie
					var userIds=",";
					var flag = $(this).prop("checked");
					if(flag){
						$(".user-list input[name='userIds']").each(function(i){
							var userId=$(this).val();
							userIds+=userId+",";
						})
					}
					$.cookie("userIds",userIds,{path:"/"});
					alert("userIds:"+userIds);
		});
		//修改白名单
		$(".select-white-sheet").click(function(){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${pageContext.request.contextPath}/proj/selectWhiteSheet.action",
				data:{
					 projId:${param.projId}
				},
				success:function(data){
					$("#updateUser").html("");
					for(var i=0;i<data.length;i++){
						$("#updateUser").append("<input type='checkbox' name='updateUserIds' checked='checked' value="
						+data[i].userId+" />"+data[i].userName);
					}
				}
			})	
		})
		//确认修改
		$(".update-selected-confirm").click(function(){
			var userIds="";
			var projId=${param.projId};
			$("input[name='updateUserIds']:checked").each(function(i){
				userIds+=$(this).val()+",";
			})
			userIds=userIds.substring(0,userIds.length-1);
			$.ajax({
				url:"${pageContext.request.contextPath}/proj/updateWhiteSheet.action",
				type:"POST",
				data:{userIds:userIds,
					  projId:projId
				},
				dataType:"json",
				success:function(data){
					$("#update-confirm-dialog").modal("hide");
					alert("修改成功");
					location.href="${pageContext.request.contextPath}/proj/whiteSheet.action?userName=&projId=${param.projId}"
				}
			})
		})
		
		
		//添加白名单
		$(".add-white-sheet").click(function(){
			var userIds=$.cookie("userIds");
			alert(userIds);
			userIds=userIds.substring(1, userIds.length-1);
			alert(userIds);
			if(userIds!=""&&userIds!=","){
			$.ajax({
				url:"${pageContext.request.contextPath}/user/findUsers.action",
				type:"POST",
				data:{userIds:userIds},
				dataType:"json",
				success:function(data){
					$("#addUser").html("");
					for(var i=0;i<data.length;i++){
						$("#addUser").append("<input type='checkbox' name='addUserIds' checked='checked' value="
						+data[i].userId+" />"+data[i].userName);
					}
				}
			})
			}
		})
		$(".add-selected-confirm").click(function(){
			var userIds="";
			var projId=${param.projId};
			$("input[name='addUserIds']:checked").each(function(i){
				userIds+=$(this).val()+",";
			})
			userIds=userIds.substring(0,userIds.length-1);
			$.ajax({
				url:"${pageContext.request.contextPath}/proj/addWhiteSheet.action",
				type:"POST",
				data:{userIds:userIds,
					  projId:projId
				},
				dataType:"json",
				success:function(data){
					$("#add-confirm-dialog").modal("hide");
					alert("添加成功");
					$.cookie("userIds","",{expires:-1,path:"/"});
					location.href="${pageContext.request.contextPath}/proj/whiteSheet.action?userName=${param.userName}&projId=${param.projId}"
				}
			})
		})
	</script>
</body>
</html>
