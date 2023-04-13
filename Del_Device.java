import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Del_Device extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Del_Device frame = new Del_Device();
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
	   
	public Del_Device() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 535, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 155, 180, 85);
		contentPane.add(scrollPane);
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		Connection conn = null;
        try {
            // db parameters
        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);	                

       	 Statement stmt = conn.createStatement();
       	 String query = "SELECT * FROM Devices";
       	 ResultSet rs = stmt.executeQuery(query);   
       	 ResultSetMetaData rsmd = rs.getMetaData();	
                 
       	 DefaultListModel listModel;
         listModel = new DefaultListModel();
         list.setModel(listModel); 
       	 
         while (rs.next()) {
      	   
             String name = rs.getString("device_name");      
             listModel.addElement(name);             
        } 	 
       	 
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         } finally {
             try {
                 if (conn != null) {
                     conn.close();
                 }             
             } catch (SQLException ex) {
                 System.out.println(ex.getMessage());
             }
         } 
        	
		JLabel lblNewLabel = new JLabel("Delete Device");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(150, 31, 235, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 24));
		btnNewButton.setBounds(0, 0, 96, 67);
		contentPane.add(btnNewButton);
		
		JButton reset = new JButton("Refresh");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);	                

		       	 Statement stmt = conn.createStatement();
		       	 String query = "SELECT * FROM Devices";
		       	 ResultSet rs = stmt.executeQuery(query);   
		       	 ResultSetMetaData rsmd = rs.getMetaData();	
		                 
		       	 DefaultListModel listModel;
		         listModel = new DefaultListModel();
		         list.setModel(listModel); 
		       	 
		         while (rs.next()) {
		      	   
		             String name = rs.getString("device_name");      
		             listModel.addElement(name);             
		        } 	 
		       	 
		         } catch (SQLException e) {
		             System.out.println(e.getMessage());
		         } finally {
		             try {
		                 if (conn != null) {
		                     conn.close();
		                 }             
		             } catch (SQLException ex) {
		                 System.out.println(ex.getMessage());
		             }
		         } 		
				        
			}
		});
		reset.setFont(new Font("Serif", Font.BOLD, 20));
		reset.setBounds(257, 278, 121, 41);
		contentPane.add(reset);
		
		JButton del_model = new JButton("Remove");
		del_model.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//adding the data selected and entered into the database table
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);		            	      
		            	          
					 String selectedname = list.getSelectedValue().toString();			
							          			     		       	     
			       	 
			        if (selectedname != null && !selectedname.isEmpty()
				        ) {
				      
			        	//devices table entry
			        	Statement stmt = conn.createStatement();
			        	String SQL = "DELETE FROM Devices WHERE device_name = '"+selectedname+"'";
			        	stmt.executeUpdate(SQL);
			        	
			        	//audit table entry	        	
			        	Statement stmt2 = conn.createStatement();
			        	String SQL2 = "DELETE FROM Audit WHERE device_name = '"+selectedname+"'";
			        	stmt2.executeUpdate(SQL2);
			        	
			        	String st = "Success";
			        	JOptionPane.showMessageDialog(null, st);
			        }	        
			        else 
			        {
			        	String st = "Not successful, Please try again!";
			        	JOptionPane.showMessageDialog(null, st);
			        }
			        
			        
		        } catch (SQLException e) {
		             System.out.println(e.getMessage());
		         } finally {
		             try {
		                 if (conn != null) {
		                     conn.close();
		                 }             
		             } catch (SQLException ex) {
		                 System.out.println(ex.getMessage());
		             }
			}	
	     
		}
			
		});
		del_model.setFont(new Font("Serif", Font.BOLD, 18));
		del_model.setBounds(124, 278, 121, 41);
		contentPane.add(del_model);
			
		JLabel labeltype = new JLabel("Please select one to remove:");
		labeltype.setFont(new Font("Serif", Font.BOLD, 14));
		labeltype.setBounds(10, 155, 175, 26);
		contentPane.add(labeltype);
		
         }
	} 
