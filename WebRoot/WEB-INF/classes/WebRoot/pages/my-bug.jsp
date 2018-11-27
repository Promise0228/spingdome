<%@ page language="java"  contentType="text/html;  charset=UTF-8"
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
.red {
	color: red
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
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">我的BUG</h1>
			<div class="row placeholders">
				<div>
					<form action="${pageContext.request.contextPath}/bug/bugList.action" method="get">
						<input type="text" name="bugTitle" id="bugTitle" value="" style="height: 30px; width: 150px"
							 placeholder="用户名"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" name="updateBys" id="updateBys" value="" style="height: 30px; width: 150px" 
						placeholder="执行人">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <input type="date" name="createTime" id="createTime" value="" pattern='yyyy-MM-dd' />
						&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp; 
						<input type="date" name="createTime1" id="createTime1" value="" pattern='yyyy-MM-dd' />
						<br><br>
						<select id="bugState" name="bugState" style="height: 29px; width: 100px">
							<option value="">BUG状态</option>
							<option value="1">已分配</option>
							<option value="2">已解决</option>
							<option value="3">已关闭</option>
							<option value="4">新建</option>
						</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						 <select id="bugLevel" name="bugLevel" style="height: 29px; width: 100px">
							<option value="">BUG级别</option>
							<option value="1">普通</option>
							<option value="2">严重</option>
							<option value="3">缺陷</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="projNum" id="projNums" style="height: 29px; width: 100px" class="selectAll" onclick="findClassByProj()">
						                    <option value="">全部项目</option>
							<c:forEach items="${proj }" var="proj">
											<option value="${proj.projNum }">${proj.projName }</option>
											</c:forEach></select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="classId" id="classIds" style="height: 29px; width: 100px">
							<option value="" disabled selected
								style="display: none;height: 100%">所属模块</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="submit" class="selectAll" value="查询">
					</form>
				</div>
				<br />


				<div>
					<c:if test="${fn:contains(authcodes,'exportuserda')}">
						<c:if test="${page.resultList!=null }">
							<a
								href="${pageContext.request.contextPath}/${page.url}?${page.params}&improt=3">
							</a>
						</c:if>
					</c:if>
					
					<!--添加新用户表单-->

					<div class="modal fade " id="add-user-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group">
											<label for="bugNumber">BUG编号</label> <input type="text"
												required placeholder="请输入BUG编号" maxlength="20"
												name="bugNumber" class="form-control" id="bugNumber"
												placeholder="BUG编号" /> <label id="bugNumber1"></label>
										</div>
										<div class="form-group">
											<label for="bugName">BUG名称</label> <input type="text"
												required placeholder="请输入BUG名称" maxlength="20"
												name="bugName" class="form-control" id="bugTitle11"
												placeholder="BUG名称" /> <label id="bugName1"></label>
										</div>
										<div class="form-group">
											<label for="bugDescribe">BUG描述</label> <input type="text"
												required placeholder="请输入BUG描述" maxlength="20"
												name="bugDescribe" class="form-control" id="bugDesc"
												placeholder="BUG描述" /> <label id="bugDescribe1"></label>
										</div>
										<div class="form-group">
											<label for="bugRank">BUG级别</label> <select class="bugRank">
											<option value="1">普通</option>
											<option value="2">严重</option>
											<option value="3">缺陷</option>
										</select>
										</div>
										<div class="form-group">
											<label for="projName">所属项目</label> <select id="projName1" onclick="findClassByProj1()">
											<c:forEach items="${sysList }" var="sysList">
											<option value="${sysList.projNum }">${sysList.projName }</option>
											</c:forEach>
										
										</select>
										</div>
										<div class="form-group">
											<label for="classId">所属模块</label> <select id="classId1">
											
										</select>
										</div>
									</form>
								</div>
								
							</div>
						</div>
					</div>

				</div>
				
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list">
					<tr>
						<td><input type="checkbox" class="select-all-btn" /></td>
						<td>BUG编号</td>
						<td>BUG名称</td>
						<td>BUG状态</td>
						<td>BUG级别</td>
						<td>所属模块</td>
						<td>所属项目</td>
						<td>创建时间</td>
						<td>执行人</td>
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
					<c:forEach items="${bugList }" var="bug">
						<tr>
							<td><input type="checkbox" name="bugIds"
								value="${bug.bugNum }" /></td>
							<td class="bugid">${bug.bugNum }</td>
							<td class="bugname">${bug.bugTitle }</td>
							<c:if test="${bug.bugState eq 1}">
								<td class="bugState">已分配</td>
							</c:if>
							<c:if test="${bug.bugState eq 2}">
								<td class=bugState>已解决</td>
							</c:if>
							<c:if test="${bug.bugState eq 3}">
								<td class="usertype">已关闭</td>
							</c:if>
							<c:if test="${bug.bugState eq 4}">
								<td class="bugState">未分配</td>
							</c:if>
							<c:if test="${bug.bugLevel eq 1}">
								<td class=bugLevel>普通</td>
							</c:if>
							<c:if test="${bug.bugLevel eq 2}">
								<td class=bugLevel>严重</td>
							</c:if>
							<c:if test="${bug.bugLevel eq 3}">
								<td class=bugLevel>缺陷</td>
							</c:if>
							<td class="classids">${bug.classIds }</td>
							<td class="projnames" >${bug.projIds}</td>
							<td class="createtimes">${bug.createTime }</td>
							<td class="updateBy">${bug.updateBys }</td>

							<td><c:if test="${bug.bugState!=3}">

									<c:if test="${fn:contains(authcodes,'updateBug')}">
										<a class="glyphicon glyphicon-wrench show-userrole-form updateBug"
											aria-hidden="true" title="修改BUG" href="javascript:void(0);"
											data-toggle="modal" data-target="#update-userrole-dialog" >
										</a>
									</c:if>
									
								<c:if test="${fn:contains(authcodes,'xiangqingBug')}">
									 <a href="${pageContext.request.contextPath}/bug/bugdetails.action?bugNum=${bug.bugNum }&bugId=${bug.bugId}">
										<input type="button" class="btn btn-primary qiyongjinyong"
											value="BUG详情"></a>
									</c:if>
									
								</c:if> 
								</td>
						</tr>
					</c:forEach>
					
					
				</table>
			<jsp:include page="standard.jsp" />
			
      
<!-- 分配BUG -->
			<div class="modal fade " id="assign-roles" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title">分配Bug</h4>
						</div>
						<div class="modal-body">
							<form class="user-form">
								<div class="form-group">
								    <label for="usernameinput">将</label>
								    <label id="bugnums"></label>
									<label for="usernameinput">号BUG分配给:</label>
									<br>
									项目组
									<select id="team" onclick="teanmIds1()">
									<option value="">请选择</option>
									</select>
									<br>
									项目成员
									<select id="teanmIds">
									<option>请选择</option>
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


				<!--修改用户角色表单-->
				<form action="${pageContext.request.contextPath}/bug/fileUpload.action" method="post" enctype="multipart/form-data">
<div class="modal fade " id="update-userrole-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
	<div class="modal-content">
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">修改Bug</h4>
	    </div>
		<div class="modal-body">
				<div class="form-group">
					<label for="bugnumber">BUG编号</label> 
					<input type="text" class="form-control" maxlength="20"name="bugNum" id="bugNumss" readonly="readonly" />
				</div>
					<div class="form-group">
					<label for="updateBugName">更改BUG名称</label> 
                    <input type="text" name="bugTitle" class="form-control" id="bugTitle1" maxlength="20">
					</div>
					<label>BUG状态:</label> 
                    <select id="bugState" name="bugState">
                    <c:if test="${role.roleCode}!=pe">
					<option value="1">已分配</option>
					</c:if>
					<option value="2">已解决</option>
					<c:if test="${role.roleCode}!=pe">
					<option value="3">已关闭</option>
					</c:if>
					
					</select><br>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp; 选择文件:<input type="file" name="file">
        <input type="file" name="file1">
        <input type="file" name="file2">
					<div class="modal-footer">
					<button type="button" class="btn btn-default"
					data-dismiss="modal">取消</button>
                    <input type="submit" class="btn btn-primary update-userrole-submit" value="提交">  
                                                
					</div>
				  	 
				</div>
				
</div>
</div>
</form>   

 


				
				
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
		
		
		
		
		$(".add-bug").click(function() {
		var bugNumber=$("#bugNumber").val();//获取bug编号
		var bugTitle=$("#bugTitle11").val();//获取bug名称
		var bugDesc=$("#bugDesc").val();//获取bug描述
		var bugLevel=$(".bugRank").val();//获取bug级别
		var classId=$("#classId1").val();//获取bug所属项目
		var projId=$("#projName1").val();//获取bug所属模块
		
			//请求添加新用户
			
			$.ajax({
				url:"${pageContext.request.contextPath}/bug/bugNumber.action",
				data : {
					bugNum:bugNumber
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					if(data.bugNumber==2){
					alert("bug标号已被占用！");
					}else if(data.bugNumber==1){
					$.ajax({
				    url:"${pageContext.request.contextPath}/bug/addbug.action",
				    data : {
					bugNum:bugNumber,
					bugTitle:bugTitle,
					bugDesc:bugDesc,
					bugLevel:bugLevel,
					classId:classId,
					projId:projId
				    },
				type : "POST",
				dataType : "json",
				success : function(data) {
					if(data.addbug==1){
					alert("添加成功！");
					location.reload();
					}else{
					alert("添加失败！");
					location.reload();
					}
				},
			error:function(){
			alert("系统异常");
					}
			});	
					}
				},
			error:function(){
			alert("系统异常");
					}
			});
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
			var bugNum = userTr.find(".bugid").html();
			if (confirm('确认删除ID为"' + bugNum + '"的用户吗？')) {
				//请求删除该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/bug/delbug.action",
					data : {
						bugNum : bugNum
					},
					dataType:"json",
					type : "POST",
					success : function(data) {
					if(data.delbug>0){
						alert("删除成功！");
						location.reload();
						}else{
						alert("删除失败！");
						location.reload();
						}				
					}
				});
			}
		});
