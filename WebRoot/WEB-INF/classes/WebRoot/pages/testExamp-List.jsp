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
<title>项目管理 - 项目测试用例列表</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<style type="text/css">
.red {
	color: red;
}
	.word{
		position: absolute;
		z-index: 99;
		width: 145px;
		height: auto;
		background-color: white;
		border: black solid 1px;
		display: none;
	}
.green {
	color: green;
}
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
	</div>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">项目测试用例列表</h1>
		<div class="row placeholders">
			<form
				action="${pageContext.request.contextPath}/test/testExampList.action">
				<input type="text" name="projName" placeholder="项目名称"
							maxlength="20" value="${param.projName}">&#160
				<input type="text" name="testDesc" id="testDesc"
					value="${param.testDesc }" placeholder="描述"> <select
					id="testState" name="testState" style="height: 27px; ">
					<option value="">测试状态</option>
					<option value="0"
						${(param.testState=='0')? "selected='selected' " : ""}>新建</option>
					<option value="1"
						${(param.testState=='1')? "selected='selected' " : ""}>已分配</option>
					<option value="2"
						${(param.testState=='2')? "selected='selected' " : ""}>未完成</option>
					<option value="3"
						${(param.testState=='3')? "selected='selected' " : ""}>已完成</option>
				</select> <select id="testType" name="testType" style="height: 26px; ">
					<option value="">测试类型</option>
					<option value="1"
						${(param.testType=='1') ?"selected='selected' " : ""}>功能性测试</option>
					<option value="2"
						${(param.testType=='2') ?"selected='selected' " : ""}>性能测试</option>
					<option value="3"
						${(param.testType=='3') ?"selected='selected' " : ""}>安全测试</option>
					<option value="4"
						${(param.testType=='4') ?"selected='selected' " : ""}>其它</option>
				</select> <input type="date" placeholder="年/月/日" id="test30" name="startTime"
					value="${param.startTime }"> <input type="date"
					placeholder="年/月/日" id="test32" name="endTime"
					value="${param.endTime }"> <input type="hidden" id="pages"
					name="pages" value="">
				<button type="submit" class="btn btn-warning "
					>查询</button>
			</form>
			<br />
			<br />
			<div>
				<!-- <button type="button" class="btn btn-warning delete-query" data-toggle="modal" data-target="#delete-confirm-dialog">删除所选</button> -->
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
							<div class="modal-body">确认删除所选测试用例吗</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button"
									class="btn btn-primary delete-selected-confirm">确认</button>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${fn:contains(authcodes,'add-test')}">
				<button type="button" class="btn btn-primary show-test-form"
					data-toggle="modal" data-target="#add-test-form">添加测试用例</button>
				</c:if>
			</div>
			<div class="space-div"></div>
			<table class="table table-hover table-bordered test-list">
				<tr align="center">
					<td>ID</td>
					<td>项目ID</td>
					<td>测试类型</td>
					<td>测试主题</td>
					<td>测试结果</td>
					<td>测试状态</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>执行人</td>
					<td>操作</td>
				</tr>

				<c:forEach items="${page.resultList}" var="test">
					<tr align="center">
						<td class="testId">${test.testId}</td>
						<td class="projId">${test.projId }</td>
						<td class="testType"><c:if test="${test.testType eq 1 }">功能性测试</c:if>
							<c:if test="${test.testType eq 2 }">性能测试</c:if> <c:if
								test="${test.testType eq 3 }">安全测试</c:if> <c:if
								test="${test.testType eq 4 }">其它</c:if></td>
						<td class="testTitle">${test.testTitle }</td>
						<td class="result"><c:if test="${test.result eq 0 }">未通过</c:if>
							<c:if test="${test.result eq 1 }">已通过</c:if></td>
						<td class="testState"><c:if test="${test.testState eq 0 }">新建</c:if>
							<c:if test="${test.testState eq 1 }">已分配</c:if> <c:if
								test="${test.testState eq 2 }">未完成</c:if> <c:if
								test="${test.testState eq 3 }">已完成</c:if></td>
						<td class="startTime">${fn:substring(test.startTime,0,10) }</td>
						<td class="endTime" >${fn:substring(test.endTime,0,10) }</td>
						<td class="executor">${test.executor }</td>
						<td>
						<c:if test="${fn:contains(authcodes,'del-test')}">
						<button type="button" class="btn btn-primary delete-test-form"
							data-toggle="modal" data-target="#delete-confirm-dialog">删除</button>
						&nbsp;
					</c:if>
					<c:if test="${fn:contains(authcodes,'edit-test')}">
						<button type="button" class="btn btn-primary update-test-form"
							data-toggle="modal" data-target="#update-test-dialog">编辑</button>
						&nbsp;
						</c:if>
						<c:if test="${fn:contains(authcodes,'assign-test')}">
						<button type="button" class="btn btn-primary assign-test-form"
							data-toggle="modal" data-target="#assign-test-dialog">分配</button>
						</c:if>
						</td>
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

			<!--创建测试用例表单-->
			<div class="modal fade " id="add-test-form" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">创建测试用例</h4>
						</div>
						<div class="modal-body">
							<form class="user-form">
								<div class="form-group">
									<label for="projId">项目ID</label> <input type="text"
										name="projId" class="form-control" id="projIdInputs"
										placeholder="项目ID">
								</div>
								<label id="projIdError"></label>
								<div class="form-group">
									<label for="testTypeInput">测试类型</label> <select
										id="testTypeInput" name="testType" class="form-control">
										<option value="">测试类型</option>
										<option value="1">功能性测试</option>
										<option value="2">性能测试</option>
										<option value="3">安全测试</option>
										<option value="4">其它</option>
									</select>
									<label for="testTitleInput">用例主题</label> <input type="text"
										name="testTitle" class="form-control" id="testTitleInput"
										placeholder="用例主题">
									<label for="testDescInput">测试描述</label> <input type="text"
										name="testDesc" class="form-control" id="testDescInput"
										placeholder="测试描述">
									<label for="startTimeInput">开始时间</label> <input type="date"
										name="startTime" class="form-control" id="startTimeInput"
									/>
									<label for="endTimeInput">结束时间</label> <input type="date"
										name="endTime" class="form-control" id="endTimeInput"
									/>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary add-test-submit">创建
							</button>
						</div>
					</div>
				</div>
			</div>
			<!--测试用例维护表单-->
			<div class="modal fade " id="update-test-dialog" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">测试用例编辑</h4>
						</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="testTitleInput">测试主题</label> <input type="text"
										name="testTitleUpdate" class="form-control" readonly="readonly"  id="testTitleUpdate"
									>
									</div>
									<div class="form-group">
									<label for="endTimeUpdate">结束时间</label> <input type="date"
										name="endTime" class="form-control" id="endTimeUpdate"
										placeholder="结束时间">
									<label for="resultUpdate">测试结果</label> <select id="resultUpdate"
										name="result" class="form-control">
										<option value="">测试结果</option>
										<option value="0">未通过</option>
										<option value="1">已通过</option>
									</select>
									<label for="testStateUpdate">测试状态</label> <select id="testStateUpdate"
										name="testState" class="form-control">
										<option value="">测试状态</option>
										<option value="0">新建</option>
										<option value="1">已分配</option>
										<option value="2">未完成</option>
										<option value="3">已完成</option>
									</select>
								</div>
							</div>
						<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary update-test-submit">修改</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<!--分配执行人-->
			<div class="modal fade " id="assign-test-dialog" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">分配测试用例</h4>
						</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="testTitleAssign">测试主题</label> <input type="text"
										readonly="readonly" name="testTitleAssign" class="form-control"  id="testTitleAssign"
									>
									<label for="userNameAssign">分配执行人</label> 
									<input type="text" name="userName" class="form-control" id="userNameAssign" placeholder="执行人">
									
									<div class="fd" style="height:20px "></div>
									<div class="word" id="wordContainer"></div>
								</div>
							</div>
						<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary assign-test-submit">修改</button>
						</div>
					</div>
				</div>
			</div>
	<!-- 附件上传 -->
	<div class="modal fade " id="update-file-dialog" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">测试用例附件上传</h4>
				</div>
				<form class="update-file-form"
					action="${pageContext.request.contextPath}/testExamp/upLoad.action"
					enctype="multipart/form-data" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="fileUrlInput">附件</label> <input type="file"
								name="fileUrl" class="form-control" id="fileUrlInput"
								placeholder="附件" multiple>
						</div>
						<label id="fileUrlError"></label>
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-primary update-file-submit">确认
					</button>
				</form>
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
		//判断时间
		$("#endTimeInput").blur(function() {
			var test30 = $("#startTimeInput").val();
			var test32 = $("#endTimeInput").val();
			//判断非空
			if (!!test30 && !!test32) {
				//将字符创转换为date
				var startTime = new Date(test30.replace("-", "/").replace("-", "/"));
				var endTime = new Date(test32.replace("-", "/").replace("-", "/"));
				//比较转换后的时间类型大小
				if (endTime - startTime < 0) {
					showTips("截止必须大于开始时间!");
					$("#endTimeInput").val("");
				}
			}
		});
	
		//提示框
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
		//创建用例提交
		$(".add-test-submit").click(function() {
			var projId = $("#projIdInputs").val();
			var testType = $("#testTypeInput").val();
			var testTitle = $("#testTitleInput").val();
			var testDesc = $("#testDescInput").val();
			var startTime = $("#startTimeInput").val();
			var endTime = $("#endTimeInput").val();
			//判断输入格式
			if (projId == "") {
				$("#projIdError").html("项目ID有误，请重新输入").addClass("red");
				return false;
			} else if (testType == "") {
				alert("请选择测试类型！");
				return false;
			} else if (testTitle == "") {
				alert("测试主题不能为空！");
				return false;
			} else if (testDesc == "") {
				alert("测试描述不能为空！");
				return false;
			}else if (startTime == "") {
				alert("开始时间不能为空！");
				return false;
			}else if (endTime == "") {
				alert("结束时间不能为空！");
				return false;
			}
			else {
				//判断项目Id是否是测试组人员可见的
				$.ajax({
					url:"${pageContext.request.contextPath}/test/checkProjId.action",
						data: {
							projId : projId
						},
					type : "POST",
					dataType : "json",
					success : function(data){
				if(data.flag==true){	
				//请求创建新用例
				$.ajax({
					url : "${pageContext.request.contextPath}/test/addTest.action",
					data : {
						projId : projId,
						testType : testType,
						testTitle : testTitle,
						testDesc : testDesc,
						startTime:startTime,	
						endTime:endTime
					},
					type : "POST",
					dataType : "json",
					success : function(data) {
						if (data.res == '1') {
							$("#add-test-form").modal("hide");
							alert("创建成功！");
							location.href="${pageContext.request.contextPath}${page.url}?${page.params}";
						} else if (data.res == '0') {
							$("#add-test-form").modal("hide");
							alert("创建失败！");
						}
					},
					error : function(){	
						alert("系统出现异常！");
					}
				})
				}else{
					$("#projIdError").html("项目ID有误，请重新输入").addClass("red");
				}
					}
				})
				
			}
	
		});
	
		var testId="";
		//修改测试用例
		$(".update-test-form").click(function() {
			testId = $(this).parents("tr").find(".testId").html();
			var testTitle = $(this).parents("tr").find(".testTitle").html();
			$("#testTitleUpdate").val(testTitle);
		});
		
		$(".update-test-submit").click(function() {
				var result = $("#resultUpdate").val();
				var endTime = $("#endTimeUpdate").val();
				var testState = $("#testStateUpdate").val();
				//判断输入类型
				if (result == "") {
					alert("请输入测试结果！");
					return false;
				} else if (endTime == "") {
					alert("请输入结束时间！");
					return false;
				} else if (testState == "") {
					alert("请输入测试状态！");
					return false;
				}else {
				$.ajax({
					url : "${pageContext.request.contextPath}/test/update.action",
					data : {
						testId : testId,
						result : result,
						endTime : endTime,
						testState : testState
					},
					type : "POST",
					dataType : "json",
					success : function(data) {
						//判断返回值
						if (data.res == 1) {
							$("#update-test-form").modal("hide");
							alert("修改成功！");
							} else if (data.res == 0) {
							$("#update-test-form").modal("hide");
							alert("修改失败！");
						}
					}
				})
				}
			});
			
		//分配
		var projId="";
		$(".assign-test-form").click(function(){
			testId = $(this).parents("tr").find(".testId").html();
			var testTitle = $(this).parents("tr").find(".testTitle").html();
			$("#testTitleAssign").val(testTitle);
			projId = $(this).parents("tr").find(".projId").html();
		})
		//输入执行人姓名,ajax自动补全
		$("#userNameAssign").keyup(function(){
			var keywords = $(this).val();
			
			if (keywords=="") { $("#wordContainer").hide(); return };
			$.ajax({
				url:"${pageContext.request.contextPath}/test/findExecutors.action",
				dataType:"json",
				//jsonp: "cb", //回调函数的参数名(键值)key
				type:"POST",
				data:{
				userName:keywords,
				projId:projId
				},
				// jsonpCallback: 'fun', //回调函数名(值) value
				beforeSend:function(){
					$("#wordContainer").append("<div>正在加载。。。</div>");
				},
				success:function(data){
					$("#wordContainer").empty().show();
					if (data.s=='')
					{
						$("#wordContainer").append('<div class="error">Not find  "' + keywords + '"</div>');
					}
					$.each(data.s, function(){
						$("#wordContainer").append('<div class="wordChild">'+ this +'</div>');
					})
				},
				error:function(data){
					$("#wordContainer").empty().show();
					$.each(data.s, function(){
					alert(data.s);
						$("#wordContainer").append('<div class="wordChild">'+ this +'</div>');
					})
				}
			})
		})
		
		//点击搜索数据复制给搜索框
		$(document).on('click','.wordChild',function(){
			var word = $(this).text();
			$('#userNameAssign').val(word);
			$('#wordContainer').hide();
			// $('#texe').trigger('click');触发搜索事件
		})
	   
	   //提交分配
	   $(".assign-test-submit").click(function(){
	   		var userName = $("#userNameAssign").val();
	   		if($("#wordContainer").is(":hidden")){
	   			$.ajax({
				url:"${pageContext.request.contextPath}/test/assignExecutor.action",
				dataType:"json",
				//jsonp: "cb", //回调函数的参数名(键值)key
				type:"POST",
				data:{
				userName:userName,
				testId:testId
				},
				success:function(data){
					$("#assign-test-dialog").modal("hide");
					location.href="${pageContext.request.contextPath}${page.url}?${page.params}";
					alert("分配成功！");
				}
			})
	   		}else{
				alert("请输入正确数据");	   		
	   		}
	   })
	   
	   $(".delete-test-form").click(function(){
	   		testId = $(this).parents("tr").find(".testId").html();
	   })
	   $(".delete-selected-confirm").click(function(){
	   		$.ajax({
				url:"${pageContext.request.contextPath}/test/delTestExamp.action",
				dataType:"json",
				type:"POST",
				data:{
				testId:testId
				},
				success:function(data){
				if(data.res==true){
					$("#delete-confirm-dialog").modal("hide");
					location.href="${pageContext.request.contextPath}${page.url}?${page.params}";
				}
				}
			})
	   })
	</script>
</body>
</html>
