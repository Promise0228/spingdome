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

<title>资源管理 - 文件列表</title>
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
        var zNodes = ${fileTypeArrays};
		function getFont(treeid,node){
			return node.font?node.font:{};
		};
		//文件下载
		 $(document).ready(function(){
		     $(".show-download-form").click(function(){
		     	//获取选中的节点
		         var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	   nodes=treeObj.getCheckedNodes(true),
            	   v="";
            	   w="";
            	   name="";
            	   str="";
            	   authDesc="";
 			   for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].grade;//等级
            		name+=nodes[i].name;
            	}
            	function check(nodes){
            		if(nodes.length>0){
            			var node = nodes[0].getPath();
            			for(var i=0;i<node.length;i++){
            			str+="/"+node[i].name;
            			}
            			id=nodes[0].id;
            		}
            		return str;
            }
            var ss=check(nodes);
        //控制按钮和input框是否显示
			if(w==""){
				alert("请选择下载的文件");
				return false;
				}else{
				 //判断选中节点为文件夹还是文件
            	   if(nodes[0].isParent!=true){
            	   //获得所选文件节点id
            	   var af=nodes[0].fileId;
            	   //文件,判断文件是否加密
            	   $.ajax({
					url:"${pageContext.request.contextPath}/repository/getMD5.action",
					dataType:"json",
					type:"POST",
					data:{
					fileId:af,
					},
					success:function(data){
						if(data.res=='1'){
						//密码存在，弹出输入密码的弹窗
							$("#download").modal("show");
							document.getElementById("downLoad").style.display="inline";
							document.getElementById("deleteFiles").style.display="none";
						}else{
						//密码不存在，直接下载
						document.getElementById("downloadA").href="${pageContext.request.contextPath}/Folder_management"+ss;
						$("#subBtn").trigger("click");
						}	
					},
					error:function(data){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
                		},
					});
					}else{
					//选中的节点为文件夹
					//判断节点下面是否有存在密码的文件节点
     				$.ajax({
					url:"${pageContext.request.contextPath}/repository/getMD5s.action",
					dataType:"json",
					type:"POST",
					data:{
					id:v,//节点id,后台判断是否存在子节点含有密码
					},
					success:function(data){
						if(data.res==1){
						//密码存在且只有一个，弹出输入密码的弹窗
						alert("存在加密文件，无法打包下载");
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
						}else if(data.res==2){
						//存在多个加密文件，无法打包下载
						alert("存在多个加密文件，无法打包下载");
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
						}else if(data.res==0){
						//没有文件
						alert("没有文件");
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
						}else{
						//密码不存在，压缩下载
						//将节点的名称传递给后台，进行压缩下载
						//获取父节点的名称
						//将文件夹压缩后的路径传入前台
						$.ajax({
							url:"${pageContext.request.contextPath}/repository/downloadAll.action",
							dataType:"json",
							type:"POST",
							data:{
							typeName:ss,
							},
							success:function(data){
							if(data.res=='1'){
							//压缩成功,将zip压缩包的路径返回
							var zip = data.zip;
							window.location.href="${pageContext.request.contextPath}/Folder_management/"+zip+"";
							}else{
							alert("失败，出现异常");
							}
							}
						});
						}
					},
					error:function(data){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
                		},
					});
					}
					}
		});
		 });
		
		//上传文件
        function upload(){
        	var ss="";
        	var id=0;
            var str="";
            var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true);
            
            	function check(nodes){
            		if(nodes.length>0){
            			var node = nodes[0].getPath();
            			for(var i=0;i<node.length;i++){
            			str+="/"+node[i].name;
            			}
            			id=nodes[0].id;
            			parent=nodes[0].children;
            		}
            		return str;
            		}
           if(nodes.length>0){
            if(nodes[0].isParent!=true){
            	alert("请选择正确位置");
            	window.location.href="${pageContext.request.contextPath}/repository/list.action";
            }else{
            ss=check(nodes);
        	//将路径值赋给上传文件的URL文本框
            document.getElementById("fileUrl").value=ss;
            //alert(id);
            document.getElementById("file_type_id").value=id;
            };
            }	
        }
        	
        	
        	
        	//删除文件或文件夹
            $(document).ready(function(){
            $(".show-delete-form").click(function(){
            	//获得选中的节点
        		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true);
            	//选中节点
            	if(nodes.length>0){
            	//判断是否为文件夹
            	if(nodes[0].isParent){
            	//文件夹、判断文件夹下有无子目录
            	if(nodes[0].children!=null){
            	//文件夹、存在子目录，无法删除
            	alert("存在子目录，无法删除");
            	return false;
            	}else{
            	//文件夹、没有下级子目录,执行删除功能
            	//表名称
            	var tableName="file_type";
            	//主键key名称
            	var keyName="file_type_id";
            	//主键value
            	var keyValue=nodes[0].id;
            	//模块id
            	var classId=0;
            	//描述
            	var intro="";
            	$.ajax({
            	url:"${pageContext.request.contextPath}/repository/deleteFileType.action",
            	dataType:"json",
            	type:"POST",
            	data:{
            	keyName:keyName,
            	keyValue:keyValue,
            	tableName:tableName,
            	classId:classId,
            	intro:intro,
            	},
            	success:function(data){
            		if(data.res=='1'){
            		alert("删除成功");
            		window.location.href="${pageContext.request.contextPath}/repository/list.action";
            		}else if(data.res=='2'){
            		alert("表删除失败");
            		window.location.href="${pageContext.request.contextPath}/repository/list.action";
            		}else if(data.res=='3'){
            		alert("文件删除失败");
            		window.location.href="${pageContext.request.contextPath}/repository/list.action";
            		}else if(data.res=='4'){
            		alert("文件不存在");
            		window.location.href="${pageContext.request.contextPath}/repository/list.action";
            		}
            		window.location.href="${pageContext.request.contextPath}/repository/list.action";
            	}
            	});
            	}
            	//$("#file-form-div").modal("show");
            	}else{
            	//文件，判断有无密码
            		//获得所选文件节点id
            	   var af=nodes[0].fileId;
            	   //文件,判断文件是否加密
            	   $.ajax({
					url:"${pageContext.request.contextPath}/repository/getMD5.action",
					dataType:"json",
					type:"POST",
					data:{
					fileId:af,
					},
					success:function(data){
						if(data.res=='1'){
						//密码存在，弹出输入密码的弹窗
						$("#download").modal("show");
						document.getElementById("deleteFiles").style.display="inline";
						document.getElementById("downLoad").style.display="none";
						}else{
						//密码不存在，直接删除
						//表名称
	            	var tableName="files";
	            	//主键key名称
	            	var keyName="file_id";
	            	//主键value
	            	var keyValue=nodes[0].fileId;
	            	//模块id
	            	var classId=0;
	            	//描述
	            	var intro="";
	            	$.ajax({
	            	url:"${pageContext.request.contextPath}/repository/deleteFileType.action",
	            	dataType:"json",
	            	type:"POST",
	            	data:{
	            	keyName:keyName,
	            	keyValue:keyValue,
	            	tableName:tableName,
	            	classId:classId,
	            	intro:intro,
	            	},
            	success:function(data){
            		if(data.res=='1'){
            		alert("删除成功");
            		}else if(data.res=='2'){
            		alert("表删除失败");
            		}else if(data.res=='3'){
            		alert("文件删除失败");
            		}else if(data.res=='4'){
            		alert("文件不存在");
            		}
            	}
            	});
						}	
					},
					error:function(data){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
                		},
					});
            	}
            	}else{
            		alert("没有要删除的文件");
            	}
		});
           $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });  
            
            
            
            
            
            
            
            //选中单选框后触发事件
            function onCheck(e,treeId,treeNode){
            
            }
            
        //添加文件夹    
        $(document).ready(function(){
        $(".show-add-form").click(function(){
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true);
            	if(nodes.length>0){
            	if(nodes[0].isParent){
            	$("#file-form-div").modal("show");
            	}else{
            	alert("文件不能添加文件夹、请选择正确位置")
            	}
				 }else{
				 $("#file-form-div").modal("show");
				 }
		});
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
        
		//请求添加文件夹(输入完成后点击确定按钮触发)
	 function addAuth1(){
	 		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	var w=0;  
            	if(nodes.length>0){   
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;//文件夹ID
            		w+=nodes[i].typeGrade;//文件夹级别
            	}
            	}
            	function check(nodes){
            	var str="";
            		if(nodes.length>0){
            			var node = nodes[0].getPath();
            			for(var i=0;i<node.length;i++){
            			str+="/"+node[i].typeCode;
            			}
            		}
            		return str;
            	}
            	s=check(nodes);
            	w++;
			var an=$("#authNameInput1").val();//文件名
      		var ac=$("#codeInput1").val();//code
      		var af=v;
      		if(!af){ 
      			af="0";
      		}
      		//判断是否非空
      		if(an==""){
      		alert("文件名不能为空");
      		return false;
      		}
      		else 
      		if(ac==""){
      		alert("文件夹code不能为空");
      		return false;
      		}
      		if(!!an&&!!ac&&!!af){
				$.ajax({
					url:"${pageContext.request.contextPath}/repository/addFolder.action",
					dataType:"json",
					type:"POST",
					data:{
					typeName:an,
					typeCode:ac,
					parentId:af,
					grade:w,
					url:s
					},
					success:function(data){
						if(data.res==1){
						alert("ok");
					   	window.location.href="${pageContext.request.contextPath}/repository/list.action";						
						}else if(data.res=='3'){
						alert("文件夹已存在");
						return;
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
						}else if(data.res=='2'){
						alert("文件夹生成失败");
					   window.location.href="${pageContext.request.contextPath}/repository/list.action";						
						}else{
						alert("添加失败");
					   window.location.href="${pageContext.request.contextPath}/repository/list.action";
						}
					},
					error:function(data){ 
					alert("执行异常");
						window.location.href="${pageContext.request.contextPath}/repository/list.action";
                		},
					});
				}else{ 
				alert("请正确填写!!");
				}
			}
			
			
			
			
  		function browseFolder(path) {
    	try {
        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        //var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
        var Folder = Shell.BrowseForFolder(0, Message, 0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items(); // 返回 FolderItems 对象
            Folder = Folder.item(); // 返回 Folderitem 对象
            Folder = Folder.Path; // 返回路径
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            document.getElementById(path).value = Folder;
            return Folder;
        }
    }
    catch (e) {
        alert(e.message);
    }
}
	//密码校验、进行下载
	function downLoad(){
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true);
            	function check(nodes){
            		if(nodes.length>0){
            			var node = nodes[0].getPath();
            			for(var i=0;i<node.length;i++){
            			str+="/"+node[i].name;
            			}
            			id=nodes[0].id;
            		}
            		return str;
            }
            var ss=check(nodes);
		/* document.getElementById("downloadAs").action="${pageContext.request.contextPath}/repository/getPswd.action"; 
		document.getElementById("downloadAs").submit(); */
		var filePswd=document.getElementById("filePswd").value;
		//document.getElementById("").value;
		$.ajax({
		url:"${pageContext.request.contextPath}/repository/getPswd.action",
		dataType:"json",
		type:"POST",
		data:{
		filePswd:filePswd,
		},
		success:function(data){
		if(data.res=='0'){
		//密码不匹配
		alert("密码错误、请重新输入");
		window.location.href="${pageContext.request.contextPath}/repository/list.action";
		}else{
		//密码正确
		//下载文件
		document.getElementById("downloadA").href="${pageContext.request.contextPath}/Folder_management"+ss;
		$("#subBtn").trigger("click");
		}
		}
		});
	}
	
	//密码校验、进行删除
	function deleteFiles(){
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true);
            var str="";
            	function check(nodes){
            		if(nodes.length>0){
            			var node = nodes[0].getPath();
            			for(var i=0;i<node.length;i++){
            			str+="/"+node[i].name;
            			}
            			id=nodes[0].id;
            		}
            		return str;
            }
            var ss=check(nodes);
		var filePswd=document.getElementById("filePswd").value;
		$.ajax({
		url:"${pageContext.request.contextPath}/repository/getPswd.action",
		dataType:"json",
		type:"POST",
		data:{
		filePswd:filePswd,
		},
		success:function(data){
		if(data.res=='0'){
		//密码不匹配
		alert("密码错误、请重新输入");
		window.location.href="${pageContext.request.contextPath}/repository/list.action";
		}else{
		alert("删除成功");
		//密码正确
		//删除文件
				//表名称
            	var tableName="files";
            	//主键key名称
            	var keyName="file_id";
            	//主键value
            	var keyValue=nodes[0].fileId;
            	//模块id
            	var classId=0;
            	//描述
            	var intro="文件删除";
            	$.ajax({
            	url:"${pageContext.request.contextPath}/repository/deleteFileType.action",
            	dataType:"json",
            	type:"POST",
            	data:{
            	keyName:keyName,
            	keyValue:keyValue,
            	tableName:tableName,
            	classId:classId,
            	intro:intro,
            	},
            	success:function(data){
            		if(data.res=='1'){
            		alert("删除成功");
            		}else{
            		alert("删除失败");
            		}
            	}
            	});
		}
		}
		});
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
				<h1 class="page-header">文件夹列表</h1>
				<div class="row placeholders">
					<!--添加权限表单 start-->
					<div>
						<button type="button" class="btn btn-primary btn btn-default show-add-form"
							data-toggle="modal"    style="width: 100px;">新建文件夹</button>
						<button type="button" class="btn btn-default btn btn-primary show-download-form" style="width:100px;"
							data-toggle="modal">下载</button>
						<!-- <button type="button" style="width:100px;" class="btn btn-primary " data-toggle="modal" data-target="#rein-auth-form-div">恢复文件</button> -->
						
						<button type="button" style="width:100px;" class="btn btn-primary" data-toggle="modal" data-target="#upload-auth-form-div" onclick="upload();">上传</button>	
						<button type="button" style="width:100px;" class="btn btn-primary show-delete-form" >删除</button>
						<a href ="#" id="downloadA" download hidden="hidden"><button class="new-btn-login" id="subBtn" type="button" hidden="hidden"></button></a>
						<!--文件下载密码-->
						<div class="modal fade " id="download" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="role-form-title">请输入密码</h4>
									</div>
									<div class="modal-body">
										<form class="role-form" id="downloadAs">
											<!-- <input type="text" name="authId" class="form-control" id="authIdInput"> -->
											<div class="form-group" >
												<label for="authNameInput2">密码:</label> <input type="text"
													name="filePswd" id="filePswd" class="form-control" id="downloadFile"
													placeholder="文件下载密码">
											</div>
											 <div class="form-group" hidden="hidden">
												<tr>
												    <td>请选择要下载的地址：</td>
												    <td><input id="path" type="text" name="path" size="30"></td>
												    <td><input type=button value="选择" onclick="browseFolder('path');"></td>
												  </tr>
											</div> 
										</form>
										<div class="modal-footer">
												<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
												<button type="button" class="btn btn-primary"
											id="downLoad" onclick="downLoad();" style="display:none">下载</button>
												<button type="button" class="btn btn-primary"
												id="deleteFiles" onclick="deleteFiles();" style="display:none" >删除</button>
										</div>
									</div>
									
								</div>
							</div>
						</div>
						<!--上传文件-->
						<div class="modal fade" id="upload-auth-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="role-form-title"></h4>
									</div>
									<div class="modal-body">
										<form action="${pageContext.request.contextPath}/repository/addFiles.action" method="post" class="files-form" enctype="multipart/form-data">
											<div class="form-group" id="files">
												<label for="codeInput2">上传文件</label> 
												<input type="file"  maxlength="28" name="file" class="form-control" id="file" 
													 onkeyup="value=value.match(/^\w{1,28}$/)">
											</div>
											 <!-- <div class="form-group">
           										 <label for="authNameInput2">名称</label> <input type="text"
             										name="fileName" class="form-control" id="authNameInput2"
            										 placeholder="文件名称">
           									</div> -->
								           <div class="form-group">
								            <label for="descInput2">文件描述</label> <textarea rows="3" cols="9"
								             name="fileDesc" class="form-control" id="fileDesc" ></textarea>
								           </div>
								           <div class="form-group" id="authurl2" style="display: none">
								            <label for="urlInput2">文件url</label> <input type="text" maxlength="50"
								             name="fileUrl" class="form-control" readonly="readonly" id="fileUrl" onkeyup="value=value.match(/^\w{1,20}$/)"
								             placeholder="文件url">
								           </div>
								           <div class="form-group" id="authcode2">
								            <label for="codeInput2">文件密码</label> <input type="text" maxlength="28"
								             name="filePswd" class="form-control" id="filePswd" 
								             placeholder="文件密码">
								           </div> 
								           <div class="form-group" id="filehids" style="display: none">
								            <input type="text" maxlength="28"
								             name="file_type_id"value=0 id="file_type_id">
								           </div>
											<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="submit" class="btn btn-primary"
											id="files_submit"">提交</button>
											</div>
										</form>
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
				
				<!--新建文件夹-->
						<div class="modal fade " id="file-form-div" tabindex="-1"
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
													name="typeName" class="form-control" id="authNameInput1" maxlength="20"
													placeholder="文件名称">
											</div>
											<div class="form-group" id="authcode">
												<label for="codeInput1">文件夹code</label> <input type="text" maxlength="28"
													name="typeCode" class="form-control" id="codeInput1"  
													placeholder="文件夹code">
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary"
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
