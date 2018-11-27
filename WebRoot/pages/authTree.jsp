<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>权限管理 - 权限列表</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/zTree/css/demo.css"
	type="text/css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.excheck.js"></script>
 <script type="text/javascript">
       var setting = {          
        	   check:{
               enable:true,
               chkStyle: "radio",  //单选框
               radioType: "all",  //对所有节点设置单选
               chkDisaledinherit:true,
            },
            view:{
            	fontCss:getFont,
            	nameIsHTML:true
            },
           
            data: {
                simpleData:{//是否使用简单数据模式
                    enable:true
                }
            },
            callback:{
                onCheck:onCheck
            }           
        };
         //id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中      ,grade 级别  
        var zNodes = ${authArrays};
		
		function getFont(treeid,node){
			return node.font?node.font:{};
		};
		
		
		//权限修改
		 $(document).ready(function(){
		     $(".show-update-form").click(function(){
		         var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	   nodes=treeObj.getCheckedNodes(true),
            	   v="";
            	   w="";
            	   authUrl="";     
            	   authCode="";
            	   name="";
            	   authDesc="";
 			   for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].authGrade;
            		authUrl+=nodes[i].authUrl;
            		authCode+=nodes[i].authCode;
            		authDesc+=nodes[i].authDesc;  
            		name+=nodes[i].name;
            	}
        //控制按钮和input框是否显示
        $("#authurl2").hide();
		$("#authcode2").hide();
		   if(w=="1"){
		   $("#authurl2").hide();
		   $("#authcode2").hide();
		   $("#authNameInput2").val(name);//权限名称回显
		   $("#descInput2").val(authDesc);//权限描述回显
		   }else if(w=="2"){
			   $("#authurl2").show();
			    $("#authNameInput2").val(name);//权限名称回显
		   $("#descInput2").val(authDesc);//权限描述回显
			   $("#urlInput2").val(authUrl);//权限URL描述回显
			   $("#authcode2").hide();
		   }else if(w=="3") {
				$("#authcode2").show();
				 $("#authNameInput2").val(name);//权限名称回显
		   $("#descInput2").val(authDesc);//权限描述回显
				$("#codeInput2").val(authCode);//权限code回显
				$("#codeInput2").attr("readonly","readonly");
			    $("#authurl2").hide();
			}else{
				alert("请选择要修改的权限");
				return false;
				}
				 $("#update-auth-form-div").modal("show");
		})
		 })
		
		
        $(document).ready(function(){
        //添加权限
        $(".show-add-form").click(function(){
        
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	w=""     
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].authGrade;
            	}
        
        //控制按钮和input框是否显示
        $("#authurl").hide();
		$("#authcode").hide();
		   if(w==""){
		   $("#authurl").hide();
		   $("#authcode").hide();
		    $("#userByAuth").find("option[value='1']").prop("selected",true);
		   $("#addlist").attr("disabled","disabled");
		   $("#addbutton").attr("disabled","disabled");
		   }else if(w=="1"){
			   $("#authurl").show();
			   $("#authcode").hide();
			   $("#userByAuth").find("option[value='2']").prop("selected",true);
			    $("#addmodel").attr("disabled",true);
			    $("#addlist").attr("disabled",false);
		   $("#addbutton").attr("disabled",true);
		   }else if(w=="2") {
				$("#authcode").show();
				$("#userByAuth").find("option[value='3']").prop("selected",true);
			    $("#authurl").hide();
			      $("#addmodel").attr("disabled",true);
			    $("#addlist").attr("disabled",true);
		   $("#addbutton").attr("disabled",false);
			}else{
				alert("无法添加子权限");
				return false;
				}
				 $("#role-form-div").modal("show");
		})

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        
        });
        
        
            //选中复选框后触发事件
            function onCheck(e,treeId,treeNode){
            	var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	//获取选中的复选框的值
            	for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            	}
           	 	
            }
         
            
