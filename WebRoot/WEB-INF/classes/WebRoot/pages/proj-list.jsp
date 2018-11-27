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

<title>项目管理-项目列表</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link 
	href="${pageContext.request.contextPath}/static/css/layout.css"
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
			<h1 class="page-header">项目列表</h1>
			<div class="row placeholders">
				<div>
				<form action="${pageContext.request.contextPath}/proj/proj-list.action">
					<input type="text" name="projName" value="${param.projName}" maxlength="16" placeholder="项目名称"> 
					&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					<select name="projState"  id="projState" style="height: 29px; width: 100px">
					<option value="">项目状态</option>  
					<option value="0" ${param.projState eq '0'?'selected':'' }>未开始</option>  
					<option value="1" ${param.projState eq '1'?'selected':'' }>进行中</option>
					<option value="2" ${param.projState eq '2'?'selected':'' }>已暂停</option>
					<option value="3" ${param.projState eq '3'?'selected':'' }>已完成</option>    
					<option value="4" ${param.projState eq '4'?'selected':'' }>已关闭</option>
					</select>
						&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="date" name="createTime" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit"  id="selectProj" class="btn btn-warning">查询</button>
					</form>
				</div>
				<br />
				
				
				<div>
				<c:if test="${fn:contains(authcodes,'add-proj')}">
				<button type="button" class="btn btn-warning" id="createProj"
         			data-toggle="modal" data-target="#add-proj"
         		>创建项目</button></c:if>
         		
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list">
					<tr>
						<td><input type="checkbox" class="select-all-btn" /></td>
						<td>项目ID</td>
						<td>项目名</td>
						<td>项目编码</td>
						<td>项目状态</td>
						<td>创建时间</td>
						<td>负责人</td>	
						<td>操作</td>
					</tr>
					<c:forEach items="${page.resultList }" var="proj">
						<tr>
							<td><input type="checkbox" name="projIds" value="${proj.projId }" /></td>
							<td class="projId">${proj.projId }</td>
							<td class="projName">${proj.projName }</td>
							<td class="projNum">${proj.projNum }</td>
							<td class="projState">
								${proj.projState==0?"未开始":""}
								${proj.projState==1?"进行中":""}
								${proj.projState==2?"已暂停":""}
								${proj.projState==3?"已完成":""}
								${proj.projState==4?"已关闭":""}
							</td>						
							<td class="createTime">
								${proj.createTime}
							</td>
							<td class="projChief">
								${proj.projChief }	
							</td>						
							<td>
	                    	<c:if test="${fn:contains(authcodes,'update-proj')}">
	                    	<a class="glyphicon glyphicon-wrench a-update-proj"
								aria-hidden="true" title="修改项目" href="javascript:void(0);"
								data-toggle="modal" data-target="#update-proj-dialog">
							</a>
							</c:if>
	                    	<input type="button" class="btn btn-primary proj-detail"  value="查看详情"/>
							<c:if test="${proj.vistorType=='2'}"></c:if>
							<c:if test="${fn:contains(authcodes,'add-white')}">
							<c:if test="${(UserInfo.userId eq proj.projChief) or roleId=='1'}">
							<input type="button" class="btn btn-primary white-sheet"  value="添加白名单"/>
							</c:if>
							</c:if>
							
							
							<c:if test="${proj.projState==3}">
							<c:if test="${fn:contains(authcodes,'version_information')}">
							<a href="${pageContext.request.contextPath}/projlist.action?projId=${proj.projId }"><button class="btn btn-primary">版本信息</button></a>
							</c:if>
							</c:if>
							<input type="button" class="btn btn-primary dynamic"  value="动态"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="standard.jsp" />
	<!-- 添加新项目 -->			
	<div class="modal fade " id="add-proj" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >添加新项目</h4>
                      </div>
                      <div class="modal-body">
                      	<form class="proj-form">
                          <div class="form-group">
                            <label for="addProjName">项目名</label>
                            <input type="text" name="addProjName" class="form-control" maxlength="10" id="addProjName" placeholder="项目名">
                			<label id="error_html" style="font-size:18px;color:red"></label>
                            <label for="addProjNum">项目编码</label>
                            <input type="text" name="addProjNum" maxlength="10" class="form-control" id="addProjNum" placeholder="项目编码">
                            <label for="addVistorType">访问类型</label>
                            <select id="addVistorType">
                            	<option value="0">公开</option>
                            	<option value="1">项目组</option>
                            	<option value="2">白名单</option>
                            </select><br/>
                            <label for="addStartTime">开始时间</label>
                            <input type="date" id="addStartTime" name="addStartTime" maxlength="10" class="form-control" placeholder="开始时间">
                            <label for="addEndTime">结束时间</label>
                            <input type="date" id="addEndTime" name="addEndTime" maxlength="10" class="form-control" placeholder="结束时间">
                            <label for="addAbleDay">工时</label>
                            <input type="number" id="addAbleDay" name="addAbleDay" maxlength="10" class="form-control" placeholder="小时"/>
                            <label for="addProjChief">项目负责人</label>
                            <div id="addProjChief"></div>
                            </div>
                         </form>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary add-proj">添加 </button>
                      </div>
                    </div>
                  </div>
                </div> 
            </div>
         </div>
    <!-- 项目修改 -->
    <div class="modal fade " id="update-proj-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >修改项目</h4>
                      </div>
                      <div class="modal-body">
                      	<form class="proj-form">
                          <div class="form-group">
                   			<input type="hidden" id="updateProjId"/>
                            <label for="updateProjName">项目名</label>
                            <input type="text" readonly="readonly" class="form-control" maxlength="10" id="updateProjName" />
                            <label for="updateVistorType">访问类型</label>
                            <select id="updateVistorType">
                            	<option value="0">公开</option>
                            	<option value="1">项目组</option>
                            	<option value="2">白名单</option>
                            </select><br/>
                            <label for="updateStartTime">开始时间</label>
                            <input type="date" id="updateStartTime" name="updateStartTime" maxlength="10" class="form-control"/>
                            <label for="updateEndTime">结束时间</label>
                            <input type="date" id="updateEndTime" name="updateEndTime" maxlength="10" class="form-control"/>
                            <label for="updateAbleDay">工时</label>
                            <input type="number" id="updateAbleDay" name="updateAbleDay" maxlength="10" class="form-control" placeholder="小时"/>
                            <label for="remark">备注</label><br/>
                            <textarea style="width:100%" id="remark"></textarea>
                            </div>
                         </form>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary update-proj">确认 </button>
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
	//对添加项目框加入项目经理
	$("#createProj").click(function(){
		$.ajax({
				url:"${pageContext.request.contextPath}/proj/findProjManagers.action",
    			type:"POST",
    			dataType:"json",
    			success:function(data){
    				var userNames= (data.userName).split(",");
    				var userIds = (data.userId).split(",");
    				$("#addProjChief").html("");
    				//length减一是为了去掉最后的""
    				for(var i = 0;i<userNames.length-1;i++){
    					$("#addProjChief").append("<input type='radio' name='projManagers' value='"+userIds[i]+"'/>"+userNames[i]);
    				}
			}
		})
	})
	//添加项目
	$(".add-proj").click(function(){
		//项目名
		var projName = $("#addProjName").val();
		//项目编码
		var projNum = $("#addProjNum").val();
		//项目访问类型
		var vistorType = $("#addVistorType").val();
		//项目开始时间
		var startTime = $("#addStartTime").val();
		//项目结束时间
		var endTime = $("#addEndTime").val();7
		//项目工时
		var ableDay = $("#addAbleDay").val();
		//项目经理
		var projChief = $("input[name='projManagers']:checked").val();
		if(!!projName&&!!projNum&&!!vistorType&&!!startTime&&!!endTime&&!!ableDay&&!!projChief){
			//项目名及项目编码查重标志
			var check=false;
			$.ajax({
				url:"${pageContext.request.contextPath}/proj/checkProj.action",
    			type:"POST",
    			data:{projName:projName,
 					  projNum:projNum   			
    			},
    			dataType:"json",
    			success:function(data){	
					if(data.res==false){
						alert("项目名或项目编码重复!");
					}else{
						//无重复,添加项目
						$.ajax({
						url:"${pageContext.request.contextPath}/proj/addProj.action",
    					type:"POST",
    					data:{projName:projName,
 							  projNum:projNum,
 						 	  vistorType:vistorType,
 						  	  startTime:startTime,
 						  	  endTime:endTime,
 						  	  ableDay:ableDay,
 						  	  projChief:projChief   			
    						 },
    						 dataType:"json",
    						 success:function(data){	
							 if(data.res=="ok"){
									alert("添加成功！");
									$("#add-proj").modal("hide");
									location.href="${pageContext.request.contextPath}${page.url}?${page.params}";
							 	}
						     }	
						})
					}	
				}	
			})
		}else{
			alert("请填写完整!");
		}
	})
	//查看详情
	$(".proj-detail").click(function(){
		var projId = $(this).parents("tr").find(".projId").html();
		location.href="${pageContext.request.contextPath}/proj/projDetail.action?projId="+projId;
	})
	//跳转到白名单页面
	$(".white-sheet").click(function(){
		var projId = $(this).parents("tr").find(".projId").html();
		location.href="${pageContext.request.contextPath}/proj/whiteSheet.action?userName=&projId="+projId;
	})
	//修改项目
	$(".a-update-proj").click(function(){
		var projId = $(this).parents("tr").find(".projId").html();
		$.ajax({
			url:"${pageContext.request.contextPath}/proj/updateProj.action",
    		type:"POST",
    		data:{projId:projId
    		},
    		dataType:"json",
    		success:function(data){
    			$("#updateProjId").val(data.projId);			
    			$("#updateProjName").val(data.projName);
    			$("#updateVistorType").val(data.vistorType);
    			$("#updateEndTime").val(data.endTime);
    			$("#updateStartTime").val(data.startTime);
    			$("#updateAbleDay").val(data.ableDay);
    			$("#remark").val(data.remark);
    		}
		})
	})
	
	//提交修改
	$(".update-proj").click(function(){
		
		var projId=$("#updateProjId").val();			
    	var projName=$("#updateProjName").val();
    	var vistorType=$("#updateVistorType").val();
    	var endTime=$("#updateEndTime").val();
    	var startTime=$("#updateStartTime").val();
    	var ableDay=$("#updateAbleDay").val();
    	var remark=$("#remark").val();
		if(!!vistorType&&!!endTime&&!!startTime&&ableDay&&remark){
		$.ajax({
			url:"${pageContext.request.contextPath}/proj/confirmUpdateProj.action",
    		type:"POST",
    		data:{projId:projId,
    			  projName:projName,
    			  vistorType:vistorType,
    			  endTime:endTime,
    			  startTime:startTime,
    			  ableDay:ableDay,
    			  remark:remark
    		},
    		dataType:"json",
    		success:function(data){
    			alert("修改成功!");
    			$("#update-proj-dialog").modal("hide");
    		}
		})
		}else{
			alert("请填写完整");
		}
	})
	
	$(".dynamic").click(function(){
		var projId = $(this).parents("tr").find(".projId").html();
		location.href="${pageContext.request.contextPath}/proj/dynamicList.action?projId="+projId;
	})
	</script>
</body>
</html>
