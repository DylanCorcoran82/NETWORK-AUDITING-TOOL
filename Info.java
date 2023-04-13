import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Info extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
					frame.setVisible(true);
					
					Container c = frame.getContentPane();
					c.setBackground(Color.LIGHT_GRAY);
					
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					JLabel emptyLabel = new JLabel("");
					emptyLabel.setPreferredSize(new Dimension( (int)dimension.getWidth() / 2, (int)dimension.getHeight()/2 ));
					frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
					frame.setLocation((int)dimension.getWidth()/4, (int)dimension.getHeight()/4);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Info() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Info");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(513, 11, 289, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home_Page.main(null);
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 24));
		btnNewButton.setBounds(0, 0, 93, 67);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Network Security Auditing Tool");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(118, 58, 237, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Created by: Dylan Corcoran");
		lblNewLabel_1_1.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(128, 93, 237, 33);
		contentPane.add(lblNewLabel_1_1);
	}

}
