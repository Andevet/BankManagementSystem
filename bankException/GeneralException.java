package bankException;

public class GeneralException extends Exception{
    private static final String PRE_ERROR = "Cant add because: \n";
	 public GeneralException(String message) {
        super(PRE_ERROR + message);
    }
}
