package com.nyu.cloud.RemindMeNow;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;

import android.app.Activity;
import android.os.AsyncTask;

public class CreateDynamoDB extends Activity{
    String tableName = "UserTable";
	public void createTable(){
		new CreateTableTask().execute();
	}
   
	public void insertItem(String email , String nameValue, String ageValue , String genderValue){
		String userInfo[] = {email ,nameValue,ageValue,genderValue};
		new InsertItem().execute(userInfo);
	}
	
     AWSCredentials cred = new BasicAWSCredentials("AKIAJIDJ22ADVJICLU2A","sXzxifTfWCisxPjey7T5G5GCuPeAJ/wIAnTn45Yk");
	 AmazonDynamoDBClient sdb = new AmazonDynamoDBClient(cred);
     
	public  void describeTable(String tableName){
		sdb.setRegion(Region.getRegion(Regions.US_EAST_1));
		DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
		TableDescription tableDescription = sdb.describeTable(describeTableRequest).getTable();
		System.out.println("Table Description: " + tableDescription);
	}
	
	public void insertItemIntoDb(String email , String nameValue, String ageValue , String genderValue){
		Map<String, AttributeValue> item = newItem(email ,nameValue, genderValue , ageValue);
        PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
        PutItemResult putItemResult = sdb.putItem(putItemRequest);
        System.out.println("Result: " + putItemResult);
        System.out.println("InsertItem Successful");
	}
	private static Map<String, AttributeValue> newItem(String email, String name, String gender, String age) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("email", new AttributeValue().withS(email));
        item.put("name", new AttributeValue().withS(name));
        item.put("gender", new AttributeValue().withS(gender));
        item.put("age", new AttributeValue().withS(age));
        System.out.println("new Item Succesful");
        return item;
    }
	private class CreateTableTask extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... voids) {

			try {
				describeTable(tableName);
			} catch (Exception e) {
				System.out.println("CreateDynamoDB Describe exception");
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(Void result) {
			finish();
		}
	}
	
	private class InsertItem extends AsyncTask<String, Void, Void> {

		protected Void doInBackground(String...pParams) {

			try {
				String email,nameValue,ageValue,genderValue;
				email= pParams[0];
				nameValue = pParams[1];
				ageValue = pParams[2];
				genderValue = pParams[3];
				
				insertItemIntoDb(email,nameValue,ageValue,genderValue);
			} catch (Exception e) {
				System.out.println("CreateDynamoDB insertItem exception");
				e.printStackTrace();
			}

			return null;	
		}

		protected void onPostExecute(Void result) {
			finish();
		}
	}

}
