import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
class NewFrame extends JFrame {
 
 public NewFrame(String string) {
  // TODO 自動生成されたコンストラクター・スタブ
  super(string);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
  JPanel p1 = new JPanel();
  p1.setPreferredSize(new java.awt.Dimension(600, 270));
  Container contentPane = getContentPane();
  contentPane.add(p1, BorderLayout.NORTH);
  contentPane.add(new JLabel("Xボタンで子ウインドウも閉じる"));
 
  pack();
 
  System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
  Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
  System.out.println(size.width);
  setLocation(size.width / 2, size.height / 2);
  setVisible(true);
 }
}
 
class NewFrame2 extends JFrame {
 
 public NewFrame2(String string) {
  // TODO 自動生成されたコンストラクター・スタブ
  super(string);
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  JPanel p1 = new JPanel();
  p1.setPreferredSize(new java.awt.Dimension(600, 270));
  Container contentPane = getContentPane();
  contentPane.add(p1, BorderLayout.NORTH);
  contentPane.add(new JLabel("Xボタンを押しても親ウインドウは閉じない"));
 
  pack();
  Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
  setLocation(size.width / 2 + 30, size.height / 2 + 30);
  setVisible(true);
 }
}
 
class NewFrame3 extends JFrame {
 
 public NewFrame3(String string) {
  // TODO 自動生成されたコンストラクター・スタブ
  super(string);
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  JPanel p1 = new JPanel();
  p1.setPreferredSize(new java.awt.Dimension(600, 270));
  Container contentPane = getContentPane();
  contentPane.add(p1, BorderLayout.NORTH);
  contentPane.add(new JLabel("Xボタンを押しても親ウインドウは閉じない"));
 
  pack();
  Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
  setLocation(size.width / 2 + 60, size.height / 2 + 60);
  setVisible(true);
 }
}
 
public class swingTestSlider {
 public static void main(String[] args) {
  new NewFrame("親フレーム");
  new NewFrame2("子ウインドウ");
  new NewFrame3("子ウインドウ２");
 }
}