package lang;


public class StringUtil {

    public static boolean strIsNullOrEmpty(String obj) {
        if(obj==null)
        {
            return  true;
        }
        else if(" ".equals(obj))
        {
            return true;
        }
        else
        {
            return  obj.isEmpty();
        }
    }
}
