import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Reports extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reports frame = new Reports();
					frame.setVisible(true);
					
					Container c = frame.getContentPane();
					c.setBackground(Color.LIGHT_GRAY);
					
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					JLabel emptyLabel = new JLabel("");
					emptyLabel.setPreferredSize(new Dimension( (int)dimension.getWidth() / 2, (int)dimension.getHeight()/2 ));
					System.out.println(emptyLabel);
					frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
					frame.setLocation((int)dimension.getWidth()/4, (int)dimension.getHeight()/4);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Reports() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reports");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(223, 27, 149, 62);
		contentPane.add(lblNewLabel);
		
		//main menu bar
	    JMenuBar menuBarmain = new JMenuBar();
	    menuBarmain.setBounds(25, 16, 102, 22);
	    contentPane.add(menuBarmain);
		
	    JMenuItem view, aud, add, com, menu, rep;
	    
	    JMenu filemain = new JMenu("Main Menu");

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
		
	    menuBarmain.add(filemain);		
	    
	    JButton createbutton = new JButton("Create Report");
	    createbutton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		Connection conn = null;
	    		
	    		 try {
	    			 
	    			 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
			           
			            conn = DriverManager.getConnection(url);  
			            
			            Statement stmt = conn.createStatement();
				       	 String query = "SELECT * FROM Audit";
				       	 ResultSet rs = stmt.executeQuery(query);
				         ResultSetMetaData rsmd = rs.getMetaData();	                
	          
	                 FileWriter fw = new FileWriter("C:/Users/Public/Desktop/Audit.csv");
	                 BufferedWriter bw = new BufferedWriter(fw);

	                 int numColumns = rsmd.getColumnCount();
	                 for (int i = 1; i <= numColumns; i++) {
	                     bw.write(rsmd.getColumnName(i));
	                     if (i < numColumns) {
	                         bw.write(",");
	                     }
	                 }
	                 bw.newLine();
	                
	                 while (rs.next()) {
	                     for (int i = 1; i <= numColumns; i++) {
	                         bw.write(rs.getString(i));
	           
	                         if (i < numColumns) {
	                             bw.write(",");
	                         }
	                     }
	                     bw.newLine();
	                  
	                 }
	                 
	                 String st = "Report Created!";
			        	JOptionPane.showMessageDialog(null, st);

	                 bw.close();
	                 fw.close();
	                 rs.close();
	                 stmt.close();
	                 conn.close();

	             }
	    		 catch (Exception ex) {
	                 ex.printStackTrace();
	             }
	    		
	    		
	    	}
	    });
	    createbutton.setFont(new Font("Serif", Font.BOLD, 22));
	    createbutton.setBounds(153, 221, 196, 62);
	    contentPane.add(createbutton);
	    
	    JLabel lblNewLabel_1 = new JLabel("Press the button below to create a device audit report");
	    lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 16));
	    lblNewLabel_1.setBounds(76, 159, 373, 30);
	    contentPane.add(lblNewLabel_1);
		
	    menu.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Home_Page.main(null);
	    	}
		});
	    
	    rep.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Reports.main(null);
	    	}
		});
	    
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
	    
	}
}
