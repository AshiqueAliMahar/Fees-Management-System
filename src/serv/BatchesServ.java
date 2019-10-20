package serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bAL.BatchBAL;
import bean.BatchBean;

@WebServlet("/BatchesServ")
public class BatchesServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		
		String delId=request.getParameter("delete");
		if (delId!=null) {
			boolean isDeleted=BatchBAL.deleteBatch(Integer.parseInt(delId));
			if (isDeleted) {
				writer.println("<script>"
						+ "alert('Batch Deleted')"
						+ "</script>");
				
				//request.getRequestDispatcher("SideBar.jsp?sideBarBtn=batch").include(request, response);
				response.sendRedirect("SideBar.jsp?sideBarBtn=batch");
			}else {
				response.sendRedirect("SideBar.jsp?sideBarBtn=batch");
			}
		}
		String add=request.getParameter("add");
		if (add!=null) {
			String bName=request.getParameter("bName");
			int bDuration=Integer.parseInt(request.getParameter("bDuration"));
			int bFees=Integer.parseInt(request.getParameter("bFees"));
			BatchBean batchBean=new BatchBean(0, bName, bDuration, bFees);
			boolean isAdded=BatchBAL.addBatch(batchBean);
			if (isAdded) {
				writer.println("<script>"
						+ "alert('Batch Added Successfully')"
						+ "</script>");
				
				//request.getRequestDispatcher("SideBar.jsp?sideBarBtn=batch").include(request, response);
				response.sendRedirect("SideBar.jsp?sideBarBtn=batch");
			}else {
				response.sendRedirect("SideBar.jsp?sideBarBtn=batch");
			}
			
		}
		String updateId=request.getParameter("update");
		if (updateId!=null) {
			
			int bId=Integer.parseInt(updateId);
			String bName=request.getParameter("bName"+updateId);
			int bDuration=Integer.parseInt(request.getParameter("bDuration"+updateId));
			int bFees=Integer.parseInt(request.getParameter("bFees"+updateId));
			BatchBean batchBean=new BatchBean(bId, bName, bDuration, bFees);
			boolean isUpdate=BatchBAL.updateBatch(batchBean);
			if (isUpdate) {
				writer.println("<script>"
						+ "alert('Batch updated Successfully')"
						+ "</script>");
				
				//request.getRequestDispatcher("SideBar.jsp?sideBarBtn=batch").include(request, response);
				response.sendRedirect("SideBar.jsp?sideBarBtn=batch");
			}else {
				response.sendRedirect("SideBar.jsp?sideBarBtn=batch");
			}
		}
		
	}
}
