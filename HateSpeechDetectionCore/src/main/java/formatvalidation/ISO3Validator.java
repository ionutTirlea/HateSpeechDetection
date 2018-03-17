package formatvalidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class ISO3Validator {
    private ArrayList<String> isoLanguages;
    private ArrayList<String> isoCountries;

    private static ISO3Validator instance;

    private ISO3Validator(){
        this.isoLanguages = new ArrayList<>();
        this.isoCountries = new ArrayList<>();
        for(String language:Arrays.asList(Locale.getISOLanguages())){
            Locale l = new Locale(language);
            isoLanguages.add(l.getISO3Language());
            isoCountries.add(l.getISO3Country());
        }
    }

    public static ISO3Validator getInstance(){
        if(instance == null){
            instance = new ISO3Validator();
        }
        return instance;
    }
    public boolean isValidISO3Language(String language){
        return isoLanguages.contains(language);
    }
    public  boolean isValidISO3Country(String country){
        return isoCountries.contains(country);
    }
}
