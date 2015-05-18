package cis234a.nsort.controller;

import cis234a.nsort.model.*;
import cis234a.nsort.view.*;

public class LoginController {
	
	private RankingSystemController controller;
	
	private LoginModel model;
	private LoginView view;
	
	private SqlUser_234a_t1 sqlUser;

	public LoginController(RankingSystemController controller, LoginModel model, LoginView view) {
		this.controller = controller;
		this.model = model;
		this.view = view;

		sqlUser = new SqlUser_234a_t1();
		
		updateView();   
	}

	private void updateView() 
	{
		view.updateLoginFrame(model.getLoggedInState());                        //when loginState is false, loginFrame is shown; when true, loginFrame is hidden
	}
	
	public boolean loginAttempt(String username)
	{
		if (sqlUser.validateUser(username))                                     //successful login      
		{
			model.setValidatedUser(sqlUser.getUser(username));                  //get the user from the database and set the model
			model.setLoginState(true);                                          //login the user
			model.setUserAccessRole(sqlUser.getUserAccessRole(username));       //get userAccessRole from the database and set the model
			return true;
		}
		else                                                                    //failed login
		{
			return false;
		}
	}
		
	public String getFullUserName()
	{
		return model.getUserFullName();
	}
	
	public void launchAdminTestSetupFrame()
	{
		controller.launchAdminTestSetup();                                  //back to the controller to launch the AdminTestSetup

	}
	
	public void hideLogin()
	{
		updateView();
	}
	
	public boolean checkUserAccessRoleForAdmin()
	{
		if (model.getUserAccessRole() == Role.UserAccessRole.Admin)
		{
			return true;
		}
		else {return false;}
	}

	public boolean checkUserAccessRoleForUser()
	{
		if (model.getUserAccessRole() == Role.UserAccessRole.User)
		{
			return true;
		}
		else {return false;}
	}
	
	public void launchUserTestFrame()
	{
		controller.launchUserTest();
	}
}
