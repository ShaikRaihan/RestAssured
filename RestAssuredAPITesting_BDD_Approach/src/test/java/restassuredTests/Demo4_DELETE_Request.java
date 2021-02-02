package restassuredTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;   //These 2 are static packages
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
import junit.swingui.StatusLine;

public class Demo4_DELETE_Request 
{
	
	
	
	
	@Test
	public void testPUT()
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RestAssured.basePath="/delete/111267";
		Response response=given()
						.when()
							.delete()
						.then()
							.statusCode(200)
							.statusLine("HTTP/1.1 200 OK")
							.log().all().extract().response();		
		
		//we have to convert the response object to string from json format
		String ResponseString=response.asString();
		Assert.assertEquals(ResponseString.contains("successfully! deleted Records"),true);
	}

}
