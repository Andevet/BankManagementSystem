package bankException;

public class RankException extends GeneralException {
	private static final String MESSAGE = "The rank number must be an Integer number between 1 - 10";
	public RankException() {
		super(MESSAGE);
	}
}
