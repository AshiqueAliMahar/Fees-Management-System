<%@page import="bAL.BatchBAL"%>
<%@page import="bean.BatchBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64"%>
<%@page import="bean.StudentsBean"%>
<%@page import="bAL.StudentsBAL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<BatchBean> batchList = BatchBAL.getBatches();
	List<StudentsBean> list = StudentsBAL.getStudents();
%>
<button class="btn btn-info" data-toggle="modal"
	data-target="#exampleModalCenter">
	<img src="open-iconic/svg/plus.svg" alt="icon name" width="15px">Add
</button>
<br>
<form action="StudentServ" method="post" enctype="multipart/form-data">
	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Add New
						Student</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<img id="blah" src="#" alt="Add Image"
						class="img-fluid rounded blah" /> <input type='file'
						onchange="readURL(this);"
						class="btn btn-success form-control-file" name="aimage" />

					<p class="h6">Name</p>
					<input class="form-control" name="aname">
					<p class="h6">Father Name</p>
					<input class="form-control" name="afName">
					<p class="h6">Email</p>
					<input class="form-control" name="aemail">
					<p class="h6">Address</p>
					<input class="form-control" name="aaddress">
					<p class="h6">Date Of Birth</p>
					<input class="form-control" type="date" placeholder="yyyy-mm-dd"
						name="adob">
					<p class="h6">Batch</p>
					<select class="custom-select" name="abatch">
						<%
							for (BatchBean batchBean : batchList) {
						%>
								<option value="<%=batchBean.getbId()%>"><%=batchBean.getBatchName()%></option>
								
						<%
							}
						%>
					</select>
					<%
							for (BatchBean batchBean : batchList) {
					%>
						<input type="hidden" name="duration<%=batchBean.getbId()%>" value="<%=batchBean.getDurationInMnths() %>">
					<%
							}
					%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary" name="add">Save</button>
				</div>
			</div>
		</div>
	</div>
	<%
		for (StudentsBean studentsBean : list) {
	%>
	<div class="card float-left" style="width: 18rem; margin: 5px">
		<%
			if (studentsBean.getPic() != null) {
		%>
		<img class="card-img-top img-fluid rounded"
			src="data:image/gif;base64,<%=new String(Base64.getEncoder().encode(studentsBean.getPic()))%>"
			alt="Card image cap">
		<%
			}
		%>
		<div class="card-body">
			<h5 class="card-title text-center"><%=studentsBean.getName()%></h5>
		</div>
		<ul class="list-group list-group-flush">

			<li class="list-group-item"><b>Father Name:</b><%=studentsBean.getFathersName()%></li>
			<li class="list-group-item"><b>Email:</b><%=studentsBean.getEmail()%></li>
			<li class="list-group-item"><b>Address:</b><%=studentsBean.getAddress()%></li>
			<li class="list-group-item"><b>Batch:</b><%=studentsBean.getBatchName()%></li>
			<li class="list-group-item"><b>Date of Birth:</b><%=studentsBean.getDob()%></li>
			<li>

				<div class="btn btn-group" style="width: 100%">
					<button class="btn btn-outline-danger btn-block" name="delete"
						value="<%=studentsBean.getId()%>" type="submit">
						<img src="open-iconic/svg/delete.svg" width="15px">
					</button>

					<button class="btn btn-outline-success" type="button"
						data-toggle="modal" data-target="#<%=studentsBean.getId()%>">
						<img src="open-iconic/svg/cog.svg" width="15px">
					</button>
					<!-- Modal -->
					<div class="modal fade" id="<%=studentsBean.getId()%>"
						tabindex="-1" role="dialog"
						aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">Edit</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">

									<%-- <img id="blah" src="<%= %>" alt="your image" class="img-fluid rounded" /> --%>
									<%
										if (studentsBean.getPic() != null) {
									%>
									<img class="img-fluid rounded blah"
										src="data:image/gif;base64,<%=new String(Base64.getEncoder().encode(studentsBean.getPic()))%>"
										id='<%=studentsBean.getId()%>'>
									<%
										}
									%>
									<br> <input type='file'
										name="image<%=studentsBean.getId()%>"
										onchange="readURL(this);"
										class="btn btn-success form-control-file" />
									<p class="h6">Name</p>
									<input class="form-control" value="<%=studentsBean.getName()%>"
										name="name<%=studentsBean.getId()%>">
									<p class="h6">Father Name</p>
									<input class="form-control"
										value="<%=studentsBean.getFathersName()%>"
										name="fName<%=studentsBean.getId()%>">
									<p class="h6">Email</p>
									<input class="form-control"
										value="<%=studentsBean.getEmail()%>"
										name="email<%=studentsBean.getId()%>">
									<p class="h6">Address</p>
									<input class="form-control"
										value="<%=studentsBean.getAddress()%>"
										name="address<%=studentsBean.getId()%>">
									<p class="h6">Date Of Birth</p>
									<input class="form-control" type="date"
										placeholder="yyyy-mm-dd" value="<%=studentsBean.getDob()%>"
										name="dob<%=studentsBean.getId()%>">
									<p class="h6">Batch</p>
									<select class="custom-select"
										name="batch<%=studentsBean.getId()%>">
										<%
											for (BatchBean batchBean : batchList) {
										%>
										<option value="<%=batchBean.getbId()%>"><%=batchBean.getBatchName()%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>

									<button type="submit" class="btn btn-primary" name="update"
										value="<%=studentsBean.getId()%>">Save changes</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<%
		}
	%>
	<script>
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
	
				reader.onload = function(e) {
					$('.blah').attr('src', e.target.result);
					/* document.getElementByClassName('blah').setAttribute('src',e.target.result); */
				};
	
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
</form>
