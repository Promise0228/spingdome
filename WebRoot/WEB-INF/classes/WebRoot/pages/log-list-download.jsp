<%@ page  pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="application/msexcel" %> 

<%   
  //������excel���   
  //response.setHeader("Content-disposition","attachment; filename=MyExcel.xls");   
  
//Ƕ����ie���excel   
  
response.setHeader("Content-disposition","inline; filename=MyExcel.xls");   
  
//response.setHeader("Content-disposition","inline; filename=MyExcel.doc");   
%>  
<html>  
<head>  
<title>���Ե���Excel��Word</title>  
</head>  
<body>  
<table width="500" border="1" align="center">  
            	<tr>
 					<td>ID</td>
					<td>url</td>
					<td>����</td>
					<td>�쳣��Ϣ</td>
					<td>����ʱ��</td>
					<td>������</td>
                </tr>
                <c:forEach items="${page.resultList}" var="logInfo">
                	<tr>
                	<td class="logid">${logInfo.logId }</td>
						<td class="ipaddress">${logInfo.url }</td>
						<td class="loginfo">${logInfo.logInfo }</td>
						<td class="exception">${logInfo.exception }</td>
						<td class="createtime"><fmt:formatDate
								value="${logInfo.createTime}" pattern='yyy-MM-dd HH:mm:ss' /></td>
						<td class="username">${userName}</td>
                	</tr>
                </c:forEach>
</table>  
</body>  
</html>  
  
  
