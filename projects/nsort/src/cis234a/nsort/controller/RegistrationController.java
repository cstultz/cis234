package cis234a.nsort.controller;

import java.util.ArrayList;

import cis234a.nsort.model.*;
import cis234a.nsort.view.*;
/**
 * The RegistrationController handles the user registration validation (valid username in the database), user registration state (logged in & logged out), 
 * and UserAccessRole logic (Admin or User).
 *   
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class RegistrationController {
	
	private RankingSystemController controller;
	
	private RegistrationModel model;
	private RegistrationView view;
	
	private SqlUser_234a_t1 sqlUser;

	/**
	 * Constructor for the class.
	 * @param RankingSystemController for communication back.
	 * @param RegistrationModel will directly manage the data, logic and rules of the registration.
	 * @param RegistrationView output representation of the registration information.
	 */
	public RegistrationController(RankingSystemController controller, RegistrationModel model, RegistrationView view) {
		this.controller = controller;
		this.model = model;
		this.view = view;

		sqlUser = new SqlUser_234a_t1();
		
		updateView();   
	}

	/**
	 * update the view shows the registration frame
	 */
	private void updateView() 
	{
		//when registrationState is false, registrationFrame is shown (logged off); when true, registrationFrame is hidden (logged in)
		view.updateRegistrationFrameState(model.getRegistrationState());                        
	}
	
	/**
	 * hide the registration frame (user is logged in)
	 */
	public void hideRegistration()
	{
		model.setRegistrationState(true);
		updateView();
	}
	
	/**
	 * launch the login frame
	 */
	public void launchLogin()
	{
		controller.launchLogin();                                  

	}
	
	/**
	 * launch the user registration frame (user logged off and signing up)
	 */
	public void launchRegistrationFrame()
	{
		controller.launchRegistration();
	}
	
	public boolean CheckUsernameAvailability(String username)
	{
		return (!sqlUser.validateUser(username));
	}
	
	public void saveUser(ArrayList<String> user)
	{
		sqlUser.saveUser(user);
	}
	
	public void saveUserAccess(int userID)
	{
		sqlUser.saveUserAccess(userID);
	}
	
	public int getUserID(String username)
	{
		return sqlUser.getUserID(username);
	}
}