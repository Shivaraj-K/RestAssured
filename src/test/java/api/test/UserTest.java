package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndPoint;
//import api.endpoint.UserEndpoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker f;
	User u;
	public Logger l;
	
	@BeforeClass
	public void setupData()
	{
		f=new Faker();
		u=new User();
		
		u.setId(123456);
		u.setFirstName(f.name().firstName());
		u.setLastName(f.name().lastName());
		u.setEmail(f.internet().emailAddress());
		u.setPassword(f.internet().password());
		u.setPhone(f.phoneNumber().cellPhone());
		u.setUsername(f.name().username());
		
		l=LogManager.getLogger(UserTest.class);
	}
	
	
	@Test(priority=1)
	public void postMethod()
	{
//
//		f=new Faker();
//		u=new User();
//		
//		u.setId(123456);
//		u.setFirstName(f.name().firstName());
//		u.setLastName(f.name().lastName());
//		u.setEmail(f.internet().emailAddress());
//		u.setPassword(f.internet().password());
//		u.setPhone(f.phoneNumber().cellPhone());
//		u.setUsername(f.name().username());
		
		l.info("Sending the data");
		Response r=UserEndPoint.postMethod(u);
		r.then()
		       .statusCode(200)
		       .log().body();
		
		l.info("Created the user");
//		       .extract()
//		       .response()
//		       .as(User.class);
//		username=s.getUsername();
		
		
	}
	
	@Test(priority=2)
	public void getMethod()
	{
		l.info("Fetches data from server");
		Response r=UserEndPoint.getMethod(this.u.getUsername());
		r.then()
		       .statusCode(200)
		       .log().body();
		l.info("Fetched data from server");
	}	
	
	@Test(priority=3)
	public void UpdateUesrs()
	{
		l.info("Update user details");
		u.setFirstName("Shivaraj");;
		u.setLastName("Guttedar");
		UserEndPoint.updateMethod(u,this.u.getUsername());

		Response r1=UserEndPoint.getMethod(this.u.getUsername());
		r1.then()
		       .statusCode(200)
		       .log().body();
		
		l.info("Updated user details");
	}
	
	@Test(priority=5)
	public void deleteMethod()
	{
		l.info("Delete data ");
		Response r=UserEndPoint.deleteMethod(u.getUsername());
		r.then()
		       .statusCode(200);
		
		l.info("Deleted the data");
	}


	
}
