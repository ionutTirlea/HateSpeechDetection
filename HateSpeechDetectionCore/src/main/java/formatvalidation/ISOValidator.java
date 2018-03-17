package formatvalidation; /**
 * Created by ionut.tirlea on 19/03/2017.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ISOValidator {

    private ArrayList<String> isoLanguages;
    private ArrayList<String> isoCountries;

    private static ISOValidator instance;

    private ISOValidator(){
        this.isoLanguages = new ArrayList<>(Arrays.asList(Locale.getISOLanguages()));
        this.isoCountries = new ArrayList<>(Arrays.asList(Locale.getISOCountries()));
    }

    public static ISOValidator getInstance(){
        if(instance == null){
            instance = new ISOValidator();
        }
        return  instance;
    }

    public boolean isValidISOLanguage(String language){
        return isoLanguages.contains(language);
    }
    public  boolean isValidISOCountry(String country){
        return isoCountries.contains(country);
    }
}
