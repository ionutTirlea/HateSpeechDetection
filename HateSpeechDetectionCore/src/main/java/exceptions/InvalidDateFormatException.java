package exceptions;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException(){
        super("Not a valid YYYY-mm-dd date!");
    }
}
