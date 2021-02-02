package SerializationDeserialization;

import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;   //These 2 are static packages
import RestAssuredAPITesting_BDD_Approach.RestAssuredAPITesting_BDD_Approach.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class APITestNoSerialization {
	public HashMap map=new HashMap();
	int emp_id=111267;
	@Test(priority=1)
	public void create()
	{
		map.put("name", RestUtils.getFirstName());
		map.put("salary", "12300");
		map.put("age", "12");
		//ArrayList<String> coursesList=new ArrayList<String>();
		//coursesList.add("Java");
		//coursesList.add("Selenium");
		//map.put("courses", coursesList);
		//RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		//RestAssured.basePath="/update/"+emp_id;
		
		
		given()
			.contentType(ContentType.JSON)
			.body(map)
		.when()
			.post("http://dummy.restapiexample.com/api/v1/update/"+emp_id)
		.then()
			.statusCode(200)
			.assertThat()
			.body("msg", equalTo("Student Added"));
	}

}
