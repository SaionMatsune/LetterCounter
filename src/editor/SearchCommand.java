package editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.KeyStroke;


public class SearchCommand extends JFrame {
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

        GridBagLayout layout = new GridBagLayout();
        JPanel p = new JPanel();
        p.setLayout(layout);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel label = new JLabel("検索する文字列:");
        
        tfield.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK), "none");
        
        getButton();
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(label, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(tfield, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(b1, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(b2, gbc);
             
        p.add(label);
        p.add(tfield);
        p.add(b1);
        p.add(b2);
     
        getContentPane().add(p, BorderLayout.NORTH);
        
        
        // Setting Frame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
	setResizable(false);
        setTitle("検索");
        setBounds((int) (getDisplaySize()/5), (int) (getDisplaySize()/10), (int) (getDisplaySize()/1.7/2.3), (int) (getDisplaySize()/1.7/8*5/3.5));
        setVisible(true);

        this.addWindowListener(new WindowAdapter(){
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
        
        b1.setPreferredSize(b2.getPreferredSize());
    }
    
    // Get DisplaySize
    private static int getDisplaySize() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle desktopBounds = env.getMaximumWindowBounds();

        return (int) desktopBounds.getWidth();
    }
}
