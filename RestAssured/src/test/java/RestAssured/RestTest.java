package RestAssured;

	import org.json.simple.JSONObject;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;

    public class RestTest {
    	
   @Test (priority=1)
      	public void testGet() {
    	
    	   /* given().
    	    when().
    	        get("https://reqres.in/api/users?page=2").
    	    then().
    	        assertThat().
    	        statusCode(200);*/
    	    
    	    RestAssured.baseURI ="https://reqres.in";
    		RequestSpecification request = RestAssured.given();
    		Response response = request.get("/api/users/1");

    		int statusCode = response.getStatusCode();
    		System.out.println(response.getBody().asString());
    		System.out.println("status is " + statusCode);
    		Assert.assertEquals(statusCode, 200);
    	    
     	}
     @SuppressWarnings("unchecked")
	@Test (priority=2)
	    public void testPost() {
	
    		RestAssured.baseURI ="https://reqres.in";
    		RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("name", "Swapnil"); // Cast
			requestParams.put("job", "berojgar");
	  
    	// Add a header stating the Request body is a JSON
    	request.header("Content-Type", "application/json");
    	 
    	// Add the Json to the body of the request
    	request.body(requestParams.toJSONString());
    	 
    	// Post the request and check the response
    	Response response = request.post("/api/users");

		int statusCode = response.getStatusCode();
		System.out.println(response.getBody().asString());
		System.out.println("status is " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
   	}   	
     @Test (priority=3)
	    public void testDelete() {
    	 
    /*	 RestAssured
 		.given()
 			.baseUri("https://reqres.in")
 		.when()
 			.delete("/api/users/1")
 		.then()
 			.assertThat()
 			.statusCode(204);
 		*/	
    	 
		RestAssured.baseURI ="https://reqres.in";
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/api/users/1");

		int statusCode = response.getStatusCode();
		System.out.println(response.getBody().asString());
		System.out.println("status is " + statusCode);
		Assert.assertEquals(statusCode, 204);
	}   
     
     @SuppressWarnings("unchecked")
	@Test (priority=4)
	    public void testPut() {
		RestAssured.baseURI ="https://reqres.in";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "swapy"); // Cast
		requestParams.put("job", "berojgar");

    	// Add a header stating the Request body is a JSON
    	request.header("Content-Type", "application/json");
    	 
    	// Add the Json to the body of the request
    	request.body(requestParams.toJSONString());

		Response response = request.put("/api/users/2");

		int statusCode = response.getStatusCode();
		System.out.println(response.getBody().asString());
		System.out.println("status is " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}  
     
     @SuppressWarnings("unchecked")
	@Test (priority=5)
	    public void testPatch() {
		RestAssured.baseURI ="https://reqres.in";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Swap"); // Cast
    	
    	// Add a header stating the Request body is a JSON
    	request.header("Content-Type", "application/json");
    	 
    	// Add the Json to the body of the request
    	request.body(requestParams.toJSONString());

		Response response = request.patch("/api/users/3");

		int statusCode = response.getStatusCode();
		System.out.println(response.getBody().asString());
		System.out.println("status is " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
 }
