package com.jchingdev.jcontrol;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.os.Build;
import android.telephony.SmsManager;

public class Servlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6018370847996819359L;

	//method called when user enters URL
	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out=resp.getWriter();
		out.println("<html style='background-color:#2c3e50'>");
		out.println("<head>");
		out.println("<title>jControl</title");
		out.println("</head>");
		out.println("<body style='color:#FFFFFF;font-family:Verdana'>");
		out.println("");
		out.println("<center>");
		out.println("<h1>Welcome to jControl</h1>");
		out.println("Last updated: "+ (new java.util.Date())+"<br><br>");
		out.println("<h3>Hardware Information</h3");
		out.println("ID: "+Build.ID+"<br>");
		out.println("Model Number: "+Build.MODEL+"<br>");
		out.println("Android SDK: "+Build.VERSION.SDK+"<br><br>");
		out.println("<h3>Send a text message</h3>");
		out.println("<form method='post'>");
		out.println("Phone #: ");
		out.println("<input type='text' name='phoneNumber'/> <br>");
		out.println("Message: ");
		out.println("<input type='text' name='message'/> <br>");
		out.println("<input type='submit' value='send'/>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	    }
	
	//method for post
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out=resp.getWriter();
		
		String phoneNumber=req.getParameter("phoneNumber");
		String message=req.getParameter("message");
		 if(phoneNumber==null || message==null || phoneNumber=="" ||message=="") {
	        out.println("<h1>Invalid input</h1>");
	        return;
		 }else{
			 sendSMS(phoneNumber, message);
			 out.println("<h1>\""+message+"\"sent to "+phoneNumber);
		 }
	}
	
	//method for sending sms
	private void sendSMS(String phoneNumber, String message){
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}
}
