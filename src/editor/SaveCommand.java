package editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.lang.String;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import frame.MainFrame;

public class SaveCommand {
    
    public static void SaveCommand(MainFrame frame, JTextArea area) {
        FileControl filecontrol = FileControl.getSingleton();
        File file = FileControl.getFileName();
        
        if(file == null) {
            SaveasCommand.SaveasCommand(frame, area);
        } else {
            try {
                if(checkWritefile(file)) {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
                    writer.write(area.getText());
                    writer.flush();
                    writer.close();
                    
                    AlertSave alertsave = AlertSave.getSingleton();
                    AlertSave.setUpdate(area, false, file);
                } else {
                    filealert("ファイルに書き込めません");
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