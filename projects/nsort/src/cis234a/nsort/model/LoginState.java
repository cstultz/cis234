package cis234a.nsort.model;
/**
 * The LoginState class captures the behavior of the user login state independent of the user interface.   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class LoginState {

	private boolean loginState;

	/**
	 * Constructor for the class.
	 */
	public LoginState()
	{
		setLoginState(false);  //initialize to false (logged off)
	}

	/**
	 * get the login state of the user.
	 * 
	 * @return true for logged in; false for logged off.
	 */
	public boolean getLoginState() {
		return loginState;
	}

	/**
	 * set the login state of the user.
	 * 
	 * @param loginState true for logged in; false for logged off.
	 */
	public void setLoginState(boolean loginState) {
		this.loginState = loginState;
	}
}
