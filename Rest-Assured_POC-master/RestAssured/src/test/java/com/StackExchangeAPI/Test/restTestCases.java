package com.StackExchangeAPI.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.StackExchangeAPI.CommonUtils.ObjectRepo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class restTestCases {
	
	public static int badge_id;
	Properties Obj = null;
	public static String baseURI;
	
	@BeforeClass
	public void setBaseUri () throws IOException, Exception {
		Obj = ObjectRepo.getInstance();
		baseURI = Obj.getProperty("baseURI");
		RestAssured.baseURI = baseURI;
	}
	
	@Test (priority=1)
  	public void testGetRecipients() throws Exception {
		String recipientsPath =  Obj.getProperty("recipientsPath");
		RequestSpecification request = RestAssured.given();
		Response response = request.get(recipientsPath);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);               // Assert for the status code
	    
 	}

	@Test (priority=2)
	public void testGetRecipients400Exception() {
		String recipients400ExceptionPath =  Obj.getProperty("recipients400ExceptionPath");
		RequestSpecification request = RestAssured.given();
		Response response = request.get(recipients400ExceptionPath);
	
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);               // Assert for the status code
    
	}

	@Test (priority=0)
	public void testGetTags() {
		String getTagPath =  Obj.getProperty("getTagPath");
		RequestSpecification request = RestAssured.given();
		Response response = request.get(getTagPath);
	
		int statusCode = response.getStatusCode();
		String badgeFinder =  Obj.getProperty("badgeFinder");
		List<Integer> ids = response.jsonPath().getList(badgeFinder);
		badge_id = ids.get(0);
		Assert.assertEquals(statusCode, 200);               // Assert for the status code
	}


	@Test (priority=3)
	public void testGetTags400Exception() {
		String getTags400Exception =  Obj.getProperty("getTags400Exception");
		RequestSpecification request = RestAssured.given();
		Response response = request.get(getTags400Exception);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);              // Assert for the status code    
	}
		
	@Test (priority=4)
	public void testGetid() {
			String getidPath =  Obj.getProperty("getidPath");
		String badgeFinder =  Obj.getProperty("badgeFinder");
	
	
		RequestSpecification request = RestAssured.given();
		Response response = request.get(getidPath,badge_id);
		
		int statusCode = response.getStatusCode();
		List<Integer> ids = response.jsonPath().getList(badgeFinder);
		int badgeExpected = ids.get(0);
		Assert.assertEquals(badge_id, badgeExpected); // Assert for the checking reponse has badge id which we have passed
		Assert.assertEquals(statusCode, 200);         // Assert for the status code
	}

	@Test(priority=5)
	public void testGetid400Exception() {
	
		String getidPath =  Obj.getProperty("getidPath");
		String wrongBadgeId =  Obj.getProperty("wrongBadgeId");
		RequestSpecification request = RestAssured.given();
		Response response = request.get(getidPath,wrongBadgeId);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);          // Assert for the status code
	}
}

