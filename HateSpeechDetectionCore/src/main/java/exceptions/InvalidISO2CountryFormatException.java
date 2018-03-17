package exceptions;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class InvalidISO2CountryFormatException extends Exception {
    public InvalidISO2CountryFormatException(){
        super("Not a valid 2-character ISO 3166-2 country code!");
    }
}
