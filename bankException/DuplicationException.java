package bankException;

public class DuplicationException extends Exception {
    private static final String PRE_ERROR = "Cant add because: \n";
    public DuplicationException(String message) {
    	super(PRE_ERROR + message);
    }

}
