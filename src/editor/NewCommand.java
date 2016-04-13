package editor;

import java.io.File;

import javax.swing.JTextArea;

import action.ClearAction;
import frame.MainFrame;


public class NewCommand {
    
    public static void NewCommand(MainFrame frame, JTextArea area) {
        AlertSave alertsave = AlertSave.getSingleton();
        FileControl filecontrol = FileControl.getSingleton();
        File file = FileControl.getFileName();
        
        if( AlertSave.getUpdate() ) {
            int i = AlertSave.alertSave(frame, file);
            if(i == 0) {
                SaveasCommand.SaveasCommand(frame, area);
                New();
            } else if (i == 1) {
                New();
            }
        } else {        
            New();
        }
        
    }
    
    private static void New () {
        ClearAction.ClearAction();        
        FileControl.setFileName(null);        
        AlertSave.setUpdate(null, false, FileControl.getFileName());
    }

}