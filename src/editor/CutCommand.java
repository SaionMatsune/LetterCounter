package editor;

import javax.swing.JTextArea;

public class CutCommand {
    
    public static void CutCommand(JTextArea area) {
        area.cut();
        
        AlertSave alertsave = AlertSave.getSingleton();
        AlertSave.setUpdate(area, true, null);
    }
    
}
