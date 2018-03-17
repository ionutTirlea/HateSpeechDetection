package formatvalidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ionut.tirlea on 19/03/2017.
 */
public class DateFormatValidator {
    public static boolean isValidDate(String date, String format){
        Date d = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            d = sdf.parse(date);
            if (!date.equals(sdf.format(d))) {
                d = null;
            }
        } catch (ParseException ex) {
        }
        if (d == null) {
            return false;
        } else {
            return true;
        }
    }
}
