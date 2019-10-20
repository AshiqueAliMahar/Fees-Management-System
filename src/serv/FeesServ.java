package serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bAL.FeesBAL;
import bean.FeesBean;


@WebServlet("/FeesServ")
public class FeesServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		String id=request.getParameter("update");
		if (id!=null) {
			int sId=Integer.valueOf(id);
			int fees=Integer.valueOf(request.getParameter("fees"+id));
			String cStartDateS=request.getParameter("cStartDate"+id);
			Date cStartDate=Date.valueOf(cStartDateS);
			String cEndDateS=request.getParameter("cEndDate"+id);
			Date cEndDate=Date.valueOf(cEndDateS);
			int totalBFees=Integer.valueOf(request.getParameter("totalBFees"+id));
			int paidFees=Integer.valueOf(request.getParameter("paidFees"+id));
			
			String status="Not Paid";
			if (totalBFees<=(paidFees+fees)) {
				status="Fully Paid";
			}else if (totalBFees>(paidFees+fees)) {
				status="Partially Paid";
			}
			FeesBean feesBean=new FeesBean(fees, cStartDate, cEndDate, status, sId);
			boolean isUpdated = FeesBAL.updateFees(feesBean);
			if (isUpdated) {
				writer.println("<script>"
						+ "alert('Fees Updated Successfully')"
						+ "</script>");
				
				//request.getRequestDispatcher("SideBar.jsp?sideBarBtn=fees").include(request, response);
				response.sendRedirect("SideBar.jsp?sideBarBtn=fees");
			}else {
				response.sendRedirect("SideBar.jsp?sideBarBtn=fees");
			}
		}
	}
}
