package com.jchingdev.browsertext;

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

	
	////BELOW IS A VERY BRUTE FORCE WAY OF PROVIDING OUTPUT TO THE USER, WOULD NOT RECOMMEND FOR PRODUCTION PROJECTS////
	////(this is just a practice/demo sort of application)////
	
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
				"	<title>Quick Text</title>" +
				"</head>" +
				"<body style='color:#FFFFFF;font-family:Verdana'>" +
				"	<center>" +
				"		<h1>Welcome to Browser Text</h1>" +
				"		<p>" +
				"			Last updated: " + (new java.util.Date()) +
				//"			<form method='post'> <input type='submit' name='useless' value='useless'/> </form>" +
				"		</p>" +
				"		</br>"+
				"		<h3>Hardware Information</h3>" +
				"		<p>" +
				"			ID: "+Build.ID+"<br>" +
				"			Model Number: "+Build.MODEL+"<br>" +
				"			Android SDK: "+Build.VERSION.SDK_INT+"<br>" +
				"		</p>" +
				"		</br>"+
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
		
		out.println(
				"<html style='background-color:#2c3e50'>" +
					"<head>" +
					"	<title>Quick Text</title>" +
					"</head>" +
					"<body style='color:#FFFFFF;font-family:Verdana'>" +
					"	<center>" +
					"		<h1>Welcome to Quick Text</h1>" +
					"		<p>" +
					"			Last updated: " + (new java.util.Date())
		);
		
		if (req.getParameter("send") != null){
			String phoneNumber=req.getParameter("phoneNumber");
			String message=req.getParameter("message");
			String numberOfTexts= req.getParameter("numberOfTexts");
			if(phoneNumber==null || message==null || phoneNumber=="" ||message=="" || numberOfTexts==null || numberOfTexts=="") {
				out.println(
							"			<h3>Invalid input. Please try again.</h3>"
							);
			}else{
				sendSMS(phoneNumber, message, Integer.parseInt(numberOfTexts));
				out.println(
							"			<h3> \""+message+"\" sent to " + phoneNumber + " a total of " + numberOfTexts + " time(s) </h3>"
							);
			}	
		}
		/*else if (req.getParameter("useless") != null){
			out.println(
				"			<h3>Why did you click this button?</h3>"
				);
		}*/
		
		out.println(
				"		</p>" +
						"	</center>" +
						"</body>" +
					"</html>" 
						);
		
	}
	
	//method for sending sms
	private void sendSMS(String phoneNumber, String message, int number){
		SmsManager sms=SmsManager.getDefault();
		for (int i=0; i < number; i++){
			sms.sendTextMessage(phoneNumber, null,message, null, null);
		}
	}
}
