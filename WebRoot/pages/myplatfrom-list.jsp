<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>我的站内信管理</title>

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
				<h1 class="page-header">我的站内信列表</h1>

				<div class="row placeholders">
					<div style="text-align: right;">
						<form id="form1" action="${pageContext.request.contextPath}/platfrom/myplatfromlist.action">
							<input type="text" maxlength="16" value="${param.msgtitle }" name="msgtitle" placeholder="站内信标题">
							<select name="readstate" id="readstate">
								<option value="">信息状态</option>
								<option value="1" ${(param.readstate=='1')?"selected='selected'":"" }>已读</option>
								<option value="0" ${(param.readstate=='0')?"selected='selected'":"" }>未读</option>
							</select>
							<input type="date" name="begintime" value="${param.begintime }">
							到
							<input type="date" name="endtime" value="${param.endtime }">
							<input type="submit" class="btn btn-primary" value="查询">
						</form>
						
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/platfrom/allreply.action">
						<button type="button" class="btn btn-warning " data-toggle="modal" data-target="">回复全部</button>
						</a>
						<a href="${pageContext.request.contextPath}/platfrom/allmarkread.action"><button class="btn btn-warning ">标记全部已读</button></a>
						<a href="${pageContext.request.contextPath}/platfrom/myplatfromlist.action?readstate=0"><button class="btn btn-warning ">未读消息${nearOutMessage }条</button></a>
						<!--  删除所选对话框 -->
						<div class="modal fade " id="delete-confirm-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-sm" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">警告</h4>
									</div>
									<div class="modal-body">确认删除所选权限吗</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary delete-selected-confirm">确认</button>
									</div>
								</div>
							</div>
						</div>
						<!-- <button type="button" class="btn btn-primary show-add-form" data-toggle="modal" data-target="#perm-form-div">添加站内信</button> -->
						<!--添加新权限表单-->
						<div class="modal fade " id="perm-form-div" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="perm-form-title"></h4>
									</div>
									<div class="modal-body">
										<form class="perm-form">
											<input type="hidden" name="msgid" class="form-control" id="msgidInput">
											<div class="form-group">
												<label for="descInput">站内信名称</label>
												<input type="text" maxlength="16" name="permissionDesc" class="form-control" id="descInput" placeholder="站内信名称">
											</div>
											<div class="form-group">
												<label for="sendtypeInput">发送类型</label>
												<input type="text" maxlength="16" name="sendtype" class="form-control" id="sendtypeInput" placeholder="发送类型">
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary perm-submit"></button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="space-div"></div>
					<table class="table table-hover table-bordered mymessage-list">
						<tr style="text-align: center;">
							<td>
								<input type="checkbox" class="select-all-btn" />
							</td>
							<td>站内信ID</td>
							<td>信息来自</td>
							<td>站内信标题</td>
							<td>站内信内容</td>
							<td>站内信附件</td>
							<td>信息状态</td>
							<td>创建时间</td>
							<td>操作</td>
						</tr>

						<c:forEach items="${page.resultList }" var="perm">
							<tr style="text-align: center;">
								<td>
									<input type="checkbox" name="permIds" value="${perm.msgid }" />
								</td>
								<td class="msgid1">${perm.msgid }</td>
								<c:forEach items="${allUsers }" var="au">
								<c:if test="${au.userId == perm.fromUser }">
								<td>${au.userName }</td>
								</c:if>
								</c:forEach>
								<td>${perm.msgtitle }</td>
								<td>${perm.msgcontext }</td>
								<td>
								<a href="${pageContext.request.contextPath}/platfrom/download.action?fileurl=${perm.fileurl }">${perm.fileurl }</a>
								<%-- <button class="deals"  data-toggle="modal" data-target="#deal" value="${perm.fileurl }">详情</button> --%>
								<!-- <input type="button" value="详情" class="deals"  data-toggle="modal" data-target="#deal -->
								</td>
								<c:if test="${perm.readstate=='0' }">
								<td>未读</td></c:if>
								<c:if test="${perm.readstate=='1' }">
								<td>已读</td></c:if>
								<td class="createtime">
								${perm.createtime }
								</td>
								<td>
								<input type="button" value="回复" class="one-reply">
								</td>
							</tr>
						</c:forEach>
					</table>
					<c:if test="${page.resultList=='[]' }">
						<h2 style="text-align: center; color: red;">没有查到相应数据</h2>
					</c:if>
					<c:if test="${page.resultList!='[]' }">
						<div style="text-align: center;">
							<jsp:include page="standard.jsp" /></div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- 提示框 -->
	<div class="modal fade" id="deal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
	<div class="modal-content">
	<div class="modal-header">
	<center>
	<h4 class="modal-title">附件详情</h4>
	<h5>标题:</h5>
	<h5>内容:</h5>
	<h5>时间:</h5>
	<input type="image" src="">
	</center>
	</div>
	</div>
	</div>
	gun
	</div>
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
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
		$(".select-all-btn").change(
				function() {
					if ($(this).is(":checked")) {//$(this).prop("checked")
						$(".perm-list input[type='checkbox']:gt(0)").prop(
								"checked", true);
					} else {
						$(".perm-list input[type='checkbox']:gt(0)").prop(
								"checked", false);
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
		$(".one-reply").click(function(){
			var msgTr = $(this).parents("tr");
			var msgid = msgTr.find(".msgid1").html();
			$.ajax({
				url : "${pageContext.request.contextPath}/platfrom/getusercode.action",
				data : {
					msgid : msgid,
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					window.location.href="${pageContext.request.contextPath}/platfrom/onereply.action?usercode="+ data.usercode + "&mark=1&msgid="+ data.msgid + "";
				}
			})
		
		})
	</script>
</body>
</html>
