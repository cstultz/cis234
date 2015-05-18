package nsort.model;

/**
 * The Role class represents the user access during the login process. The user access role can be either Admin or User.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/17/2015)
 */
public class Role {
	
	public enum UserAccessID { Admin, User};
	
	private UserAccessID userAccessID;

	/**
	 * Constructor for the role class. Does nothing but construct the object.
	 */
	public Role()
	{
		//do nothing
	}
	
	/**
	 * get the user accessID
	 * 
	 * @return the user access ID
	 */
	public UserAccessID getUserAccessID() {
		return userAccessID;
	}

	/**
	 * set the user access ID
	 * 
	 * @param userAccessID 1 for Admin; 2 for User.
	 */
	public void setUserAccessID(UserAccessID userAccessID) {
			this.userAccessID = userAccessID;
	}
} 
