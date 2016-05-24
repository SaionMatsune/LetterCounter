package editor;

import action.ClearAction;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.BorderLayout;
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





public class SearchCommand extends JFrame {
    //public static SearchCommand sframe = new SearchCommand();
    public static SearchCommand sframe;

    private static JTextField tfield = new JTextField(15);
    private static JButton b1;
    private static JButton b2;
    
    private static JTextArea searcharea;
    
    // Setting Search Frame
    public SearchCommand() {
        // Equalize Exterior of GUI to Exterior of OS
	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException
                	| IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
	}
        
        JPanel p = new JPanel();
        JPanel bp = new JPanel();
        
        JLabel label = new JLabel("検索する文字列:");
        
        getButton();
        
        
        p.add(label);
        p.add(tfield);
        bp.add(b1);
        bp.add(b2);
        
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.NORTH);
        contentPane.add(bp, BorderLayout.EAST);
        
        
        // Setting Frame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	setResizable(false);
        setTitle("検索");
        setBounds((int) (getDisplaySize()/5), (int) (getDisplaySize()/10), (int) (getDisplaySize()/1.7/2.3), (int) (getDisplaySize()/1.7/8*5/3.5));
        setVisible(true);
        
        this.addWindowListener(new WindowAdapter(){
            // In Clicking [X], Check Save or not 
            public void windowClosing(WindowEvent e) {
                sframe.dispose();
            }
        });
    }
    
    public static SearchCommand SearchFrame(JTextArea area) {
        searcharea = area;
        sframe = new SearchCommand();
        return sframe;
    }
    
    // Create Button
    private void getButton() {
        b1 = new JButton("次を検索");
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(tfield != null) {
                    //searcharea
                    int a = 0;
                    if(searcharea.getSelectedText() != null) {
                        a += searcharea.getSelectionEnd();
                        if(a > searcharea.getText().lastIndexOf(tfield.getText())) {
                            a = 0;
                        }
                    }
                    a = searcharea.getText().indexOf(tfield.getText(), a);
                    searcharea.requestFocusInWindow();
                    searcharea.select(a, a + tfield.getText().length());
                }
            }
        });

        b2 = new JButton("キャンセル");
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                sframe.dispose();
            }
        });
    }
    
    // Get DisplaySize
    private static int getDisplaySize() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle desktopBounds = env.getMaximumWindowBounds();

        return (int) desktopBounds.getWidth();
    }
}
/***検索******************************************
検索する文字列:[   (JTextField)   ]    [次を検索(F)]
                                      [キャンセル]
*************************************************/