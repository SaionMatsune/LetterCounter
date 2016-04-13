package editor;

import java.util.Calendar;

import javax.swing.JTextArea;

public class DateCommand {
    
    public static void DateCommand(JTextArea area) {
        Calendar calendar = Calendar.getInstance();
        String timedate = null;
        
        if(calendar.get(Calendar.MINUTE) < 10) {
            timedate = ""
                       + calendar.get(Calendar.HOUR_OF_DAY) 
                       + ":0"
                       + calendar.get(Calendar.MINUTE)
                       + " "
                       + calendar.get(Calendar.YEAR)
                       + "/"
                       + (calendar.get(Calendar.MONTH) + 1)
                       + "/"
                       + calendar.get(Calendar.DATE)
                       + System.lineSeparator();
        } else {
            timedate = ""
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
        }
        
        area.append(timedate);

        AlertSave alertsave = AlertSave.getSingleton();
        AlertSave.setUpdate(area, true, null);
    }

}