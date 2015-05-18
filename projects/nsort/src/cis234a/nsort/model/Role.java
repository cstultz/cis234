package cis234a.nsort.model;
/**
 * The Role class captures the user access role of the current user. The user access role can be either Admin, User, or Unassigned.
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/6/2015)
 */
public class Role {
	
	public enum UserAccessRole { Unassigned, Admin, User};
	
	private UserAccessRole userAccessRole;

	/**
	 * Constructor for the class. Sets the user access role to unassigned.
	 */
	public Role()
	{
		setUserAccessRole(Role.UserAccessRole.Unassigned);
	}
	
	/**
	 * get the user accessID
	 * 
	 * @return the user access ID
	 */
	public UserAccessRole getUserAccessRole() {
		return userAccessRole;
	}

	/**
	 * set the user access role
	 * 
	 * @param UserAccessRole { Unassigned, Admin, User}.
	 */
	public void setUserAccessRole(UserAccessRole userAccessRole) 
	{
			this.userAccessRole = userAccessRole;
	}
} 
