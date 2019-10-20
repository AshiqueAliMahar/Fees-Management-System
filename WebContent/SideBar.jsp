<%@page import="java.util.Base64"%>
<%@page import="bAL.UsersBAL"%>
<%@page import="bean.UsersBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Simple Sidebar - Start Bootstrap Template</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/simple-sidebar.css" rel="stylesheet">

<style>
.a {
	background-color: black;
}
</style>

</head>

<body>
	<%
		Object email = session.getAttribute("email");
		Object password = session.getAttribute("password");

		if (email != null && password != null) {
			UsersBean usersBean = UsersBAL.getUser(email.toString(), password.toString());
			if (usersBean != null) {
	%>

	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav list-group">
				<li class="list-group-item a">
					<h2 style="font-family: 'Times New Roman'; color: white">IT-SQUARE</h2>
				</li>
				<%
					if (usersBean.getPic() != null) {
				%>
				<li class="list-group-item a"><img
					src="data:image/gif;base64,<%=new String(Base64.getEncoder().encode(usersBean.getPic()))%>"
					class="img-fluid rounded-circle mx-auto d-block"
					style="width: 100px; height: 100px"></li>
				<%
					}
				%>

				<div class="btn-group btn-group-vertical"
					style="border-radius: 10px; border: 1px solid white">
					<form method="post" action="SideBar.jsp">
						<button class="btn btn-outline-danger btn-lg" type="submit"
							name="sideBarBtn" value="dashboard">DashBoard</button>
						<button class="btn btn-outline-danger btn-lg " type="submit"
							name="sideBarBtn" value="students" id="sideBarBtn">Students</button>
						<button class="btn btn-outline-danger btn-lg" type="submit"
							name="sideBarBtn" value="batch">Batch</button>
						<button class="btn btn-outline-danger btn-lg" type="submit"
							name="sideBarBtn" value="fees">Fees</button>
						<button class="btn btn-outline-danger btn-lg" type="submit"
							name="sideBarBtn" value="report">Report</button>
						<button class="btn btn-outline-danger btn-lg" type="submit"
							name="sideBarBtn" value="setting">Setting</button>
					</form>
					<form action="SideBarServ" method="post">
						<button class="btn btn-outline-danger btn-lg btn-block"
							type="submit" name="sideBarBtn" value="logout">Logout</button>
					</form>
				</div>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<%
					String btn = request.getParameter("sideBarBtn");
							if (btn != null) {
								if (btn.equals("students")) {
				%>
				<%@include file="Students.jsp"%>
				<%
								} else if (btn.equals("dashboard")) {
				%>
				<%@include file="Dashboard.jsp"%>
				<%
								}else if (btn.equals("batch")) {
				%>
				<%@include file="Batches.jsp"%>
				<%
								}else if (btn.equals("fees")) {
				%>
				<%@include file="Fees.jsp"%>
				<%
								}else if (btn.equals("report")) {
				%>
				<%@include file="Report.jsp"%>
				<%
								}else if (btn.equals("setting")) {
				%>
				<%@include file="MyProfile.jsp"%>
				<%
								}
							} else {
				%>
				<%@include file="Dashboard.jsp"%>
				<%
							}
				%>
			</div>
		</div>

	</div>

	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>

	<script>
		$("#wrapper").toggleClass("toggled");
	</script>
	<%}else if(usersBean==null) {
				response.sendRedirect("LogIn.jsp");
	  }
	}else{
		response.sendRedirect("LogIn.jsp");
	}
	%>
</body>

</html>
