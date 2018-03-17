import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import utils.ConnectionUtilities;
import utils.SSLUtilities;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by ionut.tirlea on 01/12/2017.
 */
public class HateBaseSearch {

    private int page;
    private boolean isLastPage;
    private List<HatebaseVocabularyModel> result;
    private HatebaseRequestModel hatebaseRequestModel;
    private Map<Integer, String> responseCache;

    public HateBaseSearch(HatebaseRequestModel hatebaseRequestModel) {
        this.hatebaseRequestModel = hatebaseRequestModel;
        this.page = 1;
        this.responseCache = new HashMap<>();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        hatebaseRequestModel.setPage(page);
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    private void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public List<HatebaseVocabularyModel> getResult() {
        return result;
    }

    public boolean getCurrentPage() {
        return get();
    }

    public boolean getFirstPage() {
        setPage(1);
        return get();
    }

    public boolean getNextPage() {
        if (isLastPage()) {
            return false;
        }
        setPage(page + 1);
        return get();
    }

    private boolean get() {
        if (responseCache.get(getPage()) == null) {
            SSLUtilities.trustAllCertificates();
            HttpsURLConnection connection = null;
            try {
                URL url = new URL(hatebaseRequestModel.getRequestURL());
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                responseCache.put(getPage(), ConnectionUtilities.readResponse(connection));
                System.out.println("Executed Hatebase API call : requestURL=" + hatebaseRequestModel.getRequestURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(connection!=null)
                    connection.disconnect();
            }
        }

        JSONObject jsonObject = (JSONObject) JSONValue.parse(responseCache.get(getPage()));
        String requestPage = (String) jsonObject.get("page");

        if (Integer.valueOf(requestPage) != this.getPage()) {
            return false;
        }

        int number_of_results_on_this_page = Integer.parseInt((String) jsonObject.get("number_of_results_on_this_page"));
        boolean isLastPage = number_of_results_on_this_page < 100;

        this.setLastPage(isLastPage);

        result = new ArrayList<>();

        String vocabulary, meaning, language;
        Double offensiveness;
        boolean aboutEthnicity, aboutNationality, aboutReligion, aboutGender, aboutSexualOrientation, aboutDisability, aboutClass;
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONArray datapoints = (JSONArray) data.get("datapoint");
        Iterator i = datapoints.iterator();
        do {
            JSONObject datapoint = (JSONObject) i.next();

            vocabulary = (String) datapoint.get("vocabulary");
            meaning = (String) datapoint.get("meaning");
            if(datapoint.get("language") instanceof String) {
                language = (String) datapoint.get("language");
            } else {
                language = "";
            }
            offensiveness = Double.parseDouble((String) datapoint.get("offensiveness"));

            aboutEthnicity = datapoint.get("about_ethnicity").equals("1");
            aboutNationality = datapoint.get("about_nationality").equals("1");
            aboutReligion = datapoint.get("about_religion").equals("1");
            aboutGender = datapoint.get("about_gender").equals("1");
            aboutSexualOrientation = datapoint.get("about_sexual_orientation").equals("1");
            aboutDisability = datapoint.get("about_disability").equals("1");
            aboutClass = datapoint.get("about_class").equals("1");

            HatebaseVocabularyModel hatebaseVocabularyModel = new HatebaseVocabularyModel();
            hatebaseVocabularyModel.setVocabulary(vocabulary);
            hatebaseVocabularyModel.setMeaning(meaning);
            hatebaseVocabularyModel.setLanguage(language);
            hatebaseVocabularyModel.setOffensiveness(offensiveness);
            hatebaseVocabularyModel.setAboutEthnicity(aboutEthnicity);
            hatebaseVocabularyModel.setAboutNationality(aboutNationality);
            hatebaseVocabularyModel.setAboutReligion(aboutReligion);
            hatebaseVocabularyModel.setAboutGender(aboutGender);
            hatebaseVocabularyModel.setAboutSexualOrientation(aboutSexualOrientation);
            hatebaseVocabularyModel.setAboutDisability(aboutDisability);
            hatebaseVocabularyModel.setAboutClass(aboutClass);

            result.add(hatebaseVocabularyModel);
        }  while (i.hasNext());
        return true;
    }

}
