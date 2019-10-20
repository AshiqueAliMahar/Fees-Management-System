<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<style>
        .button{
            width: 300px;
            height: 400px;
            font-size: 50px;
            border-radius: 30px;
            padding: 30px;
            color: white;

        }
    </style>
    <h1 style="border-bottom: 1px solid blue;text-align: center">DashBoard</h1>
    <h3 style="border-bottom: 1px dashed green;text-align: center">Welcome to IT-Square This is great Study Centre For new Techonologies</h3>
    <form action="Dashboard.jsp" method="post">
    
    <div class="btn btn-group">
    <button class="btn button btn-danger" type="submit" name="dashBtn" value="students">Student</button>
    <button class=" btn button btn-info" type="submit" name="dashBtn" value="takeFees">TakeFees</button>
    <button class=" btn button btn-secondary" type="submit" name="dashBtn" value="report">Report</button>
    </div>
    <%
    	String val=request.getParameter("dashBtn");
    	if(val!=null){
    		if(val.equals("students")){
    			response.sendRedirect("SideBar.jsp?sideBarBtn=students");
    		}else if(val.equals("takeFees")){
    			response.sendRedirect("SideBar.jsp?sideBarBtn=fees");
    		}else if(val.equals("report")){
    			response.sendRedirect("SideBar.jsp?sideBarBtn=report");
    		}
    	}
    %>
    </form>