<%@page import="bean.BatchBean"%>
<%@page import="java.util.List"%>
<%@page import="bAL.BatchBAL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form action="BatchesServ" method="post">
	<p class="h2 text-center text-muted"
		style="border-bottom: 5px solid brown">Batches</p>
	<button type="button" class="btn btn-outline-success"
		data-toggle="modal" data-target="#exampleModalCenter">
		<img src="open-iconic/svg/plus.svg" width="30px">
	</button>
	<br>
	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Add New
						Batch</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p class="h6">Name</p>
					<input class="form-control" name="bName">
					<p class="h6">Duration(In Months)</p>
					<input class="form-control" name="bDuration" type="number">
					<p class="h6">Fees</p>
					<input class="form-control" name="bFees" type="number">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary" name="add">Save</button>
				</div>
			</div>
		</div>
	</div>
	<hr style="border-bottom: 5px dashed brown">


	<table
		class="table table-responsive table-striped mt-2 table-hover table-dark table-bordered text-center"
		style="border-radius: 30px">
		<thead class="h3">
			<tr>
				<th>Sr.No#</th>
				<th>Batch Name</th>
				<th>Duration(In Months)</th>
				<th>Fees</th>
				<th>Manipulation</th>
			</tr>
		</thead>
		<%
			List<BatchBean> list = BatchBAL.getBatches();
			for (BatchBean batchBean : list) {
		%>
		<tr>
			<td><%=batchBean.getbId()%></td>
			<td><%=batchBean.getBatchName()%></td>
			<td><%=batchBean.getDurationInMnths()%></td>
			<td><%=batchBean.getFees()%></td>
			<td class="btn-group">
				<button class="btn btn-success" type="button" data-toggle="modal"
					data-target="#<%=batchBean.getbId()%>"
					value="<%=batchBean.getbId()%>">Edit</button>
				<button class="btn btn-danger" type="submit" name="delete"
					value="<%=batchBean.getbId()%>">Delete</button>


			</td>
		</tr>
		<!-- Modal -->
		<div class="modal fade" id="<%=batchBean.getbId()%>" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Update
							Batch</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p class="h6">Name</p>
						<input class="form-control" name="bName<%=batchBean.getbId()%>"
							value="<%=batchBean.getBatchName()%>">
						<p class="h6">Duration(In Months)</p>
						<input class="form-control" name="bDuration<%=batchBean.getbId()%>" type="number"
							value="<%=batchBean.getDurationInMnths()%>">
						<p class="h6">Fees</p>
						<input class="form-control" name="bFees<%=batchBean.getbId()%>" type="number"
							value="<%=batchBean.getFees()%>">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary" name="update"
							value="<%=batchBean.getbId()%>">Update</button>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>
	</table>
</form>