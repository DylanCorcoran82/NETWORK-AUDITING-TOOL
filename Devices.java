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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Devices extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textFieldsearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Devices frame = new Devices();
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

	public Devices() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("View Device Inventory");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(379, 11, 243, 62);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 72, 808, 461);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
			
	//Devices Button
		
		JButton btnNewButton_1 = new JButton("Devices");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				table = new JTable();
				scrollPane.setViewportView(table);
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);        

		       	 Statement stmt = conn.createStatement();
		       	 String query = "SELECT * FROM Devices";
		       	 ResultSet rs = stmt.executeQuery(query);
		         ResultSetMetaData rsmd = rs.getMetaData();	                 
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		                String id = rs.getString("id");
		                String name = rs.getString("device_name");
		                String vendor = rs.getString("vendor");
		                String type = rs.getString("type");
		                String os_version = rs.getString("os_version");
		                String owner = rs.getString("owner");
		                String function = rs.getString("function");
		                String model = rs.getString("model");
		                String location = rs.getString("location");
		       
		                String[] data = {id, name, vendor, type , os_version , owner , function , model , location} ;
		            
		                tm.addRow(data);       
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
		
		btnNewButton_1.setBounds(75, 141, 89, 23);
		contentPane.add(btnNewButton_1);
		
		//Users Button
		
		JButton btnNewButton_2 = new JButton("Users");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				table = new JTable();
				scrollPane.setViewportView(table);
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
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		        	    String id = rs.getString("id");
		                String name = rs.getString("user_name");
		                String role = rs.getString("role");
		                String contact = rs.getString("contact");
		               	            
		                String[] data = {id, name, role, contact} ;	         
		            
		                tm.addRow(data);		       
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
		btnNewButton_2.setBounds(75, 175, 89, 23);
		contentPane.add(btnNewButton_2);	
		
		//type Button
			
		JButton btnNewButton_3 = new JButton("Type");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				table = new JTable();
				scrollPane.setViewportView(table);
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
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		        	    String id = rs.getString("id");
		                String name = rs.getString("device_type");
		                          
		                String[] data = {id, name} ;	         
		            
		                tm.addRow(data);	     
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
		btnNewButton_3.setBounds(75, 209, 89, 23);
		contentPane.add(btnNewButton_3);
		
		//vendor Button
		
		JButton btnNewButton_4 = new JButton("Vendor");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				table = new JTable();
				scrollPane.setViewportView(table);
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);       

		       	 Statement stmt = conn.createStatement();
		       	 String query = "SELECT * FROM Vendor";
		       	 ResultSet rs = stmt.executeQuery(query);
		         ResultSetMetaData rsmd = rs.getMetaData();	         	       
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		        	    String id = rs.getString("id");
		                String vendor_name = rs.getString("vendor_name");
		                String devices = rs.getString("devices");
		                String contact_user = rs.getString("contact_user");
		                String warrenty = rs.getString("warrenty_dates");
		               	            
		                String[] data = {id, vendor_name, devices, contact_user, warrenty} ;	                
		                tm.addRow(data);
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
		btnNewButton_4.setBounds(75, 243, 89, 23);
		contentPane.add(btnNewButton_4);
		
		//model Button
		
		JButton btnNewButton_5 = new JButton("Model");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table = new JTable();
				scrollPane.setViewportView(table);
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);       

		       	 Statement stmt = conn.createStatement();
		       	 String query = "SELECT * FROM Model";
		       	 ResultSet rs = stmt.executeQuery(query);
		         ResultSetMetaData rsmd = rs.getMetaData();	         	       
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		        	    String id = rs.getString("id");
		                String model_name = rs.getString("model_name");
		                String device_type = rs.getString("device_type");
		                String os_last = rs.getString("os_last_supported");	           
		               	            
		                String[] data = {id, model_name, device_type, os_last} ;	                
		                tm.addRow(data);
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
		btnNewButton_5.setBounds(75, 277, 89, 23);
		contentPane.add(btnNewButton_5);
			
		//function Button
	
		JButton btnNewButton_6 = new JButton("Function");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table = new JTable();
				scrollPane.setViewportView(table);
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);   

		       	 Statement stmt = conn.createStatement();
		       	 String query = "SELECT * FROM Function";
		       	 ResultSet rs = stmt.executeQuery(query);
		         ResultSetMetaData rsmd = rs.getMetaData();	         	       
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		        	    String id = rs.getString("id");
		                String function_name = rs.getString("function_name");
		                String desc = rs.getString("description");	 
		               	            
		                String[] data = {id, function_name, desc} ;	                
		                tm.addRow(data);
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
		btnNewButton_6.setBounds(75, 311, 89, 23);
		contentPane.add(btnNewButton_6);
		
		//location Button
		
		JButton btnNewButton_7 = new JButton("Location");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table = new JTable();
				scrollPane.setViewportView(table);
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);        

		       	 Statement stmt = conn.createStatement();
		       	 String query = "SELECT * FROM Location";
		       	 ResultSet rs = stmt.executeQuery(query);
		         ResultSetMetaData rsmd = rs.getMetaData();	         	       
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		        	    String id = rs.getString("id");
		                String location_name = rs.getString("location_name");
		                String building = rs.getString("building");
		                String room = rs.getString("room");
		               	            
		                String[] data = {id, location_name, building, room} ;	                
		                tm.addRow(data);
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
		btnNewButton_7.setBounds(75, 345, 89, 23);
		contentPane.add(btnNewButton_7);
		
		//OS version Button		
		JButton btnNewButton_8 = new JButton("OS Version");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table = new JTable();
				scrollPane.setViewportView(table);
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);	              

		       	 Statement stmt = conn.createStatement();
		       	 String query = "SELECT * FROM Os_Version";
		       	 ResultSet rs = stmt.executeQuery(query);
		         ResultSetMetaData rsmd = rs.getMetaData();	         	       
		            
		            DefaultTableModel tm = (DefaultTableModel) table.getModel();
		            int cols = rsmd.getColumnCount();
		            String[] colName = new String[cols];
		            for (int i=0; i<cols;i++)
		            	colName[i] = rsmd.getColumnName(i+1);
		            tm.setColumnIdentifiers(colName);
		            
		           while (rs.next()) {
		        	   
		        	    String id = rs.getString("id");
		                String version_name = rs.getString("version_name");
		                String devices = rs.getString("devices");
		                String desc = rs.getString("description");
		               	            
		                String[] data = {id, version_name, devices, desc} ;	                
		                tm.addRow(data);
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
		btnNewButton_8.setBounds(75, 379, 89, 23);
		contentPane.add(btnNewButton_8);
		
		textFieldsearch = new JTextField();
		textFieldsearch.setBounds(639, 32, 179, 26);
		contentPane.add(textFieldsearch);
		textFieldsearch.setColumns(10);
		
		textFieldsearch.setVisible(false);
		
	        Connection conn = null;
	        try {
	            // db parameters
	        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
	            // create a connection to the database
	            conn = DriverManager.getConnection(url);
	            
	            System.out.println("Connection to SQLite has been established.");         

	       	 Statement stmt = conn.createStatement();
	       	 String query = "SELECT * FROM Devices";
	       	 ResultSet rs = stmt.executeQuery(query);
	         ResultSetMetaData rsmd = rs.getMetaData();           
	            
	            DefaultTableModel tm = (DefaultTableModel) table.getModel();
	            int cols = rsmd.getColumnCount();           
	            String[] colName = new String[cols];
	            for (int i=0; i<cols;i++)
	            	colName[i] = rsmd.getColumnName(i+1);
	            tm.setColumnIdentifiers(colName);
	            
	           while (rs.next()) {
	        	   
	                String id = rs.getString("id");
	                String name = rs.getString("device_name");
	                String vendor = rs.getString("vendor");
	                String type = rs.getString("type");
	                String os_version = rs.getString("os_version");
	                String owner = rs.getString("owner");
	                String function = rs.getString("function");
	                String model = rs.getString("model");
	                String location = rs.getString("location");
	                
	                String[] data = { id, name, vendor, type , os_version , owner , function , model , location} ;
	            
	                tm.addRow(data);	     
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
	           
	        //search functions
	    	JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(1033, 32, 132, 22);
			contentPane.add(menuBar);
			
			JMenuItem dev, user, type, ven, mod, fun, loc, os;
		    
		    //type menu
		    JMenu filesearch = new JMenu("Search by:");

		    dev = new JMenuItem("Devices");
		    user = new JMenuItem("Users");
		    type = new JMenuItem("Device Type");
		    ven = new JMenuItem("Vendor");
		    mod = new JMenuItem("Model");
		    fun = new JMenuItem("Function");
		    loc = new JMenuItem("Location");
		    os = new JMenuItem("OS Version");
		    
		    filesearch.add(dev);
		    filesearch.add(user);
		    filesearch.add(type);
		    filesearch.add(ven);
		    filesearch.add(mod);
		    filesearch.add(fun);
		    filesearch.add(loc);
		    filesearch.add(os);
			
		    menuBar.add(filesearch);		
		    
		    //main menu bar
		    JMenuBar menuBarmain = new JMenuBar();
		    menuBarmain.setBounds(25, 16, 102, 22);
		    contentPane.add(menuBarmain);
			
		    JMenuItem view, aud, add, com, rep, menu;
		    
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
			
		    menu.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	dispose();
					Home_Page.main(null);
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
		    
		    rep.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	dispose();
					Reports.main(null);
		    	}
			});
		    
		    //devices search function
		    dev.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_1.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_1.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Devices WHERE device_name LIKE '%"+query+"%' OR vendor LIKE '%"+query+"%' "
		    			       	 		+ "OR type LIKE '%"+query+"%' "
		    			       	 		+ "OR os_version LIKE '%"+query+"%'"
		    			       	 		+ "OR owner LIKE '%"+query+"%'"
		    			       	 		+ "OR function LIKE '%"+query+"%'"
		    			       	 		+ "OR model LIKE '%"+query+"%'"
		    			       	 		+ "OR location LIKE '%"+query+"%'"
		    			       	 		+ "OR exploits LIKE '%"+query+"%'";
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {
		    			        		   
		    			        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
		    
		  //users search function    
		    user.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_2.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_2.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Users WHERE user_name LIKE '%"+query+"%' OR role LIKE '%"+query+"%' "
		    			       	 		+ "OR contact LIKE '%"+query+"%' "; 
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {
		    			        		   
		    			        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
		    	    
		    //device type search function	    
		    type.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_3.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_3.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Device_Type WHERE device_type LIKE '%"+query+"%'";
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {
		    			        		   
		    			        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
		    
		    //vendor search function
		    ven.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_4.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_4.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Vendor WHERE vendor_name LIKE '%"+query+"%' OR devices LIKE '%"+query+"%' "
			    			       	 		+ "OR contact_user LIKE '%"+query+"%' "
			    			       	 		+ "OR warrenty_dates LIKE '%"+query+"%'";
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {
		    			        		   
		    			        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
		    
		    //model search function
		    mod.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_5.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_5.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Model WHERE model_name LIKE '%"+query+"%' OR device_type LIKE '%"+query+"%' "
			    			       	 		+ "OR os_last_supported LIKE '%"+query+"%' "
			    			       	 		+ "OR exploits LIKE '%"+query+"%'";
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {  		   
		    			        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
		    		    
		    //function search function
		    fun.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_6.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_6.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Function WHERE function_name LIKE '%"+query+"%'";
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {
		    			        		   		        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
		     
		    //location search function
		    loc.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_7.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_7.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Location WHERE location_name LIKE '%"+query+"%' OR building LIKE '%"+query+"%' "
			    			       	 		+ "OR room LIKE '%"+query+"%' ";
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {
		    			        		   
		    			        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
	          
		    //os version search function
		    os.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        	btnNewButton_8.doClick();
		        	textFieldsearch.setVisible(true);
		        	      	
		        	JButton btnsearch = new JButton("Search");
		    		btnsearch.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				
		    				btnNewButton_8.doClick();
		    				String query = textFieldsearch.getText();
		    				table = new JTable();
		    				scrollPane.setViewportView(table);
		    				
		    				 Connection conn = null;
		    			        try {
		    			            // db parameters
		    			        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    			            // create a connection to the database
		    			            conn = DriverManager.getConnection(url);
		    			            
		    			            System.out.println("Connection to SQLite has been established.");  
		    			            
		    			       	 Statement stmt = conn.createStatement();
		    			       	 String query2 = "SELECT * FROM Os_Version WHERE version_name LIKE '%"+query+"%' OR devices LIKE '%"+query+"%' "
			    			       	 		+ "OR description LIKE '%"+query+"%' ";
		    			       	 ResultSet rs = stmt.executeQuery(query2);
		    			         ResultSetMetaData rsmd = rs.getMetaData();
		    				
		    			         DefaultTableModel tm = (DefaultTableModel) table.getModel();
		    			            int cols = rsmd.getColumnCount();           
		    			            String[] colName = new String[cols];
		    			            for (int i=0; i<cols;i++)
		    			            	colName[i] = rsmd.getColumnName(i+1);
		    			            tm.setColumnIdentifiers(colName);
		    			            
		    			           while (rs.next()) {
		    			        	   
		    			        	   Object[] rowData = new Object[cols];
		    			        	   
		    			        	   for (int i=1; i<= cols; i++) {
		    			        		   
		    			        	   
		    			                rowData[i-1] = rs.getObject(i);
		    			        	   }
		    			                            
		    			                tm.addRow(rowData);	     
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
		    		btnsearch.setBounds(830, 22, 153, 39);
		    		contentPane.add(btnsearch);	
		    	}
			});
	        }
	}
	     
	   