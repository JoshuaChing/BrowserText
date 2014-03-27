package com.jchingdev.jcontrol;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6018370847996819359L;

	//method called when user enters URL
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out=resp.getWriter();
		out.println("<html style=\"background-color:#2c3e50\">");
		out.println("<head>");
		out.println("<title>jControl</title");
		out.println("</head>");
		out.println("<body style=\"color:#FFFFFF;font-family:Verdana\">");
		out.println("<center>");
		out.println("<h1>Welcome to jControl</h1>");
		out.println("<p>This application does nothing at the moment.</p>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	    }
	
}
