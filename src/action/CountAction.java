package action;


public class CountAction {
 
    public static int CountAction(String text, int line) {      
        return text.length() - line;
    }
}