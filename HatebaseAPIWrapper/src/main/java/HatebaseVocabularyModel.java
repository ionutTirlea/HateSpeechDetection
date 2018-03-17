import java.io.Serializable;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class HatebaseVocabularyModel implements Serializable{
    private String vocabulary;
    private String meaning;
    private String language;
    private Double offensiveness;
    private boolean aboutEthnicity;
    private boolean aboutNationality;
    private boolean aboutReligion;
    private boolean aboutGender;
    private boolean aboutSexualOrientation;
    private boolean aboutDisability;
    private boolean aboutClass;


    public String getVocabulary() {
        return vocabulary;
    }

    protected void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getMeaning() {
        return meaning;
    }

    protected void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getLanguage() {
        return language;
    }

    protected void setLanguage(String language) {
        this.language = language;
    }

    public Double getOffensiveness() {
        return offensiveness;
    }

    protected void setOffensiveness(Double offensiveness) {
        this.offensiveness = offensiveness;
    }

    public boolean isAboutEthnicity() {
        return aboutEthnicity;
    }

    protected void setAboutEthnicity(boolean aboutEthnicity) {
        this.aboutEthnicity = aboutEthnicity;
    }

    public boolean isAboutNationality() {
        return aboutNationality;
    }

    protected void setAboutNationality(boolean aboutNationality) {
        this.aboutNationality = aboutNationality;
    }

    public boolean isAboutReligion() {
        return aboutReligion;
    }

    protected void setAboutReligion(boolean aboutReligion) {
        this.aboutReligion = aboutReligion;
    }

    public boolean isAboutGender() {
        return aboutGender;
    }

    protected void setAboutGender(boolean aboutGender) {
        this.aboutGender = aboutGender;
    }

    public boolean isAboutSexualOrientation() {
        return aboutSexualOrientation;
    }

    public void setAboutSexualOrientation(boolean aboutSexualOrientation) {
        this.aboutSexualOrientation = aboutSexualOrientation;
    }

    public boolean isAboutDisability() {
        return aboutDisability;
    }

    protected void setAboutDisability(boolean aboutDisability) {
        this.aboutDisability = aboutDisability;
    }

    public boolean isAboutClass() {
        return aboutClass;
    }

    protected void setAboutClass(boolean aboutClass) {
        this.aboutClass = aboutClass;
    }
}
