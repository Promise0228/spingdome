<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="application/msexcel" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- 
Word只需要把contentType="application/msexcel"改为contentType="application/msword" 
--%>
<%   
  //独立打开excel软件   
  response.setHeader("Content-disposition","attachment; filename=MyExcel.xls");   
  
//嵌套在ie里打开excel   
  
response.setHeader("Content-disposition","inline; filename=MyExcel.xls");   
  
//response.setHeader("Content-disposition","inline; filename=wwww.doc");   
%>  
<html>  
<head>  
<title>测试导出Excel和Word</title>  
</head>  
<body>  
	<table width="500" border="1" align="center">
					<tr>
						<td>ID</td>
						<td>用户名</td>
						<td>昵称</td>
						<td>用户状态</td>
						<td>创建人</td>
						<td>创建时间</td>	
					</tr>
				
					<c:forEach items="${page.resultList }" var="user">
						<tr>
							<td class="userid">${user.userId }</td>
							<td class="username">${user.userName }</td>
							<td class="nickName">${user.nickName }</td>						
							<td class="userState">${user.userState}</td>
							<td class="createBy">${user.createBy }</td>
							<td class="createTime"><fmt:formatDate value="${user.createTime }" type="date" dateStyle="medium"/></td>							
						</tr>
						</c:forEach>
				</table>
</body>  
</html>  
