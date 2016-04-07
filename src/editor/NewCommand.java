package editor;

import java.io.File;

import action.ClearAction;
import frame.MainFrame;

public class NewCommand {
    
    public static void NewCommand(MainFrame frame) {
        AlertSave alertsave = AlertSave.getSingleton();
        FileControl filecontrol = FileControl.getSingleton();
        File file = FileControl.getFileName();
        System.out.println(file);
        
        if( AlertSave.getUpdate() ) {
            AlertSave.alertSave(frame, file);
        }
        
        ClearAction.ClearAction();
        //FileControl filecontrol = FileControl.getSingleton();
        FileControl.setFileName(null);
        
        AlertSave.setUpdate(null, false);
        
    }
    
}