//--------------------------------分配bug-------------------------------------------------------------------
		$(".user-list").on("click", ".fenpeibug", function() {
			var userTr = $(this).parents("tr");
			var projId = userTr.find(".fenpeibug").val();
			var userTr = $(this).parents("tr");
			var bugNum = userTr.find(".bugid").html();
			$("#bugnums").html(bugNum);
				//请求禁用该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/bug/findProjTeam.action",
					data : {
						projId : projId
					},
					type : "POST",
					success : function(data) {
					 var res = $.parseJSON(data);//把后台传回的json数据解析
					 var option;
					 if(res==null||res==""){
					 option = "<option value='"+""+"'>"+"该项目下无模块"+"</option>"
					 $("#team").html(option);
					 }else{
					$.each(res,function(i,n){//循环，i为下标从0开始，n为集合中对应的第i个对象
					option += "<option value='"+n.projId+"'>"+n.teamname+"</option>"
					});
					$("#team").html(option);//将循环拼接的字符串插入第二个下拉列表
					}
					}
				});
		});		
		//--------------------------
		
		function teanmIds1(){
			var teanmId =$("#team").val();
				//请求禁用该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/bug/findProjTeamUser.action",
					data : {
						teamId : teanmId
					},
					type : "POST",
					success : function(data) {
					var res = $.parseJSON(data);//把后台传回的json数据解析
					 var option;
					 if(res==null||res==""){
					 option = "<option value='"+""+"'>"+"该项目下无模块"+"</option>"
					 $("#teanmIds").html(option);
					 }else{
					$.each(res,function(i,n){//循环，i为下标从0开始，n为集合中对应的第i个对象
						option += "<option value='"+n.userIds+"' class='userids1'>"+n.userNames+"</option>"
					});
					$("#teanmIds").html(option);//将循环拼接的字符串插入第二个下拉列表
					}
					}
				});
		};
