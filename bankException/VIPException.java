package bankException;

public class VIPException extends Exception{
	private static final String NEW_VIP = "congratulations, your account become vip account";
	public VIPException() {
		super(NEW_VIP);
	}

}
