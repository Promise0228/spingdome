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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
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
        var zNodes = ${sysClass};
		
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
            	   className="";
            	   classDesc="";
            	   classCode="";
 			   for(var i=0;i<nodes.length;i++){
            		v=nodes[i].id;
            		w=nodes[i].grade;
            		className=nodes[i].name;
            		classDesc=nodes[i].classDesc;
            		classCode=nodes[i].classCode;  
            	}
        //控制按钮和input框是否显示
           //alert(className);
		   $("#classNameInput3").val(className);//名称回显
		   $("#descInput2").val(classDesc);//描述回显
		   $("#classCode3").val(classCode);//代码回显
		   
		$("#update-auth-form-div").modal("show");
		})
		 })
		
		
        $(document).ready(function(){
        //添加权限
        $(".show-add-form").click(function(){
        
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	w="";     
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].grade;
            	}
        alert(w);
         //控制按钮和input框是否显示
        $("#authurl").hide();
		$("#authcode").hide();
		   if(w==""){
		   $("#authurl").hide();
		   $("#authcode").hide();
		    $("#sysType").find("option[value='1']").prop("selected",true);
		   $("#addlist").attr("disabled","disabled");
		   $("#addbutton").attr("disabled","disabled");
		   }else if(w=="1"){
			   $("#authurl").show();
			   $("#authcode").hide();
			   $("#sysType").find("option[value='2']").prop("selected",true);
			    $("#addmodel").attr("disabled",true);
			    $("#addlist").attr("disabled",false);
		   $("#addbutton").attr("disabled",true);
		   }else if(w=="2") {
				/* $("#authcode").show();
				$("#sysType").find("option[value='3']").prop("selected",true);
			    $("#authurl").hide();
			      $("#addmodel").attr("disabled",true);
			    	$("#addlist").attr("disabled",true);
		   			$("#addbutton").attr("disabled",false); */
		   			alert ("无法添加下级模块");
		   			return false;
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
         
            
//请求添加分类系统(输入完成后点击确定按钮触发)
	 function addSysClass(){
	 		
	 		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	w="";     
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;//每级ID
            		w+=nodes[i].grade;//系统级别
            	}
			var an=$("#classNameInput1").val();//系统名称
      		var ac=$("#classDescInput1").val();//描述
      		var ad=$("#classCodeInput1").val();//编码
      		var at=$("#sysType").val();	//平台，项目，模块的值
      		var af=v;
      		
      		 if(!af){ 
      			af="0";
      		}
      		
      		
      		
      		//判断是否非空
      		if(w==""){
      		if(an==""||ad==""){
      		alert("名称不能为空");
      		return false;
      		}
      		}else if(w=="1"){
      		if(an=="" || ad==""){
      		alert("名称或编码不能为空");
      		return false;
      		}
      		}else if(w=="2"){
      		if(an=="" || ad==""){
      		alert("名称或编码不能为空");
      		return false;
      		}
      		}else if(w=="3"){
      		if(an=="" || ad==""){
      		alert("名称或编码不能为空");
      		return false;
      		}
      		} 
      		
      		if(!!an){
      			
				$.ajax({
					url:"${pageContext.request.contextPath}/sysclass/addClass.action",
					dataType:"json",
					type:"POST",
					data:{
						className:an,
						sysType:at,
						classDesc:ac,
						classCode:ad,
						parentId:af,
						grade:w
					},
					
					success:function(data){
						if(data.msg=='1'){
						alert("添加成功！");
					    window.location.href="${pageContext.request.contextPath}/sysclass/sysClassList.action";						
						}else if(data.msg=='3'){
						alert("此类系统名或系统编码已经存在，请重新输入！");
						return;
						window.location.href="${pageContext.request.contextPath}/sysclass/sysClassList.action";
						}else{
						alert("添加失败！");
					  window.location.href="${pageContext.request.contextPath}/sysclass/sysClassList.action";						
						}
					},
					error:function(){ 
						alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/sysclass/sysClassList.action";
                	}
                		
					});
				}else{ 
				alert("请正确填写!!");
				}
			}
			 
			 
		//修改项目
		function updateSysClass(){
      		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";      
            	w="";  
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].grade;//权限级别
            	}
            var an=$("#classNameInput3").val();           
      		var ad=$("#descInput2").val();		
      		var ac=$("#classCode3").val();	
      		
      		/* //判断是否非空
      		if(w==""){
      		if(an==""){
      		alert("名称不能为空");
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
      		} */
      		
      		
      		var af =v;
      		if(!!af){
				$.ajax({
					url:"${pageContext.request.contextPath}/sysclass/updateClass.action",
					type:"POST",
					dataType:"json",
					data:{
					className:an,
					classDesc:ad,
					classCode:ac,
					classId:af,
					},					
				
					success:function(data){
						if(data.msg==1){
						alert("修改成功！");
					   window.location.href="${pageContext.request.contextPath}/sysclass/sysClassList.action";						
						}else{
							alert("修改失败！");
					   window.location.href="${pageContext.request.contextPath}/sysclass/sysClassList.action";						
						}
					},
					error:function(){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/sysclass/sysClassList.action"; 
                		},
					});
		    }else{
		    	alert("请选择要修改的权限")
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
				<h1 class="page-header">分类列表</h1>
				<div class="row placeholders">
					
					
					<!--添加修改按钮-->
					<div>
						<button type="button" class="btn btn-primary show-update-form" style="width:100px;"
							data-toggle="modal"  >修改项目</button>
						
						<button type="button" class="btn btn-primary  show-add-form"
							data-toggle="modal" 
							style="width: 100px;">添加项目</button>
						
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
											<div class="form-group" id="className">
												<label for="authNameInput2">名称</label> <input type="text"
													name="authName" class="form-control" id="classNameInput3"
													placeholder="要修改的项目名称">
											</div>
											<div class="form-group" id="classDesc">
												<label for="descInput2">项目描述</label> <textarea rows="3" cols="9"
													name="authGrade" class="form-control" id="descInput2"
													></textarea>
											</div>
											
											<div class="form-group" id="classCode">
												<label for="codeInput2">项目code</label> <input type="text" maxlength="100"
													name="authCode" class="form-control" id="classCode3"  onkeyup="value=value.match(/^\w{1,28}$/)"
													placeholder="项目代码(请输入英文)">
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary role-submit"
											id="checkon" onclick="updateSysClass()">提交</button>
									</div>
								</div>
							</div>
						</div>
						
						
						
				
				<!--新增项目-->
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
												<label for="classNameInput1">名称</label> <input type="text"
													name="className" class="form-control" id="classNameInput1" maxlength="20"
													placeholder="项目名称">
											</div>
											<div class="form-group" id="classDesc">
												<label for="classDescInput1">描述</label> <input type="text" maxlength="28"
													name="classDesc" class="form-control" id="classDescInput1"  
													placeholder="分类描述">
											</div>
											<div class="form-group">
												<label for="classCodeInput1">分类编码</label> <textarea type="text" maxlength="100"
													name="classCode" class="form-control" id="classCodeInput1"
													placeholder="分类编码，请输入字母和数字"></textarea>
											</div>
											
											<select  id="sysType" name="sysType" style="height:28px;">
									         	
									         	<option id="addmodel" value="1">项目</option>
									         	<option id="addlist" value="2">模块</option>
									         	<!-- <option id="addbutton" value="3">模块</option> -->
									         </select> &nbsp;
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary role-submit"
											id="checkon" onclick="addSysClass()">提交</button>
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
