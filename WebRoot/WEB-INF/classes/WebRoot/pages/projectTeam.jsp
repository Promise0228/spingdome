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

    <title>用户管理 - 角色列表</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/layout.css" rel="stylesheet">
  </head>

  <body>

    <!-- 头部 -->
    <jsp:include page="header.jsp"/>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <jsp:include page="navibar.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">项目组管理</h1>
          <div class="row placeholders">
          	<div>
          	<form action="${pageContext.request.contextPath}/projectTeam/list.action">
                <table>
                <tr>
                <td>&nbsp; <select style="width: 198px; height: 28px" name="projId" id="teamState" >
                			<option value="" disabled selected style="display:none;height: 100%">项目</option> 
                			<c:forEach items="${projInfo}" var="proj">
                			<option value="${proj.projId}" class="projectTeam">${proj.projName}</option>
                			</c:forEach>
                			</select>&nbsp;</td>
                <td><input type="text" name="teamName" placeholder="查询" value="${projTeam.teamName}"></td>
                <td>&nbsp;<input id="selectProjTeam" type="submit" class="selectRole" value="查询">&nbsp;</td>
                </tr>
                </table>
                </form>
          	<br/>
               
                <!--  删除所选对话框 -->
                <div class="modal fade " id="delete-confirm-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >警告</h4>
                      </div>
                      <div class="modal-body">
                        确认删除所选角色吗
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary delete-selected-confirm">确认</button>
                      </div>
                    </div>
                  </div>
                </div>
                <c:if test="${roleId==1 or roleId==2}">
            	<button type="button" class="btn btn-primary show-add-form" data-toggle="modal" >创建项目组</button>
                </c:if>
                <!--创建项目组表单-->
                <div class="modal fade " id="proj-form-div" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-md" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="proj-form-title" ></h4>
                      </div>
                      <div class="modal-body">
                      	<form class="proj-form">
                          <input type="hidden" name="roleId" class="form-control" id="roleIdInput">
                          <div class="form-group">
                            <label for="roleNameInput">项目</label>
                            <select style="width: 198px; height: 28px" name="projId" id="teamStates" >
                			<option value="" disabled selected style="display:none;height: 100%">选择项目</option>
                			<c:forEach items="${projInfo}" var="proj">
                			<option value="${proj.projId}" class="projectTeam">${proj.projName}</option>
                			</c:forEach>
                			</select>
                          </div>
                          <div class="form-group">
                            <label for="teamNameInput">项目组名称</label>
                            <input type="text" name="teamName" maxlength="16" class="form-control" id="teamNameInput" placeholder="项目组名称" onkeyup="value=value.match(/^.{2,36}$/)"/>
                          </div>
                          <div class="form-group">
                            <label for="descInput">项目组描述</label>
                            <input type="text" name="teamDesc" maxlength="16" class="form-control" id="descInput" placeholder="描述" onkeyup="value=value.match(/^.{2,16}$/)"/>
                          </div>
                          <div class="form-group">
                            <label for="codeInput">项目组代码</label>
                            <input type="text" name="teamCode" maxlength="16" class="form-control" id="codeInput" placeholder="code" onkeyup="value=value.match(/^\w{1,16}$/)"/>
                          </div>
                          <div class="form-group">
                            <label for="codeInput">项目组负责人</label>
                            <select style="width: 198px; height: 28px" name="projChief" id="projChief" >
                			<option value="" class="projectTeam">选择</option>
                			<c:forEach items="${projUser }" var="projUser">
                			<option value="${projUser.userId }">${projUser.userName}</option>
                			</c:forEach>
                			</select>
                          </div>
                        </form>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary proj-submit" value="创建"></button>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
            <div class="space-div"></div>
            <table class="table table-hover table-bordered role-list">
            	<tr>
                	<td><input type="checkbox" class="select-all-btn"/></td>
                    <td>ID</td>
                    <td>名称</td>
                    <td>描述</td>
                    <td>代码</td>
                    <td>负责人</td>
                    <td>操作</td>
                </tr>
                 <c:forEach items="${page.resultList}" var="projTeam">
	                <tr>
	                	<td><input type="checkbox" name="teamIds" value="${projTeam.teamId }"/></td>
	                    <td class="teamId">${projTeam.teamId }</td>
	                    <td class="teamName">${projTeam.teamName }</td>
	                    <td class="teamDesc">${projTeam.teamDesc }</td>
	                    <td class="teamCode">${projTeam.teamCode }</td>
	                    <td class="projChief">${projTeam.projChief}</td>
	                    <td> <a href="${pageContext.request.contextPath}/projectTeam/findTeamUser.action?teamId=${projTeam.teamId}"><input class="btn btn-primary" type="button" value="成员"></a></td>
	                </tr>
                </c:forEach> 
            </table>
            <jsp:include page="standard.jsp"/>
          </div>          
        </div>
      </div>
    </div>
	<!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
      <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
        	<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示信息</h4>
          </div>
          <div class="modal-body" id="op-tips-content">
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script>
   
    
    
    	//信息查询
    	function findProjectInfo(){
    	var userTr = $(this).parents("tr");
    	//获取项目主键id
     	  var teamId=userTr.find(".teamId").html();
    	  $.ajax({
    	  url:"",
    	  type:"POST",
    	  dataType:"json",
    	  data:{
    	  teamId:teamId,
    	  },
    	  success:function(data){
    	  //获得模块信息，并将模块信息传递给模块下拉框
    	  }
    	  });
    	
    	}
    
    
    
    
    	function showTips(contents){
    		$("#op-tips-content").html(contents);
			$("#op-tips-dialog").modal("show");
       	}
       	function resetRoleForm(title,button){
           	$(".proj-form input[type='text']").val("");
			$(".proj-form input[type='checkbox']").prop("checked",false);
       		$(".proj-form-title").html(title);
			$(".proj-submit").html(button);
      	}
       	function getAllPerms(obj){
        	obj.html("");
    		$.ajax({
				url:"listperms.html",
				type:"POST",
				dataType:"json",
				success:function(data){
					for(var i in data){
						obj.append('<input type="checkbox" name="permIds" value="'+
								data[i].permissionId+'"/>'+data[i].permissionDesc+':');
						if(data[i].isNavi===1){
							obj.append('<font color="red">导航结点</font>');
						}else{
							obj.append("非导航结点");
						}
						obj.append("<br/>");
					}
				}
			});
       	}
    	$(".select-all-btn").change(function(){
			if($(this).is(":checked")){//$(this).prop("checked")
				$(".role-list input[type='checkbox']:gt(0)").prop("checked",true);
			}else{
				$(".role-list input[type='checkbox']:gt(0)").prop("checked",false);
			}
		});
		$(".delete-selected-confirm").click(function(){
			$("#delete-confirm-dialog").modal("hide");
			var cbs=$("input[name='roleIds']:checked")
			if(cbs.length===0){
				showTips("没有选中任意项！");
			}else{
				var roleIds='';
				$.each(cbs,function(i,perm){
					//roleIds+=perm.value;
				});
				//请求删除所选角色
				$.ajax({
					url:"deletemore.html",
					data:{roleIds:roleIds},
					type:"POST",
					traditional:true,
					success:function(){
						cbs.parent().parent().remove();
						showTips("删除所选成功！");
					}
				});
			}
		});
		
		$(".show-add-form").click(function(){
		$("#proj-form-div").modal("show");
			resetRoleForm("创建项目组","创建");
			$("#codeInput").attr("readonly",false);
			getAllPerms($(".perm-inputs"));
		});
		function getPermsByRoleId(roleId,doSuccess){
			$.ajax({
				url:"${pageContext.request.contextPath}/role/findRoleById.action",
				data:{roleId:roleId},
				type:"POST",
				dataType:"json",
				success:function(data){
					doSuccess(data);
				}
			});
		}
		$(".role-list").on("click",".show-role-perms",function(){
			var roleId=$(this).parents("tr").find(".roleid").html();
			var rlTd=$(this).parent();
			//请求查看用户角色
			getPermsByRoleId(roleId,function(data){
				rlTd.html("");
				for(var i in data){
					var role=data[i].permissionDesc+"<br/>";
					rlTd.append(role);
				}
			});
		});
		$(".role-list").on("click",".show-roleinfo-form",function(){
			getAllPerms($(".perm-inputs"));
			resetRoleForm("更新角色信息","更新");
			var roleId=$(this).parents("tr").find(".roleid").html();
			$("#codeInput").attr("readonly","readonly");
			$("input[name='roleId']").val(roleId);
			$.ajax({
				url:"${pageContext.request.contextPath}/role/findRoleById.action",
				data:{roleId:roleId},
				type:"POST",
				dataType:"json",
				success:function(data){
					$("input[name='roleName']").val(data.roleName);
					$("input[name='roleDesc']").val(data.roleDesc);
					$("input[name='roleCode']").val(data.roleCode);
				}
			});
			getPermsByRoleId(projId,function(data){
				for(var i in data){
					$('.proj-form input[name="permIds"][value="'+data[i].permissionId+'"]').prop("checked",true);
				}
			});
		});
		
		$(".proj-submit").click(function(){
			var teamNameInput=$("#teamNameInput").val();
			alert(teamNameInput);
				//请求创建项目组
				$.ajax({
					url:"${pageContext.request.contextPath}/projectTeam/insertProjTeam.action",
					type:"POST",
					data:$(".proj-form").serialize(),
					dataType:"json",
					success:function(data){
						$("#proj-form-div").modal("hide");
						if(data.res=='1'){
						alert("创建成功");
						window.location.href="${pageContext.request.contextPath}/projectTeam/list.action";
						}else if(data.res=='2'){
						alert("项目组已存在");
						}else{
						alert("创建失败");
						}
					}
				});
			
		});
		
		
		$(".role-list").on("click",".restart-this-role",function(){
			var roleTr=$(this).parents("tr");
			var roleId=roleTr.find(".roleid").html();
			var roleState=roleTr.find(".rolestate").html();
			if(confirm('确认'+(roleState=='启用'?'禁用':'启用')+'ID为"'+roleId+'"的角色吗？')){
				
				//请求删除该用户
				$.ajax({
					url:"${pageContext.request.contextPath}/role/restartRole.action",
					data:{
					roleId:roleId,
					roleState:roleState
					},
					type:"POST",
					dataType:"json",
					success:function(data){
						roleTr.remove();
						if(data.remsg=='1'){
						alert("启用成功");
						window.location.href="${pageContext.request.contextPath}/role/rolelist.action";
						}else if(data.remsg=='0'){
						alert("禁用成功");
						window.location.href="${pageContext.request.contextPath}/role/rolelist.action";
						}else{
						alert("操作失败");
						}
						
					}
				});
			}
		});
		
		
						//设置分页的下拉列表的默认值
		$(document).ready(function(){	
    $("#selectedpage").find("option[value='"+${page.page}+"']").prop("selected",true);
		});
		
		
		//搜索框有效无效的设置
		/* $(document).ready(function(){	
   // $("#roleState").find("option[value='"+${role.roleState}+"']").prop("selected",true);
    $("#teamState").val(${projTeam.projId});
		}); */
    </script>
  </body>
</html>
