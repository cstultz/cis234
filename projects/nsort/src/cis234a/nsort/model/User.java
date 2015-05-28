package cis234a.nsort.model;
/**
 * The User class captures user personal info, the user access role, the user login state and the user unique test session ID. 
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (5/6/2015)
 */
public class User
{
    private String firstName;
    private String lastName;
    private String eMail;
    private String username;
    private Role role;
    private LoginState loginState;

	private int testSessionID; 

    /**
     * Constructor for the class. set the user access role. set the user login state.
     */
    public User()
    {
    	role = new Role();
    	loginState = new LoginState();
    }

    /**
     * get the first name of the user.
     * 
     * @return firstName of the user.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * get the last name of the user.
     * 
     * @return lastName of the user.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * get the e-mail of the user.
     * 
     * @return eMail of the user.
     */
    public String getEmail()
    {
        return eMail;
    }

    /**
     * get the username of the user.
     * 
     * @return username of the user.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * get the role of the user access ID either Admin or User
     * 
     * @return the role of the user.
     */
    public Role.UserAccessRole getUserAccessRole() {
		return role.getUserAccessRole();
	}

    /**
     * get the Unique Test Session ID of the test currently being taken.
     * 
     * @return the unique test session ID of the test being taken.
     */
    public int getTestSessionID() {
		return testSessionID;
	}

    /**
     * get the login state of the user.
     * 
     * @return true if the user is logged in; false if not.
     */
    public boolean getLoginState() {
		return loginState.getLoginState();
	}

    /**
     * set the first name of the user.
     * 
     * @param firstName of the user.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * set the last name of the user.
     * 
     * @param lastName of the user.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * set the e-mail of the user.
     * 
     * @param email of the user.
     */
    public void setEmail(String eMail)
    {
        this.eMail = eMail;
    }

    /**
     * set the username of the user.
     * 
     * @param username of the user.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * set the user access role of the user access ID either Admin or User.
     * 
     * @param role of the user access ID.
     */

	public void setUserAccessRole(Role.UserAccessRole role) {
		this.role.setUserAccessRole(role);
	}
	
    /**
     * set the unique test session ID of the test currently being taken.
     * 
     * @param the test session ID of the test being taken.
     */
	public void setTestSessionID(int testSessionID) {
		this.testSessionID = testSessionID;
	}
	
	/**
	 * Set the login state of the user.
	 * 
	 * @param loginState true if user is logged in; false if not.
	 */
	public void setLoginState(boolean loginState) {
		this.loginState.setLoginState(loginState);
	}

}
