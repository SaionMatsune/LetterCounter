package editor;

import javax.swing.JTextArea;

import java.util.Calendar;

public class DateCommand {
    
    public static void DateCommand(JTextArea area) {
        Calendar calendar = Calendar.getInstance();
        
        String timedate = ""
                          + calendar.get(Calendar.HOUR_OF_DAY) 
                          + ":"
                          + calendar.get(Calendar.MINUTE)
                          + " "
                          + calendar.get(Calendar.YEAR)
                          + "/"
                          + (calendar.get(Calendar.MONTH) + 1)
                          + "/"
                          + calendar.get(Calendar.DATE)
                          + System.lineSeparator();
        
        area.append(timedate);
        AlertSave alertsave = AlertSave.getSingleton();
        AlertSave.setUpdate(area, true, null);
    }
}
//21:31 2016/04/13