//-----------------------------确认分配-------------------------------------------------------------------
                $(".confirm-assign").click(function() {
			    var bugNum = $("#bugnums").html();
			    var updateBy=$("#teanmIds").val();
			    $.ajax({
			        url:"${pageContext.request.contextPath}/bug/allocationbug.action",
					data : {
						bugNum : bugNum,
						updateBy : updateBy
					},
					dataType:"json",
					type : "POST",
					success : function(data) {
					if(data.allocationbug==1){
						alert("分配成功！");
						location.reload();
						}else{
						alert("分配失败！");
						location.reload();
						}				
					}
			    });
                });	
//-----------------------------禁用bug-----------------------------------------------------------------
		$(".user-list").on("click", ".jinyong", function() {
			var userTr = $(this).parents("tr");
			var bugNum = userTr.find(".bugid").html();
			if (confirm('确认禁用bug编码为"' + bugNum + '"的用户吗？')) {
				//请求禁用该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/bug/forbidbug.action",
					data : {
						bugNum : bugNum
					},
					dataType:"json",
					type : "POST",
					success : function(data) {
					if(data.forbidbug>0){
						alert("禁用成功！");
						location.reload();
						}else{
						alert("禁用失败！");
						location.reload();
						}				
					}
				});
			}
		});
//---------------------------启动----------------------------------------------
		$(".user-list").on("click", ".qidong", function() {
			var userTr = $(this).parents("tr");
			var bugNum = userTr.find(".bugid").html();
			if (confirm('确认启动bug编码为"' + bugNum + '"的BUG吗？')) {
				//请求禁用该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/bug/startBug.action",
					data : {
						bugNum : bugNum
					},
					dataType:"json",
					type : "POST",
					success : function(data) {
					if(data.startBug>0){
						alert("启动成功！");
						location.reload();
						}else{
						alert("启动失败！");
						location.reload();
						}				
					}
				});
			}
		});
		//搜索框有效无效的设置
		$(document).ready(function(){	
     $("#userState").val(${userInfo.userState});
		});
