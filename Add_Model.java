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

public class Add_Model extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldname;
	private JTextField textFieldos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Model frame = new Add_Model();
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
	   
	public Add_Model() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 520, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 155, 180, 34);
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
       	 String query = "SELECT * FROM Device_Type";
       	 ResultSet rs = stmt.executeQuery(query);   
       	 ResultSetMetaData rsmd = rs.getMetaData();	
                 
       	 DefaultListModel listModel;
         listModel = new DefaultListModel();
         list.setModel(listModel); 
       	 
         DefaultListModel listModel2;
         listModel2 = new DefaultListModel();
         
         while (rs.next()) {
      	   
             String type = rs.getString("device_type");      
             listModel.addElement(type);   
             
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
        	
		JLabel lblNewLabel = new JLabel("Add A Device Hardware Model");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(121, 15, 328, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 24));
		btnNewButton.setBounds(0, 0, 102, 67);
		contentPane.add(btnNewButton);
		
		JButton add_model = new JButton("Add Model");
		add_model.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//adding the data selected and entered into the database table
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);		            	      
		            
		            String name = textFieldname.getText();
		            String oslast = textFieldos.getText();
			   	    String selectedtype = list.getSelectedValue().toString();			
					          
			        Statement stmt = conn.createStatement();
			        String query = "SELECT MAX(id) FROM Model";
			       	 ResultSet rs = stmt.executeQuery(query);
			     		  
			        int highestID = 0;

			        if (rs.next()) {
			          highestID = rs.getInt(1);
			        }

			        int newID = highestID + 1;			       	     
			       	 
			        if (name != null && !name.isEmpty() &&
			        		oslast != null && !oslast.isEmpty() &&
				        	selectedtype != null && !selectedtype.isEmpty()) {
				      
				        	PreparedStatement stmt4 = conn.prepareStatement("insert into Model (id,model_name,device_type,os_last_supported) values(?,?,?,?) ");			   
	       		       	
				        	stmt4.setInt(1, newID);
				         	stmt4.setString(2, name);
				         	stmt4.setString(3, selectedtype);
				         	stmt4.setString(4, oslast);
				        				        	
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
		add_model.setFont(new Font("Serif", Font.BOLD, 18));
		add_model.setBounds(106, 254, 121, 41);
		contentPane.add(add_model);
		
		JLabel labelname = new JLabel("Device Model Name");
		labelname.setFont(new Font("Serif", Font.BOLD, 16));
		labelname.setBounds(45, 114, 140, 20);
		contentPane.add(labelname);
		
		JLabel labeltype = new JLabel("Device Type");
		labeltype.setFont(new Font("Serif", Font.BOLD, 16));
		labeltype.setBounds(83, 155, 102, 26);
		contentPane.add(labeltype);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(195, 116, 183, 20);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel labeloslast = new JLabel("OS Last Supported");
		labeloslast.setFont(new Font("Serif", Font.BOLD, 16));
		labeloslast.setBounds(45, 192, 140, 33);
		contentPane.add(labeloslast);
		
		textFieldos = new JTextField();
		textFieldos.setBounds(195, 198, 183, 20);
		contentPane.add(textFieldos);
		textFieldos.setColumns(10);
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldname.setText(null);
				textFieldos.setText(null);
				list.clearSelection();			
			}
		});
		reset.setFont(new Font("Serif", Font.BOLD, 20));
		reset.setBounds(239, 254, 121, 41);
		contentPane.add(reset);
		
         }
	} 
