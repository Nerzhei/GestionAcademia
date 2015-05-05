package other;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validator {

    public static boolean IsValidCi(String ci) {
        if (Pattern.matches("^\\d{8}$", ci)) return true;
        else return false;
    }

    public static boolean IsValidTel(String tel) {
        if (Pattern.matches("^\\d{4,20}$", tel)) return true;
        else return false;
    }

    public static String getString(Object o) {
        String string = "";
        try {
            string = o.toString();
        } catch (Exception e) {
        }
        return string;
    }


    @FunctionalInterface
    public interface GetFecha {
        LocalDate get(String fechadb);
    }
}
