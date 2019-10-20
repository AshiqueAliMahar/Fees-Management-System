package serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SideBarServ")
public class SideBarServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn=request.getParameter("sideBarBtn");
		if (btn.equalsIgnoreCase("logout")) {
			HttpSession session=request.getSession();
			session.invalidate();
			response.sendRedirect("LogIn.jsp");
		}
	}

}
