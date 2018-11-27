<%@ page  pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="application/msexcel" %> 

<%   
  //独立打开excel软件   
  //response.setHeader("Content-disposition","attachment; filename=MyExcel.xls");   
  
//嵌套在ie里打开excel   
  
response.setHeader("Content-disposition","inline; filename=MyExcel.xls");   
  
//response.setHeader("Content-disposition","inline; filename=MyExcel.doc");   
%>  
<html>  
<head>  
<title>测试导出Excel和Word</title>  
</head>  
<body>  
<table width="500" border="1" align="center">  
            	<tr>
 					<td>ID</td>
					<td>url</td>
					<td>内容</td>
					<td>异常信息</td>
					<td>创建时间</td>
					<td>操作人</td>
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
  
  
