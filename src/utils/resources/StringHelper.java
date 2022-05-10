package utils.resources;

public class StringHelper {
    public static String getSpaces(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += " ";
        }
        return result;
    }
}
