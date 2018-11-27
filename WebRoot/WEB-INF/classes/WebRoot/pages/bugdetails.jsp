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
  <jsp:include page="header.jsp" />
  <div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
		</div>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">BUG详情</h1>
			<div class="row placeholders">
				<div>	
   <div style="width:100%; ">
   <table border="2px" cellpadding: "5px"; cellspacing="5px" style="width: 100%;">
					<tr height="50px">
					    <td>BUGID</td>
						<td>BUG编号</td>
						<td>BUG名称</td>
						<td>BUG状态</td>
						<td>BUG级别</td>
						<td>所属模块</td>
						<td>所属项目</td>
						<td>创建时间</td>
						<td>执行人</td>
					</tr>
                    </td>
                </tr>
					<c:forEach items="${bugdetails }" var="bug">
						<tr height="50px";>
						    <td class="bugId">${bug.bugId }</td>
							<td class="bugnum">${bug.bugNum }</td>
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
							<c:if test="${bug.bugState eq 0}">
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
						</tr>
					</c:forEach>
					
					
				</table>
   </div>
   <div style="display: inline-block; width:49%; position: absolute; top: 200px; ;">
   <c:forEach items="${img}" var="img">
   <img src="${pageContext.request.contextPath}/${img}" width="450px" height="300px"><br>
   </c:forEach>
   </div>
    <div style="display: inline-block;width:49%; position: absolute; top: 180px; left: 500px;">
         <h3>留言板：</h3>
   <textarea rows="5" cols="25" style="width: 100%;top: 20px;" id="replyContent">
   </textarea></br>
   <button onclick="leave()">留言</button></br>
   <c:forEach items="${listReply}" var="listReply">
   <tr>  
         <td> ${listReply.createBys}:</td>
         <td> ${listReply.replyContent}
         <a href="${pageContext.request.contextPath}/bug/delReply.action?replyId=${listReply.replyId}&bugId=${listReply.bugId}">
         <span style="position: absolute;right:0px;">删除</span></a>
         </td>
  </br>
         <td> ${listReply.createTime}</td>
         </br>
   </tr>
   </c:forEach>
   </div>
   
   
   
   
   
   
   
   
   </div>
   </div>
  </div> 
  <script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
  <script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
		
<script type="text/javascript">
    function leave (){
     var replyContent =$("#replyContent").val();
     var bugId=$(".bugId").html();
     $.ajax({
     url:"${pageContext.request.contextPath}/bug/leave.action",
     data:{
     bugId:bugId,
     replyContent:replyContent
     },
     type : "POST",
     dataType:"json",
	success : function(data) {
		if(data.leave=='1'){
		location.reload();
		}else{
		alert("网络繁忙");
		location.reload();
		}						
	 }
     });
    };
    function delleave(){
    var userTr = $(this).parents("tr");
	var replyId = userTr.find(".del").html();
     alert(replyId);
     /* $.ajax({
     url:"${pageContext.request.contextPath}/bug/delReply.action",
     data:{
     replyId:replyId
     },
     type : "POST",
	success : function(data) {
		if(data.leave==1){
		location.reload();
		}else{
		alert("网络繁忙");
		location.reload();
		}						
	 }
     }); */
    };
    
</script>
  </body>
  
</html>

