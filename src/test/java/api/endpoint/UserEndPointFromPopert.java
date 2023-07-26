package api.endpoint;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointFromPopert {

	public static ResourceBundle getUrl()
	{
		ResourceBundle res=ResourceBundle.getBundle("Routes");
		return res;
	}

	@Test(priority=1)
	public static Response postMethod(User u)
	{

		String url=getUrl().getString("create_Url");
		Response rr=given()
				.contentType("application/json")
				.accept(ContentType.JSON)
				.body(u)

				.when()
				.post(url);

		return rr;
	}

	@Test(priority=2)
	public static Response getMethod(String Username)
	{

		String url=getUrl().getString("get_Url");
		Response r=given()
				.contentType("application/json")
				.accept(ContentType.JSON)
				.pathParam("username", Username)

				.when()
				.get(url);
		return r;
	}

	@Test(priority=3)
	public static Response  updateMethod(User u,String username)
	{

		String url=getUrl().getString("put_Url");
		Response r=given()
				.log().all()
				.contentType("application/json")
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(u)

				.when()
				.put(url);
		return r;
	}

	@Test(priority=4)
	public static Response deleteMethod(String Username)
	{

		String url=getUrl().getString("delete_Url");
		Response r=given()
				.contentType("application/json")
				.accept(ContentType.JSON)
				.pathParam("username", Username)

				.when()
				.delete(url);

		return r;
	}
}
