package com.nyu.cloud.RemindMeNow;

import com.nyu.cloud.RemindMeNow.CreateDynamoDB;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.dynamodbv2.model.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoginActivity extends Activity implements OnClickListener{

	Button button;
	RadioGroup genderRadioGroup;
	EditText name;
	EditText age,email;
	String nameValue,genderValue;
	String ageValue,emailValue;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		findAllViewsId();
		button.setOnClickListener(this);
		System.out.println("Create Executed");
		checkCredentials();

	}

	private void findAllViewsId(){
		button = (Button)findViewById(R.id.submit);
		name = (EditText) findViewById(R.id.name);
		email = (EditText) findViewById(R.id.email);
		age = (EditText) findViewById(R.id.age);
		genderRadioGroup = (RadioGroup) findViewById(R.id.gender);
		System.out.println("Finaall Ids executed");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Intent intent = new Intent(getApplicationContext(),RegistrationMainActivity.class);
		Bundle b = new Bundle();
		b.putString("name", name.getText().toString());
		b.putString("email", email.getText().toString());
		b.putString("age", age.getText().toString());
		int id = genderRadioGroup.getCheckedRadioButtonId();
		RadioButton radioButton = (RadioButton)findViewById(id);
		b.putString("gender", radioButton.getText().toString());
		nameValue = name.getText().toString();
		emailValue = email.getText().toString();
		ageValue = age.getText().toString();
		genderValue = radioButton.getText().toString();
		new CreateDynamoDB().insertItem( emailValue, nameValue,ageValue,genderValue);
		//to go to the content
		//intent.putExtras(b);
		startActivity(intent);
	}

	public void checkCredentials() {
		new CreateDynamoDB().createTable();
        

	}


}

