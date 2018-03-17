package textprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ionut.tirlea on 15/01/2018.
 */
public class TwitterDFEDatasetCSVParser {
    public static void read(String csvFile){
        String line = "";
        String cvsSplitBy = ",";
        line = "853733898,FALSE,finalized,3,1/13/16 4:54,The tweet is not offensive,0.3418,,,,,,,,,,,,6.80E+17,I'm sorry. Did I offend your white supremacist, Aryan Nation, neo-Nazi, best fascist friends somehow? \n";
        line.split(",");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if(!(data.length < 21)){
                    System.out.println(data[5] + "," + data[20] + "line :"+ line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
