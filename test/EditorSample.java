 //---------------------------------------------
 //  簡易テキストエディタの作成
 //    ( EditorSample.java )
 //---------------------------------------------
/* 以下のような機能をもつ簡単なテキストエディタを作成する.
    1.ファイルダイアログを開き、テキストファイルを読み込み
      スクロール表示する.
    2.編集操作（切り取り・コピー・貼り付け）ができる.　
    3.保存ダイアログを開き、フアイルを保存する.
　  4.以上をメニューから選択する.

    メニュー作成に
       JMenuBar, JMenu, JMenuItem
    ファイルを選択して開いたり、保存に
       JFileChooser
    ファイルの読み書きに
       FileReader, FileWriter,BufferedReader,ufferedWriter
    テキストファイルのスクロール表示に
       JScrollPane 
    などを使う.
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

 public  class  EditorSample {
    public  EditorSample() {
        EditorFrame  et = new EditorFrame();
           et.setVisible(true);
    }
 
    public static void main(String[] args) {
        new EditorSample();
    }


   public class EditorFrame extends JFrame implements  ActionListener {
   
    Container  con;
    JMenuBar mb ;
    JMenu muFile ;
    JMenu muEdit ;

    JMenuItem  miOpen, miSave, miExit ;  
    JMenuItem  miCut, miCopy, miPaste ;

    JTextArea  ta ;
    JScrollPane sp ; // スクロールバー付きのコンテナ
    JFileChooser fc ; // フアイルを選択して開いたり保存したり
                      // するためのダイアログを表示するときに使う　
    File file;
    Font ft1, ft2;
   
    public EditorFrame() { // コンストラクタ
     setSize(700, 600);
     setTitle("Text Editor");
     setLocation(150,20);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     con = getContentPane();
     ft1 = new Font("SansSerif",Font.BOLD,18);
     ft2 = new Font("SansSerif",Font.BOLD,16);

     mb = new JMenuBar();
     muFile = new JMenu("ファイル");
     muEdit = new JMenu("編集");
     muFile.setFont(ft1);
     muEdit.setFont(ft1);

     miOpen = new JMenuItem("開く");
     miSave = new JMenuItem("保存");
     miExit = new JMenuItem("終了");
  
     miCut = new JMenuItem("切り取り");
     miCopy = new JMenuItem("コピー");
     miPaste = new JMenuItem("貼り付け");

     miOpen.setFont(ft1);
     miSave.setFont(ft1);
     miExit.setFont(ft1);
     miCut.setFont(ft1);
     miCopy.setFont(ft1);
     miPaste.setFont(ft1);

     ta = new JTextArea();
     ta.setFont(ft2);
     sp = new JScrollPane(ta);
     fc = new JFileChooser();
 
     miOpen.addActionListener(this);
     miSave.addActionListener(this);
     miExit.addActionListener(this);
     miCut.addActionListener(this);
     miCopy.addActionListener(this);
     miPaste.addActionListener(this);
 
     muFile.add(miOpen);
     muFile.add(miSave);
     muFile.addSeparator();
     muFile.add(miExit);

     muEdit.add(miCut);
     muEdit.add(miCopy);
     muEdit.add(miPaste);

     mb.add(muFile);
     mb.add(muEdit);
     con.add(sp, BorderLayout.CENTER);
         
     setJMenuBar(mb);

   } // コンストラクタ終り

   public void actionPerformed(ActionEvent e) {
     // アクションコマンドの文字列を取得
     String str = e.getActionCommand();

     if ( str.equals("開く") ) {
          miOpen_Action(e);
     } else if ( str.equals("保存") ) {
          miSave_Action(e);
     } else if ( str.equals("終了") ) {
          miExit_Action(e);
     } else if ( str.equals("切り取り") ) {
          miCut_Action(e);
     } else if ( str.equals("コピー") ) {
          miCopy_Action(e);
     } else if ( str.equals("貼り付け") ) {
          miPaste_Action(e);
     }
   }
    // メニュー「開く」
   public void  miOpen_Action(ActionEvent e) {
      // ファイルダイアログを開き(showOpenDialog)、
      //「開く」ボタンが押されたら(APPROVE_OPTION)
     if ( fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) {
        // 開いたファイルを取得
        file = fc.getSelectedFile();
        // 読み込み用のストリーム
        BufferedReader br;
        // 1行ずつ読み込むためのバッファを確保
        String  str;
        ta.setText("");
        try {
          // ファイルを開く
          br = new BufferedReader(new FileReader(file));
          // ファイルを1行ずつ読み込み、テキストエリアに書く
          for (str=br.readLine(); str!=null; str=br.readLine()) {
              ta.append(str + '\n');
          }
          br.close();

        } catch (IOException  ex) {
             ex.printStackTrace();          
        }
    }
   }

  // メニュー「保存」
   public void miSave_Action(ActionEvent e) {
     // 保存用ダイアログを開き(showSaveDialog)、
     //「保存」ボタンが押されたら(APPROVE_OPTION)
     if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ) {
       // ファイルを取得
       file = fc.getSelectedFile();
       // 書き込み用ストリーム
       PrintWriter wt;

       try {
        // ファイルを開く
         wt=new PrintWriter(
         new BufferedWriter(new FileWriter(file))
                );
        // テキストエリアの内容を書き込む
        wt.print( ta.getText());
        wt.close();
       } catch (IOException ex) {
             ex.printStackTrace();
       }
     }
   }

   // メニュー「終了」
   public void miExit_Action(ActionEvent e) {
      System.exit(0); 
   }

   // テキストエリア（JTextArea)は,「切り取り」(cutメソッド)
   //「コピー」(copy),「貼り付け」(paste) 機能を持っている.
   // 以下のように、呼び出すだけでよい.

    // メニュー「切り取り」
   public void miCut_Action(ActionEvent e) {
       ta.cut();
   }

   // メニュー「コピー」
   public void miCopy_Action(ActionEvent e) {
       ta.copy();
   }

   // メニュー「貼り付け」
   public void miPaste_Action(ActionEvent e) {
       ta.paste();
   }
 }
}