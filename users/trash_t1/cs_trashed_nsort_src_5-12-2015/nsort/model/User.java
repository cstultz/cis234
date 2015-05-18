package nsort.model;


/**
 * The User class stores the user's personal info, the login role, and the testSessionID of the test they are taking.
 * 
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class User
{
    private String firstName;
    private String lastName;
    private String eMail;
    private String username;
    private Role role;
    private int testSessionID; 

    /**
     * Constructor for objects of class User. all property values required when constructed.
     */
    public User(String firstName, String lastName, String eMail, String username)
    {
        // initialize instance variables
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(eMail);
        setUsername(username);
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
     * get the role of the user access ID either Admin or User
     * 
     * @return the role of the user
     */
    public Role getRole() {
		return role;
	}

    /**
     * set the user access role of the user access ID either Admin or User
     * 
     * @param role of the user access ID
     */

	public void setRole(Role role) {
		this.role = role;
	}
	
    /**
     * get the Unique Test Session ID of the test currently being taken.
     * 
     * @return the unique test session ID of the test being taken
     */
    public int getTestSessionID() {
		return testSessionID;
	}

    /**
     * set the unique test session ID of the test currently being taken.
     * 
     * @param the test session ID of the test being taken
     */
	public void setTestSessionID(int testSessionID) {
		this.testSessionID = testSessionID;
	}
}
