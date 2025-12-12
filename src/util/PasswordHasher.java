package util;

public class PasswordHasher {

    public static boolean verify(String password, String hash){
        if((hash(password)).equals(hash)){
            return true;
        }
        return false;
    }

    public static String hash(String password){
        return ((Integer)(password.hashCode())).toString();
    } 
}
