package api.endpoint;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint {

	
	
	@Test(priority=1)
	public static Response postMethod(User u)
	{
		
		Response rr=given()
		      .contentType("application/json")
		      .accept(ContentType.JSON)
		      .body(u)
		
		.when()
		      .post(Routes.create_Url);
		
		return rr;
	}
	
	@Test(priority=2)
	public static Response getMethod(String Username)
	{
		
		Response r=given()
		      .contentType("application/json")
		      .accept(ContentType.JSON)
		      .pathParam("username", Username)
		
		.when()
		      .get(Routes.get_Url);
		return r;
	}
	
	@Test(priority=3)
	public static Response  updateMethod(User u,String username)
	{
		
		Response r=given()
				.log().all()
		      .contentType("application/json")
		      .accept(ContentType.JSON)
		      .pathParam("username", username)
		      .body(u)
		
		.when()
		      .put(Routes.put_Url);
		return r;
	}
	
	@Test(priority=4)
	public static Response deleteMethod(String Username)
	{
		
		Response r=given()
		      .contentType("application/json")
		      .accept(ContentType.JSON)
		      .pathParam("username", Username)
		
		.when()
		      .delete(Routes.delete_Url);
		
		return r;
	}
}
