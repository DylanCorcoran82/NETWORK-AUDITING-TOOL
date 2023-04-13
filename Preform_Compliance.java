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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;

public class Preform_Compliance extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldsearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Preform_Compliance frame = new Preform_Compliance();
					frame.setVisible(true);
					
					Container c = frame.getContentPane();
					c.setBackground(Color.LIGHT_GRAY);
					
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					JLabel emptyLabel = new JLabel("");
					emptyLabel.setPreferredSize(new Dimension( (int)dimension.getWidth() / 2, (int)dimension.getHeight()/2 ));
					frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
					frame.setLocation((int)dimension.getWidth()/5, (int)dimension.getHeight()/5);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Preform_Compliance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Preform User Compliance Audit");
		lblNewLabel.setBounds(262, 23, 343, 62);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		contentPane.add(lblNewLabel);
		
		 JLabel lblNewLabel_1 = new JLabel("Search for Device");
	        lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 17));
	        lblNewLabel_1.setBounds(37, 295, 138, 21);
	        contentPane.add(lblNewLabel_1);
	        
	        textFieldsearch = new JTextField();
	        textFieldsearch.setBounds(18, 328, 117, 26);
	        contentPane.add(textFieldsearch);
	        textFieldsearch.setColumns(10);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(0, 0, 111, 69);
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 24));
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 165, 138, 118);
		contentPane.add(scrollPane);
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("Search");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String query = textFieldsearch.getText();
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);
		           
		            DefaultListModel listModel2;
			         listModel2 = new DefaultListModel();
			         list.setModel(listModel2);
			         
		       	 Statement stmt = conn.createStatement();
		       	 String query2 = "SELECT * FROM Users WHERE user_name LIKE '%"+query+"%'";
		       	 ResultSet rs = stmt.executeQuery(query2);
		       	       
		         while (rs.next()) {
		        	
		                String name = rs.getString("user_name");
		                listModel2.addElement(name);  		        		
		         }  			         
		         
		         } catch (SQLException f) {
		             System.out.println(f.getMessage());
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
        btnNewButton_1.setBounds(136, 328, 97, 29);
        contentPane.add(btnNewButton_1);
		 
		 JLabel labelquestion = new JLabel("New label");
		 labelquestion.setFont(new Font("Serif", Font.BOLD, 15));
		 labelquestion.setBounds(197, 167, 547, 55);
	        contentPane.add(labelquestion);
	        labelquestion.setVisible(false);
	        
	        JRadioButton radioyes = new JRadioButton("Yes");
	        radioyes.setBounds(346, 246, 141, 23);
	        contentPane.add(radioyes);
	        
	        JRadioButton radiono = new JRadioButton("No");
	        radiono.setBounds(346, 283, 141, 23);
	        contentPane.add(radiono);
	        
	        JRadioButton radiona = new JRadioButton("NA");
	        radiona.setBounds(346, 318, 141, 23);
	        contentPane.add(radiona);
	           
	           ButtonGroup group = new ButtonGroup();
	           group.add(radioyes);
	           group.add(radiono);
	           group.add(radiona);
	        
		
		Connection conn = null;
        try {
            // db parameters
        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);	                

       	 Statement stmt = conn.createStatement();
       	 String query = "SELECT * FROM Users";
       	 ResultSet rs = stmt.executeQuery(query);   
       	 ResultSetMetaData rsmd = rs.getMetaData();	
                                       
       	 //type list 
         DefaultListModel listModel;
         listModel = new DefaultListModel();  
         list.setModel(listModel);          
       	 
         while (rs.next()) {
      	   
             String name = rs.getString("user_name");            
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
        
        JButton buttonselectaudit = new JButton("Select");
        buttonselectaudit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		buttonselectaudit.setVisible(false);
        		lblNewLabel_1.setVisible(false);
	        	textFieldsearch.setVisible(false);
	        	btnNewButton_1.setVisible(false);
	      	
	        	 
	        	 if (list.getSelectedValue() == null) {
	        		 String st = "Device not selected, Please select a device to audit!";
			        	JOptionPane.showMessageDialog(null, st); 
			        	lblNewLabel_1.setVisible(true);
			        	textFieldsearch.setVisible(true);
			        	btnNewButton_1.setVisible(true);
			        	buttonselectaudit.setVisible(true);
	        	 }
	        	 
	        	 else { 		
        		Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);	
		             	          
					 String selecteduser = list.getSelectedValue().toString();			
							          			     		       	     
					 if (selecteduser != null && !selecteduser.isEmpty()) {
						 					 					      
					        	Statement stmt = conn.createStatement();
					        	String query = "SELECT * FROM Users WHERE user_name = '"+selecteduser+"'";
					        	 ResultSet rs = stmt.executeQuery(query);
					        	 
					        	 Statement stmt3 = conn.createStatement();
							        String query3 = "SELECT * FROM Compliance WHERE user_name = '"+selecteduser+"'";
							       	 ResultSet rs3 = stmt3.executeQuery(query3);
							       	 
							       	String name = rs3.getString("user_name"); 
							  
							       	
							       	if (name == null) {
							       		
							       		Statement stmt5 = conn.createStatement();
								        String query5 = "SELECT MAX(id) FROM Compliance";
								       	 ResultSet rs5 = stmt.executeQuery(query5);
								     		  
								        int highestID = 0;

								        if (rs5.next()) {
								          highestID = rs5.getInt(1);
								        }

								        int newID = highestID + 1;
								       	 
								        String timestamp = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());  
								        
								        int score = 0;
								      
								        	PreparedStatement stmt4 = conn.prepareStatement("insert into Compliance (id,user_name,score,timestamp) values(?,?,?,?) ");
								        	stmt4.setInt(1, newID);
								        	stmt4.setString(2, selecteduser);					        
								        	stmt4.setInt(3, score);
								        	stmt4.setString(4, timestamp);			        	
								        	
								        	int rs2 = stmt4.executeUpdate();
								        	
							        }
							       	
							     	if (name != null) {
							     		int score = 0;
										PreparedStatement ps2 = conn.prepareStatement("UPDATE Compliance SET score=? WHERE user_name= '"+selecteduser+"'");
							        	ps2.setInt(1, score);
							        	ps2.executeUpdate();
							     	}
							       	
						        	 Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM Compliance_Questions";
				  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
				  		        	
				  		         	 rs.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs.getString("question");
				  		         	 labelquestion.setText(question);
						        				       	 						       	 				       	 		
						     	
					        }	 

		        }catch (SQLException f) {
				             System.out.println(f.getMessage());
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
        	}
        });
        
			        	  
        //next button moves onto next question
        JButton nextbutton = new JButton("Next");
	        nextbutton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        	 String selecteduser = list.getSelectedValue().toString();	
	        	 
	        		Connection conn = null;
	  		          try {
	  		              // db parameters
	  		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
	  		              // create a connection to the database
	  		              conn = DriverManager.getConnection(url);
	  		              
	        	 Statement stmt = conn.createStatement();
		        	String query = "SELECT * FROM Compliance WHERE user_name = '"+selecteduser+"'";
		        	 ResultSet rs = stmt.executeQuery(query);
		        	 
		        	 //if no was selected update into entry with new score
		        	 int score = rs.getInt("score");
	        		
	        		if(radiono.isSelected()) {
		         		score ++; 		
		         		String timestamp = new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date());
		         		
		         		Statement stmt4 = conn.createStatement();
						PreparedStatement ps2 = conn.prepareStatement("UPDATE Compliance SET score=?, timestamp=? WHERE user_name= '"+selecteduser+"'");
			        	ps2.setInt(1, score);
			        	ps2.setString(2, timestamp);
			        	ps2.executeUpdate();
		         		
		         	 }        		         		              	
		         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM Compliance_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 ResultSetMetaData rsmd = rs.getMetaData();
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM Compliance_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");      	
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM Compliance_questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	 int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of user complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        		        	
		        	
	  		        } catch (SQLException f) {
			             System.out.println(f.getMessage());
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
	        nextbutton.setBounds(372, 384, 117, 29);
	        contentPane.add(nextbutton);
            
        buttonselectaudit.setFont(new Font("Serif", Font.BOLD, 24));
        buttonselectaudit.setBounds(27, 407, 138, 42);
        contentPane.add(buttonselectaudit);
        
        JLabel labeldevices = new JLabel("Select User to Audit:");
        labeldevices.setFont(new Font("Serif", Font.BOLD, 17));
        labeldevices.setBounds(18, 131, 184, 26);
        contentPane.add(labeldevices);
        	
	}
}