//请求添加权限(输入完成后点击确定按钮触发)
	 function addAuth1(){
	 		
	 		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	w=""     
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;//权限ID
            		w+=nodes[i].authGrade;//权限级别
            	}
			var an=$("#authNameInput1").val();//权限名
      		var ac=$("#codeInput1").val();//code
      		var ad=$("#descInput1").val();//描述
      		var au=$("#urlInput1").val();//url
      		var at=$("#userByAuth").val();	//模块，按钮，列表的值
      		var af=v;
      		if(!af){ 
      			af="0";
      		}
      		
      				   		//判断是否非空
      		if(w==""){
      		if(an==""){
      		alert("权限名不能为空");
      		return false;
      		}
      		}else if(w=="1"){
      		if(an=="" || au==""){
      		alert("权限名或URL不能为空");
      		return false;
      		}
      		}else if(w=="2"){
      		if(an=="" || ac==""){
      		alert("权限名或code不能为空");
      		return false;
      		}
      		}
      		
      		
      		
      		if(!!an&&!!ad&&!!af){
				$.ajax({
					url:"${pageContext.request.contextPath}/auth/addAuth.action",
					dataType:"json",
					type:"POST",
					data:{
					authName:an,
					authType:at,
					authCode:ac,
					authUrl:au,
					authDesc:ad,
					parentId:af,
					authGrade:w,
					},
					success:function(data){
						if(data.msg==1){
						alert("添加成功！");
					   window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";						
						}else if(data.msg=='3'){
						alert("此类权限名或权限code已经存在，请重新输入！");
						return;
						window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
						}else{
						alert("添加失败！");
					   window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";						
						}
					},
					error:function(data){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
                		},
					});
				}else{ 
				alert("请正确填写!!");
				}
			}
			 
			 
		//修改权限
		function updateAuth(){
      		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";      
            	w="";  
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].authGrade;//权限级别
            	}
            var an=$("#authNameInput2").val();           
      		var ad=$("#descInput2").val();		
      		var au=$("#urlInput2").val();	
      		
      		
      		
      		      				   		//判断是否非空
      		if(w==""){
      		if(an==""){
      		alert("权限名不能为空");
      		return false;
      		}
      		}else if(w=="1"){
      		if(an=="" || au==""){
      		alert("权限名或URL不能为空");
      		return false;
      		}
      		}else if(w=="2"){
      		if(an==""){
      		alert("权限名不能为空");
      		return false;
      		}
      		}
      		
      		
      		var authId =v;
      		if(!!authId){
				$.ajax({
					url:"${pageContext.request.contextPath}/auth/updateAuth.action",
					type:"POST",
					dataType:"json",
					data:{
					authName:an,
					authDesc:ad,
					authId:authId,
					authUrl:au
					},					
					success:function(data){
						if(data.msg==1){
						alert("修改成功！");
					   window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";						
						}else{
							alert("修改失败！");
					   window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";						
						}
					},
					error:function(){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/auth/permission-list.action"; 
                		},
					});
		    }else{
		    	alert("请选择要修改的权限");
		    } 
		    }
		    
		    
		    //删除权限
		 function deleteAuth(){
      		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";       
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            }	
      		var authId =v;
      		if(!!authId){
				$.ajax({
					url:"${pageContext.request.contextPath}/auth/deleteAuth.action",
					type:"POST",
					dataType:"json",
					data:{
					authId:authId,
					},
					success:function(data){
					if(data.delmsg=='1'){
					alert("删除成功！");
					    window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
					}else if(data.delmsg=='2'){
					alert("删除失败！");
					    window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
					}else{
					alert("此权限下有子权限，不能删除！");
					window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
					}
					},
					error:function(){ 
					alert("执行异常");
					window.location.href="${pageContext.request.contextPath}/auth/permission-list.action"; 
                		},
					});
		    }else{
		    	alert("请选择要删除的权限");
		    }
  		}
  		/*恢复权限
  		 */
  		 
  		  function reinAuth(){
      		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";       
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            }	
      		var authId =v;
      		if(!!authId){
				$.ajax({
					url:"${pageContext.request.contextPath}/auth/resumeAuth.action",
					type:"POST",
					dataType:"json",
					data:{
					authId:authId,
					},
					success:function(data){
					if(data.msg=='1'){
					alert("恢复成功！");
					    window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
					}else if(data.msg=='2'){
					alert("恢复失败！");
					    window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
					}else{
					alert("此权限上级有权限，不能直接恢复！");
					window.location.href="${pageContext.request.contextPath}/auth/permission-list.action";
					}
					},
					error:function(){ 
					alert("执行异常");
					window.location.href="${pageContext.request.contextPath}/auth/permission-list.action"; 
                		},
					});
		    }else{
		    	alert("请选择要恢复的权限");
		    }
  		}
  		 
    </script>
</head>
 
