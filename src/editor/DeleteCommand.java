package editor;

import javax.swing.JTextArea;

public class DeleteCommand {

    public static void DeleteCommand(JTextArea area) {
        area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        
        AlertSave alertsave = AlertSave.getSingleton();
        AlertSave.setUpdate(area, true);
    }
}
