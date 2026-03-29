package bankException;

public class AccountNotFoundException extends GeneralException{
    public AccountNotFoundException(String message) {
        super(message);
    }

}
