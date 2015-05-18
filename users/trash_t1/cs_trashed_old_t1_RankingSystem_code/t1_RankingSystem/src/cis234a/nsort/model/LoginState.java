package cis234a.nsort.model;

public class LoginState {

	private boolean loginState;

	public LoginState()
	{
		setLoginState(false);
	}

	public boolean getLoginState() {
		return loginState;
	}

	public void setLoginState(boolean loginState) {
		this.loginState = loginState;
	}
}
