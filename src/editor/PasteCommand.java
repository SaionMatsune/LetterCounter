package editor;

import javax.swing.JTextArea;

public class PasteCommand {
    
    public static void PasteCommand(JTextArea area) {
        area.paste();
        
        AlertSave alertsave = AlertSave.getSingleton();
        AlertSave.setUpdate(area, true, null);
    }
    
}
