package nsort.test;

import static org.junit.Assert.*;
import nsort.model.Role;
import nsort.model.Role.UserAccessID;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class Role_UT {

	@Test
	public void ObjectCreation() {
		Role role = new Role();
		assertNotNull(role);
	}

	@Test
	public void setUserAccessID() {
		Role role = new Role();
		role.setUserAccessID(UserAccessID.Admin);
		assertNotNull("role has not been set to Admin", role.getUserAccessID());
	}
	
	@Test
	public void getUserAccessID()
	{
		Role role = new Role();
		role.setUserAccessID(UserAccessID.User);
		assertEquals("role has not been set to User", role.getUserAccessID(), UserAccessID.User);
	}
}
