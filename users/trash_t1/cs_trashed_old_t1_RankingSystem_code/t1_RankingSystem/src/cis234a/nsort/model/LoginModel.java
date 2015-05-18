package cis234a.nsort.model;

import java.util.ArrayList;

public class LoginModel {
	
	private User user;

	/**
	 * Constructor
	 */
	public LoginModel()
	{
		//do nothing
	}

	/**
	 * Set the user login in state to true
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
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public void setValidatedUser(ArrayList<String> user)
	{
		this.user.setFirstName(user.get(0));                //0 index is First Name 
		this.user.setLastName (user.get(1));                //1 index is Last Name   
		this.user.setEmail    (user.get(2));                //2 index is Email   
		this.user.setUsername (user.get(3));                //3 index is Username 
	}
	
	public Role.UserAccessRole getUserAccessRole()
	{
		return user.getUserAccessRole();
	}
	
	public void setUserAccessRole(Role.UserAccessRole userAccessRole)
	{
		user.setUserAccessRole(userAccessRole);
	}
	
	public String getUserFullName()
	{
		return user.getFirstName() + " " + user.getLastName();
	}
}
