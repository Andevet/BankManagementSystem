package bankException;

public class MounthlyPaymentException extends GeneralException {
	private static final String MESSEGE = "You enter invaild number\nThe Payment must be above 0";
	public MounthlyPaymentException() {
		super(MESSEGE); 
	}
}
