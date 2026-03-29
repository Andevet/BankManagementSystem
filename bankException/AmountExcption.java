package bankException;

public class AmountExcption extends GeneralException{
	private static final String MESSEGE = "\nYou enter unlogic ";
	public AmountExcption(String Messege) {
		super(MESSEGE + Messege);
	}

}
