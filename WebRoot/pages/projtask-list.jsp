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

<title>站内信管理</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
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
				<h1 class="page-header">任务管理列表</h1>

				<div class="row placeholders">
					<div style="text-align: right;">
						<form
							action="${pageContext.request.contextPath}/projtask/projtasklist.action">
							<select name="fprojId">
								<option value="">项目名称</option>
								<c:forEach items="${sysc }" var="sys">
									<option value="${sys.classId}"
										${(param.fprojId==sys.classId)?"selected='selected'":"" }>${sys.className }</option>
								</c:forEach>
							</select> <select name="fclassId">
								<option value="">模块名称</option>
								<c:forEach items="${syscmk }" var="sys">
									<option value="${sys.classId}"
										${(param.fclassId==sys.classId)?"selected='selected'":"" }>${sys.className }</option>
								</c:forEach>
							</select> <input type="text" maxlength="16" value="${param.taskName }"
								name="taskName" placeholder="任务名称">
								
								 <input type="text"
								maxlength="16" value="${param.usercode}" name="usercode"
								placeholder="执行人" selected> <input type="date"
								name="begintime" value="${param.begintime }"> 到 <input
								type="date" name="endtime" value="${param.endtime }"> 
								<c:if test="${outtimemark eq 'out' }">
								<input type="hidden" name="outtimemark" value="out">
								<input type="submit" class="btn btn-primary" value="查询">
								</c:if>
								<c:if test="${outtimemark != 'out' }">
								<input type="submit" class="btn btn-primary" value="查询">
								</c:if>
						</form>
					</div>
					<div>
						<c:if test="${fn:contains(authcodes,'add-projtask')}">
							<button type="button" class="btn btn-warning" data-toggle="modal"
								data-target="#proj-add-div">添加任务</button>
						</c:if>
						<a
							href="${pageContext.request.contextPath}/projtask/projtasklist.action?outtimemark=out">
							<c:if test="${mark=='out' }">
								<button type="button" class="btn btn-warning"
									data-toggle="modal" data-target="#proj-add-div">有${outtime}条快过期任务</button>
							</c:if> <c:if test="${mark!='out' }">
								<button type="button" class="btn btn-warning"
									data-toggle="modal" data-target="#proj-add-div">有${nearOutTask}条快过期任务</button>
							</c:if>

						</a>
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
						<!-- <button type="button" class="btn btn-primary show-add-form" data-toggle="modal" data-target="#perm-form-div">添加站内信</button> -->
						<!--添加新权限表单-->
						<div class="modal fade" id="proj-add-div" tabindex="-1"
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
									<form
										action="${pageContext.request.contextPath}/projtask/addprojtask.action"
										id="add-submit-projtask">
										<div class="modal-body">

											<div class="form-group">
												<label for="descInput">任务名称</label> <input type="text"
													maxlength="16" name="taskName" class="form-control"
													id="taskNameInput" placeholder="任务名称"> <label
													for="descInput">任务描述</label> <input type="text"
													maxlength="16" name="taskDesc" class="form-control"
													id="taskDescInput" placeholder="任务描述"> <label
													for="descInput">执行人</label> <input type="text"
													maxlength="16" class="form-control" id="usercodeInput"
													placeholder="执行人"> <input type="hidden"
													maxlength="16" name="excutor" class="form-control"
													id="excutorInput" placeholder="执行人"> <label
													for="descInput">工时</label> <input type="text"
													maxlength="16" name="ableDay" class="form-control"
													id="ableDayInput" placeholder="工时"> <label
													for="descInput">计划时间</label> <input type="text"
													maxlength="16" name="planTime" class="form-control"
													id="planTimeInput" placeholder="计划时间"> <label
													for="descInput">备注</label> <input type="text"
													maxlength="16" name="remark" class="form-control"
													id="remarkInput" placeholder="备注"> <select
													name="projId" id="addprojId">
													<option value="">项目名称</option>
													<c:forEach items="${sysc }" var="sys">
														<option value="${sys.classId}"
															${(param.fprojId==sys.classId)?"selected='selected'":"" }>${sys.className }</option>
													</c:forEach>
												</select> <select name="classId" id="addclassId">
													<option value="">模块名称</option>
													<c:forEach items="${syscmk }" var="sys">
														<option value="${sys.classId}"
															${(param.fclassId==sys.classId)?"selected='selected'":"" }>${sys.className }</option>
													</c:forEach>
												</select> <input type="date" name="startTime" id="startTime">
												到 <input type="date" name="endTime" id="endTime">
											</div>
										</div>
										<div class="modal-footer">
											<button type="reset" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary perm-submit"
												id="add-submit">提交</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- 修改任务 -->
					<div class="modal fade" id="perm-form-div" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<center>
										<h4 class="perm-form-title">修改任务</h4>
									</center>
								</div>
								<form
									action="${pageContext.request.contextPath}/projtask/updateprojtask.action">
									<div class="modal-body">
										<div class="form-group">
											<center>
												<h4>完成时间:</h4>
												<input value="消耗时间" id="ufinishTime" class="form-control"
													name="finishTime">
											</center>
										</div>
										<div class="form-group">
											<center>
												<h4>任务进度:</h4>
												<input value="完成进度" id="uprocess" class="form-control"
													name="process">
											</center>
										</div>
										<div class="form-group">
											<center>

												<h4>备注:</h4>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<textarea rows="5" cols="21" id="uremark" name="remark"
													class="form-control"></textarea>
											</center>
										</div>
										<div class="form-group">
											<center>
												<select id="utaskState" name="taskState">
													<option value="">任务状态</option>
													<option value="0">未开始</option>
													<option value="1">进行中</option>
													<option value="2">已暂停</option>
													<option value="3">已完成</option>
													<option value="4">已关闭</option>
												</select>
											</center>
										</div>
									</div>
									<div class="modal-footer">
										<input type="hidden" id="utaskId" name="taskId" value="">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="submit" class="btn btn-primary updateprojtask">提交</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="space-div"></div>
				<table class="table table-hover table-bordered perm-list">
					<tr style="text-align: center;">
						<td><input type="checkbox" class="select-all-btn" /></td>
						<td>任务ID</td>
						<td>任务名称</td>
						<td>执行人</td>
						<td>创建人</td>
						<td>所属模块</td>
						<td>实际进度</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${page.resultList }" var="perm">
						<tr style="text-align: center;">
							<td><input type="checkbox" name="permIds"
								value="${perm.taskId }" /></td>
							<td class="permid">${perm.taskId }</td>
							<td>${perm.taskName }</td>
							<c:forEach items="${puser }" var="user">
								<c:if test="${user.userId==perm.excutor }">
									<td>${user.userName }</td>
								</c:if>
							</c:forEach>


							<c:forEach items="${puser }" var="user">
								<c:if test="${user.userId==perm.createBy }">
									<td>${user.userName }</td>
								</c:if>
							</c:forEach>

							<c:forEach items="${syscmk }" var="sysc">
								<c:if test="${sysc.classId==perm.classId }">
									<td>${sysc.className }</td>
								</c:if>
							</c:forEach>
							<td>${perm.process }%</td>
							<td>${perm.createTime }<%-- <fmt:formatDate value="${perm.createTime }" /> --%>
							</td>
							<td><c:if test="${fn:contains(authcodes,'upd-projtask')}">
									<button class="btn btn-primary" value="修改任务信息" id="upd"
										data-toggle="modal" data-target="#perm-form-div">修改信息</button>
								</c:if> <c:if test="${fn:contains(authcodes,'del-projtask')}">
									<button class="btn btn-primary" value="删除任务" id="del">删除任务</button>
								</c:if>
								<button value="详情" id="deal" class="btn btn-primary"
									data-toggle="modal" data-target="#proj-deal">详情</button></td>
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
	<div class="modal fade" id="proj-deal" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<center>
						<h4 class="modal-title">任务详情</h4>

						<table>
							<tr style="text-align: center;">
								<td>任务id:</td>
								<td id="dtaskId"></td>
							</tr>
							<tr style="text-align: center;">
								<td>项目主键id:</td>
								<td id="dprojId"></td>
							</tr>
							<tr style="text-align: center;">
								<td>任务名称:</td>
								<td id="dtaskName"></td>
							</tr>
							<tr style="text-align: center;">
								<td>任务描述:</td>
								<td id="dtaskDesc"></td>
							</tr>
							<tr style="text-align: center;">
								<td>执行人:</td>
								<td id="dexcutor"></td>
							</tr>
							<tr style="text-align: center;">
								<td>任务状态:</td>
								<td id="dtaskState"></td>
							</tr>
							<tr style="text-align: center;">
								<td>创建时间:</td>
								<td id="dcreateTime"></td>
							</tr>
							<tr style="text-align: center;">
								<td>创建人:</td>
								<td id="dcreateBy"></td>
							</tr>
							<tr style="text-align: center;">
								<td>所属模块:</td>
								<td id="dclassId"></td>
							</tr>
							<tr style="text-align: center;">
								<td>工时:</td>
								<td id="dableDay"></td>
							</tr>
							<tr style="text-align: center;">
								<td>开始时间:</td>
								<td id="dstartTime"></td>
							</tr>
							<tr style="text-align: center;">
								<td>结束时间:</td>
								<td id="dendTime"></td>
							</tr>
							<tr style="text-align: center;">
								<td>计划时间:</td>
								<td id="dplanTime"></td>
							</tr>
							<tr style="text-align: center;">
								<td>实际消耗时间:</td>
								<td id="dfinishTime"></td>
							</tr>
							<tr style="text-align: center;">
								<td>实际进度:</td>
								<td id="dprocess"></td>
							</tr>
							<tr style="text-align: center;">
								<td>删除标记:</td>
								<td id="disDelete"></td>
							</tr>
							<tr style="text-align: center;">
								<td>备注:</td>
								<td id="dremark"></td>
							</tr>
						</table>
					</center>
				</div>
				<div class="modal-body" id="op-tips-content"></div>
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
		$(".perm-list")
				.on(
						"click",
						"#del",
						function() {
							alert("确认删除？");
							/* $("#del").click(function() { */
							var id = $(this).parents("tr").find(".permid")
									.html();
							window.location.href = "${pageContext.request.contextPath}/projtask/delprojtask.action?taskId="
									+ id + "";
						});
		$(".perm-list")
				.on(
						"click",
						"#deal",
						function() {
							/* $("#deal").click(function() { */
							var id = $(this).parents("tr").find(".permid")
									.html();
							$
									.ajax({
										url : "${pageContext.request.contextPath}/projtask/getdealprojtask.action",
										dataType : "json",
										type : "post",
										data : {
											taskId : id,
										},
										success : function(data) {
											var sys = data.sysmk;
											for (var i = 0; i < sys.length; i++) {
												if (sys[i].classId == data.msg.classId) {
													$("#dclassId").html(
															sys[i].className);
												}
											}
											$("#dtaskId").html(data.msg.taskId);
											$("#dprojId").html(data.msg.projId);
											$("#dtaskName").html(
													data.msg.taskName);
											$("#dtaskDesc").html(
													data.msg.taskDesc);
											$("#dcreateTime").html(
													data.msg.createTime);
											$("#dableDay").html(
													data.msg.ableDay);
											$("#dstartTime").html(
													data.msg.startTime);
											$("#dendTime").html(
													data.msg.endTime);
											$("#dexcutor").html(
													data.msg.excutor);
											$("#dcreateBy").html(
													data.msg.createBy);
											var dts = data.msg.taskState;
											if (dts == '0') {
												$("#dtaskState").html("未开始");
											}
											if (dts == '1') {
												$("#dtaskState").html("进行中");
											}
											if (dts == '2') {
												$("#dtaskState").html("已暂停");
											}
											if (dts == '3') {
												$("#dtaskState").html("已完成");
											}
											if (dts == '4') {
												$("#dtaskState").html("已关闭");
											}
											$("#dplanTime").html(
													data.msg.planTime);
											$("#dfinishTime").html(
													data.msg.finishTime);
											$("#dprocess").html(
													data.msg.process);
											$("#disDelete").html(
													data.msg.isDelete);
											$("#dremark").html(data.msg.remark);
										},
										error : function() {
											alert("error")
										}
									})
						});
		/* 修改信息回写 */
		$(".perm-list")
				.on(
						"click",
						"#upd",
						function() {
							/* $("#upd").click(function() { */
							var id = $(this).parents("tr").find(".permid")
									.html();
							$
									.ajax({
										url : "${pageContext.request.contextPath}/projtask/getupdprojtask.action",
										dataType : "json",
										type : "post",
										data : {
											taskId : id,
										},
										success : function(data) {
											$("#ufinishTime").val(
													data.msg.finishTime);
											$("#uprocess")
													.val(data.msg.process);
											$("#uremark").val(data.msg.remark);
											$("#utaskState").val(
													data.msg.taskState);
											$("#utaskId").val(data.msg.taskId);
										},
										error : function() {

										}
									})
						});
		$("#usercodeInput")
				.blur(
						function() {
							var usercode = $("#usercodeInput").val();

							$
									.ajax({
										url : "${pageContext.request.contextPath}/projtask/getuseridbyusercode.action",
										type : "post",
										dataType : "json",
										data : {
											userCode : usercode,
										},
										success : function(data) {
											if (data.msg != "") {
												$("#excutorInput").val(data.msg);
											}
										},
										error : function() {
										}
									})
						});

		$("#add-submit").click(function() {
			var taskName = $("#taskNameInput").val();
			var excutor = $("#excutorInput").val();
			var usercode = $("#usercodeInput").val();
			var ableDay = $("#ableDayInput").val();
			var planTime = $("#planTimeInput").val();
			var addprojId = $("#addprojId").val();
			var addclassId = $("#addclassId").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if (taskName != null && taskName != "") {//任务名称
				if (ableDay != null && ableDay != "") {//工时
					if (planTime != null && planTime != "") {//计划时间
						if (addprojId != null && addprojId != "") {//项目名称
							if (addclassId != null && addclassId != "") {
								if (startTime!= "") {
									if (endTime!= "") {
										if (!!usercode) {
											if (!!excutor) {
											$("#add-submit-projtask").submit();
										} else {
											alert("请输入正确的执行人！")
										}
										} else {
											alert("执行人不能为空")
										}
									} else {
										alert("结束时间不能为空")
									}
								} else {
									alert("开始时间不能为空")
								}
							} else {
								alert("模块不能为空")
							}
						} else {
							alert("项目不能为空")
						}
					} else {
						alert("计划时间不能为空")
					}
				} else {
					alert("工时信息不能为空")
				}
			} else {
				alert("项目名称不能为空")
			}

		});
	</script>
</body>
</html>
