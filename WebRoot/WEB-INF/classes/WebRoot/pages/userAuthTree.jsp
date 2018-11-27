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

<title>用户管理 - 权限列表</title>
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
        	  /*
        	   check:{
               enable:true,
               chkStyle: "radio",  //单选框
               radioType: "all",  //对所有节点设置单选
               chkDisaledinherit:true,
            },
            */
            check:{//使用复选框,默认复选框
                enable:true,
                autoCheckTrigger: true,
                nocheckInherit: true,
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
        var zNodes = ${jsonArr};
		
		function getFont(treeid,node){
			return node.font?node.font:{};
		};
		
	//给用户分配权限
        $(document).ready(function(){
        $(".show-add-form").click(function(){
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	w="";     
            	userId="";
            		
 			for(var i=0;i<nodes.length;i++){
 			if(nodes.length-i>1){
 				v+=nodes[i].id+",";//权限ID
 			}else{
            		v+=nodes[i].id;//权限ID
            		}
            		w+=nodes[i].authGrade;
            	   userId=nodes[i].userId;//用户ID
            	}
            	
            	userId=${userId22}*1;
            	
          $.ajax({
					url:"${pageContext.request.contextPath}/user-auth/addUserAuth.action",
					dataType:"json",
					type:"POST",
					data:{
					userId:userId,
					authId:v
					},
					success:function(data){
						if(data.msg=='1'){
						alert("添加成功！");
					   window.location.href="${pageContext.request.contextPath}/user-auth/showUserAuth.action?userId="+userId+"";						
						}else if(data.msg=='-1'){
						alert("取消成功！");
					   window.location.href="${pageContext.request.contextPath}/user-auth/showUserAuth.action?userId="+userId+"";						
						}else{
						alert("添加失败！");
					   window.location.href="${pageContext.request.contextPath}/user-auth/showUserAuth.action?userId="+userId+"";						
						}
					},
					error:function(data){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/user-auth/showUserAuth.action?userId="+userId+"";	
                		},
					});
           
           
            	
     });
            
            
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
				<h1 class="page-header">用户权限列表</h1>
				<div class="row placeholders">
					
					
					<!--添加权限表单 start-->
					<div>
					
						<button type="button" class="btn btn-default btn btn-primary  show-add-form" style="width:100px;"
							>保存分配</button>
						
						<a href="${pageContext.request.contextPath}/user/list.action">
						<button type="button" style="width:100px;" class="btn btn-primary " data-toggle="modal" data-target="#rein-auth-form-div">返回</button>
						</a>

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

	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</body>
</html>
