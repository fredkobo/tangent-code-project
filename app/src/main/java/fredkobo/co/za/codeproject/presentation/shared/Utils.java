package fredkobo.co.za.codeproject.presentation.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by frederickkobo on 2017/02/02.
 */

public class Utils {

    public static Date getDateFromString(String dateString) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }
}
