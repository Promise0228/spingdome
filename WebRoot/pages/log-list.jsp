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

<title>日志管理 - 日志列表</title>
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
			<h1 class="page-header">日志列表</h1>
			<div class="row placeholders">
				<div>
					<form method="get"
						action="${pageContext.request.contextPath}/loglist.action">
						<select name="projName"  id="projName" style="height: 29px; width: 100px">
						<option  style="display: none;height: 100%" value="">项目名称</option>  
						 <c:forEach items="${projectInfoList }" var="projectInfo">
						<option value="${projectInfo.projId }" ${(param.projName==projectInfo.projId)?"selected='selected'":"" }>${projectInfo.projName}</option> 
						</c:forEach>  
						</select>&nbsp;&nbsp;
						<input type="text" id="logInfo" name="logInfo" value="${param.logInfo }" style="height:28px; width:130px" placeholder="日志内容"">&nbsp;&nbsp;
						<input type="text" id="ipAddress" name="ipAddress" value="${param.ipAddress }"  style="height:28px; width:130px" placeholder="IP"">&nbsp;&nbsp;
						<input type="text" id="exception" name="exception" value="${param.exception }" style="height:28px; width:130px" placeholder="异常信息"">&nbsp;&nbsp;
						<input type="text" id="userName" name="userName" value="${param.userName }" style="height:28px; width:130px" placeholder="操作人"">&nbsp;&nbsp;
						<input type="date" name="stateTime" value="${param.stateTime }" style="height:28px; width:130px">&nbsp;&nbsp;
						<input type="date" name="endTime" id="endTime" value="${param.endTime }" style="height:28px; width:130px">&nbsp;&nbsp;
						<input type="submit" id="findLogInfoBy" class="btn btn-primary" value="查询"">
						<br />
						<br />
						<a href = "${pageContext.request.contextPath}/${page.url}?${page.params}&export=1">
						<button type="button" class="btn btn-warning delete-query"
						data-toggle="modal" data-target="#delete-confirm-dialog" id="exportData">导出并删除日志信息</button>
						</a>	
					</form>
				</div>
			</div>
		
	

				
					<!--  删除所选对话框 -->
					 <div class="modal fade " id="delete-confirm-dialog" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">警告</h4>
								</div>
								<div class="modal-body">确认删除导出的数据吗</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button"
										class="btn btn-primary delete-selected-confirm">确认</button>
								</div>
							</div>
						</div>
					</div> 
			</form>
			<div class="space-div"></div>
			<table class="table table-hover table-bordered log-list">
				<tr style="text-align: center;">
					<td><input type="checkbox" class="select-all-btn" /></td>
					<td>ID</td>
					<td>IP</td>
					<td>url</td>
					<td>内容</td>
					<td>异常信息</td>
					<td>创建时间</td>
					<td>操作人</td>
				</tr>
				<c:forEach items="${page.resultList}" var="logInfo">
					<tr style="text-align: center;">
						<td><input type="checkbox" name="logIds"
							value="${logInfo.logId }" /></td>
						<td class="logid">${logInfo.logId }</td>
					    <td class="ipAddress">${logInfo.ipAddress }</td>
						<td class="url">${logInfo.url }</td>
						<td class="loginfo">${logInfo.logInfo }</td>
						<td class="exception">${logInfo.exception }</td>
						<td class="createtime"><fmt:formatDate
								value="${logInfo.createTime}" pattern='yyy-MM-dd HH:mm:ss' /></td>
						<c:forEach items="${allUsers }" var="au">
						<c:if test="${au.userId==logInfo.createBy }">
						<td class="username">${au.userName}</td>
						</c:if>
						</c:forEach>
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
		//批量删除用户
		$(".delete-selected-confirm").click(function() {
		   $("#delete-confirm-dialog").modal("hide");
								//请求删除所选用户
								$.ajax({
					     		url : "${pageContext.request.contextPath}/deleteLogInfo.action",
								dataType : "json",
								type : "POST",
								success : function(data) {
										if (data.re == 1) {
										alert("导出数据删除成功！");
												} else {
													alert("删除所选失败！");
												}
												 location.reload(true);
											}
										});
										});		
		//设置分页的下拉列表的默认值
		$(document).ready(function(){	
    $("#selectedpage").find("option[value='"+${page.page}+"']").prop("selected",true);
		});
					
	</script>
</body>
</html>
