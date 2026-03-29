package bankException;

public class LimitInputException extends GeneralException {
	private static final String MESSEGE = "you must enter";
	public LimitInputException(String Messege) {
		super(MESSEGE + Messege);
	}

	
}
