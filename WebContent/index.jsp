<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/login.scaction" method="post">
		<div>
				用户名&nbsp;&nbsp;
				<input type="text" name="name" />
			</div>
			<div style="margin-top: 10px;">
				&nbsp;&nbsp;密码&nbsp;&nbsp;
				<input type="password" name="password" />
			</div>
			<div style="margin-top: 10px;">
				<input type="submit" value="登陆" />
			</div>
	</form>
</body>
</html>