<body>

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid" style="margin-top: 30px;">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">权限列表</h1>
				<div class="row placeholders">
					
					
					<!--添加权限表单 start-->
					<div>
						<button type="button" class="btn btn-default btn btn-primary show-update-form" style="width:100px;"
							data-toggle="modal">修改权限</button>
						
						
						<button type="button" style="width:100px;" class="btn btn-primary " data-toggle="modal" data-target="#rein-auth-form-div">恢复权限</button>
						
						
						<button type="button" class="btn btn-primary btn btn-default show-add-form"
							data-toggle="modal" 
							style="width: 100px;">添加权限</button>
						
						
						<button type="button" style="width:100px;" class="btn btn-primary" onclick="deleteAuth()">删除权限</button>		
						
						
						<!--修改权限-->
						<div class="modal fade " id="update-auth-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="role-form-title"></h4>
									</div>
									<div class="modal-body">
										<form class="role-form">

											<!-- <input type="text" name="authId" class="form-control" id="authIdInput"> -->
											<div class="form-group">
												<label for="authNameInput2">名称</label> <input type="text"
													name="authName" class="form-control" id="authNameInput2"
													placeholder="要修改的权限名称">
											</div>
											<div class="form-group">
												<label for="descInput2">权限描述</label> <textarea rows="3" cols="9"
													name="authGrade" class="form-control" id="descInput2"
													></textarea>
											</div>
											<div class="form-group" id="authurl2">
												<label for="urlInput2">权限url</label> <input type="text" maxlength="50"
													name="authurl" class="form-control" id="urlInput2" onkeyup="value=value.match(/^\w{1,20}$/)"
													placeholder="权限url(请输入英文)">
											</div>
											<div class="form-group" id="authcode2">
												<label for="codeInput2">权限code</label> <input type="text" maxlength="28"
													name="authCode" class="form-control" id="codeInput2"  onkeyup="value=value.match(/^\w{1,28}$/)"
													placeholder="权限代码(请输入英文)">
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary role-submit"
											id="checkon" onclick="updateAuth()">提交</button>
									</div>
								</div>
							</div>
						</div>
						
						
						<!--恢复权限-->
						<div class="modal fade " id="rein-auth-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="role-form-title">恢复权限</h4>
									</div>
									<div class="modal-body">
								<c:forEach items="${authids}" var="auth0">
								<div><input type="checkbox" name="authId" value="${auth0.authId}"/>${auth0.authName}</div>
								</c:forEach>
							<button type="button" style="width:100px;" class="btn btn-primary" data-dismiss="modal" class="btn btn-default">取消</button>
							<button type="button" style="width:100px;" class="btn btn-primary" onclick="reinAuth()">提交</button>
						</div>
						</div>
					</div>
				</div>
				</div>
				
				<!--新增权限-->
						<div class="modal fade " id="role-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="role-form-title"></h4>
									</div>
									<div class="modal-body">
										<form class="role-form">

											<!-- <input type="text" name="authId" class="form-control" id="authIdInput"> -->
											<div class="form-group">
												<label for="authNameInput1">名称</label> <input type="text"
													name="authName" class="form-control" id="authNameInput1" maxlength="20"
													placeholder="权限名称">
											</div>
											<div class="form-group" id="authcode">
												<label for="codeInput1">权限code</label> <input type="text" maxlength="28"
													name="authCode" class="form-control" id="codeInput1"  
													placeholder="权限代码(请输入英文)">
											</div>
											<div class="form-group">
												<label for="descInput1">权限描述</label> <textarea type="text" maxlength="20"
													name="authDesc" class="form-control" id="descInput1"
													placeholder="权限描述"></textarea>
											</div>
											 <div class="form-group" id="authurl">
												<label for="urlInput1">权限url</label> <input type="text" maxlength="50"
													name="authurl" class="form-control" id="urlInput1" 
													placeholder="权限url(请输入英文)">
											</div>
											<select  id="userByAuth" name="userState" style="height:28px;">
									         	
									         	<option id="addmodel" value="1">模块</option>
									         	<option id="addlist" value="2">列表</option>
									         	<option id="addbutton" value="3">按钮</option>
									         </select> &nbsp;
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary role-submit"
											id="checkon" onclick="addAuth1()">提交</button>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!--添加权限表单 end-->
					<div class="space-div"></div>

					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree" style="width:1024px;"></ul>
					</div>
					
					<div class="space-div"></div>
															
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
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</body>
</html>
