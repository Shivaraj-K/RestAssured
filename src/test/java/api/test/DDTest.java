package api.test;

import org.testng.annotations.Test;

import api.endpoint.UserEndPoint;
import api.payload.User;
import api.utilities.DataProviderData;
import io.restassured.response.Response;

public class DDTest {

	public  User u;
	@Test(priority=1,dataProvider="data",dataProviderClass=DataProviderData.class)
	public void postUser(String id,String userName,String FirstName,String lastName,String email,String password,String phone)
	{
		u=new User();
		u.setId(Integer.parseInt(id));
		u.setFirstName(FirstName);
		u.setLastName(lastName);
		u.setEmail(email);
		u.setPassword(password);
		u.setPhone(phone);
		u.setUsername(userName);
		
		Response r=UserEndPoint.postMethod(u);
		r.then()
		       .statusCode(200)
		       .log().body();
		
	}
	
	
	
	//@Test(priority=2,dataProvider="username",dataProviderClass=DataProviderData.class)
	public void updateUser(String username1)
	{
		System.out.println(username1);
		u.setFirstName("Shivaraj-K");
		Response r=UserEndPoint.updateMethod(u, username1);
		r.then()

		       .log().body();
	}
	
	
	@Test(priority=3,dataProvider="username",dataProviderClass=DataProviderData.class)
	public void getUser(String username)
	{
		//u.setFirstName("Shivaraj-K");
		Response r=UserEndPoint.getMethod( username);
		r.then()
		       .statusCode(200)
		       .log().body();
	}
	
	@Test(priority=4,dataProvider="username",dataProviderClass=DataProviderData.class)
	public void deleteUser(String username)
	{
		Response r=UserEndPoint.deleteMethod(username);
		r.then().log().body();
	}
}
