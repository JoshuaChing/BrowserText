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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out=resp.getWriter();
		out.println
		(
				"<html style='background-color:#2c3e50'>" +
				"<head>" +
				"	<title>jControl</title>" +
				"	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>" +
				"	<script>" +
				"		$(document).ready(function(){" +
				"			$('#updateTime').click(function(){" +
				"				$.post('http://192.168.0.17:8080/', null, function(newTime){" + //need to get proper IP
				"					$('time').text(newTime);" +
				"					alert('clicked');" +
				"				});" +
				"			});" +
				"		});" +
				"	</script>" +
				"</head>" +
				"<body style='color:#FFFFFF;font-family:Verdana'>" +
				"	<center>" +
				"		<h1>Welcome to jControl</h1>" +
				"		<p>" +
				"			Last updated: <div id='time'>" + (new java.util.Date()) + "</div>" +
				"			<button id='updateTime'>Update Time</button>" +
				"		</p>" +
				"		<h3>Hardware Information</h3>" +
				"		<p>" +
				"			ID: "+Build.ID+"<br>" +
				"			Model Number: "+Build.MODEL+"<br>" +
				"			Android SDK: "+Build.VERSION.SDK_INT+"<br>" +
				"		</p>" +
				"		<h3>Send a text message</h3>" +
				"		<form method='post'>" +
				"			Phone #: <input type='text' name='phoneNumber'/> <br>" +
				"			Message: <input type='text' name='message'/> <br>" +
				"			# of Texts: <input type='number' name='numberOfTexts' min='1' max='20' value='1'> <br>" +
				"			<input type='submit' name ='send' value='send'/>" +
				"		</form>" +
				"	</center>" +
				"</body>" +
			"</html>"		
		);
	}	
	
	//method for post
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out=resp.getWriter();
		
		if (req.getParameter("send") != null){
			String phoneNumber=req.getParameter("phoneNumber");
			String message=req.getParameter("message");
			String numberOfTexts= req.getParameter("numberOfTexts");
			if(phoneNumber==null || message==null || phoneNumber=="" ||message=="" || numberOfTexts==null || numberOfTexts=="") {
				out.println("<h1>Invalid input</h1>");
			}else{
				sendSMS(phoneNumber, message, Integer.parseInt(numberOfTexts));
				out.println("<h1>\""+message+"\"sent to "+phoneNumber+" ");
			}	
		}
		else if (req.getParameter("updateTime") != null){
			out.println(new java.util.Date());
		}
	}
	
	//method for sending sms
	private void sendSMS(String phoneNumber, String message, int number){
		SmsManager sms=SmsManager.getDefault();
		for (int i=0; i < number; i++){
			sms.sendTextMessage(phoneNumber, null,message, null, null);
		}
	}
}
