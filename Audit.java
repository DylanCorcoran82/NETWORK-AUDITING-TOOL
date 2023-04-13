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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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

public class Audit extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldsearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Audit frame = new Audit();
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

	public Audit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 1400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	   
		
		JLabel lblNewLabel = new JLabel("Auditing");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(513, 11, 289, 62);
		contentPane.add(lblNewLabel);
		
		JButton pauditbutton = new JButton("Preform Audit");
		pauditbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Preform_Audit.main(null);
			}
		});
		pauditbutton.setFont(new Font("Serif", Font.BOLD, 24));
		pauditbutton.setBounds(444, 479, 220, 75);
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
         
         //timestamp to check if its older than 2 weeks, if so reset the score back to 0.
         
         Statement stmt3 = conn.createStatement();
     	String query3 = "SELECT * FROM Audit";
     	 ResultSet rs3 = stmt3.executeQuery(query3);
     	 
     	 
     	 while (rs3.next()) {
     	 
     		String oldtime = rs3.getString("timestamp");  	

     		String currenttime = new SimpleDateFormat("d-MMM-yyyy").format(new java.util.Date()); 
     		
     		DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
     		  LocalDate  d1 = LocalDate.parse(oldtime, df);
     		 LocalDate  d2 = LocalDate.parse(currenttime, df);
  
     		Long datediff = ChronoUnit.DAYS.between(d1,d2); 	
     		
     	 
     	 if(datediff >=14) {
     		 
     		 int score = 0;
     		 
     		 Statement stmt4 = conn.createStatement();
     		PreparedStatement ps2 = conn.prepareStatement("UPDATE Audit SET score=? WHERE timestamp= '"+oldtime+"'");
     		 
          	ps2.setInt(1, score);
        	ps2.executeUpdate();	 
     	 } 
     	 
      }
         
         ListModel model = list.getModel();

    	 for(int i=0; i < model.getSize(); i++){
    		 Object name = model.getElementAt(i); 
    		 
    		 Statement stmt2 = conn.createStatement();
	        	String query2 = "SELECT * FROM Audit WHERE device_name ='"+name+"'";
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
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(206, 185, 789, 271);
        contentPane.add(scrollPane_1);
        table = new JTable();
        scrollPane_1.setViewportView(table);
        
        JLabel labelscore = new JLabel("Info");
		labelscore.setBounds(275, 120, 508, 29);
		contentPane.add(labelscore);
		labelscore.setVisible(false);
		
		JLabel labelinfo = new JLabel("Info 2");
	    labelinfo.setBounds(275, 157, 633, 16);
	    contentPane.add(labelinfo);
	    labelinfo.setVisible(false);
	    
	    JLabel labeldevice = new JLabel("Device Info");
	    labeldevice.setFont(new Font("Serif", Font.BOLD, 15));
	    labeldevice.setBounds(1027, 120, 352, 348);
	    contentPane.add(labeldevice);
	    labeldevice.setVisible(false);
	        
	    
		JButton btnNewButton_1 = new JButton("Select");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table = new JTable();
				scrollPane_1.setViewportView(table);
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);	
		             	          
					 String selecteddevice = list.getSelectedValue().toString();
					 					          			     		       	     
					 if (selecteddevice != null && !selecteddevice.isEmpty()) {
						 					 						      
					        	Statement stmt = conn.createStatement();
					        	String query = "SELECT * FROM Audit WHERE device_name = '"+selecteddevice+"'";
					        	 ResultSet rs = stmt.executeQuery(query);
					        	 
					        	 String score = rs.getString("score");
					        	 String type = rs.getString("type_questions");		
					        	 
					        	 if (score == null) {
					        		 String st = "Device has not been audited yet, please audit now!";
							        	JOptionPane.showMessageDialog(null, st);
					        	 }
					        	 
					        	 else {
					        		 int rating = Integer.parseInt(score);
					        		if(rating <= 2){
					        			labelscore.setText("The severity of this device is Low");
							        	 labelscore.setVisible(true);	
							        	 labelinfo.setText("Here are some attacks that can happen with "+selecteddevice);
							        	 labelinfo.setVisible(true);
					        		} 
					        	
					        		if(rating <= 4 && rating >=3){
					        			labelscore.setText("The severity of this device is Medium");
							        	 labelscore.setVisible(true);	
							        	 labelinfo.setText("Here are some attacks that can happen with "+selecteddevice);
							        	 labelinfo.setVisible(true);
					        		}
					        		
					        		if(rating >= 5){
					        			labelscore.setText("The severity of this device is High");
							        	 labelscore.setVisible(true);
							        	 labelinfo.setText("Here are some attacks that can happen with "+selecteddevice);
							        	 labelinfo.setVisible(true);
							        	 
					        		}
					        	 
					        	 String router = "Router";
						        	String switcht = "Switch";
						        	String ap ="AccessPoint";
						        	String hub ="Hub";
						        	String modem ="Modem";
						        	String server ="Server";
						        	String asa ="ASA";
						        	String bridge ="Bridge";
						        	
						        	
						        	//use the router questions
							       	 if (type.equals(router)) {
							       						
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM Router_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();	  		         	 
					  		         
					  		           DefaultTableModel tm = (DefaultTableModel) table.getModel();
	
					  		         table.setRowHeight(40);
					  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
					       
					  		         String[] columnNames = {"Attack",
					                         "Mitigation"};
							            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);

					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br>Can anyone other than the user "+owner+
							                						" access it?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           }	
							       	 
							         //use the switch questions
							       	 if (type.equals(switcht)) {
								        	
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM Switch_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();
					  		         	 
					  		         	DefaultTableModel tm = (DefaultTableModel) table.getModel();
					  		      	
						  		         table.setRowHeight(40);
						  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
						       
						  		         String[] columnNames = {"Attack",
						                         "Mitigation"};
								            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);	
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);
					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br>Can anyone other than the user "+owner+
							                						" access it?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           }
							       	 
							       	 //use the access point questions
							       	 if (type.equals(ap)) {
								        	
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM AP_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();
					  		         	 
					  		         	DefaultTableModel tm = (DefaultTableModel) table.getModel();
					  		      	
						  		         table.setRowHeight(40);
						  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
						       
						  		         String[] columnNames = {"Attack",
						                         "Mitigation"};
								            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);
					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br>Can anyone other than the user "+owner+
							                						" access it?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           }
							       	 
							       	 //use the bridge questions
							       	 if (type.equals(bridge)) {
								        	
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM Bridge_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();
					  		         	 
					  		         	DefaultTableModel tm = (DefaultTableModel) table.getModel();
					  		      	
						  		         table.setRowHeight(40);
						  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
						       
						  		         String[] columnNames = {"Attack",
						                         "Mitigation"};
								            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);
					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                					           
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br> Could a hub be used instead of a bridge?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           }
							       	 	 
							       	 //use the hub questions
							       	 if (type.equals(hub)) {
								        	
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM Hub_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();
					  		         	 
					  		         	DefaultTableModel tm = (DefaultTableModel) table.getModel();
					  		      	
						  		         table.setRowHeight(40);
						  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
						       
						  		         String[] columnNames = {"Attack",
						                         "Mitigation"};
								            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);	
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);
					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br>Can anyone other than the user "+owner+
							                						" access it?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           } 
							       	     	 
							       	 //use the server questions
							       	 if (type.equals(server)) {
								        	
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM Server_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();
					  		         	 
					  		         	DefaultTableModel tm = (DefaultTableModel) table.getModel();
					  		      	
						  		         table.setRowHeight(40);
						  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
						       
						  		         String[] columnNames = {"Attack",
						                         "Mitigation"};
								            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);	
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);
					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br>Can anyone other than the user "+owner+
							                						" access it?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           }
							       	        	 
							       	 //use the modem questions
							       	 if (type.equals(modem)) {
								        	
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM Modem_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();
					  		         	 
					  		         	DefaultTableModel tm = (DefaultTableModel) table.getModel();
					  		      	
						  		         table.setRowHeight(40);
						  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
						       
						  		         String[] columnNames = {"Attack",
						                         "Mitigation"};
								            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);	
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);
					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br>Can anyone other than the user "+owner+
							                						" access it?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           }
							       	  
							       	 //use the ASA questions
							       	 if (type.equals(asa)) {
								        	
							        	 Statement stmt2 = conn.createStatement();
					  		         	 String query2 = "SELECT * FROM ASA_Exploits";
					  		         	 ResultSet rs2 = stmt.executeQuery(query2);
					  		         	 ResultSetMetaData rsmd2 = rs2.getMetaData();
					  		         	 
					  		         	DefaultTableModel tm = (DefaultTableModel) table.getModel();
					  		      	
						  		         table.setRowHeight(40);
						  		       table.setFont(new java.awt.Font("Tahoma", 0, 10));
						       
						  		         String[] columnNames = {"Attack",
						                         "Mitigation"};
								            tm.setColumnIdentifiers(columnNames);
					  		         	 
					  		         	 while (rs2.next()) {
					  		        	   					 		        	  
					 		                String name = rs2.getString("exploit_name");
					 		                String role = rs2.getString("fix");
					 		                
					 		                String[] data = {name, role} ;	         
					 		            
					 		                tm.addRow(data);
					 		                
					 		               table.getColumnModel().getColumn(0).setPreferredWidth(240);
					  		         	 }
					  		         	 
					  		         	Statement stmt3 = conn.createStatement();
								       	 String query3 = "SELECT * FROM Devices WHERE device_name = '"+selecteddevice+"'";
					  		         	 ResultSet rs3 = stmt.executeQuery(query3);	 
								       	 
					  		         	while (rs3.next()) {
	   					 		        	  
					  		         	    String name = rs3.getString("device_name");
							                String vendor = rs3.getString("vendor");
							                String typedev = rs3.getString("type");
							                String os_version = rs3.getString("os_version");
							                String owner = rs3.getString("owner");
							                String function = rs3.getString("function");
							                String model = rs3.getString("model");
							                String location = rs3.getString("location");
					 		                
							                labeldevice.setText("<html>Should this device "+name+"<br>belong to the user "+owner+"?<br><br>"
							                		+ "This version "+os_version+"may not be the latest version<br> Please update it if not<br><br>"+
							                		"The vendor "+vendor+" might be able to assist with any exploits<br>to their hardware or software, contact them to enquire<br><br>"
							                				+"The hardware version "+model+"for this "+typedev+"<br>may be too old, consider updating the device<br><br>"
							                						+ "Should the device be in the location "+location+"?<br>Can anyone other than the user "+owner+
							                						" access it?</html>");
							  		          labeldevice.setVisible(true);
					  		         	 }  
					 		           }						       	 
							       	 //end of displaying device type data 					       	        	 
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
		textFieldsearch.setBounds(6, 141, 130, 26);
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
		           
		            DefaultListModel listModel2;
			         listModel2 = new DefaultListModel();
			         list.setModel(listModel2);
			         
		       	 Statement stmt = conn.createStatement();
		       	 String query2 = "SELECT * FROM Devices WHERE device_name LIKE '%"+query+"%'";
		       	 ResultSet rs = stmt.executeQuery(query2);
		         
		       int i = -1;
		       
		         while (rs.next()) {
		        	 
		        	 i++;
		                String name = rs.getString("device_name");
		                listModel2.addElement(name);  
		        
				    		 
				    		 Statement stmt3 = conn.createStatement();
					        	String query3 = "SELECT * FROM Audit WHERE device_name ='"+name+"'";
					        	 ResultSet rs3 = stmt3.executeQuery(query3);
					        	 
					        	 String score = rs3.getString("score");
					        	 
					        	 if (score ==null) {
					        		 listModel2.setElementAt("(HIGH RISK) "+name, i);
					        	 }
				    		 
					        	 if (score !=null) {
					        		 int risk = Integer.parseInt(score);
					        		 
					        		 if (risk == 0) {
					        			 listModel2.setElementAt("(HIGH RISK) "+name, i);
						        	 }				        		        		 
					        	  		 
				    	 }
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
		btnsearch.setBounds(134, 141, 93, 29);
		contentPane.add(btnsearch);
		
		 //main menu bar
	    JMenuBar menuBarmain = new JMenuBar();
	    menuBarmain.setBounds(25, 16, 102, 22);
	    contentPane.add(menuBarmain);
		
	    JMenuItem view, add, com, rep, menu, aud;
	    
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
	    
	    menu.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Home_Page.main(null);
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
	    
	    aud.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
				Audit.main(null);
	    	}
		});
	    
	    JLabel lblNewLabel_1 = new JLabel("Select Device to view Audit");
	    lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 15));
	    lblNewLabel_1.setBounds(16, 183, 178, 23);
	    contentPane.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("Search For Device");
	    lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 16));
	    lblNewLabel_2.setBounds(39, 106, 188, 26);
	    contentPane.add(lblNewLabel_2);
	}
}
