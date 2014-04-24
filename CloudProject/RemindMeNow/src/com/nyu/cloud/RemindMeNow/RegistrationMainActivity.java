package com.nyu.cloud.RemindMeNow;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationMainActivity extends Activity{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivityform);
		
		
		TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
		
		Button loginButton = (Button) findViewById(R.id.btnLogin);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//switch to registration screen on login
				//Intent intent = new Intent(getApplicationContext(), MainActivity.class);

				//startActivity(intent);

			}
		});
		
		registerScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Switching to Register screen
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

				startActivity(intent);
			}
		});
	}
}