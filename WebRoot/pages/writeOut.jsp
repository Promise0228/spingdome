<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="application/msexcel" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- 
Wordֻ��Ҫ��contentType="application/msexcel"��ΪcontentType="application/msword" 
--%>
<%   
  //������excel���   
  response.setHeader("Content-disposition","attachment; filename=MyExcel.xls");   
  
//Ƕ����ie���excel   
  
response.setHeader("Content-disposition","inline; filename=MyExcel.xls");   
  
//response.setHeader("Content-disposition","inline; filename=wwww.doc");   
%>  
<html>  
<head>  
<title>���Ե���Excel��Word</title>  
</head>  
<body>  
	<table width="500" border="1" align="center">
					<tr>
						<td>ID</td>
						<td>�û���</td>
						<td>�ǳ�</td>
						<td>�û�״̬</td>
						<td>������</td>
						<td>����ʱ��</td>	
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
