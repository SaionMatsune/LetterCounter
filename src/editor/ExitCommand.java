package editor;

import frame.MainFrame;

public class ExitCommand {
    
    public static void ExitCommand(MainFrame frame) {
        AlertSave alertsave = AlertSave.getSingleton();
        if( AlertSave.getUpdate() ) {
            AlertSave.alertSave(frame, null);
        }
        
        System.exit(0);
    }
    
}
