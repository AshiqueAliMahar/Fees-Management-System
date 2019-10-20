package serv;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bAL.UsersBAL;
import bean.UsersBean;

@WebServlet("/MyProfileServ")
@MultipartConfig
public class MyProfileServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String name = request.getParameter("name");
		Part picPart = request.getPart("image");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		InputStream iStream = picPart.getInputStream();
		byte [] pic=new byte[iStream.available()];
		iStream.read(pic,0,pic.length);
		UsersBean usersBean=new UsersBean(name, email, password, pic);
		boolean isUpdate = UsersBAL.updateUser(usersBean);
		if (isUpdate) {
			writer.println("<script>"
					+ "alert('Record Deleted')"
					+ "</script>");
			
			//request.getRequestDispatcher("SideBar.jsp?sideBarBtn=students").include(request, response);
			response.sendRedirect("SideBar.jsp?sideBarBtn=setting");
		}else {
			response.sendRedirect("SideBar.jsp?sideBarBtn=setting");
		}
	}

}
