package frame;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;


import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import action.CountAction;
import action.ClearAction;


public class MainFrame extends JFrame {
    
    private static MainFrame frame = new MainFrame();

    public static JTextArea area;
    private static JScrollPane sp;
    private static JButton b1;
    private static JButton b2;
    public static JLabel label;
            
	private MainFrame() {
		// GUIの外観をOSの外観と同じにする．
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

                
                JPanel p = new JPanel();

                getButton();
                getTextArea();
                getLabel();            
                
                p.add(sp);
                p.add(b1);
                p.add(b2);                             

                
                Container contentPane = getContentPane();
                contentPane.add(p, BorderLayout.CENTER);
                contentPane.add(label, BorderLayout.SOUTH);


                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
                setTitle("文字数カウント");
                setBounds(150, 100, 800, 500);
                setVisible(true);                
        }

        public static MainFrame getInstance(){
                return frame;
	}
        
        private static void getTextArea() {
                area = new JTextArea();
                area.setLineWrap(true);
                area.setWrapStyleWord(true);
                
                sp = new JScrollPane(area);
                sp.setPreferredSize(new Dimension(780, 300));
                sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }

        private void getButton() {
            b1 = new JButton("カウント");
            b1.addActionListener(new CountButton());
            b2 = new JButton("クリア");
            b2.addActionListener(new ClearButton());
        }
        
        private void getLabel(){
            label = new JLabel();
        }
        
        private void getMenu(){
            
        }
        
        // Count
        private class CountButton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
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
