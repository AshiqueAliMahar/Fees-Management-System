<%@page import="bean.FeesBean"%>
<%@page import="java.util.List"%>
<%@page import="bAL.FeesBAL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="post" action="FeesServ">
	<p class="h1 text-center text-muted"
		style="border-bottom: 5px solid brown">Fees</p>
	<table
		class="table table-responsive table-striped mt-2 table-hover table-dark table-bordered text-center"
		style="border-radius: 30px">
		<thead class="h4">
			<tr>
				<th>Name</th>
				<th>Batch</th>
				<th>Paid Fees</th>
				<th>Total Fees</th>
				<th>Course Starting Date</th>
				<th>Course Ending Date</th>
				<th>Actions</th>
			</tr>
		</thead>

		<%
			List<FeesBean> fList = FeesBAL.getStdsWithFes();
			for (FeesBean feesBean : fList) {
		%>
		<tr>
			<td><%=feesBean.getName()%></td>
			<td><%=feesBean.getBatchName()%></td>
			<td><%=feesBean.getPaidFees()%></td>
			<td><%=feesBean.getFees()%></td>
			<td><%=feesBean.getStartDate()%></td>
			<td><%=feesBean.getEndDate()%></td>
			<td><%=feesBean.getStatus()%></td>
			<td><button class="btn btn-success btn-block" type="button"
					data-toggle="modal" data-target="#<%=feesBean.getId()%>">Take
					Fees</button></td>
			<!-- Modal -->
			<div class="modal fade" id="<%=feesBean.getId()%>" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalCenterTitle"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLongTitle">Take Fees</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p class="h5">Name</p>
							<input class="form-control" readonly
								value="<%=feesBean.getName()%>">
							<p class="h5">Batch</p>
							<input class="form-control" readonly
								value="<%=feesBean.getBatchName()%>">
							<p class="h5">Paid Fees</p>
							<input class="form-control" type="number" readonly
								value="<%=feesBean.getPaidFees()%>" name="paidFees<%=feesBean.getId()%>">
							<p class="h5">Total Fees</p>
							<input class="form-control" type="number" readonly
								value="<%=feesBean.getFees()%>" name="totalBFees<%=feesBean.getId()%>">
							<p class="h5">Pay Fees</p>
							<input class="form-control" type="number"
								name="fees<%=feesBean.getId()%>" value="0">
							<p class="h5">Course Starting Date</p>
							<input class="form-control" type="date"
								name="cStartDate<%=feesBean.getId()%>"
								value="<%=feesBean.getStartDate()%>">
							<p class="h5">Course Ending Date</p>
							<input class="form-control" type="date"
								name="cEndDate<%=feesBean.getId()%>"
								value="<%=feesBean.getEndDate()%>">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary"
								value="<%=feesBean.getId()%>" name="update">Save changes</button>
						</div>
					</div>
				</div>
			</div>
		</tr>
		<%
			}
		%>
	</table>
</form>