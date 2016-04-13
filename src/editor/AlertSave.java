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
        System.out.println(b);
        debugprint(1);
        //System.out.println("file:" + file);
        /*
        if(!b) {
            areaaf = area;
        } 
            areabe = area;
        */
        if(!b) {
            //areaaf = file.getText();
            areaaf = "";
            try {
                if(file != null) {
                    //areaaf.setText("");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    String str, lise = null;
                    int i = 0;

                    str = reader.readLine();
                    while(str != null) {
                        //areaaf.append(str+System.lineSeparator());
                        if(i != 0) {
                            areaaf += System.lineSeparator();
                        }
                        areaaf += str;
                        lise = str;
                        str = reader.readLine();
                        //System.out.print("save" + i + ":" + str);
                        //System.out.println("  " + lise);
                        i++;
                    }
                    //System.out.print("save" + i + ":" + str);
                    //System.out.println("  " + lise);
                    
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
        //areabe = area;
        areabe = area.getText();

        debugprint(2);
        /*
        if(!areabe.getText().equals( areaaf.getText() )) {  // areabe.getTxet() != areaaf.getTexe()
            boo = true;
        } else {
            boo = false;
        }
        */
        if(!areabe.equals( areaaf )) {  // areabe.getTxet() != areaaf.getTexe()
            boo = true;
            if(!b) {
                boo = false;
                areaaf = areabe;
            }
            
        } else {
            boo = false;
        }
        
        System.out.println(boo);
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
        System.out.println("select:" + select);
        
        return select;
    }
    
    public static void alertExist() {
        String selectvalueas[] = {"はい", "いいえ"};
        
    }
 
    private static void filealert(String str) {
        JLabel label = new JLabel(str);
        JOptionPane.showMessageDialog(null, label, "警告", JOptionPane.ERROR_MESSAGE);
    }
    
    private static void debugprint(int i) {
        System.out.println(i);
        System.out.println("before");
        //System.out.println(areabe.getText());
        System.out.println(areabe);
        System.out.println("after");
        //System.out.println(areaaf.getText());
        System.out.println(areaaf);
    }
    
}

/*
"絶対パス"(新規の場合は無題)
への変更内容を保存しますか?
保存する(S)　保存しない(N)　キャンセル
*/

/*名前を付けて保存の確認
"ファイル名"は既に存在します。
上書きしますか?
はい(Y)　いいえ(N)
*/

/*
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class JOptionPaneTest13 extends JFrame implements ActionListener{

  JLabel ansLabel;

  public static void main(String[] args){
    JOptionPaneTest13 frame = new JOptionPaneTest13();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 1500, 1000);
    frame.setTitle("タイトル");
    frame.setVisible(true);
  }

  JOptionPaneTest13(){
    JButton infoButton = new JButton("Question");
    infoButton.addActionListener(this);

    JPanel p = new JPanel();
    p.add(infoButton);

    ansLabel = new JLabel("未入力です");
    JPanel ansPanel = new JPanel();
    ansPanel.add(ansLabel);

    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(ansPanel, BorderLayout.PAGE_END);
  }

  public void actionPerformed(ActionEvent e){
    String selectvalues[] = {"読書", "ドライブ", "映画", "スポーツ"};

    int select = JOptionPane.showOptionDialog(this,
      "休日の過ごし方は？", 
      "休日の過ごし方", 
      JOptionPane.YES_NO_OPTION,
      JOptionPane.QUESTION_MESSAGE,
      null, 
      selectvalues, 
      selectvalues[0]
    );
    // 1:ダイアログを表示するための親フレームの指定
    // 2:ダイアログに表示するオブジェクト
    // 3:ダイアログのタイトル
    // 4:ダイアログに表示するボタンの種類
    // 5:ダイアログのメッセージタイプを表す
    // 6:表示したい画像を表すIconインターフェースを実装したクラスのオブジェクト(使わないのでnull)
    // 7:ボタンとして表示される値の集合を表す配列
    // 8:デフォルトの状態で選択されているボタンを指定

    if (select == JOptionPane.CLOSED_OPTION){
      ansLabel.setText("選択されずに閉じられました");
    }else{
      ansLabel.setText(selectvalues[select]);
    }
  }
}
*/