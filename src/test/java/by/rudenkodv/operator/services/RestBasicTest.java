package by.rudenkodv.operator.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.get;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;

import com.jayway.restassured.response.Response;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class RestBasicTest {
	
	public static final String rootURL = "http://localhost:8080/operatorInquiry";
	

	@Test
	public void getTopics() throws JSONException {
		
		//make get request to fetch capital of norway
		Response resp = get(rootURL + "/topics");
		
		//Fetching response in JSON
		JSONArray jsonResponse = new JSONArray(resp.asString());
		
		//Fetching value of capital parameter
		String testValue = jsonResponse.getJSONObject(0).getString("name");
		
		Assert.assertEquals(testValue, "Ремонт");
	}


}