//-------------------------------------根据项目查模块-------------------------------------------	
function findClassByProj(){
			var projNum =$("#projNums").val();
		
				//请求禁用该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/bug/findClassByProj.action",
					data : {
						projNum: projNum
					},
					type : "POST",
					success : function(data) {
					var res = $.parseJSON(data);//把后台传回的json数据解析
					 var option;
					 if(res==null||res==""){
					 option = "<option value='"+""+"'>"+"该项目下无模块"+"</option>"
					 $("#classIds").html(option);
					 }else{
					$.each(res,function(i,n){//循环，i为下标从0开始，n为集合中对应的第i个对象
						option += "<option value='"+n.classids+"' class='userids1'>"+n.classNames+"</option>"
					});
					$("#classIds").html(option);//将循环拼接的字符串插入第二个下拉列表
					}
					}
				});
		};
//-----------------------------------增加bug二级联动--------------------------
function findClassByProj1(){
			var projNum =$("#projName1").val();
		
				//请求禁用该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/bug/findClassByProj.action",
					data : {
						projNum: projNum
					},
					type : "POST",
					success : function(data) {
					var res = $.parseJSON(data);//把后台传回的json数据解析
					 var option;
					 if(res==null||res==""){
					 option = "<option value='"+""+"'>"+"该项目下无模块"+"</option>"
					 $("#classId1").html(option);
					 }else{
					$.each(res,function(i,n){//循环，i为下标从0开始，n为集合中对应的第i个对象
						option += "<option value='"+n.classids+"' class='userids1'>"+n.classNames+"</option>"
					});
					$("#classId1").html(option);//将循环拼接的字符串插入第二个下拉列表
					}
					}
				});
		};	
//--------------------------------查询-----------------------------------------
                 $(".selectAll").click(function() {
                 var bugTitle=$("#bugTitle").val();
                 var bugTitle=$("#bugTitle").val(bugTitle);
                 var updateBys=$("#updateBys").val();
                 var createTime=$("#createTime").val();
                 var createTime1=$("#createTime1").val();
                 var bugState11=$("#bugState111").val();
                 var bugLevel11=$("#bugLevel111").val();
                 var projNum=$("#projIds").val();
                 var classIds=$("#classIds").val();
                 $.ajax({
					url:"${pageContext.request.contextPath}/bug/bugList.action",
					data : {
						projNum:projNum
					},
					type : "POST",
					success : function(data) {
					
					},
				});
                 });	
//---------------------------修改bug---------------------------------
$(".updateBug").click(function() {
			var userTr = $(this).parents("tr");
			var bugNum = userTr.find(".bugid").html();
			var bugName = userTr.find(".bugname").html();
			$("#bugNumss").val(bugNum);
			$("#bugTitle1").val(bugName);
			});
			
			
			
			
			
			
			
			
			
			
			
			
			
			/* $.ajax({
                 url:"${pageContext.request.contextPath}/bug/bugList.action",
					type:"POST",
					dataType:"json",
					data:{
						bugTitle:bugTitle,
						updateBys:updateBys,
						createTime:createTime,
						createTime1:createTime1,
						bugState:bugState,
						bugLevel:bugLevel,
						projNum:projNum,
						classIds:classIds
					},
					//服务器响应（返回）的数据格式xml/script/html/text/json/jsonp
                    success:function(){
                    //ajax请求服务端ok，状态200,sa表示形参
                    },
                    error:function(){
                    alert("---");
                     },
                 }); */
			
	</script>
</body>
</html>


