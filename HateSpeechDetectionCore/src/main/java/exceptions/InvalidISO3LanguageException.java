package exceptions;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class InvalidISO3LanguageException extends Exception{
    public InvalidISO3LanguageException(){
        super("Not a valid 3-character ISO 639-3 language code!");
    }
}
