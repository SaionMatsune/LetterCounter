package editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.lang.String;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import action.ClearAction;
import frame.MainFrame;

public class OpenCommand {
    
    public static void OpenCommand(MainFrame frame, JTextArea area) {
        AlertSave alertsave = AlertSave.getSingleton();
        FileControl filecontrol = FileControl.getSingleton();
        File file = FileControl.getFileName();

        if( AlertSave.getUpdate() ) {
            int i = AlertSave.alertSave(frame, file);
            if(i == 0) {
            	SaveCommand.SaveCommand(frame, area);
		Open(area);
            } else if(i == 1) {
		Open(area);
            }
        } else {
            Open(area);
        }
        
    }
    
    private static void Open(JTextArea area) {
        JFileChooser filechooser = new JFileChooser();
        
        if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            
            ClearAction.ClearAction();
            try {
                if(checkReadfile(file)) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    String str, lise = null;
                    int i = 0;

                    str = reader.readLine();
                    while(str != null) {
                        if(i != 0) {
                            area.append(System.lineSeparator());
                        }
                        area.append(str);
                        lise = str;
                        str = reader.readLine();
                        i++;
                    }
                    if(lise == System.lineSeparator()) {
                        area.append(System.lineSeparator());
                    }
                    reader.close();
                    
                    FileControl filecontrol = FileControl.getSingleton();
                    FileControl.setFileName(file);
                    
                    AlertSave alertsave = AlertSave.getSingleton();
                    AlertSave.setUpdate(area, false, file);
                } else {
                    filealert("ファイルが見つからないか開けません");
                }
                
            } catch(FileNotFoundException err) {
                filealert("エラーが発生しました");
            } catch(IOException err) {
                filealert("エラーが発生しました");
            }
            
        }
    }

    private static boolean checkReadfile(File file) {
        if(file.exists()) {
            if(file.isFile() && file.canRead()) {
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
