package frame;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import action.CountAction;
import action.ClearAction;
import editor.AlertSave;
import editor.DateCommand;
import editor.FileControl;
import editor.ReplaceCommand;
import editor.SaveCommand;
import javax.swing.KeyStroke;



public class MainFrame extends JFrame {
    
    public static MainFrame frame = new MainFrame();
    
    // Start Definition
    public static JTextArea area;
    private static JScrollPane sp;
    private static JButton b1;
    private static JButton b2;
    public static JLabel label;
    public static JMenuBar menubar;
    // End Definition

    private MainFrame() {
	// Equalize Exterior of GUI to Exterior of OS
	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException
                	| IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
	}

                
        JPanel p = new JPanel();
                
        // Definition
        getMenu();
        getButton();
        getTextArea();
        getLabel();            

        // Setting Arrangement
        setJMenuBar(menubar);
                
        p.add(sp);
        p.add(b1);
        p.add(b2);                             

        // Setting Arrangement All
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);
        contentPane.add(label, BorderLayout.SOUTH);

        // Setting Frame
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
	setResizable(false);
        setTitle("文字数カウント");
        setBounds((int) (getDisplaySize()/15), (int) (getDisplaySize()/12), (int) (getDisplaySize()/1.7), (int) (getDisplaySize()/1.7/8*5));
        setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            // In Clicking [X], Check Save or not 
            public void windowClosing(WindowEvent e) {
                AlertSave alertsave = AlertSave.getSingleton();
                FileControl filecontrol = FileControl.getSingleton();
                File file = FileControl.getFileName();

                if( AlertSave.getUpdate() ) {
                    int i = AlertSave.alertSave(frame, file);
                    if(i == 0) {
                        SaveCommand.SaveCommand(frame, area);
                        System.exit(0);
                    } else if (i == 1) {
                        System.exit(0);
                    }
                } else {        
                    System.exit(0);
                }
            }
        });
    }

    public static MainFrame getInstance() {
        return frame;
    }

    // Create MenuBar
    private static void getMenu() {
        menubar = new JMenuBar();
        Menu.setMenuBar(menubar);
    }

    // Create TextArea and Scroll
    private static void getTextArea() {
        // Setting TextArea
        area = new JTextArea();        
        area.setFont(new Font("メイリオ", Font.PLAIN, (int) (getDisplaySize()/100)));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK), "none");
        area.addKeyListener(new KeyAdapter(){
            // Check area Update or not
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_F5) {
                    DateCommand.DateCommand(area);
                } else if((e.getKeyCode() == KeyEvent.VK_H) && ((e.getModifiers() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                    ReplaceCommand.ReplaceFrame(area);
                } else {
                    AlertSave alertsave = AlertSave.getSingleton();
                    AlertSave.setUpdate(area, true, null);
                }
            } 
        });

        // Setting ScrollPanel
        sp = new JScrollPane(area);
        sp.setPreferredSize(new Dimension((int) (getDisplaySize()/1.7-getDisplaySize()/70), (int) (getDisplaySize()/1.7/8*3)));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // Create Button
    private void getButton() {
        b1 = new JButton("カウント");
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                label.setFont(new Font("メイリオ", Font.PLAIN, (int) (getDisplaySize()/100)));
                label.setText("文字数は" + CountAction.CountAction(area.getText(), area.getLineCount() - 1) + "です");
            }
        });
        b2 = new JButton("クリア");
        b2.setPreferredSize(b1.getPreferredSize());
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ClearAction.ClearAction();
            }
        });
    }

    // Set Count Comment
    private void getLabel() {
        label = new JLabel();
    }

    // Get DisplaySize
    private static int getDisplaySize() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle desktopBounds = env.getMaximumWindowBounds();

        return (int) desktopBounds.getWidth();
    }

}