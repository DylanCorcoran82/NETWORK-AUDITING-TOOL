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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class Preform_Audit extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldsearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Preform_Audit frame = new Preform_Audit();
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
	
	public Preform_Audit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Preform Audit");
		lblNewLabel.setBounds(262, 23, 211, 62);
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
		btnNewButton.setBounds(0, 0, 106, 67);
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
		       	 String query2 = "SELECT * FROM Devices WHERE device_name LIKE '%"+query+"%'";
		       	 ResultSet rs = stmt.executeQuery(query2);
		       	       
		         while (rs.next()) {
		        	
		                String name = rs.getString("device_name");
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
       	 String query = "SELECT * FROM Devices";
       	 ResultSet rs = stmt.executeQuery(query);   
       	 ResultSetMetaData rsmd = rs.getMetaData();	
                                       
       	 //type list 
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
		             	      
		            String selecteddevice = list.getSelectedValue().toString();	
							          			     		       	     
					 if (selecteddevice != null && !selecteddevice.isEmpty()) {
						 					 
						      
					        	Statement stmt = conn.createStatement();
					        	String query = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					        	 ResultSet rs = stmt.executeQuery(query);
					        	 String type = rs.getString("type");
					        	 
					        	 Statement stmt3 = conn.createStatement();
							        String query3 = "SELECT * FROM Audit WHERE device_name = '"+selecteddevice+"'";
							       	 ResultSet rs3 = stmt.executeQuery(query3);
							       	 
							       	String name = rs3.getString("device_name"); 
							  
							       	
							       	if (name == null) {
							       		
							       		Statement stmt5 = conn.createStatement();
								        String query5 = "SELECT MAX(id) FROM Audit";
								       	 ResultSet rs5 = stmt.executeQuery(query5);
								     		  
								        int highestID = 0;

								        if (rs5.next()) {
								          highestID = rs5.getInt(1);
								        }

								        int newID = highestID + 1;
								       	 
								        String timestamp = new SimpleDateFormat("d-MMM-yyyy").format(new java.util.Date());  
											        
								        int score = 0;
								      
								        	PreparedStatement stmt4 = conn.prepareStatement("insert into Audit (id,device_name,type_questions,score,timestamp) values(?,?,?,?,?) ");
								        	stmt4.setInt(1, newID);
								        	stmt4.setString(2, selecteddevice);
								        	stmt4.setString(3, type);
								        	stmt4.setInt(4, score);
								        	stmt4.setString(5, timestamp);			        	
								        	
								        	int rs2 = stmt4.executeUpdate();
								        	
							        }
							       	
							     	if (name != null) {
							     		int score = 0;
										PreparedStatement ps2 = conn.prepareStatement("UPDATE Audit SET score=? WHERE device_name= '"+selecteddevice+"'");
							        	ps2.setInt(1, score);
							        	ps2.executeUpdate();
							     	}
							       	
							     	String router = "Router";
						        	String switcht = "Switch";
						        	String accesspoint ="AccessPoint";
						        	String hub ="Hub";
						        	String modem ="Modem";
						        	String server ="Server";
						        	String asa ="ASA";
						        	String bridge ="Bridge";
					        	
					        	//use the router questions
						       	 if (type.equals(router)) {
						        	
						        	 Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM Router_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);
						           	
						       	 }
						       	  
						       	 //use the switch questions
						       	if (type.equals(switcht)) {
						       		
						       		Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM Switch_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);	
						       	 }
						       	
						       	//use the access point questions
						     	if (type.equals(accesspoint)) {
						     		
						     		Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM AP_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);
						       	 } 	       	 
						       	 
						       	 //use the bridge questions
						     	if (type.equals(bridge)) {
						     		
						     		Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM Bridge_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);
						       	 } 	
				 
						       	 //use the hub questions
						     	if (type.equals(hub)) {
						     		
						     		Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM Hub_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);
						       	 } 	
						       	 
						       	 //use the server questions
						     	if (type.equals(server)) {
						     		
						     		Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM Server_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);
						       	 } 	
						       	 	 
						       	 //use the modem questions
						     	if (type.equals(modem)) {
						     		
						     		Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM Modem_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);
						       	 } 	
						       	   	 
						       	 //use the ASA questions
						     	if (type.equals(asa)) {
						     		
						     		Statement stmt2 = conn.createStatement();
				  		         	 String query2 = "SELECT * FROM ASA_Questions";
				  		         	 ResultSet rs2 = stmt2.executeQuery(query2);   
				  		        	
				  		         	 rs2.next();
				  		         	 labelquestion.setVisible(true);
				  		         	 String question = rs2.getString("question");
				  		         	 labelquestion.setText(question);
						       	 } 	
						     	
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
	        		
	        	 String selecteddevice = list.getSelectedValue().toString();	
	        	 
	        		Connection conn = null;
	  		          try {
	  		              // db parameters
	  		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
	  		              // create a connection to the database
	  		              conn = DriverManager.getConnection(url);
	  		              
	        	 Statement stmt = conn.createStatement();
		        	String query = "SELECT * FROM Audit WHERE device_name = '"+selecteddevice+"'";
		        	 ResultSet rs = stmt.executeQuery(query);
		        	 
		        	 //if no was selected update into entry with new score
		        	 int score = rs.getInt("score");
	        		
	        		if(radiono.isSelected()) {
		         		score ++; 		
		         		String timestamp = new SimpleDateFormat("d-MMM-yyyy").format(new java.util.Date());
		         		
		         		Statement stmt4 = conn.createStatement();
						PreparedStatement ps2 = conn.prepareStatement("UPDATE Audit SET score=?, timestamp=? WHERE device_name= '"+selecteddevice+"'");
			        	ps2.setInt(1, score);
			        	ps2.setString(2, timestamp);
			        	ps2.executeUpdate();
		         		
		         	 }        
		         		              	
		         	//checks what device type this is
 		         	String query6 = ("SELECT * FROM Audit WHERE device_name='"+selecteddevice+"'");
		         	 ResultSet rs6 = stmt.executeQuery(query6); 
		        	 
		         	String typequestions = rs6.getString("type_questions");
		         	String router = "Router";
		        	String switcht = "Switch";
		        	String accesspoint ="AccessPoint";
		        	String hub ="Hub";
		        	String modem ="Modem";
		        	String server ="Server";
		        	String asa ="ASA";
		        	String bridge ="Bridge";
		        	
		         	//if they are the router questions ask the router questions then
		        	if(typequestions.equals(router)) {
		         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM Router_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM Router_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");      	
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM Router_questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	 int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	}
		        	
		        	//use switch questions instead
		        	if(typequestions.equals(switcht)) {
			         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM Switch_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM Switch_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");
	  		         	 
	  		         	rs3.next();
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM Switch_questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	}
		        	
		        	//use the access point questions
		        	if(typequestions.equals(accesspoint)) {
			         	
		        		 String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM AP_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM AP_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs.getString("question");   		         	
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM AP_Questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	 int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	} 		         	    
	  		      
		        	//use the server questions instead 
		        	if(typequestions.equals(server)) {
			         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM Server_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 ResultSetMetaData rsmd = rs.getMetaData();
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM Server_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");
	  		         	 
	  		         	rs3.next();
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM Server_questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	} 
		        
			        	//use the ASA questions instead 
		        	if(typequestions.equals(asa)) {
			         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM ASA_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 ResultSetMetaData rsmd = rs.getMetaData();
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM ASA_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");
	  		         	 
	  		         	rs3.next();
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM ASA_questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	} 
		        		
		        	//use the Modem questions instead 
		        	if(typequestions.equals(modem)) {
			         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM Modem_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 ResultSetMetaData rsmd = rs.getMetaData();
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM Modem_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");
	  		         	 
	  		         	rs3.next();
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM Modem_questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	} 
		        	
		        	//use the hub questions instead
		        	if(typequestions.equals(hub)) {
			         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM Hub_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 ResultSetMetaData rsmd = rs.getMetaData();
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM Hub_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");
	  		         	 
	  		         	rs3.next();
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM Hub_questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	} 
		        	        	
		        	//use the bridge questions instead
		        	if(typequestions.equals(bridge)) {
			         	
	  		            String text = labelquestion.getText();

	  		         	 Statement stmt2 = conn.createStatement();
	  		         	 String query2 = ("SELECT * FROM Bridge_Questions WHERE question='"+text+"'");
	  		         	 ResultSet rs2 = stmt.executeQuery(query2);   
	  		         	 ResultSetMetaData rsmd = rs.getMetaData();
	  		         	 
	  		         	String id = rs.getString("id");
	  		         	int newid = Integer.parseInt(id);
	  		         	newid++;
			          	
	  		          String query3 = ("SELECT * FROM Bridge_Questions WHERE id='"+newid+"'");
	  		         	 ResultSet rs3 = stmt.executeQuery(query3); 
	  		        	
	  		         	 rs3.next();
	  		         	 
	  		         	 String question = rs2.getString("question");
	  		         	 
	  		         	rs3.next();
	  		         	 
	  		         	 String query4 = "SELECT MAX(id) FROM Bridge_Questions";
				       	 ResultSet rs4 = stmt.executeQuery(query4);
				     		  
				       	int highestID = rs.getInt(1);
				       	 highestID++;
	  		         	 
	  		         	 if (newid == highestID) {
	  		         		String st = "Audit of device complete!";
				        	JOptionPane.showMessageDialog(null, st); 
				        	list.clearSelection();
				        	buttonselectaudit.setVisible(true);
				        	lblNewLabel_1.setVisible(true);
				        	textFieldsearch.setVisible(true);
				        	btnNewButton_1.setVisible(true);
				        	group.clearSelection();
				        	
	  		         	 }
	  		              	 
	  		         	 labelquestion.setText(question); 	  	 
		        	} 
		        	//end of functions for questions
		        	
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
        
        JLabel labeldevices = new JLabel("Select Device to Audit:");
        labeldevices.setFont(new Font("Serif", Font.BOLD, 17));
        labeldevices.setBounds(18, 131, 184, 26);
        contentPane.add(labeldevices);
        	
	}
}
