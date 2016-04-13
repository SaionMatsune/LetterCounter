package editor;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.lang.String;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import frame.MainFrame;

public class SaveasCommand {
    
    public static void SaveasCommand(MainFrame frame, JTextArea area) {
        JFileChooser filechooser = new JFileChooser();
        
        if(filechooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            
            try {
                FileControl filecontrol = FileControl.getSingleton();
                if(checkWritefile(file)) {
                    if(AlertSave.alertExist(frame, file) == 0) {
                        FileControl.setFileName(file);
                        SaveCommand.SaveCommand(frame, area);
                    } else {
                        SaveasCommand(frame, area);
                    }
                } else {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
                    writer.write(area.getText());
                    writer.flush();
                    writer.close();
                    
                    FileControl.setFileName(file);
                    
                    AlertSave alertsave = AlertSave.getSingleton();
                    AlertSave.setUpdate(area, false, file);
                }
            } catch(IOException e) {
                filealert("エラーが発生しました");
            }
        }
        
    }
    
    private static boolean checkWritefile(File file) {
        if(file.exists()) {
            if(file.isFile() && file.canWrite()) {
                return true;
            }
        }
        
        return false;
    }
    
    private static void filealert(String str) {
        JLabel label = new JLabel(str);
        JOptionPane.showMessageDialog(null, label, "警告", JOptionPane.ERROR_MESSAGE);
    }

}
