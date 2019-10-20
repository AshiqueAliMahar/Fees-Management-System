<%@page import="java.util.Base64"%>
<%@page import="bean.FeesBean"%>
<%@page import="java.util.List"%>
<%@page import="bAL.FeesBAL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<p class="h1 text-center text-muted"
	style="border-bottom: 5px solid brown">Report</p>
<table
	class="table table-responsive table-striped mt-2 table-hover table-secondary table-bordered text-center"
	style="border-radius: 30px;width: 100%">
	<thead>
		<tr class="h5">
			<th>Name</th>
			<th>Batch</th>
			<th>Paid Fees</th>
			<th>Course Starting Date</th>
			<th>Course Ending Date</th>
			<th>Status</th>
			<th>Actions</th>
		</tr>
	</thead>
	<%
		List<FeesBean> lFeesBeans = FeesBAL.getStdsWithFes();
		for (FeesBean feesBean : lFeesBeans) {
	%>
	<tr>
		<td><%=feesBean.getName()%></td>
		<td><%=feesBean.getBatchName()%></td>
		<td><%=feesBean.getPaidFees()%></td>
		<td><%=feesBean.getStartDate()%></td>
		<td><%=feesBean.getEndDate()%></td>
		<td><%=feesBean.getStatus()%></td>
		<td>
			<button class="btn btn-success btn-block" data-toggle="modal"
				data-target="#<%=feesBean.getId()%>">Take Report</button>
		</td>
	</tr>
	<%
		}
	%>
</table>
<!--Modal-->
<%
	for (FeesBean feesBean : lFeesBeans) {
%>
<div id="<%=feesBean.getId()%>" class="modal fade bs-example-modal-lg"
	tabindex="-1" role="dialog" aria-labelledby="classInfo"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="classModalLabel">Report</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<table id="classTable" class="table table-bordered">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<%
									if (feesBean.getPic() != null) {
								%>
									<img class="img-fluid rounded"
										src="data:image/gif;base64,<%=new String(Base64.getEncoder().encode(feesBean.getPic()))%>"
										alt="Not Found">
								<%
									}
								%>
							</td>
						</tr>
						<tr>
							<td class="h6">Name</td>
							<td><%=feesBean.getName()%></td>
							<td class="h6">Batch</td>
							<td><%=feesBean.getBatchName()%></td>
						</tr>
						<tr>
							<td class="h6">Paid Fees</td>
							<td><%=feesBean.getPaidFees() %></td>
							<td class="h6">Course Starting Date</td>
							<td><%=(feesBean.getStartDate())%></td>
						</tr>
						<tr>
							<td class="h6">Course Ending Date</td>
							<td><%=feesBean.getEndDate()%></td>
						</tr>

					</tbody>
				</table>
				<p>
					<b>Total Fees:</b><%=feesBean.getFees() %>
				</p>
				<p>
					<b>Paid Fees:</b><%=feesBean.getPaidFees()%>
				</p>
				<p>
					<b>Remaining Fees:</b><%=feesBean.getFees()-feesBean.getPaidFees()%>
				</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">
					Close</button>
			</div>
		</div>
	</div>
</div>
<%
	}
%>