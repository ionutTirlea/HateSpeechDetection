import java.util.ArrayList;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class Test {
    public static void main(String[] args){

        ArrayList<HatebaseVocabularyModel> results = new ArrayList<>();

        HatebaseRequestModel hatebaseRequestModel = new HatebaseRequestModel();
        HateBaseSearch hateBaseSearch = new HateBaseSearch(hatebaseRequestModel);

        hateBaseSearch.getFirstPage();
        do{
            results.addAll(hateBaseSearch.getResult());
            hateBaseSearch.getNextPage();
        } while (!hateBaseSearch.isLastPage());
        hateBaseSearch.getCurrentPage();
        results.addAll(hateBaseSearch.getResult());

        System.out.println(results.size());
    }
}
