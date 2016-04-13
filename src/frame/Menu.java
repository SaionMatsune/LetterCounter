package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;
import javax.swing.KeyStroke;

import editor.*;


public class Menu {
    
    public static JMenu menu1;
    public static JMenu menu2;
    public static JMenuItem menuitem11;
    public static JMenuItem menuitem12;
    public static JMenuItem menuitem13;
    public static JMenuItem menuitem14;
    public static JMenuItem menuitem15;
    public static JMenuItem menuitem21;
    public static JMenuItem menuitem22;
    public static JMenuItem menuitem23;
    public static JMenuItem menuitem24;
    public static JMenuItem menuitem27;
    public static JMenuItem menuitem28;
    public static Separator separator1;
    public static Separator separator2;
    
    public static JMenuBar setMenuBar(JMenuBar menubar){
        // Start Definition
        menu1 = new JMenu("ファイル(F)");
        menu2 = new JMenu("編集(E)");

        menuitem11 = new JMenuItem("新規(N)");
        menuitem12 = new JMenuItem("開く(O)");
        menuitem13 = new JMenuItem("上書き保存(S)");
        menuitem14 = new JMenuItem("名前をつけて保存(A)");
        menuitem15 = new JMenuItem("終了(X)");
        
        menuitem21 = new JMenuItem("切り取り(T)");
        menuitem22 = new JMenuItem("コピー(C)");
        menuitem23 = new JMenuItem("貼り付け(P)");
        menuitem24 = new JMenuItem("削除(L)");
        menuitem27 = new JMenuItem("すべて選択(A)");
        menuitem28 = new JMenuItem("日付と時刻(D)");
        
        separator1 = new Separator();
        separator2 = new Separator();
        // End Definition
        
        // Start Definition of Function
        setmenuMnemonic();
        setAction();

        // Add menuitems in menu1
        menu1.add(menuitem11);
        menu1.add(menuitem12);
        menu1.add(menuitem13);
        menu1.add(menuitem14);
        menu1.add(separator1);
        menu1.add(menuitem15);
        
        // Add menuitems in menu2
        menu2.add(menuitem21);
        menu2.add(menuitem22);
        menu2.add(menuitem23);
        menu2.add(menuitem24);
        menu2.add(separator2);
        menu2.add(menuitem27);
        menu2.add(menuitem28);
        
        // menus in "menubar"
        menubar.add(menu1);
        menubar.add(menu2);
        
        return menubar;
    }
    
    public static void setmenuMnemonic() {
        menu1.setMnemonic(KeyEvent.VK_F);
        menu2.setMnemonic(KeyEvent.VK_E);
        
        menuitem11.setMnemonic('N');
        menuitem11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        menuitem12.setMnemonic('O');
        menuitem12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menuitem13.setMnemonic('S');
        menuitem13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuitem14.setMnemonic('A');
        menuitem15.setMnemonic('X');
        
        menuitem21.setMnemonic('T');
        menuitem21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        menuitem22.setMnemonic('C');
        menuitem22.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuitem23.setMnemonic('P');
        menuitem23.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        menuitem24.setMnemonic('L');
        menuitem24.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        menuitem27.setMnemonic('A');
        menuitem27.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        menuitem28.setMnemonic('D');
        menuitem28.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
    }
    
    public static void setAction() {
        menuitem11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewCommand.NewCommand(MainFrame.frame, MainFrame.area);
            }
        });
        menuitem12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenCommand.OpenCommand(MainFrame.frame, MainFrame.area);
            }
        });
        menuitem13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveCommand.SaveCommand(MainFrame.frame, MainFrame.area);
            }
        });
        menuitem14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveasCommand.SaveasCommand(MainFrame.frame, MainFrame.area);
            }
        });
        menuitem15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExitCommand.ExitCommand(MainFrame.frame, MainFrame.area);
            }
        });
        
        menuitem21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CutCommand.CutCommand(MainFrame.area);
            }
        });       
        menuitem22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CopyCommand.CopyCommand(MainFrame.area);
            }
        });
        menuitem23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PasteCommand.PasteCommand(MainFrame.area);
            }
        });
        menuitem24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteCommand.DeleteCommand(MainFrame.area);
            }
        });
        menuitem27.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectAllCommand.SelectAllCommand(MainFrame.area);
            }
        });
        menuitem28.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateCommand.DateCommand(MainFrame.area);
            }
        });
    }

}

