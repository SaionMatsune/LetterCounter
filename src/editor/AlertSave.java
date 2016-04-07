package editor;

import java.io.File;

import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import frame.MainFrame;



public class AlertSave {
    private static AlertSave instance = new AlertSave();
    private static JTextArea areabe, areaaf;
    private static boolean boo;
        
    private AlertSave() {
        areabe = null;
        areaaf = null;
        boo = false;
    }
    
    public static AlertSave getSingleton() {
        return instance;
    }
    
    public static void setUpdate(JTextArea area, boolean b) {
        System.out.println(b);
        if(b) {
            areabe = area;
            boo = true;
        } else {
            areaaf = area;
            boo = false;
        }
        /*
        if(areabe != areaaf) {
            boo = true;
        } else {
            boo = false;
        }
        */
        if(areabe != null) {
            System.out.println(areabe.getText());            
        }

        if(areaaf != null) {
            System.out.println(areaaf.getText());
        }
        System.out.println(boo);
    }
    
    public static boolean getUpdate() {
        // True:Alert False:NoAlert
        return boo;
    }
    
    public static void alertSave(MainFrame frame, File file) {
        /*
        JButton bsave = new JButton("保存する(S)");
        JButton bnosave = new JButton("保存しない(N)");
        JButton bcancel = new JButton("キャンセル");
        
        JPanel pas = new JPanel();
        */
        String selectvalueas[] = {"保存する(S)", "保存しない(N)", "キャンセル"};
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
          JOptionPane.QUESTION_MESSAGE,
          null, 
          selectvalueas, 
          selectvalueas[0]
    );
        
        
    }
    
    public static void alertExist() {
        String selectvalueas[] = {"はい(S)", "いいえ(N)"};
        
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