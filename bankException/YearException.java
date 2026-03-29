package bankException;

public class YearException extends GeneralException {
	private static final String MESSEGE = "You enter wrong input for the year.\nYou must enter correct number above 0";
	public YearException() {
		super(MESSEGE);
	}

}
