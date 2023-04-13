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

public class Add_Vendor extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldname;
	private JTextField textFieldwarr;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Vendor frame = new Add_Vendor();
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
	   

	public Add_Vendor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 189, 183, 26);
		contentPane.add(scrollPane);
		JList listtype = new JList();
		scrollPane.setViewportView(listtype);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(195, 225, 183, 27);
		contentPane.add(scrollPane_1);
		JList listuser = new JList();
		scrollPane_1.setViewportView(listuser);
		
		Connection conn = null;
        try {
            // db parameters
        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);	                

       	 Statement stmt = conn.createStatement();
       	 String query = "SELECT * FROM Device_Type";
       	 ResultSet rs = stmt.executeQuery(query);      
                          
       	 //type list 
         DefaultListModel listModel;
         listModel = new DefaultListModel();  
         listtype.setModel(listModel);    
           
         //vendor list
         DefaultListModel listModel2;
         listModel2 = new DefaultListModel();  
         listuser.setModel(listModel2);
       	 
         while (rs.next()) {
      	   
             String type = rs.getString("device_type");            
             listModel.addElement(type);                      
        }    	 
       	 String query2 = "SELECT * FROM Users";
       	 ResultSet rs2 = stmt.executeQuery(query2);
       	 
       	while (rs2.next()) {
       	   
            String user = rs.getString("user_name");           
            listModel2.addElement(user);                      
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
        
        	
		JLabel lblNewLabel = new JLabel("Add A Device Vendor");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(160, 15, 289, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 24));
		btnNewButton.setBounds(0, 0, 96, 69);
		contentPane.add(btnNewButton);
		
		JButton add_device = new JButton("Add Vendor");
		add_device.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);		            	      
		            
		            String name = textFieldname.getText();	          
		            String warr = textFieldwarr.getText();
		           
					 String selectedtype = listtype.getSelectedValue().toString();			
					 String selecteduser = listuser.getSelectedValue().toString();
			          
			        Statement stmt = conn.createStatement();
			        String query = "SELECT MAX(id) FROM Vendor";
			       	 ResultSet rs = stmt.executeQuery(query);
			     		  
			        int highestID = 0;

			        if (rs.next()) {
			          highestID = rs.getInt(1);
			        }

			        int newID = highestID + 1;			       	     
			       	 
			        if (name != null && !name.isEmpty() &&
			        	warr != null && !warr.isEmpty() &&
				        	selectedtype != null && !selectedtype.isEmpty() &&
				        	selecteduser != null && !selecteduser.isEmpty()) {
				      
				        	PreparedStatement stmt4 = conn.prepareStatement("insert into Vendor (id,vendor_name,devices,contact_user,warrenty_dates) values(?,?,?,?,?) ");			   
	       		       	
				        	stmt4.setInt(1, newID);
				         	stmt4.setString(2, name);
				        	stmt4.setString(3, selectedtype);
				        	stmt4.setString(4, selecteduser);
				        	stmt4.setString(5, warr);				     
				        	
				        	int rs2 = stmt4.executeUpdate();
		
			        	
			        	String st = "Success";
			        	JOptionPane.showMessageDialog(null, st);
			        }
			        
			        else 
			        {
			        	String st = "All fields must be filled in, please try again!";
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
		add_device.setFont(new Font("Serif", Font.BOLD, 18));
		add_device.setBounds(93, 308, 140, 41);
		contentPane.add(add_device);
		
		JLabel labelname = new JLabel("Vendor Name");
		labelname.setFont(new Font("Serif", Font.BOLD, 16));
		labelname.setBounds(44, 140, 140, 20);
		contentPane.add(labelname);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(195, 142, 183, 20);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel labeltype = new JLabel("Device Type");
		labeltype.setFont(new Font("Serif", Font.BOLD, 16));
		labeltype.setBounds(44, 182, 102, 26);
		contentPane.add(labeltype);
		
		JLabel labeluser = new JLabel("Contact User");
		labeluser.setFont(new Font("Serif", Font.BOLD, 16));
		labeluser.setBounds(45, 225, 101, 27);
		contentPane.add(labeluser);
		
		JLabel labelwarr = new JLabel("Warrenty Date");
		labelwarr.setFont(new Font("Serif", Font.BOLD, 16));
		labelwarr.setBounds(45, 263, 139, 26);
		contentPane.add(labelwarr);
		
		textFieldwarr = new JTextField();
		textFieldwarr.setBounds(195, 268, 183, 20);
		contentPane.add(textFieldwarr);
		textFieldwarr.setColumns(10);
		
		JButton buttonadduser = new JButton("Add User/ Owner");
		buttonadduser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add_Owner.main(null);
			}
		});
		buttonadduser.setFont(new Font("Serif", Font.BOLD, 12));
		buttonadduser.setBounds(388, 229, 121, 23);
		contentPane.add(buttonadduser);
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldname.setText(null);
				textFieldwarr.setText(null);
				listtype.clearSelection();
				listuser.clearSelection();
				
			}
		});
		reset.setFont(new Font("Serif", Font.BOLD, 20));
		reset.setBounds(257, 308, 121, 41);
		contentPane.add(reset);
		
         }
	} 
