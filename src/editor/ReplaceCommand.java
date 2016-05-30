package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import frame.MainFrame;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class ReplaceCommand extends JFrame {
    public static ReplaceCommand rframe;

    private static JTextField tfield1 = new JTextField(15);
    private static JTextField tfield2 = new JTextField(15);
    private static JButton b1;
    private static JButton b2;
    private static JButton b3;
    private static JButton b4;
    
    private static JTextArea searcharea;
    
    // Setting Replace Frame
    public ReplaceCommand() {
        // Equalize Exterior of GUI to Exterior of OS
	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException
                	| IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
	}
        
        GridBagLayout layout = new GridBagLayout();
        JPanel p = new JPanel();
        p.setLayout(layout);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel label1 = new JLabel("検索する文字列:");
        JLabel label2 = new JLabel("置換後の文字列:");
        
        tfield1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK), "none");
        tfield2.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK), "none");
        
        getButton();        

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(label1, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(tfield1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(label2, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(tfield2, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        layout.setConstraints(b1, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        layout.setConstraints(b2, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        layout.setConstraints(b3, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        layout.setConstraints(b4, gbc);

        p.add(label1);
        p.add(tfield1);
        p.add(label2);
        p.add(tfield2);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        
        getContentPane().add(p, BorderLayout.NORTH);
        
        // Setting Frame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	setResizable(false);
        setTitle("置換");
        setBounds((int) (getDisplaySize()/5), (int) (getDisplaySize()/10), (int) (getDisplaySize()/1.7/1.7), (int) (getDisplaySize()/1.7/8*5/2.5));
        setVisible(true);
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                AlertSave alertsave = AlertSave.getSingleton();
                AlertSave.setUpdate(MainFrame.area, true, null);
                
                rframe.dispose();
            }
        });
    }
    
    public static ReplaceCommand ReplaceFrame(JTextArea area) {
        searcharea = area;
        rframe = new ReplaceCommand();
        return rframe;
    }
    
    
    // Create Button
    private void getButton() {
        b1 = new JButton("次を検索");
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(tfield1 != null) {
                    int a = 0;
                    if(searcharea.getSelectedText() != null) {
                        a += searcharea.getSelectionEnd();
                        if(a > searcharea.getText().lastIndexOf(tfield1.getText())) {
                            a = 0;
                        }
                    }
                    a = searcharea.getText().indexOf(tfield1.getText(), a);
                    searcharea.requestFocusInWindow();
                    searcharea.select(a, a + tfield1.getText().length());
                }
            }
        });
        b2 = new JButton("置換");
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(tfield1 != null) {
                    int a = 0;
                    boolean boo = false;
                    if(searcharea.getSelectedText() != null) {
                        searcharea.replaceRange(tfield2.getText(), searcharea.getSelectionStart(), searcharea.getSelectionEnd());
                        if(a > searcharea.getText().lastIndexOf(tfield1.getText())) {
                            boo = true;
                        }
                    }
                    a = searcharea.getText().indexOf(tfield1.getText(), a);
                    searcharea.requestFocusInWindow();
                    searcharea.select(a, a + tfield1.getText().length());
                    
                    if(boo || searcharea.getText().indexOf(tfield1.getText(), 0) == -1) {
                            searcharea.select(0, 0);
                    } 
                }
            }
        }); 
        b3 = new JButton("すべて置換");
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(tfield1 != null) {
                    int a = 0;

                    while(a < searcharea.getText().lastIndexOf(tfield1.getText())) {
                        a = searcharea.getText().indexOf(tfield1.getText(), a);
                        searcharea.requestFocusInWindow();
                        searcharea.select(a, a + tfield1.getText().length());
                        
                        searcharea.replaceRange(tfield2.getText(), searcharea.getSelectionStart(), searcharea.getSelectionEnd());
                    }

                    searcharea.select(0, 0);
                }
            }
        });        
        b4 = new JButton("キャンセル");
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                AlertSave alertsave = AlertSave.getSingleton();
                AlertSave.setUpdate(MainFrame.area, true, null);
                
                rframe.dispose();
            }
        });
        
        b1.setPreferredSize(b3.getPreferredSize());
        b2.setPreferredSize(b3.getPreferredSize());
        b4.setPreferredSize(b3.getPreferredSize());
    }
    
    // Get DisplaySize
    private static int getDisplaySize() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle desktopBounds = env.getMaximumWindowBounds();

        return (int) desktopBounds.getWidth();
    }
    
}


/*************************************
検索する文字列:[TextField] 次を検索
置換後の文字列:[TextField] 置換して次へ
                           すべて置換
                           キャンセル
*************************************/