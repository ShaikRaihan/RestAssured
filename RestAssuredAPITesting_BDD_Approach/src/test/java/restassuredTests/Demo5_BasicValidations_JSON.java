package restassuredTests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;   //These 2 are static packages
/*
 * 
 * 1.Test Status COde
 * 2.Log Response
 * 3.Verifying single content in  response body
 * 4.Verifying multiple contents in response body
 * 5.Setting parameters & headers
 */

import org.testng.annotations.Test;

//We are going to validate JSON response
public class Demo5_BasicValidations_JSON 
{
	
	//1.Test Status COde
	@Test(priority=1)
	public void testStatusCode()
	{
		//Given statement is optional as there is no parameter inside given
		when()
			.get("http://jsonplaceholder.typicode.com/posts/5")
		.then()
			.statusCode(200)		
			.log().all();  //-> to print the result
	}
	
	
	//2.Log response
	@Test(priority=2)
	public void testLogging()
	{
		when()
			.get("https://api.printful.com/countries")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	//3.Verifying single content in  response body
	@Test(priority=3)
	public void testSingleContent()
	{
		when()
		.get("https://api.printful.com/countries")
	.then()
		.statusCode(200)
		.body("result[0].name", equalTo("Andorra"));
		//.body("code", equalTo(200));
		//Use JSONFInder in chrome to get the jsonpath
	}
	
	
	//4.Verifying multiple contents in response body
	@Test(priority=4)
	public void testMultipleContent()
	{
		when()
		.get("https://api.printful.com/countries")
	.then()
		.statusCode(200)
		.body("result.name", hasItems("Andorra","Afghanistan","Anguilla")).log().all();
		
		//hasItems is used to check if the value is existing from list of values
		//Doesn't work
	}
	
	
	// 5.Setting parameters & headers
	@Test(priority=4)
	public void testParamsAndHeaders()
	{
		given()
			.param("orderby","fullName")
			.header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlFqZEdORGcyUlRVM01EYzNPVFpGTVVJNU5EZzRPVFEwUXpoRk5VSXdRek14TURBM1JqQTJNZyJ9.eyJodHRwczovL2NpYi5jb20vYnVpbGRpbmdDb2RlIjoiIiwiaHR0cHM6Ly9jaWIuY29tL3VzZXJJZCI6Ijc3N0M1OTc0LTNFOUEtNDFBNi05QTU0LTA1M0FCNTM4Mzg5MiIsImh0dHBzOi8vY2liLmNvbS91c2VyTmFtZSI6ImFkbWluQGNvaGVzaW9uaWIuY29tIiwiaHR0cHM6Ly9jaWIuY29tL2NvbXBhbnlDb2RlIjoiIiwiaHR0cHM6Ly9jaWIuY29tL3VzZXJUeXBlIjoxLCJodHRwczovL2NpYi5jb20vcm9sZXMiOnsicm9sZXMiOiJ1U3VqTmNPNEY4dG80SUNxai9DRW1QSVFXZ1U2NklXdGh3R1E5ZFpSZENLcUpRSnpwNVVYcElGMTBlYUdqcndkTmhlc1p0eDFFRG12ckFzemRZQkV4aEh4eGdKWElMcWw2RDYrblBjWW5Bb253cWlYSjZRZlJtOWZyUUhqVlgwblBQa3VqUGdPSTE5TTZBMHlrZ0N5VTlUM1gyaEM0dEhIOGFhc09DTnJKQmgyV3hDbGJ5SzFETjEwalkwMUZEcVI0NlJmMGpsWmVMTjJRdmNoQXMyL3VaYWdNL2RrYjJwdndCMXZKbVlJMElSbzlMNXlpTW1IMzFqUCt2b1JrT21TWSthL0FvOEk5Y3QxRHVSbUdnQzZTWFYrOUYzcDE2bkJQUGl5UjZzZUlOZ1krK0paZSs1cWtPWFZsaFQ5bjRVUHF5MTNOcjV1UGRja0hzMkx2dlVXUkF0M3piTm9XaTZlYldGTUF2RHY1b1NFdTl0WDRzQlVmRnowazFudlQ0WldFUldVdXplZlo4Y21Dek1jOEFRY1RLUjJIaGtNMzlvWHprZmRUUUlVTHo1SHluSlhiZXhMY3V5d25BamV1SXJHUDRnaERaZ3hHOS9jTEwzQXlVZnBIemh0REVuYnVsSXg1T0pBV0pmS0pLalphSmJzWnJMWk9MQWZHLzBZU1hZWldrVVMvazE1R004d2R6RFpiRHVEblQ0UE85dWFiUmdLS1lUUldZWVc1dFhFTDlrNUNoVXVNaEpIdEJoVVdiMThJTThRZTVzUDdWZGs3SVd2V3h6bHJxYU9CQmZEVk42c3kreGVSN21sclMvL3M0aXdiN25XMkZKeXVLclljVXYrc0FDM3BCeGpaZ0g5UHNRUXlwNlB5ZGR0Z3FNQ3VVWHNNc3VXZEw0NW5RT3cvTXBOYmtFS09IWk8xckRuVlZUckFmZks1V3IvbDR0TCtXVzRrNWJBM002dzZDWnRNNUZYN3Fwd0owTTU4dXhGN0xEdERwazhwNG9WR2JpZ2hIWHlmZzZuZmpva3dmSVo5OWZjcnlvNjByVm9mSHJneVNMTzNwK05tcGpCYjRyVXR1QUdyVUQ0a2ZUblZyNlE3dW4ramFDbEVkL2xBelV5RTVUYUdYRDk1VTQ1UFNrYkRMM1J1WjR5N2pOalA3VVo4ZzR1dXhYalJOaGxBb0U1a3NHbGtzQ29tTWlyd051WEUwMDY3VUs0alZ3WVluUkc4TkNhYXcyei84VkwxdjR5d1ErQ0ZYYUc1dWxkSWhKWWI4NEkram93K3BORkU4Y0U4RGlkZTE3L1hNK3dpVkxKdXhpOEFZK3hHK0hCb2lRZHpnTmJUcXhLUlFFODBTL2FCc21MN2RvdzJuajI0ZVVOR0RxNEljd0JQcjZPTCtsb0dwdnlsUTByUGxPL0xRanZYQ3VXV282bnptOUlSczg3Z3lVODZRMStGdHJoN0kxNUtLTnhVY3NJV2FPY1JxL01hU1BCQzZ3Z2p1TW9TVUkrc0kzcHBiejZHQUQwSHU2MEthb3VVUTNVTWxMbWRxd1VzUEtFV2VOZzduQmpEZEZnRzNmQVVCUGNoTkdQQm9YbjV1M3F6VlIrbTJja0pQRzlSNXY1Q1NjNDF6Zlc0VTZsRUxoTlZNcWdUeGdSRG5JS0dxenBVSVQ1S25xVDhOZUVxKzRFMXUvQSs5bmNOK3JSZmZCWGJyb1JQUmNaZ0phcmxSMysvdk5FaGtONGlMWmo4Tzg0dm1vREc1UERjRnFjZmlKZkRwYkNSdVpLMnVDVW1DZ2lENGZVb0JBaDBvT3VtUFdmV1ZCY0tVS21HKzV6Z2R5TGNwUG9lbUZNTEEydGxzTkFNNjVEWjEreGx6dmJNNElTTnJZK3BrektNYzVjLzNZQXZmU2JmVzExOEwrVkEwMUZ6R0h3Q1lIMzRYMVhab1BoZGV1SWVxeS9OQ3o1akJWYXNKSVBDQ1RFN3BaUEx4WDFxRWhKVm1yUG5URWFYZE55N1hOcEhsdEI1TWJlcUpxREVxZGlhVDNzNDFSVGNaZEJ1MEpLbHNJQVV2QkNFL3hmTVcrdG1rVmhxVXlDVWNpajZleGQ0WmQrSWF3eC9KYjluM0NweFBrNXlvb0Y1eFVDeURhTkVROW9yVDVpL1pEMXdwdDhucnhmMktjZ2tXb053RWtadnlVSGRvRGU2WnVhazk2UUFhZUZ4Y1Y2VTBaZVRIbkM3SEZ6dHRDclhVcnZ6VUxlTWRyZktPU1l2YzJCT1ZYazVyZm1XUWcwaUFZeVdaczBDR2tYcHNkWXFqTFJnYWFaTmNxeTNlRUZWUCsxNWUwQ1haYmpOZmxzeVVyMDN1NFc0MFdEOVRsOG5hR0V1UFZpS0Q0c3BGZmVMMStQMVdJV1Q4aWY4VjhoSEZ3RVBDQ1NQc0x3bTVGdktVbncifSwiaXNzIjoiaHR0cHM6Ly9jb2hlc2lvbmliLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw1YzVhYThlNDZhNWIzYTc0NTNkYjBhOTkiLCJhdWQiOiJodHRwOi8vZGV2LWNvcmUuY29oZXNpb25pYi5jb20vIiwiaWF0IjoxNTY3NjY2MTc5LCJleHAiOjE1Njc3NTI1NzcsImF6cCI6IjRXRTB0OUhsUEpKeTUzN3F1bXEzaU9oRWNuTkJ5MHR0Iiwic2NvcGUiOiIifQ.fo6ptDQ3NmTjxxwrFRj6h-l5VkdxPtiCBiUYCNIkwTF_ATaLZ5gqYBSa9bWlVepGyQqmQUdzKEKYRa4XiksXgaicgV1DmTZQzK6IO2qLhCIeoUijmsCNPJsmWODiO7t5GhIfatSuQICuDJWbUQP2NwdkEuQAGQgwHkS9_4VlmVkjKAqmRY2_D2gCAzi-TntKfzw6oUqXWCRf_mbpAKe6HDWUO7GMYd3sNjCIdoCPWD8grdjX0GsJrHZi8XLorLFFRDlMZGNPoCELU8tvOFOjws1S71G1hiDl3kpdWj00bpgwQOdhIwV943_TDEw4RAMYgqPd7vGk82r17Dy_K3JCJw")		
		.when()
			.get("http://dev-cib-reporting-api.azurewebsites.net/api/v1/building/712/user")
		.then()
			.statusCode(200)
			.log().all();
		
		//hasItems is used to check if the value is existing from list of values
		//Doesn't work
	}
	
}
