package cis234a.nsort.model;

/**
 * The RegistrationModel class captures the behavior of the user registration independent of the user interface.   
 *  
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/8/2015)
 */
public class RegistrationModel {
	
	private boolean registrationState;

	/**
	 * Constructor for the class.
	 */
	public RegistrationModel()
	{
		//do nothing
	}

	/**
	 * Set the user registration in state to true
	 * @param registrationState of the user; true for logged in; false for not.
	 */
	public void setRegistrationState(boolean registrationState) {
		this.registrationState = registrationState;
	}

	/**
	 * Get the registration state of the user
	 * @return true if user is logged in; false if not.
	 */
	public boolean getRegistrationState() {
		return registrationState;
	}
}
