package cis234a.nsort.controller;

import cis234a.nsort.model.*;
import cis234a.nsort.view.*;
/**
 * RankingSystemController class is the main controller for the Ranking System application following the MVC design pattern. 
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/6/2015)
 */

public class RankingSystemController {
	
	private User user;
	private SqlUser_234a_t1 sqlUser;             

	public RankingSystemController() 
	{
		user = new User();
		
		sqlUser = new SqlUser_234a_t1();
	}

	public void launchLogin() 
	{
		LoginModel model = new LoginModel();
		model.setUser(user);
		
		LoginView view = new LoginFrame();
		LoginController controller = new LoginController(this, model, view);   
		view.registerController(controller);                                        
	}
	
	public void launchAdminTestSetup()
	{
		AdminTestSetupModel model = new AdminTestSetupModel();
		model.setExistingItemsList(sqlUser.pullExistingItems());
		model.setTestItemsList(sqlUser.pullTestItems());
		model.setUser(user);
		
		AdminTestSetupView view = new AdminTestSetupFrame();
		AdminTestSetupController controller = new AdminTestSetupController(this, model, view);    
		view.registerController(controller);                                                      
	}
	
	public void launchUserTest()
	{
		UserTestModel model = new UserTestModel();
		model.setTestItemsList(sqlUser.pullTestItems());	
		model.setUserTest();
		model.setUser(user);
		
		UserTestView view = new UserTestFrame();
		UserTestController controller = new UserTestController(this, model, view);    
		view.registerController(controller);          
	}
}
