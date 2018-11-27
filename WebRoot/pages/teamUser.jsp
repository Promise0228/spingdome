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

<title>用户管理 - 用户列表</title>
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
			<h1 class="page-header">项目组成员</h1>
			<div class="row placeholders">
				<div>
				<form action="${pageContext.request.contextPath}/projectTeam/findTeamUser.action">
					<input type="text" name="userName" value="${userInfo.userName}" maxlength="16" placeholder="成员名称"> 
					&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					<input type="hidden" name="teamId" value="${teamId}">	
					<button type="submit"  id="selectAll">查询</button>
					</form>
				</div>
				<br />
				<div>
					
					<c:if test="${roleId==1 or roleId==2}">	
					<button type="button" class="btn btn-primary show-user-form"
						data-toggle="modal" data-target="#add-user-form">添加成员</button>
					</c:if>
					<!--添加成员表单-->
					<div class="modal fade " id="add-user-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">添加成员</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group">
											<label for="userNameInput">成员名称</label>
											 <select style="width: 198px; height: 28px" name="projChief" id="projUser" >
					                			<option value="" class="projectTeam">选择</option>
					                			<c:forEach items="${groupUser}" var="groupUser">
					                			<option value="${groupUser.userId }">${groupUser.userName}</option>
					                			</c:forEach>
					                			</select>
												  <label id="userNameError"></label>
										</div>
										
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary add-user-submit" >添加
									</button>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list">
					<tr>
						<td><input type="checkbox" class="select-all-btn" /></td>
						<td>ID</td>
						<td>用户组</td>
						<td>用户名</td>
						<td>昵称</td>
						<td>用户状态</td>
						<td>创建时间</td>	
						<td>操作</td>
					</tr>
					<!--  <tr>
                	<td><input type="checkbox" name="userIds" value="11"/></td>
                    <td class="userid">1</td>
                    <td class="username">sisu</td>
                    <td><a href="javascript:void(0);" class="show-user-roles" >查看所有角色</a></td>
                    <td>
                    	<a class="glyphicon glyphicon-wrench show-userrole-form" aria-hidden="true" title="修改所有角色" href="javascript:void(0);" data-toggle="modal" data-target="#update-userrole-dialog"></a>
                    	<a class="glyphicon glyphicon-remove delete-this-user" aria-hidden="true" title="删除用户" href="javascript:void(0);"></a>
                    </td>
                </tr>-->
					<c:forEach items="${projTeam}" var="user">
						<tr>
							<td><input type="checkbox" name="userIds" value="" /></td>
							<td class="userid">${user.userId}</td>
							<td class="groupid">${user.groupId}</td>
							<td class="username">${user.userName}</td>
							<td class="nickName">${user.nickName}</td>						
							<td class="userState">${user.userState}
							</td>
							<td class="createTime"><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd "/></td>							
							<td >
								
								
								
								<c:if test="${user.userState==1 }" >
								
								<c:if test="${roleId==1 or roleId==2}">	
								 <a class="glyphicon glyphicon-remove delete-this-user"
								aria-hidden="true" title="删除用户" ></a>
								</c:if>
								<c:if test="${roleId==1 or roleId==2}">	
								<button  ${(user.userState=='0')?'style="display:none"':''} class="btn btn-primary show-user-form" data-toggle="modal" id="assignRoles"  >人员调配</button>
								</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="standard.jsp" />
				
				
				 <!--修改用户角色表单-->
        <div class="modal fade " id="update-userrole-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title">修改用户角色</h4>
						</div>
						<div class="modal-body">
							<form class="update-userrole-form">
								<div class="form-group">
									<label for="userNameInput">用户名</label>
									<input type="text" name="userName" class="form-control" maxlength="20" id="updateName" readonly="readonly">
								</div>
								<div class="form-group">
									<label for="userNameInput">更改昵称</label>  
									<input type="text" name="userName" class="form-control" id="updateCode" maxlength="20" >
								</div>
								<div class="form-group">
									<label for="passwordInput1">密码</label>
									<input type="password" name="password" class="form-control" id="passwordInput1" placeholder="密码" maxlength="12">
								</div>
								<label>用   户   组:</label>
								<select id="update_group">
								<c:forEach items="${userGrouplist }" var="ugroup">
									<option value="${ugroup.groupId }">${ugroup.groupName }</option>
									</c:forEach>
								</select><br/> 
								<!-- 
								<label>用户类型:</label>
								<select id="update_type">
									<option value="3">普通用户</option>
									<option value="1">超级管理员</option>
									<option value="2">管理员</option>
								</select>
								 -->
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary update-userrole-submit">提交 </button>
						</div>
					</div>
				</div>
			</div>
				
	
	<!-- 分配角色 -->
			<div class="modal fade " id="assign-roles" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title">分配角色</h4>
						</div>
						<div class="modal-body">
							<form class="user-form">
								<div class="form-group">
									<label for="userNameInput">用户名</label>
									<input type="text" name="userName" class="form-control" id="User_Code" placeholder="用户名" readonly="readonly">
									<label id="userNameError" style="color:red"></label>
								</div>
								<div class="form-group">
									<input type="hidden" name="userId" class="form-control" id="User_Id"  readonly="readonly">
								</div>
								<div class="form-group">
									<label for="passwordInput">项目组：</label><br>
									<select id="teamProj">
									 <option value="" disabled selected style="display:none;height: 100%">项目组</option>
									</select>
								</div>

								<div class="roles-div">
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary confirm-assign">确定分配 </button>
						</div>
					</div>
				</div>
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
	   

		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
		
		
		$(".select-all-btn").change(
				function() {
					if ($(this).is(":checked")) {//$(this).prop("checked")
						$(".user-list input[type='checkbox']:gt(0)").prop(
								"checked", true);
					} else {
						$(".user-list input[type='checkbox']:gt(0)").prop(
								"checked", false);
					}
				});
				
				
				//批量删除用户
		$(".delete-selected-confirm").click(function() {
			$("#delete-confirm-dialog").modal("hide");
			var cbs = $("input[name='userIds']:checked");
			if (cbs.length === 0) {
				showTips("没有选中任意项！");
			} else {
				var userids = new Array();
				$.each(cbs, function(i, cb) {
					userids[i] = cb.value;
				});
				//请求删除所选用户
				$.ajax({
					url:"${pageContext.request.contextPath}/user/updateStatic.action",
					data:{userId:userids},
					dataType:"json",
					type:"POST",
					traditional:true,
					success:function(json){
						cbs.parent().parent().remove();
						if(json.msg==1){
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/user/list.action";						
						}else{
						alert("删除失败");
						}
					},
					error:function(json){
					//cbs.parent().parent().remove();
					window.location.href="${pageContext.request.contextPath}/user/list.action";
						alert("删除所选成功");
					}
				});
				
			}
		});



 var flag=false;
		
		//异步校验用户名是否存在
		$(document).ready(function(){
	
	$("#userNameInput").blur(function (){
			var userName=$("#userNameInput").val();//获取用户名
	 	 	$.ajax({  
	                type:"POST",  
	                url:"${pageContext.request.contextPath}/user/selectUserByName.action",
	                dataType:"json", 
	                data:{
	               		userName:userName,
	                },
	                success:function(data){ 
	                	if(data.msg=='0'){
	                      	flag=true;
                      	 	$("#error_html").removeClass("red").html("");
                       }else{
                        	$("#error_html").addClass("red").html("用户名已存在!");
                        	 alert("用户名已存在,请重新输入!");
                        	flag=false;
                       }
	                },
	                error:function (data){
	                	alert("系统异常！");
	                }    
	         }); 
	} );
	});
		
		
		
		//添加成员
		$(".add-user-submit").click(function() {
		var teamId=${teamId};
		alert(teamId);
		var userId=$("#projUser").val();//获取用户id
		alert(userId);
			//请求添加新用户
			if(userId==""){
				alert("请选择成员");
				return false;
			}else{
			$.ajax({
				url:"${pageContext.request.contextPath}/projectTeam/addTeamUser.action?teamId="+teamId,
				data : {
					userId:userId,
					teamId:teamId
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					$("#add-user-form").modal("hide");
					if(data.res=='1'){
					alert("添加成功！");
					window.location.href="${pageContext.request.contextPath}/projectTeam/findTeamUser.action?teamId="+teamId;
					}else{
						alert("添加失败！!!");
					window.location.href="${pageContext.request.contextPath}/projectTeam/findTeamUser.action?teamId="+teamId;
					}
				},
			error:function(json){
					//cbs.parent().parent().remove();
					window.location.href="${pageContext.request.contextPath}/projectTeam/findTeamUser.action?teamId="+teamId;
						alert("添加失败11！");
					}
			});
		}
		
		
		});

		function getRolesByUserName(username, doSuccess) {
			$.ajax({
				url : "showroles.html",
				data : {
					userName : username
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					doSuccess(data);
				}
			});
		}
	
		//单个删除用户
		$(".user-list").on("click", ".delete-this-user", function() {
			var userTr = $(this).parents("tr");
			var userid = userTr.find(".userid").html();
			var teamId = ${teamId};
			alert(teamId);
			if (confirm('确认删除？')) {
				//请求删除该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/projectTeam/deleteTeamMember.action?teamId="+teamId,
					data : {
						userId : userid,
						teamId : teamId
					},
					dataType:"json",
					type : "POST",
					success : function(data) {
					if(data.res=='1'){
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/projectTeam/findTeamUser.action?teamId="+teamId;
						}else{
						alert("删除失败！");
						window.location.href="${pageContext.request.contextPath}/projectTeam/findTeamUser.action?teamId="+teamId;
						}				
					userTr.remove();
					}
				});
			}
		});
		
			//重置密码
		$(".user-list").on("click", ".updatapswd", function() {
			var userTr = $(this).parents("tr");
			var userid = userTr.find(".userid").html();
			if (confirm('确认修改ID为"' + userid + '"的用户吗？')) {
				//请求重置密码
				$.ajax({
					url:"${pageContext.request.contextPath}/user/updatapswd.action",
					data : {
						userId : userid
					},
					dataType:"json",
					type : "POST",
					success : function(data) {
					if(data.msg>0){
						alert("修改成功！");
						window.location.href="${pageContext.request.contextPath}/user/list.action";
						}else{
						alert("修改失败！");
						}				
					userTr.remove();
					}
				});
			}
		});
		
		//启用禁用
		$(".user-list").on("click", ".qiyongjinyong", function() {
		var userTr = $(this).parents("tr");
			var userid = userTr.find(".userid").html();
			var userState = userTr.find(".userState").html();
				$.ajax({
					url:"${pageContext.request.contextPath}/user/updataStaticById.action",
					data : {
						userId : userid,
						userState:userState
					},
					dataType:"json",
					type : "POST",
					success : function(data) {
					if(data.msg==1){
						alert("修改成功！");
						window.location.href="${pageContext.request.contextPath}/user/list.action";
						}else{
						alert("修改失败！");
						}				
					}
				});
		
		});
		
		//设置分页的下拉列表的默认值
		$(document).ready(function(){	
    $("#selectedpage").find("option[value='"+${page.page}+"']").prop("selected",true);
		});
	
	//修改用户信息
			$(".user-list").on("click", ".show-userrole-form", function() {
				var nickName = $(this).parents("tr").find(".nickName").html();
				$("#updateCode").val(nickName);
				var userName = $(this).parents("tr").find(".username").html();
				$("#updateName").val(userName);
				var userid = $(this).parents("tr").find(".userid").html();				
				$(".update-userrole-submit").click(function() {
					var nickName = $("#updateCode").val();
					var userPwd = $("#passwordInput1").val();
					var geoupId = $("#update_group").val(); 
					if(userPwd=='' && userPwd==null){
		    				alert("密码不能为空");
							return false;
						}
						if(userPwd.length>16 || userPwd.length<6){
				        alert("密码不合法! 6-16位，字母，数字");
				        return false;
			         }
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/user/updatapswds.action",
						data:{
							nickName: nickName,
							userId: userid,
							userPwd:userPwd,
							groupId:geoupId
						},
						success: function(json) {
							if(json.msg==1) {
								alert("修改成功！");
								window.location.href="${pageContext.request.contextPath}/user/list.action";
							}else{
							alert("修改失败！");
								window.location.href="${pageContext.request.contextPath}/user/list.action";
							}
						},
						error:function(json){
					window.location.href="${pageContext.request.contextPath}/user/list.action";
						alert("修改失败！");
					}
					});
				});
			});
	
	//分配角色
			 $(".user-list").on("click", "#assignRoles", function() {
			 $("#assign-roles").modal("show");
			 //登录用户id
			 var s=${UserInfo.userId};
			 //被分配成员信息
				var userId = $(this).parents("tr").find(".userid").html();
				var userName = $(this).parents("tr").find(".username").html();
				$("#User_Code").val(userName);
				$("#User_Id").val(userId);
				$("input[name='roleName']").each(function() {
					$(this).prop("checked", false);
				});
				if(!!userId) {
					$.ajax({
						url: "${pageContext.request.contextPath}/projectTeam/selectProjTask.action",
						data: { userId: userId },
						type: "POST",
						dataType: "json",
						success: function(json) {
						var team=json.projTeams;
						
						for (var i = 0; i < team.length; i++) {
							//先创建好select里面的option元素
			                var option = document.createElement("option");
			                //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
			                $(option).val(team[i].teamId);
			                //给option的text赋值,这就是你点开下拉框能够看到的东西
			                $(option).text(team[i].teamName);
			                //获取select 下拉框对象,并将option添加进select
			                $('#teamProj').append(option);
							
							//document.getElementById("teamProj").options[i].value=team[i].teamId;
							//document.getElementById("teamProj").options[i].html=team[i].teamName;
						}
						}
					});
				}
				
				
				//人员调配进行确认提交
				$(".confirm-assign").click(function() {
				//项目组id
					var teamId =${teamId};
				//用户id
					var userId=${UserInfo.userId}
				//调配人员id
					var userIds = $("#User_Id").val();
				//调配项目组id
					var teamIds=$("#teamProj").val();
					$.ajax({
						url: "${pageContext.request.contextPath}/projectTeam/findTaskId.action",
						type: "POST",
						dataType: "json",
						data: {
							userId: userId,
							teamId:teamId,
							userIds:userIds,
							teamIds:teamIds
						},
						success: function(json) {
							if(json.res == '1') {
								alert("调配成功");
							} else if(json.res == '0') {
								alert("调配失败");
							} else if(json.res == '2') {
								alert("请完成交接项目");
							} else{
								alert("没取到值");
							} 
						}
					});
				});
			});
	
		//搜索框有效无效的设置
		$(document).ready(function(){	
     $("#userState").val(${userInfo.userState});
		});
	</script>
</body>
</html>
