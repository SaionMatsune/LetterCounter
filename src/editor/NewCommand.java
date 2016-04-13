package editor;

import javax.swing.JTextArea;

import java.io.File;

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
                SaveasCommand.SaveasCommand(area);
                ClearAction.ClearAction();        
                FileControl.setFileName(null);        
                AlertSave.setUpdate(null, false, FileControl.getFileName());
            } else if (i == 1) {
                ClearAction.ClearAction();        
                FileControl.setFileName(null);        
                AlertSave.setUpdate(null, false, FileControl.getFileName());
            }
        } else {        
            ClearAction.ClearAction();        
            FileControl.setFileName(null);        
            AlertSave.setUpdate(null, false, FileControl.getFileName());        
        }
        
    }
    
}
/*
Yes
select:0->save,clear
select:1->clear
select:-1&2->nothing
No
clear
*/