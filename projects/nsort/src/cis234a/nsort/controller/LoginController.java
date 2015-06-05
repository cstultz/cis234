package cis234a.nsort.controller;

import cis234a.nsort.model.*;
import cis234a.nsort.view.*;
/**
 * The LoginController handles the user login validation (valid username in the database), user login state (logged in & logged out), 
 * and UserAccessRole logic (Admin or User).
 *   
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class LoginController {
	
	private RankingSystemController controller;
	
	private LoginModel model;
	private LoginView view;
	
	private SqlUser_234a_t1 sqlUser;

	/**
	 * Constructor for the class.
	 * @param RankingSystemController for communication back.
	 * @param LoginModel will directly manage the data, logic and rules of the login.
	 * @param LoginView output representation of the login information.
	 */
	public LoginController(RankingSystemController controller, LoginModel model, LoginView view) {
		this.controller = controller;
		this.model = model;
		this.view = view;

		sqlUser = SqlUser_234a_t1.INSTANCE;
		
		updateView();   
	}

	/**
	 * update the view shows the login frame
	 */
	private void updateView() 
	{
		//when loginState is false, loginFrame is shown (logged off); when true, loginFrame is hidden (logged in)
		view.updateLoginFrameState(model.getLoggedInState());                        
	}
	
	/**
	 * user has attempted to login.
	 * @param username entered by the user.
	 * @return if the username is a valid username in the database.
	 */
	public boolean loginAttempt(String username)
	{
		if (sqlUser.validateUser(username))                                     //successful login      
		{
			model.setValidatedUser(sqlUser.getUser(username));                  //get the user from the database and set the model
			model.setUserAccessRole(sqlUser.getUserAccessRole(username));       //get userAccessRole from the database and set the model
			model.setLoginState(true);                                          //login the user
			return true;
		}
		else                                                                    //failed login
		{
			return false;
		}
	}
		
	/**
	 * return the full name of the user
	 * @return the user firstName and lastName concatenated
	 */
	public String getFullUserName()
	{
		return model.getUserFullName();
	}
	
	/**
	 * launch the admin test setup frame
	 */
	public void launchAdminTestSetup()
	{
		controller.launchAdminTestSetup();                                  //back to the controller to launch the AdminTestSetup

	}
	
	/**
	 * hide the login frame (user is logged in)
	 */
	public void login()
	{
		model.setLoginState(true);
		updateView();
	}
	
	/**
	 * check to see if the the user access role is administrator 
	 * @return true if user access role is 'Admin'; false if not.
	 */
	public boolean checkUserAccessRoleForAdmin()
	{
		if (model.getUserAccessRole() == Role.UserAccessRole.Admin)
		{
			return true;
		}
		else {return false;}
	}

	/**
	 * check to see if the the user access role is a User. 
	 * @return true if user access role is 'User'; false if not.
	 */
	public boolean checkUserAccessRoleForUser()
	{
		if (model.getUserAccessRole() == Role.UserAccessRole.User)
		{
			return true;
		}
		else {return false;}
	}
	
	/**
	 * launch the user test frame (User logged in and ready to take the test)
	 */
	public void launchUserTestFrame()
	{
		controller.launchUserTest();
	}
	
	/**
	 * launch the user registration frame (user logged off and signing up)
	 */
	public void launchRegistrationFrame()
	{
		controller.launchRegistration();
	}
}