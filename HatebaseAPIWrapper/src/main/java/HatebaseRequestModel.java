import exceptions.InvalidDateFormatException;
import exceptions.InvalidISO2CountryFormatException;
import exceptions.InvalidISO3LanguageException;
import formatvalidation.DateFormatValidator;
import formatvalidation.ISO3Validator;
import formatvalidation.ISOValidator;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class HatebaseRequestModel implements Serializable{

    private static final String requestBaseURL = "https://api.hatebase.org/v3-0/8aa11e6a37c347f2fb8485f1f955aff3/vocabulary/json/";

    private static final String KEY_SEPARATOR = "%7C";
    private static final String KEY_VALUE_SEPARATOR = "%3D";

    private HashMap<String, String> requestParametersHashMap;

    private String vocabulary;
    private String variantOf;
    /* 3-character ISO 639-3 code */
    private String language;
    private boolean aboutEthnicity;
    private boolean aboutNationality;
    private boolean aboutReligion;
    private boolean aboutGender;
    private boolean aboutSexualOrientation;
    private boolean aboutDisability;
    private boolean aboutClass;
    private boolean archaic;
    private int page;
    /* 2-character ISO 3166-2 code */
    private String country;
    private String cityOrCommunity;
    private RequestType type;
    /* yyyy-MM-dd */
    private String startDate;
    /* yyyy-MM-dd */
    private String endDate;

    public HatebaseRequestModel(){
        requestParametersHashMap = new HashMap<>();
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
        this.requestParametersHashMap.put("vocabulary", vocabulary);
    }

    public String getVariantOf() {
        return variantOf;
    }

    public void setVariantOf(String variantOf) {
        this.variantOf = variantOf;
        this.requestParametersHashMap.put("variant_of", variantOf);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) throws InvalidISO3LanguageException {
        ISO3Validator iso3Validator = ISO3Validator.getInstance();
        if(!iso3Validator.isValidISO3Language(language)){
            throw new InvalidISO3LanguageException();
        }
        this.language = language;
        this.requestParametersHashMap.put("language", language);
    }

    public boolean isAboutEthnicity() {
        return aboutEthnicity;
    }

    public void setAboutEthnicity(boolean aboutEthnicity) {
        this.aboutEthnicity = aboutEthnicity;
        this.requestParametersHashMap.put("about_ethnicity", getAPIBooleanFormat(aboutEthnicity));
    }

    public boolean isAboutNationality() {
        return aboutNationality;
    }

    public void setAboutNationality(boolean aboutNationality) {
        this.aboutNationality = aboutNationality;
        this.requestParametersHashMap.put("about_nationality", getAPIBooleanFormat(aboutNationality));
    }

    public boolean isAboutReligion() {
        return aboutReligion;
    }

    public void setAboutReligion(boolean aboutReligion) {
        this.aboutReligion = aboutReligion;
        this.requestParametersHashMap.put("about_religion", getAPIBooleanFormat(aboutReligion));
    }

    public boolean isAboutGender() {
        return aboutGender;
    }

    public void setAboutGender(boolean aboutGender) {
        this.aboutGender = aboutGender;
        this.requestParametersHashMap.put("about_gender", getAPIBooleanFormat(aboutGender));
    }

    public boolean isAboutSexualOrientation() {
        return aboutSexualOrientation;
    }

    public void setAboutSexualOrientation(boolean aboutSexualOrientation) {
        this.aboutSexualOrientation = aboutSexualOrientation;
        this.requestParametersHashMap.put("about_sexual_orientation", getAPIBooleanFormat(aboutSexualOrientation));
    }

    public boolean isAboutDisability() {
        return aboutDisability;
    }

    public void setAboutDisability(boolean aboutDisability) {
        this.aboutDisability = aboutDisability;

    }

    public boolean isAboutClass() {
        return aboutClass;
    }

    public void setAboutClass(boolean aboutClass) {
        this.aboutClass = aboutClass;
        this.requestParametersHashMap.put("about_class", getAPIBooleanFormat(aboutClass));
    }

    public boolean isArchaic() {
        return archaic;
    }

    public void setArchaic(boolean archaic) {
        this.archaic = archaic;
        this.requestParametersHashMap.put("archaic", getAPIBooleanFormat(aboutClass));
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.requestParametersHashMap.put("page", String.valueOf(page));
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws InvalidISO2CountryFormatException {
        ISOValidator isoValidator = ISOValidator.getInstance();
        if(!isoValidator.isValidISOCountry(country)){
            throw new InvalidISO2CountryFormatException();
        }
        this.country = country;
        this.requestParametersHashMap.put("country", country);
    }

    public String getCityOrCommunity() {
        return cityOrCommunity;
    }

    public void setCityOrCommunity(String cityOrCommunity) {
        this.cityOrCommunity = cityOrCommunity;
        this.requestParametersHashMap.put("city_or_community", cityOrCommunity);
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
        this.requestParametersHashMap.put("type", type.getType());
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws InvalidDateFormatException {
        if(!DateFormatValidator.isValidDate(startDate,"yyyy-MM-dd")){
            throw new InvalidDateFormatException();
        }
        this.startDate = startDate;
        this.requestParametersHashMap.put("start_date", startDate);
    }

    public String getEndDate() throws InvalidDateFormatException {
        return endDate;
    }

    public void setEndDate(String endDate) throws InvalidDateFormatException {
        if(!DateFormatValidator.isValidDate(endDate,"yyyy-MM-dd")){
            throw new InvalidDateFormatException();
        }
        this.endDate = endDate;
        this.requestParametersHashMap.put("end_date", endDate);
    }

    private String getAPIBooleanFormat(boolean value){
        if(value){
            return "1";
        } else {
            return "0";
        }
    }

    private String addParameter(String baseURL, String parameterKey, String parameterValue){
        if(parameterValue.isEmpty()) return baseURL;
        return baseURL + parameterKey + KEY_VALUE_SEPARATOR + parameterValue + KEY_SEPARATOR;
    }

    public String getRequestURL(){
        String requestURL;
        requestURL = requestBaseURL;
        for(String key: requestParametersHashMap.keySet()){
            requestURL = addParameter(requestURL, key, requestParametersHashMap.get(key));
        }
        return requestURL;
    }

    @Override
    public String toString() {
        return "HatebaseRequestModel{" +
                "vocabulary='" + vocabulary + '\'' +
                ", variantOf='" + variantOf + '\'' +
                ", language='" + language + '\'' +
                ", aboutEthnicity=" + aboutEthnicity +
                ", aboutNationality=" + aboutNationality +
                ", aboutReligion=" + aboutReligion +
                ", aboutGender=" + aboutGender +
                ", aboutSexualOrientation=" + aboutSexualOrientation +
                ", aboutDisability=" + aboutDisability +
                ", aboutClass=" + aboutClass +
                ", archaic=" + archaic +
                ", page=" + page +
                ", country='" + country + '\'' +
                ", cityOrCommunity='" + cityOrCommunity + '\'' +
                ", type='" + type + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

}

