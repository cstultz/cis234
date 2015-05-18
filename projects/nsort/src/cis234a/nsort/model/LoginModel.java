package cis234a.nsort.model;

import java.util.ArrayList;
/**
 * The LoginModel class captures the behavior of the user login independent of the user interface.   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class LoginModel {
	
	private User user;

	/**
	 * Constructor for the class.
	 */
	public LoginModel()
	{
		//do nothing
	}

	/**
	 * Set the user login in state to true
	 * @param loginState of the user; true for logged in; false for not.
	 */
	public void setLoginState(boolean loginState) {
		user.setLoginState(loginState);
	}

	/**
	 * Get the login state of the user
	 * @return true if user is logged in; false if not.
	 */
	public boolean getLoggedInState() {
		return user.getLoginState();
	}
	
	/**
	 * set the user in the model
	 * @param user to be set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
	
	/**
	 * set the validated user info in the model
	 * 
	 * @param ArrayList<String> containg 4 elements of user info; 0 firstName; 1 lastName; 2 Email; 3 username. 
	 */
	public void setValidatedUser(ArrayList<String> user)
	{
		this.user.setFirstName(user.get(0));                //0 index is First Name 
		this.user.setLastName (user.get(1));                //1 index is Last Name   
		this.user.setEmail    (user.get(2));                //2 index is Email   
		this.user.setUsername (user.get(3));                //3 index is Username 
	}
	
	/**
	 * get the user access role from the model
	 * 
	 * @return the Role.UserAccessRole {Admin, User, or Unassigned}
	 */
	public Role.UserAccessRole getUserAccessRole()
	{
		return user.getUserAccessRole();
	}
	
	/**
	 * set the user access role in the model
	 * 
	 * @param userAccessRole {Admin, User, or Unassigned}
	 */
	public void setUserAccessRole(Role.UserAccessRole userAccessRole)
	{
		user.setUserAccessRole(userAccessRole);
	}
	
	/**
	 * get the full name of the user
	 * @return user firstName and lastName concatenated
	 */
	public String getUserFullName()
	{
		return user.getFirstName() + " " + user.getLastName();
	}
}
