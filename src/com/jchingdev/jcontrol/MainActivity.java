package com.jchingdev.jcontrol;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {

	private HttpServer server;
	private TextView IPtext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IPtext = (TextView)findViewById(R.id.IP);
		server = new HttpServer(MainActivity.this);
		server.start();
		
		IPtext.setText(server.getAddress());
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		server.stop();
	}

}
