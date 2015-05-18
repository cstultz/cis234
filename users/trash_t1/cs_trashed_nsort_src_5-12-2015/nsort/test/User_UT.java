package nsort.test;

import static org.junit.Assert.*;
import nsort.model.User;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class User_UT {

	@Test
	public void ObjectCreated() {
		User user = new User("Chris",                //First Name
				             "Stultz",               //Last Name
				             "cstultz@gmail.com",    //E-mail
				             "cstultz");             //username
		assertNotNull(user);
	}
	
	@Test 
	public void testGetters()
	{
		User user = new User("Chris",                //First Name
	             "Stultz",               //Last Name
	             "cstultz@gmail.com",    //E-mail
	             "cstultz");             //username
		String firstName = user.getFirstName();
		assertNotNull(firstName);
		String lastName = user.getLastName();
		assertNotNull(lastName);
		String eMail = user.getEmail();
		assertNotNull(eMail);
		String username = user.getUsername();
		assertNotNull(username);
	}
	
	@Test
	public void testSetters()
	{
		User user = new User("Chris",                //First Name
	             "Stultz",               //Last Name
	             "cstultz@gmail.com",    //E-mail
	             "cstultz");             //username
		String firstName = "Mickey";
		user.setFirstName(firstName);
		assertEquals("First Name was not set", firstName, user.getFirstName());

		String lastName = "Mouse";
		user.setLastName(lastName);
		assertEquals("Last Name was not set", lastName, user.getLastName());

		String eMail = "mickey@disney.com";
		user.setEmail(eMail);
		assertEquals("E-mail was not set", eMail, user.getEmail());

		String username = "mmouse";
		user.setUsername(username);
		assertEquals("Username was not set", username, user.getUsername());
	}
}
