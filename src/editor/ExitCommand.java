package editor;

import javax.swing.JTextArea;

import frame.MainFrame;

public class ExitCommand {
    
    public static void ExitCommand(MainFrame frame, JTextArea area) {
        AlertSave alertsave = AlertSave.getSingleton();
        if( AlertSave.getUpdate() ) {
            int i = AlertSave.alertSave(frame, null);
            if(i == 0) {
                SaveasCommand.SaveasCommand(area);
                System.exit(0);
            } else if (i == 1) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
    
}
