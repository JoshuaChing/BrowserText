package com.jchingdev.jcontrol;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;
import android.util.Log;

public class HttpServer {
	
	private ServletContextHandler handler;
	private Server server;
	private Context context;
	private int port=8080;
	
	//for Android 2.x bug
	static{
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
			System.setProperty("java.net.preferIPv6Addresses", "false");
		}
	}
	
	//jetty server and listen at port 8080
	HttpServer(Context context){
		this.context=context;
		
		server = new Server(port); //port
		handler = new ServletContextHandler();
		handler.setContextPath("/");
		handler.addServlet(new ServletHolder(new Servlet()), "/");
		server.setHandler(handler);
	}
	
	//run server in separate thread
	public void start(){
		new Thread(){
			public void run(){
				try{
					server.start();
				}catch(Exception e){
					Log.e("SERVER_START",e.toString());
				}
			}
		}.start();
	}
	
	//stop server
	public void stop(){
		try{
			server.stop();
		}catch(Exception e){
			Log.e("SERVER_STOP",e.toString());
		}
	}
	
	//get ip address of device
	@SuppressWarnings("deprecation")
	public String getAddress(){
		ConnectivityManager con;
		con=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if(con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
			//check if device is connected to wifi network
			WifiManager wifi;
			wifi=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
			
			int IP=wifi.getConnectionInfo().getIpAddress();
			
			return "http://"+Formatter.formatIpAddress(IP)+":"+port;
		}
		else{
			return "You are not connected to WIFI";
		}
	}
	
	//get port
	public String getPort(){
		return String.valueOf(port);
	}
}
