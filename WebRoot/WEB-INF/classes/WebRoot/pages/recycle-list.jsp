<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>用户管理</title>

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
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">回收站列表</h1>
				<div>
					<form
						action="${pageContext.request.contextPath}/recycle/recyclelist.action">
						<input type="text" name="projName" placeholder="项目名称"
							maxlength="20" value="${param.projName}"> &#160 <input
							type="text" name="className" placeholder="模块名称" maxlength="50"
							value="${param.className}"> &#160 <input type="text"
							name="intro" placeholder="名称" maxlength="50"
							value="${param.intro}">&#160<input type=date
							name=beginTime value="${param.beginTime}">&#160&#160-&#160
						<input type=date name=endTime value="${param.endTime}"> <input
							type="submit" value="查询" class="btn btn-primary">
					</form>
				</div>
				<br />
				<div class="row placeholders">
					<div>
						<button type="button" class="btn btn-warning delete-query"
							data-toggle="modal" data-target="#delete-confirm-dialog">删除所选</button>
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
									<div class="modal-body">确认删除所选权限吗</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button"
											class="btn btn-primary delete-selected-confirm">确认</button>
									</div>
								</div>
							</div>
						</div>

						<!--添加新权限表单-->
						<div class="modal fade " id="perm-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="perm-form-title"></h4>
									</div>
									<div class="modal-body"></div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary perm-submit"></button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="space-div"></div>
					<table class="table table-hover table-bordered perm-list">
						<tr align="center">
							<td><input type="checkbox" class="select-all-btn" /></td>
							<td>ID</td>
							<td>描述</td>
							<td>名称</td>
							<td>时间</td>
							<td>操作</td>
						</tr>
						<!-- <tr>
                        <td><input type="checkbox" name="permIds"/></td>
                        <td class="permid">11</td>
                        <td>增加</td>
                        <td>/add</td>
                        <td>是</td>
                        <td>add</td>
                        <td><a class="glyphicon glyphicon-pencil show-perminfo-form" aria-hidden="true" title="修改权限信息" href="javascript:void(0);" data-toggle="modal" data-target="#perm-form"></a>
                    	<a class="glyphicon glyphicon-remove delete-this-perm" aria-hidden="true" title="删除权限" href="javascript:void(0);"></a></td>
                    </tr> -->
						<c:forEach items="${page.resultList}" var="recycle">
							<tr align="center">
								<td><input type="checkbox" name="permIds"
									value="${perm.permissionId }" /></td>
								<td class="recyid">${recycle.recyId }</td>
								<td>${recycle.intro }</td>
								<td>${recycle.keyName }<input type="hidden"
									value="${recycle.keyName }" class="keyName"> <input
									type="hidden" value="${recycle.tableName }" class="tableName"><input
									type="hidden" value="${recycle.keyValue }" class="keyValue"></td>
								<td class="createtime"><fmt:formatDate
										value='${recycle.createTime}' pattern='yyyy-MM-dd' /></td>
								<td><button type="button"
										class="btn btn-primary show-user-form" id="updaterecycle">还原
									</button> <!--  <a class="glyphicon glyphicon-remove delete-this-perm"
									aria-hidden="true" title="删除权限" href="javascript:void(0);"></a> -->
									<button type="button"
										class="btn btn-warning delete-query delete-this-perm"
										data-toggle="modal" id="updateUserState">删除</button></td>
							</tr>
						</c:forEach>
					</table>
					<br />
					<c:if test="${page.resultList=='[]'}">
						<h2 style="text-align: center;color:red;">暂无相应数据</h2>
					</c:if>
					<c:if test="${page.resultList!='[]'}">
						<div style="text-align: center;">
							<jsp:include page="standard.jsp" />
						</div>

					</c:if>

				</div>
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
		function showTips(contents) {
			$("#op-tips-content").html(contents);
			$("#op-tips-dialog").modal("show");
		}
		function resetPermForm(title, button) {
			$(".perm-form input[type='text']").val("");
			$(".perm-form input[type='checkbox']").prop("checked", false);
	
			$(".perm-form-title").html(title);
			$(".perm-submit").html(button);
		}
		$(".select-all-btn").change(function() {
			if ($(this).is(":checked")) { //$(this).prop("checked")
				$(".perm-list input[type='checkbox']:gt(0)").prop("checked", true);
			} else {
				$(".perm-list input[type='checkbox']:gt(0)").prop("checked", false);
			}
		});
		$(".delete-selected-confirm").click(function() {
			$("#delete-confirm-dialog").modal("hide");
			var cbs = $("input[name='permIds']:checked")
			if (cbs.length === 0) {
				showTips("没有选中任意项！");
			} else {
				var permIds = new Array();
				$.each(cbs, function(i, cb) {
					permIds[i] = cb.value;
				});
				//请求删除所选角色
				$.ajax({
					url : "deletemore.html",
					data : {
						permIds : permIds
					},
					type : "POST",
					traditional : true,
					success : function() {
						cbs.parent().parent().remove();
						showTips("删除所选成功！");
					}
				});
			}
		});
		
		
		//数据的删除(物理删除)（表名称、主键key、主键value），同时删除回收站中数据
		$(".perm-list").on("click", ".delete-this-perm", function() {
			var permTr = $(this).parents("tr");
			var recyId = permTr.find(".recyid").html();
			var keyname = permTr.find(".keyName").val();
			var keyvalue = permTr.find(".keyValue").val();
			var tablename = permTr.find(".tableName").val();
			if (confirm('确认删除该信息吗')) {
				//请求删除该权限
				$.ajax({
					url : "${pageContext.request.contextPath}/recycle/delRecycleById.action",
					data : {
						recyId : recyId,
						keyName : keyname,
						keyValue : keyvalue,
						tableName :tablename
					},
					type : "POST",
					dataType : "json",
					success : function(date) {
						if (date.del == '1') {
							permTr.remove();
							alert("删除成功！");
						} else if (date.del == '2'){
							alert("表删除失败！");
						}else if (date.del == '3'){
							alert("文件删除失败！");
						}else if (date.del == '4'){
							alert("文件不存在！");
						}
						location.reload(true);
					}
				});
			}
		});
	
		//回收站还原,同时删除回收站中数据
		$(".perm-list").on("click", ".show-user-form", function() {
			var permTr = $(this).parents("tr");
			var keyname = permTr.find(".keyName").val();
			var keyvalue = permTr.find(".keyValue").val();
			var tablename = permTr.find(".tableName").val();
			if (confirm('确认还原该信息吗')) {
				//请求删除该权限
				$.ajax({
					url : "${pageContext.request.contextPath}/recycle/rollbackRecycle.action",
					data : {
						keyName : keyname,
						keyValue : keyvalue,
						tableName :tablename
					},
					type : "POST",
					dataType : "json",
					success : function(date) {
						if (date.roll == 1) {
							permTr.remove();
							alert("还原成功！");
						} else {
							alert("还原失败！");
						}
						location.reload(true);
					}
				});
			}
		});
	</script>
</body>
</html>
