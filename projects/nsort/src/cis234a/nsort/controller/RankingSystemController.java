package cis234a.nsort.controller;

import cis234a.nsort.model.*;
import cis234a.nsort.view.*;
/**
 * The RankingSystemController is the parent controller for the 4 child controllers (Login, AdminTestSetup, UserTest, and Report).
 * This class handles construction of the MVC and passes database data to the models before instantiating the views.  
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public enum RankingSystemController {
	
	INSTANCE();
	
	private User user;
	private SqlUser_234a_t1 sqlUser;             

	/**
	 * Constructor for the class.
	 */
	RankingSystemController() 
	{
		user = new User();
		sqlUser = SqlUser_234a_t1.INSTANCE;
	}

	/**
	 * launch the login frame (user logged off)
	 */
	public void launchLogin() 
	{
		LoginModel model = new LoginModel();
		model.setUser(user);
		
		LoginView view = new LoginFrame();
		LoginController controller = new LoginController(this, model, view);   
		view.registerController(controller);                                        
	}
	
	/**
	 * launch the admin test setup frame ('Admin' user logged in)
	 */
	public void launchAdminTestSetup()
	{
		AdminTestSetupModel model = new AdminTestSetupModel();
		model.setExistingItemsList(sqlUser.pullExistingItems());
		model.setTestItemsList(sqlUser.pullTestItemsAndImages());
		model.setUser(user);
		
		AdminTestSetupView view = new AdminTestSetupFrame();
		AdminTestSetupController controller = new AdminTestSetupController(this, model, view);    
		view.registerController(controller);                                                      
	}
	
	/**
	 * launch the user test frame ('User' logged in)
	 */
	public void launchUserTest()
	{
		UserTestModel model = new UserTestModel();
		model.setTestItemsList(sqlUser.pullTestItemsAndImages());
		model.setUserTest();
		model.setUser(user);
		
		UserTestView view = new UserTestFrame();
		UserTestController controller = new UserTestController(this, model, view);    
		view.registerController(controller);          
	}
	
	/**
	 * launch the user registration frame (user logged off - signing up)
	 */
	public void launchRegistration()
	{
		RegistrationModel model = new RegistrationModel();
		RegistrationView view = new RegistrationFrame();
		RegistrationController controller = new RegistrationController(this, model, view);   
		view.registerController(controller);      
	}
}
