package serv;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bAL.FeesBAL;
import bAL.StudentsBAL;
import bean.FeesBean;
import bean.StudentsBean;

@WebServlet("/StudentServ")
@MultipartConfig
public class StudentServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String id=request.getParameter("delete");
		
		if(id!=null) {
			boolean isDeleted=StudentsBAL.deleteStd(Integer.valueOf(id));
			System.out.println(isDeleted);
			if (isDeleted) {
				writer.println("<script>"
						+ "alert('Record Deleted')"
						+ "</script>");
				
				request.getRequestDispatcher("SideBar.jsp?sideBarBtn=students").include(request, response);
			}else {
				response.sendRedirect("SideBar.jsp?sideBarBtn=students");
			}
		}
		id=request.getParameter("update");
		if(id!=null) {
			String name=request.getParameter("name"+id);
			String fName=request.getParameter("fName"+id);
			String email=request.getParameter("email"+id);
			String address=request.getParameter("address"+id);
			String dobString=request.getParameter("dob"+id);
			Date dob=Date.valueOf(dobString);
			System.out.println(dobString+"batch"+request.getParameter("batch"+id));
			int batch=Integer.parseInt(request.getParameter("batch"+id));
			Part part = null;
			byte [] bs=new byte[0];
			try {
				part=request.getPart("image"+id);
				InputStream inputStream = part.getInputStream();
				bs=new byte[inputStream.available()];
				inputStream.read(bs, 0,bs.length);
				
			} catch (Exception e) {
				System.out.println("empty");
			}
			
			StudentsBean studentsBean=new StudentsBean(Integer.parseInt(id), name, fName, email, address,dob, batch, bs);
			boolean isUpdated=StudentsBAL.updateStudents(studentsBean);
			
			if (isUpdated) {
				writer.println("<script>"
						+ "alert('Record Updated')"
						+ "		</script>");
				request.getRequestDispatcher("SideBar.jsp?sideBarBtn=students").include(request, response);
			}else {
				response.sendRedirect("SideBar.jsp?sideBarBtn=students");
			}
		}
		String add=request.getParameter("add");
		if (add!=null) {
			String name=request.getParameter("aname");
			String fName=request.getParameter("afName");
			String email=request.getParameter("aemail");
			String address=request.getParameter("aaddress");
			String dobString=request.getParameter("adob");
			Date dob=Date.valueOf(dobString);
			int batchId=Integer.parseInt(request.getParameter("abatch"));
			int batchDuration=Integer.parseInt(request.getParameter("duration"+batchId));
			Part part = null;
			byte [] bs=null;
			try {
				part=request.getPart("aimage");
				InputStream inputStream = part.getInputStream();
				bs=new byte[inputStream.available()];
				inputStream.read(bs, 0,bs.length);
				
			} catch (Exception e) {
				System.out.println("empty");
			}
			
			StudentsBean studentsBean=new StudentsBean(0, name, fName, email, address,dob, batchId, bs);
			boolean isAdded=StudentsBAL.addStudent(studentsBean);
			Date startDate=Date.valueOf(LocalDate.now());
			Date endDate=Date.valueOf(LocalDate.now().plusMonths(batchDuration));
			FeesBean feesBean=new FeesBean(0,startDate,endDate, "not Paid",1);
			FeesBAL.addFees(feesBean);
			if (isAdded) {
				writer.println("<script>"
						+ "alert('Record Added');"
						+ "		</script>");
				request.getRequestDispatcher("SideBar.jsp?sideBarBtn=students").include(request, response);
			}else {
				response.sendRedirect("SideBar.jsp?sideBarBtn=students");
			}
		}
	}//end of post
	
}
