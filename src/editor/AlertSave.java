package editor;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import frame.MainFrame;


public class AlertSave {
    
    // Create AlertSave by Singleton
    private static AlertSave instance = new AlertSave();
    private static String areabe, areaaf;
    private static boolean boo;
        
    private AlertSave() {
        areabe = "";
        areaaf = "";
        boo = false;
    }
    
    public static AlertSave getSingleton() {
        return instance;
    }
    
    public static void setUpdate(JTextArea area, boolean b, File file) {
        if(!b) {
            areaaf = "";
            try {
                if(file != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    String str, lise = null;
                    int i = 0;

                    str = reader.readLine();
                    while(str != null) {
                        if(i != 0) {
                            areaaf += System.lineSeparator();
                        }
                        areaaf += str;
                        lise = str;
                        str = reader.readLine();
                        i++;
                    }
                    
                    if(lise == System.lineSeparator()) {
                        areaaf += System.lineSeparator();
                    }
                    
                    reader.close();
                } 
            } catch(FileNotFoundException err) {
                filealert("エラーが発生しました");
            } catch(IOException err) {
                filealert("エラーが発生しました");
            }
        } 
        areabe = area.getText();

        if(!areabe.equals( areaaf )) {
            boo = true;
            if(!b) {
                boo = false;
                areaaf = areabe;
            }
            
        } else {
            boo = false;
        }
    }
    
    public static boolean getUpdate() {
        // True:Alert False:NoAlert
        return boo;
    }
    
    public static int alertSave(MainFrame frame, File file) {
        String selectvalueas[] = {"保存する", "保存しない", "キャンセル"};
        String message = null;
        
        if(file == null) {
            message ="無題";
        } else {
            message = file.getAbsolutePath();
        }
        message = message + "への変更内容を保存しますか?";
        
        int select = JOptionPane.showOptionDialog(frame,
                                                  message, 
                                                  "確認", 
                                                  JOptionPane.YES_NO_CANCEL_OPTION,
                                                  JOptionPane.PLAIN_MESSAGE,
                                                  null, 
                                                  selectvalueas, 
                                                  selectvalueas[0]
                                                  );
        
        return select;
    }
    
    public static int alertExist(MainFrame frame, File file) {
        String selectvalueas[] = {"はい", "いいえ"};
        String message = null;
        
        message = file.getAbsolutePath() + "は既に存在します。" + System.lineSeparator() + "上書きしますか?";
        
        int select = JOptionPane.showOptionDialog(frame,
                                                  message, 
                                                  "名前を付けて保存の確認", 
                                                  JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.PLAIN_MESSAGE,
                                                  null, 
                                                  selectvalueas, 
                                                  selectvalueas[1]
                                                  );
        return select;
        
    }
 
    private static void filealert(String str) {
        JLabel label = new JLabel(str);
        JOptionPane.showMessageDialog(null, label, "警告", JOptionPane.ERROR_MESSAGE);
    }
    
}