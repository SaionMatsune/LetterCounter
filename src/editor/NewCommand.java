package editor;

import action.ClearAction;

public class NewCommand {
    
    public static void NewCommand() {
        ClearAction.ClearAction();
        FileControl filecontrol = FileControl.getSingleton();
        FileControl.setFileName(null);
    }
    
}
