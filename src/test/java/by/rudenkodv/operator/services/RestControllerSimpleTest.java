package by.rudenkodv.operator.services;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.module.mockmvc.response.MockMvcResponse;

import by.rudenkodv.operator.controller.OperatorController;

public class RestControllerSimpleTest {
	
	@Ignore
	@Test
	public void getAllTopic(){
		
		MockMvcResponse response =
		
		given().
			standaloneSetup(new OperatorController()).
	        param("name", "TestName").
        when().
	        get("/topics").
        then().
	        statusCode(200).
	        body("name", equalTo("TestName")).
	    extract().
	    	response();		
		
		 assertThat(response.path("content")).isEqualTo("TestName");
	}

}
