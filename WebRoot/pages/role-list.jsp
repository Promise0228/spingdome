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
          <h1 class="page-header">角色列表</h1>
          <div class="row placeholders">
          	<div>
          	<form action="${pageContext.request.contextPath}/role/rolelist.action">
                <table>
                <tr>
                <td>&nbsp;<input type="text" maxlength="16" value="${role.roleName}" name="roleName" placeholder="角色名">&nbsp;</td>
                <td>&nbsp;
                <select name="roleState" id="roleState" style="height: 28px; width: 198px; ">  
                <option value="" disabled selected style="display: none;">角色状态</option>  
                 <option value="1">有效</option>
                <option value="0">无效</option>  
                <option value="">全部</option>    
                </select> &nbsp;</td>
                <td>&nbsp;<input type="date" value="<fmt:formatDate value='${role.createTime }' pattern='yyyy-MM-dd ' />"  name="createTime">&nbsp;</td>
               <td>&nbsp;—&nbsp;</td>
               <td>&nbsp;<input type="date" value="<fmt:formatDate value='${role.createTime2 }' pattern='yyyy-MM-dd ' />"  name="createTime2">&nbsp;</td>
                <td>&nbsp;<input id="selectRole" type="submit" class="selectRole" value="查询">&nbsp;</td>
                </tr>
                </table>
                </form>
          	<br/>
            	<button type="button" class="btn btn-warning " >数据导出</button>
               
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
            	<button type="button" class="btn btn-primary show-add-form" data-toggle="modal" data-target="#role-form-div">添加新角色</button>
                <!--添加新角色表单-->
                <div class="modal fade " id="role-form-div" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-md" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="role-form-title" ></h4>
                      </div>
                      <div class="modal-body">
                      	<form class="role-form">
                          <input type="hidden" name="roleId" class="form-control" id="roleIdInput">
                          <div class="form-group">
                            <label for="roleNameInput">名称</label>
                            <input type="text" name="roleName" maxlength="16" class="form-control" id="roleNameInput" placeholder="角色名" onkeyup="value=value.match(/^.{2,16}$/)"/>
                          </div>
                          <div class="form-group">
                            <label for="descInput">描述</label>
                            <input type="text" name="roleDesc" maxlength="16" class="form-control" id="descInput" placeholder="角色描述" onkeyup="value=value.match(/^.{2,16}$/)"/>
                          </div>
                          <div class="form-group">
                            <label for="codeInput">代码</label>
                            <input type="text" name="roleCode" maxlength="16" class="form-control" id="codeInput" placeholder="角色代码" onkeyup="value=value.match(/^\w{1,16}$/)"/>
                          </div>
                          
                        </form>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary role-submit"></button>
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
                    <td>状态</td>
                    <td>修改时间</td>
                    <td>操作</td>
                </tr>
                <!--  <tr>
                	<td><input type="checkbox" name="roleIds"/></td>
                    <td class="roleid">11</td>
                    <td>用户管理员</td>
                    <td>用户管理</td>
                    <td>user_admin</td>
                    <td><a href="javascript:void(0);" class="show-role-perms" >查看所有权限</a></td>
                    <td><a class="glyphicon glyphicon-pencil show-roleinfo-form" aria-hidden="true" title="修改角色信息" href="javascript:void(0);" data-toggle="modal" data-target="#role-form"></a>
                    	<a class="glyphicon glyphicon-remove delete-this-role" aria-hidden="true" title="删除角色" href="javascript:void(0);"></a></td>
                </tr>-->
                <c:forEach items="${page.resultList}" var="role">
	                <tr>
	                	<td><input type="checkbox" name="roleIds" value="${role.roleId }"/></td>
	                    <td class="roleid">${role.roleId }</td>
	                    <td class="rolename">${role.roleName }</td>
	                    <td class="roledesc">${role.roleDesc }</td>
	                    <td class="rolecode">${role.roleCode }</td>
	                    <td class="rolestate">${role.roleState==0?'禁用':'启用' }</td>
	                    <td><fmt:formatDate value="${role.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td>
	                   <c:if test="${role.roleState==0}">
	                    <button type="button" class="btn btn-warning restart-this-role" id="openRole">启用</button>
	                   </c:if>
	                   <c:if test="${role.roleState==1}">
	                   	<a class="glyphicon glyphicon-pencil show-roleinfo-form" aria-hidden="true" title="修改角色信息" href="javascript:void(0);" data-toggle="modal" data-target="#role-form-div"></a>	                   
	                   <button type="button" class="btn btn-primary restart-this-role" id="closeRole">禁用</button>	                   
	                  <a href="${pageContext.request.contextPath}/role/updateRole.action?roleId=${role.roleId }">
	                  <button type="button" class="btn btn-primary update-Role" id="closeRole">更改权限</button>	                   
	                    </a>
	                    </c:if>
	                    </td>
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
    	function showTips(contents){
    		$("#op-tips-content").html(contents);
			$("#op-tips-dialog").modal("show");
       	}
       	function resetRoleForm(title,button){
           	$(".role-form input[type='text']").val("");
			$(".role-form input[type='checkbox']").prop("checked",false);
       		$(".role-form-title").html(title);
			$(".role-submit").html(button);
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
			resetRoleForm("添加新角色","添加");
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
			getPermsByRoleId(roleId,function(data){
				for(var i in data){
					$('.role-form input[name="permIds"][value="'+data[i].permissionId+'"]').prop("checked",true);
				}
			});
		});
		$(".role-submit").click(function(){
			if($(this).html()==="添加"){
				//请求添加新角色
				$.ajax({
					url:"${pageContext.request.contextPath}/role/addRole.action",
					type:"POST",
					data:$(".role-form").serialize(),
					dataType:"json",
					success:function(data){
						$("#role-form-div").modal("hide");
						if(data.addmsg>0){
						alert("新增成功");
						window.location.href="${pageContext.request.contextPath}/role/rolelist.action";
						}else if(data.addmsg<0){
						alert("角色名或角色Code重复");
						}else{
						alert("新增成功失败");
						}
					}
				});
			}else{
				//更新角色信息
				$.ajax({
					url:"${pageContext.request.contextPath}/role/refushRole.action",
					data:$(".role-form").serialize(),
					type:"POST",
					dataType:"json",
					success:function(){
						$("#role-form-div").modal("hide");
						window.location.href="${pageContext.request.contextPath}/role/rolelist.action";
					}
				});
			}
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
		$(document).ready(function(){	
   // $("#roleState").find("option[value='"+${role.roleState}+"']").prop("selected",true);
    $("#roleState").val(${role.roleState});
		});
    </script>
  </body>
</html>
