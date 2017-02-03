package fredkobo.co.za.codeproject.presentation.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by frederickkobo on 2017/02/02.
 */

public class Utils {

    public static boolean isValidDate(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            df.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
