<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
	<div style="float: left">
		<h1>Simple Web App</h1>
	</div>
	
	<div style="float: center">
		<a href="${pageContext.request.contextPath}/">Home</a> | <a
		href="${pageContext.request.contextPath}/productList">Product List</a>
	| <a href="${pageContext.request.contextPath}/userInfo">My Account
		Info</a> | <a href="${pageContext.request.contextPath}/login">Login</a>
	</div>

	<div style="float: right; padding: 0px; text-align: right;">

		<!-- User store in session with attribute: loginedUser -->
		Hello <b>${loginedUser.userName}</b> <br /> Search <input
			name="search">

	</div>

</div>