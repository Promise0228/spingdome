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

<title>版本信息管理 - 版本信息列表</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
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
			<h1 class="page-header">版本信息列表</h1>
			<div class="row placeholders">
				<div>
					<form method="post"
						action="${pageContext.request.contextPath}/projlist.action">
						<input type="text" id="versionNum" name="versionNum" value="${param.versionNum }" style="height:28px; width:130px" placeholder="版本号">&nbsp;&nbsp;
						<input type="text" id="versionDesc" name="versionDesc" value="${param.versionDesc }" style="height:28px; width:130px" placeholder="版本说明">&nbsp;&nbsp;
						<input type="date" name="stateTime" value="${param.stateTime }" style="height:28px; width:130px">&nbsp;&nbsp;
						<input type="date" name="endTime" id="endTime" value="${param.endTime }" style="height:28px; width:130px">&nbsp;&nbsp;
						<input type="hidden" name="projId" value="${projId }">
						<input type="submit" id=findProjBy class="btn btn-primary" value="查询"">
						<br />
						<br />
						<button type="button" class="btn btn-primary show-user-form"
						data-toggle="modal" data-target="#add-proj-form">添加版本信息</button>
						<a href="${pageContext.request.contextPath}/proj/proj-list.action"><button type="button" style="width:100px;" class="btn btn-primary">返回</button></a>
					</form>
				</div>
								
					<!--添加新版本信息表单-->
					<div class="modal fade " id="add-proj-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">添加新版本</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group">
											<label for="versionNumInput">版本号</label>
											 <input type="text" required placeholder="请输入版本号"  maxlength="20"
												name="versionNum" class="form-control" id="versionNumInput"
												placeholder="版本号"  />
												  <label id="versionNumError"></label>
										</div>
										<div class="form-group">
											<label for="versionDescInput">版本说明</label> 
											<input type="text"  required placeholder="请输入版本说明"  maxlength="20"
												 name="versionDesc" class="form-control" id="versionDescInput"
												placeholder="版本说明"/>
											
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary add-proj-submit" >添加
									</button>
								</div>
							</div>
						</div>
					</div>
				
		 <!--修改用户角色表单-->
        <div class="modal fade " id="update-userrole-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">						
						<div class="modal-body">
							<form class="update-userrole-form">
								<div class="form-group">
									<label for="versionNumInput">更改版本号</label>  
									<input type="text" name="versionNum" class="form-control" id="versionNum1" maxlength="20" >
								</div>
								<div class="form-group">
									<label for="versionDescInput">更改版本说明</label>
									<input type="text" name="versionDesc" class="form-control" id="versionDesc1" maxlength="50">
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary update-userrole-submit">提交 </button>
						</div>
					</div>
				</div>
			</div>				
			<div class="space-div"></div>
			<table class="table table-hover table-bordered proj-list">
				<tr align="center">
					<td><input type="checkbox" class="select-all-btn" /></td>
					<td>ID</td>
					<td>版本号</td>
					<td>版本说明</td>
					<td>创建时间</td>
					<td>创建人</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${page.resultList}" var="projVersion">
					<tr align="center">
						<td><input type="checkbox" name="versionIds"
							value="${projVersion.versionId }" /></td>							
						<td class="versionid">${projVersion.versionId }</td>
					    <td class="versionnum">${projVersion.versionNum }</td>
						<td class="versiondesc">${projVersion.versionDesc }</td>
						<td class="createtime"><fmt:formatDate
								value="${projVersion.createTime }" pattern='yyy-MM-dd HH:mm:ss' />
								</td>	
						<td class="createby">${userName}</td>
						<td>
						<a class="glyphicon glyphicon-wrench show-userrole-form"
								aria-hidden="true" title="修改版本信息" href="javascript:void(0);"
								data-toggle="modal" data-target="#update-userrole-dialog">
								</a>
								 <a class="glyphicon glyphicon-remove delete-this-proj"
								aria-hidden="true" title="删除版本信息" 
								data-toggle="modal" data-target="#delete-confirm-dialog" >
								</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		<c:if test="${page.resultList=='[]'}">
		<h1 style="text-align: center;color: red;">暂无数据</h1>
		</c:if>
		<c:if test="${page.resultList!='[]'}">
		<div style="text-align: center;">
		<jsp:include page="standard.jsp" />
		</div>
		</c:if>	
	</div>
</div>
</div>				
	<!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body" id="op-tips-content"></div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script>
		//提示信息
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
		//多选框
		$(".select-all-btn").change(function() {
			if ($(this).is(":checked")) {
			//$(this).prop("checked")
			$(".log-list input[type='checkbox']:gt(0)").prop("checked", true);
					} else {
						$(".log-list input[type='checkbox']:gt(0)").prop("checked", false);
					}
				});
		//添加版本信息
		$(".add-proj-submit").click(function() {
		var projId=${projId}
		alert(projId);
		var versionNum=$("#versionNumInput").val();//获取版本号
		var versionDesc=$("#versionDescInput").val();//获取版本信息		
			//请求添加新版本信息
			if(!! versionNum&&!!versionDesc){
			$.ajax({
				url:"${pageContext.request.contextPath}/addProjVersion.action",
				data : {
					versionNum:versionNum,
					versionDesc:versionDesc,
					projId:projId
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					$("#add-proj-form").modal("hide");
					if(data.msg==1){
					alert("添加成功！");
					location.reload(true);
					}else if(data.msg==-1){
					alert("版本号低于前版本号！");
					}else{
						alert("添加失败！!!");
					}				
				},
			error:function(json){
					//cbs.parent().parent().remove();
						alert("添加失败11！");
						location.reload(true);
					}
			});
			}else{
			alert("版本号或版本信息不能为空!!!");
			return;
			}
	
		});		
		
		//修改版本信息
			$(".proj-list").on("click", ".show-userrole-form", function() {
				var versionNum = $(this).parents("tr").find(".versionnum").html();
				$("#versionNum1").val(versionNum);
				var versionDesc = $(this).parents("tr").find(".versiondesc").html();				
				$("#versionDesc1").val(versionDesc);
				var versionId = $(this).parents("tr").find(".versionid").html();				
				$(".update-userrole-submit").click(function() {
					var versionNum = $("#versionNum1").val();
					var versionDesc1 = $("#versionDesc1").val();
					if(!!versionNum&&!!versionDesc1){
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/updateProjVersion.action",
						data:{
							versionNum: versionNum,
							versionDesc: versionDesc1,
							versionId:versionId
						},
						success: function(json) {
							if(json.msg==1) {
								alert("修改成功！");
								location.reload(true);
							}else if(json.msg==-1){
							alert("版本号低于前版本号！");
							}else{
							alert("修改失败！");
							}
						},
						error:function(json){
					    location.reload(true);
						alert("修改失败！");
					}
					});
					}else{
					alert("版本号或版本信息不能为空!!!");
					return;
					}
				});
			});		
				
		//删除用户信息
		$(".proj-list").on("click", ".delete-this-proj", function() {
		    var versionNum = $(this).parents("tr").find(".versionnum").html();
			var versionId = $(this).parents("tr").find(".versionid").html();
			if (confirm('确认删除版本号为"' + versionNum + '"的版本信息吗？')) {
				//删除单个用户
				$.ajax({
					url : "${pageContext.request.contextPath}/deleteProjVersion.action",
					data : {
						versionId : versionId
					},
					type : "POST",
					dataType:"json",
					success : function(data) {
						if(data.msg==1){
						alert("删除成功！");
						location.reload(true);
						}else{
						alert("删除失败！");
						return;
						}						
					}
				});
			}
		});					
	</script>
</body>
</html>
