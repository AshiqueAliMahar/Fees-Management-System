package serv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bAL.UsersBAL;
import bean.UsersBean;

@WebServlet("/LogInServ")
public class LogInServ extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("pass");
		if(email!=null && password!=null) {
			
			UsersBean usersBean= UsersBAL.getUser(email, password);
			if (usersBean!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				request.setAttribute("user", usersBean);
				request.getRequestDispatcher("SideBar.jsp").forward(request, response);
			}else {
				request.setAttribute("alert","Incorrect Email Or Password");
				request.getRequestDispatcher("LogIn.jsp").forward(request, response);
			}
		}
	}
}
