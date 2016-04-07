package frame;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

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

import action.CountAction;
import action.ClearAction;
import editor.AlertSave;


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


                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
                setTitle("文字数カウント");
                setBounds((int) (getDisplaySize()/15), (int) (getDisplaySize()/12), (int) (getDisplaySize()/1.7), (int) (getDisplaySize()/1.7/8*5));
                setVisible(true);                
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
                area = new JTextArea();
                area.setFont(new Font("メイリオ", Font.PLAIN, (int) (getDisplaySize()/100)));
                area.setLineWrap(true);
                area.setWrapStyleWord(true);
                area.addKeyListener(new areaUpdate());
                
                sp = new JScrollPane(area);
                sp.setPreferredSize(new Dimension((int) (getDisplaySize()/1.7-getDisplaySize()/70), (int) (getDisplaySize()/1.7/8*3)));
                sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }

        // Create Button
        private void getButton() {
            b1 = new JButton("カウント");
            b1.addActionListener(new CountButton());
            b2 = new JButton("クリア");
            b2.addActionListener(new ClearButton());
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
        
        // Check area Update or not
        private static class areaUpdate extends KeyAdapter {
            public void keyReleased(KeyEvent e) {
                AlertSave alertsave = AlertSave.getSingleton();
                AlertSave.setUpdate(area, true);
            }   
        }
        
        // Count
        private class CountButton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setFont(new Font("メイリオ", Font.PLAIN, (int) (getDisplaySize()/100)));
		label.setText("文字数は" + CountAction.CountAction(area.getText(), area.getLineCount() - 1) + "です");
            }
        }
        
        // Clear
        private class ClearButton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
		ClearAction.ClearAction();                
            }
        }
}
