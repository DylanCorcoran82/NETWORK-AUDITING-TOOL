import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class Compliance extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldsearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compliance frame = new Compliance();
					frame.setVisible(true);
					
					Container c = frame.getContentPane();
					c.setBackground(Color.LIGHT_GRAY);
					
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					JLabel emptyLabel = new JLabel("");
					emptyLabel.setPreferredSize(new Dimension( (int)dimension.getWidth() / 2, (int)dimension.getHeight()/2 ));
					frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
					frame.setLocation((int)dimension.getWidth()/30, (int)dimension.getHeight()/7);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Compliance() {
		    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
	
		JLabel lblNewLabel = new JLabel("Compliance For Users");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(327, 18, 289, 62);
		contentPane.add(lblNewLabel);
		
		JButton pauditbutton = new JButton("Preform Compliance Check");
		pauditbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Preform_Compliance.main(null);
			}
		});
		pauditbutton.setFont(new Font("Serif", Font.BOLD, 18));
		pauditbutton.setBounds(309, 485, 254, 69);
		contentPane.add(pauditbutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 220, 164, 220);
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
         
         ListModel model = list.getModel();

    	 for(int i=0; i < model.getSize(); i++){
    		 Object name = model.getElementAt(i); 
    		 
    		 Statement stmt2 = conn.createStatement();
	        	String query2 = "SELECT * FROM Compliance WHERE user_name ='"+name+"'";
	        	 ResultSet rs2 = stmt2.executeQuery(query2);
	        	 
	        	 String score = rs2.getString("score");
	        	 
	        	 if (score ==null) {
	        		 listModel.setElementAt("(HIGH RISK) "+name, i);
	        	 }
    		 
	        	 if (score !=null) {
	        		 int risk = Integer.parseInt(score);
	        		 
	        		 if (risk == 0) {
	        			 listModel.setElementAt("(HIGH RISK) "+name, i);
		        	 }
		        		        		 
	        	 }  		 
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
        
        JLabel labelscore = new JLabel("Info");
		labelscore.setBounds(275, 120, 508, 29);
		contentPane.add(labelscore);
		labelscore.setVisible(false);
		
		JLabel labelinfo = new JLabel("Info 2");
	    labelinfo.setBounds(275, 157, 633, 16);
	    contentPane.add(labelinfo);
	    labelinfo.setVisible(false);
	    
	    JLabel labeldevice = new JLabel("User Info");
	    labeldevice.setFont(new Font("Serif", Font.BOLD, 15));
	    labeldevice.setBounds(299, 141, 352, 348);
	    contentPane.add(labeldevice);
	    labeldevice.setVisible(false);
	        
	    
		JButton btnNewButton_1 = new JButton("Select");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);	
		             	          
					 String selecteduser = list.getSelectedValue().toString();
					 					          			     		       	     
					 if (selecteduser != null && !selecteduser.isEmpty()) {
						 					 						      
					        	Statement stmt = conn.createStatement();
					        	String query = "SELECT * FROM Compliance WHERE user_name = '"+selecteduser+"'";
					        	 ResultSet rs = stmt.executeQuery(query);
					        	 
					        	 String score = rs.getString("score");	
					        	 
					        	 if (score == null) {
					        		 String st = "User has not been audited yet, please audit now!";
							        	JOptionPane.showMessageDialog(null, st);
					        	 }
					        	 
					        	 else {
					        		 int rating = Integer.parseInt(score);
					        		if(rating <= 2){
					        			labelscore.setText("The risk of this user is Low");
							        	 labelscore.setVisible(true);								      
							        	 labelinfo.setVisible(true);
					        		} 
					        	
					        		if(rating <= 4 && rating >=3){
					        			labelscore.setText("The risk of this user is Medium");
							        	 labelscore.setVisible(true);	
							        	 labelinfo.setVisible(true);
					        		}
					        		
					        		if(rating >= 5){
					        			labelscore.setText("The risk of this user is High");
							        	 labelscore.setVisible(true);
							        	 labelinfo.setVisible(true);
							        	 
					        		}					        	 		
							       						
							        				  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Users WHERE user_name = '"+selecteduser+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("user_name");
							                String role = rs3.getString("role");
							                String contact = rs3.getString("contact");
					 		                
							                labeldevice.setText("<html>Should this user "+name+"<br>be allowed to access these devices?<br><br>"
							                		+ "Should the user "+name+"have this role "+role+"<br> Please change it if not<br><br>"+
							                		"Is the user following security standards and policies?<br><br>"
							                				+"Please contact the user on "+contact+"<br>if anything is inaccurate or not correct</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
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
		});
		btnNewButton_1.setFont(new Font("Serif", Font.BOLD, 24));
		btnNewButton_1.setBounds(45, 463, 138, 44);
		contentPane.add(btnNewButton_1);
		
		textFieldsearch = new JTextField();
		textFieldsearch.setBounds(18, 141, 117, 26);
		contentPane.add(textFieldsearch);
		textFieldsearch.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = textFieldsearch.getText();
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);
		            
		            System.out.println("Connection to SQLite has been established.");  
		            
		       	 Statement stmt = conn.createStatement();
		       	 String query2 = "SELECT * FROM Users WHERE user_name LIKE '%"+query+"%'";
		       	 ResultSet rs = stmt.executeQuery(query2);
		         
		     	DefaultListModel listModel2;
		         listModel2 = new DefaultListModel();
		         list.setModel(listModel2);
		         
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
		btnsearch.setBounds(130, 141, 90, 29);
		contentPane.add(btnsearch);
		
		 //main menu bar
	    JMenuBar menuBarmain = new JMenuBar();
	    menuBarmain.setBounds(25, 16, 102, 22);
	    contentPane.add(menuBarmain);
		
	    JMenuItem view, aud, add, rep, menu , com;
	    
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
	    
	    menu.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Home_Page.main(null);
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
	    
	    JLabel lblNewLabel_2 = new JLabel("Search For User");
	    lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 16));
	    lblNewLabel_2.setBounds(50, 103, 156, 26);
	    contentPane.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_1 = new JLabel("Select User to view Compliance");
	    lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 15));
	    lblNewLabel_1.setBounds(18, 185, 212, 23);
	    contentPane.add(lblNewLabel_1);
	    
	}
}
