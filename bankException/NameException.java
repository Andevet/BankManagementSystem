package bankException;

public class NameException extends GeneralException {
	private static final String MESSEGE = "You enter unknown name ";
	public NameException(String s) {
		super(s != null && !s.isEmpty() ? s : MESSEGE); // אם ההודעה ריקה, השתמש בברירת המחדל
	}

}
