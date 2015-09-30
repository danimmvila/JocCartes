package Frames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("CARDHORSES RACE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new CarrerasPanel(5);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setBounds(10, 10, contentPane.getWidth()+7,  contentPane.getHeight()+30);
		setResizable(false);
	}

}
