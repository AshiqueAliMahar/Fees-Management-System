<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	email = session.getAttribute("email");
	password = session.getAttribute("password");

	if (email != null && password != null) {
		usersBean = UsersBAL.getUser(email.toString(), password.toString());
		if (usersBean != null) {
%>
<p class="h1 text-center text-muted"
	style="border-bottom: 5px solid brown">My Profile</p>
<div class="m-auto w-50"
	style="border: 5px solid blue; border-radius: 10px; padding: 5px;">
	<form action="MyProfileServ" method="post"
		enctype="multipart/form-data">
		<%
			if (usersBean.getPic() != null) {
		%>

		<img src="data:image/gif;base64,<%=new String(Base64.getEncoder().encode(usersBean.getPic()))%>"
			class="img-fluid rounded mb-3" id="img">
		<%
			}
		%>

		<input type="file" onchange="readURL(this)"
			accept="image/png, image/jpeg"
			class="btn btn-outline-success form-control-file mb-2" name="image"> <input
			type="text" placeholder="name" name="name" class="form-control mb-2"
			value="<%=usersBean.getName()%>"> <input type="text"
			placeholder="Email" name="email" class="form-control mb-2"
			value="<%=usersBean.getEmail()%>" readonly="readonly"> <input type="password"
			placeholder="Password" class="form-control mb-2" id="myInput"
			value="<%=usersBean.getPassword()%>" name="password"> <input type="checkbox"
			onclick="myFunction()"
			class="form-control mb-2 form-check form-check-input ml-1"><span
			class="form-check-label ml-5">Show Password</span>
			<button type="submit" class="btn btn-block btn-secondary mt-2">Save</button>
	</form>
</div>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img').attr('src', e.target.result);
				/* document.getElementByClassName('blah').setAttribute('src',e.target.result); */
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
	function myFunction() {
		var x = document.getElementById("myInput");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}
</script>
<%
	} else {
			response.sendRedirect("LogIn.jsp");
		}
	} else {
		response.sendRedirect("LogIn.jsp");
	}
%>