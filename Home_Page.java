import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home_Page extends JFrame {

	private JPanel contentPane;

	 //Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_Page frame = new Home_Page();
					frame.setVisible(true);
					
					Container c = frame.getContentPane();
					c.setBackground(Color.LIGHT_GRAY);
					
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					JLabel emptyLabel = new JLabel("");
					emptyLabel.setPreferredSize(new Dimension( (int)dimension.getWidth() / 2, (int)dimension.getHeight()/2 ));
					frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
					frame.setLocation((int)dimension.getWidth()/30, (int)dimension.getHeight()/7);
					
					 JLabel label = new JLabel(); //JLabel Creation
				        label.setIcon(new ImageIcon("C:/NETWORK-AUDITING-TOOL/homepagepic.jpeg")); //Sets the image to be displayed as an icon
				      //  Dimension size = label.getPreferredSize(); //Gets the size of the image
				        label.setBounds(280, 200, 600, 350); //Sets the location of the image
				     
				 
				    c.add(label); //Adds objects to the container
				        frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 public Home_Page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Network Security Auditing Tool");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(403, 11, 524, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_6 = new JButton("i");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Info.main(null);
			}
		});
		btnNewButton_6.setFont(new Font("Times New Roman", Font.PLAIN, 80));
		btnNewButton_6.setBounds(1089, 0, 95, 68);
		contentPane.add(btnNewButton_6);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(73, 28, 101, 22);
		contentPane.add(menuBar);
		
		JMenuItem dev, aud, add, com, rep, view, menu;
	    
	    //type menu
	    JMenu filemain = new JMenu("Menu");

	    menu = new JMenuItem("Home Page");
	    view = new JMenuItem("View Device Inventory");
	    aud = new JMenuItem("Manage Auditing");
	    add = new JMenuItem("Manage Devices/Inventory");
	    com = new JMenuItem("Manage Compliance");
	    rep = new JMenuItem("Reports");
	    
	    filemain.add(menu);
	    filemain.add(view);
	    filemain.add(aud);
	    filemain.add(add);
	    filemain.add(com);
	    filemain.add(rep);
		
	    menuBar.add(filemain);		
	    
	    JLabel lblNewLabel_1 = new JLabel("Welcome to the network securtity auditing tool where you can track, record and audit your network");
	    lblNewLabel_1.setBounds(274, 156, 822, 32);
	    contentPane.add(lblNewLabel_1);
		
	    view.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Devices.main(null);
	    	}
		});
	    
	    aud.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Audit.main(null);
	    	}
		});
	    		   	   
	    add.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				AddDevice.main(null);
	    	}
		});
	    
	    com.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Compliance.main(null);
	    	}
		});
	    	 
	    rep.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Reports.main(null);
	    	}
		});
	    
	    menu.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Home_Page.main(null);
	    	}
		});
		
	}
}