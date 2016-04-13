package action;

import editor.AlertSave;
import static frame.MainFrame.area;
import static frame.MainFrame.label;

public class ClearAction {
    
    public static void ClearAction() {
        area.setText("");
        label.setText("");
        AlertSave alertsave = AlertSave.getSingleton();
        AlertSave.setUpdate(area, true, null);
    }
    
}
