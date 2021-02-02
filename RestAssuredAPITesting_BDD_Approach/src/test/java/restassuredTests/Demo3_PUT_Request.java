package restassuredTests;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;   //These 2 are static packages
import RestAssuredAPITesting_BDD_Approach.RestAssuredAPITesting_BDD_Approach.RestUtils;
import io.restassured.RestAssured;

public class Demo3_PUT_Request {
	public static HashMap map=new HashMap();
	int emp_id=111267;
	@BeforeClass
	public void putData() {
		map.put("name", RestUtils.getFirstName());
		map.put("salary", "12300");
		map.put("age", "12");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RestAssured.basePath="/update/"+emp_id;
	}
	@Test
	public void testPUT()
	{
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.put()
		.then()
			.statusCode(200)
		.log().all();
		
		
	}

}
