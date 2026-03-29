package bankException;

public class CheckedException extends DuplicationException{
	private final int number;
   public CheckedException(int userInput) {
        super(userInput + "already exist");
        this.number = userInput;
    }

}
