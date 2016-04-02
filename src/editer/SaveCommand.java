package editer;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import java.lang.String;

import static frame.MainFrame.area;

public class SaveCommand {
    
    public static void SaveCommand(JTextArea area) {
        
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