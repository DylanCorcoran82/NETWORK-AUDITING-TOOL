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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JList;

public class Edit_Device extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldname;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit_Device frame = new Edit_Device();
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

	public Edit_Device() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(147, 212, 162, 34);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 66, 803, 134);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(147, 264, 142, 26);
		contentPane.add(scrollPane_1);
		JList listtype = new JList();
		scrollPane_1.setViewportView(listtype);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(147, 316, 142, 26);
		contentPane.add(scrollPane_2);		
		JList listvendor = new JList();
		scrollPane_2.setViewportView(listvendor);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(167, 366, 142, 26);
		contentPane.add(scrollPane_3);		
		JList listmodel = new JList();
		scrollPane_3.setViewportView(listmodel);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(510, 216, 189, 26);
		contentPane.add(scrollPane_4);	
		JList listos = new JList();
		scrollPane_4.setViewportView(listos);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(510, 266, 189, 26);
		contentPane.add(scrollPane_5);	
		JList listowner = new JList();
		scrollPane_5.setViewportView(listowner);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(510, 318, 189, 26);
		contentPane.add(scrollPane_6);	
		JList listfun = new JList();
		scrollPane_6.setViewportView(listfun);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(510, 368, 189, 26);
		contentPane.add(scrollPane_7);	
		JList listloc = new JList();
		scrollPane_7.setViewportView(listloc);
		
		table = new JTable();
		scrollPane.setViewportView(table);
			
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();
				String tblname = tblmodel.getValueAt(table.getSelectedRow(), 1).toString();		
				textFieldname.setText(tblname);
				
				
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
		         listtype.setModel(listModel);
		         
		         DefaultListModel listModel2;
		         listModel2 = new DefaultListModel();
		         listvendor.setModel(listModel2); 
		         
		         DefaultListModel listModel3;
		         listModel3 = new DefaultListModel();
		         listmodel.setModel(listModel3); 
		         
		         DefaultListModel listModel4;
		         listModel4 = new DefaultListModel();
		         listos.setModel(listModel4); 
		         
		         DefaultListModel listModel5;
		         listModel5 = new DefaultListModel();
		         listowner.setModel(listModel5); 
		         
		         DefaultListModel listModel6;
		         listModel6 = new DefaultListModel();
		         listfun.setModel(listModel6); 
		         
		         DefaultListModel listModel7;
		         listModel7 = new DefaultListModel();
		         listloc.setModel(listModel7); 
		         
		         //type display
		         while (rs.next()) {
		      	   
		             String type = rs.getString("device_type"); 		      
		             listModel.addElement(type);   
		        }
		         
		         //vendor display
		         Statement stmt5 = conn.createStatement();
		       	 String query5 = "SELECT * FROM Vendor";
		       	 ResultSet rs5 = stmt.executeQuery(query5);   
		       	 
		       	while (rs5.next()) {
			      	   
		             String vendor =rs5.getString("vendor_name");
		             listModel2.addElement(vendor);   
		        }
		       	
		       	//model display
		       	Statement stmt6 = conn.createStatement();
		       	 String query6 = "SELECT * FROM Model";
		       	 ResultSet rs6 = stmt.executeQuery(query6);   
		       	 
		       	while (rs6.next()) {
			      	   
		             String model =rs5.getString("model_name");
		             listModel3.addElement(model);   
		        }
		       	
		      //os display
		       	Statement stmt7 = conn.createStatement();
		       	 String query7 = "SELECT * FROM Os_Version";
		       	 ResultSet rs7 = stmt.executeQuery(query7);   
		       	 
		       	while (rs7.next()) {
			      	   
		             String os =rs7.getString("version_name");
		             listModel4.addElement(os);   
		        }
		       	
		      //owner display
		       	Statement stmt8 = conn.createStatement();
		       	 String query8 = "SELECT * FROM Users";
		       	 ResultSet rs8 = stmt.executeQuery(query8);   
		       	 
		       	while (rs8.next()) {
			      	   
		             String owner =rs8.getString("user_name");
		             listModel5.addElement(owner);   
		        }
		       	
		      //function display
		       	Statement stmt9 = conn.createStatement();
		       	 String query9 = "SELECT * FROM Function";
		       	 ResultSet rs9 = stmt.executeQuery(query9);   
		       	 
		       	while (rs9.next()) {
			      	   
		             String function =rs9.getString("function_name");
		             listModel6.addElement(function);   
		        }
		       	
		      //location display
		       	Statement stmt10 = conn.createStatement();
		       	 String query10 = "SELECT * FROM Location";
		       	 ResultSet rs10 = stmt.executeQuery(query10);   
		       	 
		       	while (rs10.next()) {
			      	   
		             String model =rs10.getString("location_name");
		             listModel7.addElement(model);   
		        }
		       	
		         //get id of line
		         String idrow = tblmodel.getValueAt(table.getSelectedRow(), 0).toString();
		         //device type selection
		         Statement stmt2 = conn.createStatement();
		       	 String query2 = ("SELECT * FROM Devices WHERE id='"+idrow+"'");
		       	 ResultSet rs2 = stmt.executeQuery(query2);
		   
		       	 String type = rs2.getString("type");
		       	 String vendor =rs2.getString("vendor");
		       	 String model =rs2.getString("model");
		       	 String os =rs2.getString("os_version");
		       	 String owner =rs2.getString("owner");
		       	 String function =rs2.getString("function");
		       	 String location =rs2.getString("location");
		    
		         Statement stmt3 = conn.createStatement();
		       	 String query3 = ("SELECT * FROM Device_type WHERE device_type='"+type+"'");
		       	 ResultSet rs3 = stmt3.executeQuery(query3); 
		         
		       	String id = rs3.getString("id");
		       	int num = Integer.parseInt(id)-1;
		        listtype.setSelectedIndex(num);
		        
		        //device vendor selection
		    
		         Statement stmt4 = conn.createStatement();
		       	 String query4 = ("SELECT * FROM Vendor WHERE vendor_name='"+vendor+"'");
		       	 ResultSet rs4 = stmt4.executeQuery(query4); 
		         
		       	String id2 = rs4.getString("id");
		       	int num2 = Integer.parseInt(id2)-1;
		        listvendor.setSelectedIndex(num2);
		        
		        //device model selection
		        
		        Statement stmt11 = conn.createStatement();
		       	 String query11 = ("SELECT * FROM Model WHERE model_name='"+model+"'");
		       	 ResultSet rs11 = stmt11.executeQuery(query11); 
		         
		       	String id3 = rs11.getString("id");
		       	int num3 = Integer.parseInt(id3)-1;
		        listmodel.setSelectedIndex(num3);
		        
		        //os version selection
		        
		        Statement stmt12 = conn.createStatement();
		       	 String query12 = ("SELECT * FROM Os_Version WHERE version_name='"+os+"'");
		       	 ResultSet rs12 = stmt12.executeQuery(query12); 
		         
		       	String id4 = rs12.getString("id");
		       	int num4 = Integer.parseInt(id4)-1;
		        listos.setSelectedIndex(num4);
		        
		        //owner selection
		        
		        Statement stmt13 = conn.createStatement();
		       	 String query13 = ("SELECT * FROM Users WHERE user_name='"+owner+"'");
		       	 ResultSet rs13 = stmt13.executeQuery(query13); 
		         
		       	String id5 = rs13.getString("id");
		       	int num5 = Integer.parseInt(id5)-1;
		        listowner.setSelectedIndex(num5);
		        
		        //function selection
		        
		        Statement stmt14 = conn.createStatement();
		       	 String query14 = ("SELECT * FROM Function WHERE function_name='"+function+"'");
		       	 ResultSet rs14 = stmt14.executeQuery(query14); 
		         
		       	String id6 = rs14.getString("id");
		       	int num6 = Integer.parseInt(id6)-1;
		        listfun.setSelectedIndex(num6);
		        
		        //location selection
		        
		        Statement stmt15 = conn.createStatement();
		       	 String query15 = ("SELECT * FROM Location WHERE location_name='"+location+"'");
		       	 ResultSet rs15 = stmt15.executeQuery(query15); 
		         
		       	String id7 = rs15.getString("id");
		       	int num7 = Integer.parseInt(id7)-1;
		        listloc.setSelectedIndex(num7);
		        
		        
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
		
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
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
              String exploits = rs.getString("exploits");
              
              String[] data = {id, name, vendor, type , os_version , owner , function , model , location, exploits} ;
          
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
           
        //update button add in new data
		
		JButton updatebutton = new JButton("Update");
		updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Connection conn = null;	
			try {
		    // db parameters
				 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		    // create a connection to the database
		    conn = DriverManager.getConnection(url);
		            
			DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();		
				
			if(table.getSelectedRowCount() == 1) {
				
				String name = textFieldname.getText();
				String selectedtype = listtype.getSelectedValue().toString();
				String selectedvendor = listvendor.getSelectedValue().toString();
				String selectedos = listos.getSelectedValue().toString();
				String selectedown = listowner.getSelectedValue().toString();
				String selectedfun = listfun.getSelectedValue().toString();
				String selectedmod = listmodel.getSelectedValue().toString();
				String selectedloc = listloc.getSelectedValue().toString();
				
				Statement stmt = conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM Devices");
				
				String idrow = tblmodel.getValueAt(table.getSelectedRow(), 0).toString();
				
				Statement stmt2 = conn.createStatement();
				PreparedStatement ps2 = conn.prepareStatement("UPDATE Devices SET device_name=?, vendor=?, type=?, os_version=?, owner=?, function=?, model=?, location=? WHERE id= '"+idrow+"'");
	        	ps2.setString(1, name);
	        	ps2.setString(2, selectedvendor);
	        	ps2.setString(3, selectedtype);
	        	ps2.setString(4, selectedos);
	        	ps2.setString(5, selectedown);
	        	ps2.setString(6, selectedfun);
	        	ps2.setString(7, selectedmod);
	        	ps2.setString(8, selectedloc);
	        	ps2.executeUpdate();
	        	
				tblmodel.setValueAt(name, table.getSelectedRow(), 1);
				tblmodel.setValueAt(selectedvendor, table.getSelectedRow(), 2);
				tblmodel.setValueAt(selectedtype, table.getSelectedRow(), 3);
				tblmodel.setValueAt(selectedos, table.getSelectedRow(), 4);
				tblmodel.setValueAt(selectedown, table.getSelectedRow(), 5);
				tblmodel.setValueAt(selectedfun, table.getSelectedRow(), 6);
				tblmodel.setValueAt(selectedmod, table.getSelectedRow(), 7);
				tblmodel.setValueAt(selectedloc, table.getSelectedRow(), 8);
				
				String st = "Success";
	        	JOptionPane.showMessageDialog(null, st);
			}
        
        else 
        {
        	String st = "Table Line Not Selected, Please Try gain!";
        	JOptionPane.showMessageDialog(null, st);
        			
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
		updatebutton.setFont(new Font("Serif", Font.BOLD, 20));
		updatebutton.setBounds(326, 408, 134, 43);
		contentPane.add(updatebutton);
		
		JLabel lblNewLabel = new JLabel("Edit Device");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(355, 18, 166, 42);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("	Device Name");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_1.setBounds(21, 214, 114, 26);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Device Type");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_2.setBounds(21, 263, 96, 26);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 18));
		btnNewButton.setBounds(0, 0, 117, 48);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Vendor");
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_3.setBounds(21, 316, 96, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Hardware Model");
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_4.setBounds(21, 366, 134, 26);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("OS Version");
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_5.setBounds(365, 216, 103, 26);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Owner");
		lblNewLabel_6.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_6.setBounds(355, 264, 85, 26);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Function");
		lblNewLabel_7.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_7.setBounds(355, 316, 96, 26);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Location");
		lblNewLabel_8.setFont(new Font("Serif", Font.BOLD, 18));
		lblNewLabel_8.setBounds(355, 366, 96, 26);
		contentPane.add(lblNewLabel_8);
		
	}
}
