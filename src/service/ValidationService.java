import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidationService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; //allowed email pattern
    
    public static boolean validateEmail(String email){
        if (email == null) 
            return false; 

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateNotEmpty(String str){
        if (str!=null && !str.isEmpty()){
            return true;
        }
        return false;
    } 

    public static boolean validateSprintTimeline(LocalDate start, LocalDate end){
        if (start == null || end == null) {
            return false; // dates cannot be null
        }

        return !start.isAfter(end); // start <= end
    }
}
