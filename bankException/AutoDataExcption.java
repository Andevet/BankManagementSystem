package bankException;

public class AutoDataExcption extends GeneralException{
	private static final String MESSEGE = "You must enter 1 or 2";
	public AutoDataExcption() {
		super(MESSEGE);
	}
	
}
