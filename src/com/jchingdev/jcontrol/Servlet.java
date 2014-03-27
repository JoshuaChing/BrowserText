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
		out.println("<html>"+
				 "<title>Simple Form</title>" +
	                "<body>" +
	                "<h1>Hello jControl</h1>"+
	                "</body>" +
	                "</html>");
	    }
}
