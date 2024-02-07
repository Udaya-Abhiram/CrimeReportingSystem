package CustomExceptions;

public class CrimeNotFoundException extends Exception{
    public CrimeNotFoundException(String message){
        super(message);
    }
}
