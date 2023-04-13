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
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AddDevice extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDevice frame = new AddDevice();
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

	public AddDevice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add A Device");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(453, 44, 195, 62);
		contentPane.add(lblNewLabel);
		
		JLabel labelname = new JLabel("Device Name");
		labelname.setFont(new Font("Serif", Font.BOLD, 16));
		labelname.setBounds(295, 112, 102, 20);
		contentPane.add(labelname);
		
		JLabel labeltype = new JLabel("Device Type");
		labeltype.setFont(new Font("Serif", Font.BOLD, 16));
		labeltype.setBounds(295, 162, 102, 26);
		contentPane.add(labeltype);
		
		JLabel labelvendor = new JLabel("Vendor");
		labelvendor.setFont(new Font("Serif", Font.BOLD, 16));
		labelvendor.setBounds(295, 211, 105, 26);
		contentPane.add(labelvendor);
		
		JLabel labelmodel = new JLabel("Hardware Model");
		labelmodel.setFont(new Font("Serif", Font.BOLD, 14));
		labelmodel.setBounds(295, 262, 111, 29);
		contentPane.add(labelmodel);
		
		JLabel labelos = new JLabel("OS Version");
		labelos.setFont(new Font("Serif", Font.BOLD, 16));
		labelos.setBounds(295, 314, 102, 20);
		contentPane.add(labelos);
		
		JLabel labelowner = new JLabel("Owner");
		labelowner.setFont(new Font("Serif", Font.BOLD, 16));
		labelowner.setBounds(295, 357, 102, 26);
		contentPane.add(labelowner);
		
		JLabel labelfunction = new JLabel("Function");
		labelfunction.setFont(new Font("Serif", Font.BOLD, 16));
		labelfunction.setBounds(295, 407, 102, 28);
		contentPane.add(labelfunction);
		
		JLabel labellocation = new JLabel("Location");
		labellocation.setFont(new Font("Serif", Font.BOLD, 16));
		labellocation.setBounds(295, 452, 102, 27);
		contentPane.add(labellocation);

		 textField = new JTextField();
	 	 textField.setBounds(418, 114, 273, 20);
	 	 contentPane.add(textField);
	 	 textField.setColumns(10); 
	 	 	       
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.setBounds(418, 162, 273, 38);
         contentPane.add(scrollPane);
         JList list = new JList();
         scrollPane.setViewportView(list); 
         
         JScrollPane scrollPane_1 = new JScrollPane();
         scrollPane_1.setBounds(418, 211, 273, 26);
         contentPane.add(scrollPane_1);
         JList list_1 = new JList();
         scrollPane_1.setViewportView(list_1);
         
         JScrollPane scrollPane_2 = new JScrollPane();
         scrollPane_2.setBounds(417, 262, 274, 28);
         contentPane.add(scrollPane_2);
         JList list_2 = new JList();
         scrollPane_2.setViewportView(list_2);
         
         JScrollPane scrollPane_3 = new JScrollPane();
         scrollPane_3.setBounds(418, 314, 273, 26);
         contentPane.add(scrollPane_3);
         JList list_3 = new JList();
         scrollPane_3.setViewportView(list_3);
         
         JScrollPane scrollPane_4 = new JScrollPane();
         scrollPane_4.setBounds(418, 357, 272, 26);
         contentPane.add(scrollPane_4);
         JList list_4 = new JList();
         scrollPane_4.setViewportView(list_4);
         
         JScrollPane scrollPane_5 = new JScrollPane();
         scrollPane_5.setBounds(418, 407, 273, 26);
         contentPane.add(scrollPane_5);       
         JList list_5 = new JList();
         scrollPane_5.setViewportView(list_5);
         
         JScrollPane scrollPane_6 = new JScrollPane();
         scrollPane_6.setBounds(418, 452, 273, 26);
         contentPane.add(scrollPane_6);      
         JList list_6 = new JList();
         scrollPane_6.setViewportView(list_6);
		
		//Each Of The Lists
		Connection conn = null;		
        try {
            // db parameters
        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);        	
                
         //type list 
         DefaultListModel listModel;
         listModel = new DefaultListModel();  
         list.setModel(listModel);    
           
         //vendor list
         DefaultListModel listModel2;
         listModel2 = new DefaultListModel();  
         list_1.setModel(listModel2);
         
         //hardware model list
         DefaultListModel listModel3;
         listModel3 = new DefaultListModel();   
         list_2.setModel(listModel3);
         
         //OS version list
         DefaultListModel listModel4;
         listModel4 = new DefaultListModel();  
         list_3.setModel(listModel4);
         
         //owner list
         DefaultListModel listModel5;
         listModel5 = new DefaultListModel();  
         list_4.setModel(listModel5);
         
         //function list
         DefaultListModel listModel6;
         listModel6 = new DefaultListModel();
         list_5.setModel(listModel6);
         
         //location list
         DefaultListModel listModel7;
         listModel7 = new DefaultListModel();
         list_6.setModel(listModel7);
         
         Statement stmt = conn.createStatement();
       	 String query = "SELECT * FROM Device_Type";
       	 ResultSet rs = stmt.executeQuery(query);
     
           while (rs.next()) {
        	   
                String type = rs.getString("device_type");                        
                listModel.addElement(type);                     
           }  
           
           Statement stmt2 = conn.createStatement();
           String query2 = "SELECT * FROM Vendor";
           ResultSet rs2 = stmt.executeQuery(query2);
           
           while (rs2.next()) {
        	   
               String vendor = rs.getString("vendor_name");
               listModel2.addElement(vendor);
          } 
           
           Statement stmt3 = conn.createStatement();
           String query3 = "SELECT * FROM Model";
           ResultSet rs3 = stmt.executeQuery(query3);
           
           while (rs3.next()) {
        	   
                   String model = rs.getString("model_name");                           
                   listModel3.addElement(model);       
          } 
           
           Statement stmt4 = conn.createStatement();
           String query4 = "SELECT * FROM Os_Version";
           ResultSet rs4 = stmt.executeQuery(query4);
           
           while (rs4.next()) {
        	   
                   String os_version = rs.getString("version_name");                           
                   listModel4.addElement(os_version);     
          } 
           
           Statement stmt5 = conn.createStatement();
           String query5 = "SELECT * FROM Users";
           ResultSet rs5 = stmt.executeQuery(query5);
           
           while (rs5.next()) {
        	   
        	   	   String owner = rs.getString("user_name");                          
        	   	   listModel5.addElement(owner);      
          } 
           
           Statement stmt6 = conn.createStatement();
           String query6 = "SELECT * FROM Function";
           ResultSet rs6 = stmt.executeQuery(query6);
           
           while (rs6.next()) {
        	   
        	   String function = rs.getString("function_name");                           
        	   listModel6.addElement(function);       
          }
           
           Statement stmt7 = conn.createStatement();
           String query7 = "SELECT * FROM Location";
           ResultSet rs7 = stmt.executeQuery(query7);
           
           while (rs7.next()) {
        	   
        	   	   String location = rs.getString("location_name");                         
        	   	   listModel7.addElement(location);      	   	   
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
        
        //Add device button and code that adds the entries into the database    
        JButton add_device = new JButton("Add Device");
		add_device.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);		            	      
		            
		            String name = textField.getText();
					 String selectedtype = list.getSelectedValue().toString();			
					 String selectedven = list_1.getSelectedValue().toString();
					 String selectedmod = list_2.getSelectedValue().toString();
					 String selectedos = list_3.getSelectedValue().toString();
					 String selectedown = list_4.getSelectedValue().toString();
					 String selectedfun = list_5.getSelectedValue().toString();
					 String selectedloc = list_6.getSelectedValue().toString();
					          
			        Statement stmt = conn.createStatement();
			        String query = "SELECT MAX(id) FROM Devices";
			       	 ResultSet rs = stmt.executeQuery(query);
			     		  
			        int highestID = 0;

			        if (rs.next()) {
			          highestID = rs.getInt(1);
			        }

			        int newID = highestID + 1;			       	     
			       	 
			        if (name != null && !name.isEmpty() &&
				        	selectedtype != null && !selectedtype.isEmpty() &&
				        	selectedven != null && !selectedven.isEmpty() &&
				        	selectedmod != null && !selectedmod.isEmpty() &&
				            selectedos != null && !selectedos.isEmpty() &&
				        	selectedown != null && !selectedown.isEmpty() &&
				        	selectedfun != null && !selectedfun.isEmpty() &&
				        	selectedloc != null && !selectedloc.isEmpty()) {
				      
				        	PreparedStatement stmt4 = conn.prepareStatement("insert into Devices (id,device_name,vendor,type,os_version,owner,function,model,location) values(?,?,?,?,?,?,?,?,?) ");			   
	       		       	
				        	stmt4.setInt(1, newID);
				         	stmt4.setString(2, name);
				        	stmt4.setString(3, selectedven);
				        	stmt4.setString(4, selectedtype);
				        	stmt4.setString(5, selectedos);
				        	stmt4.setString(6, selectedown);
				        	stmt4.setString(7, selectedfun);
				        	stmt4.setString(8, selectedmod);
				        	stmt4.setString(9, selectedloc);
				        	
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
		add_device.setBounds(416, 504, 142, 41);
		contentPane.add(add_device);
		
		JMenuBar menuBar = new JMenuBar();
	    menuBar.setBounds(148, 11, 600, 22);
	    contentPane.add(menuBar);
		
		    JMenuItem eddev,  deldev, addven, edven, delven, addmod, edmod, delmod, addos, edos, delos, addown, edown, delown,
		    addfun, edfun, delfun, addloc, edloc, delloc;
		    
		    //type menu
		    JMenu filedev = new JMenu("Devices");

		    eddev = new JMenuItem("Edit Device");
		    deldev = new JMenuItem("Delete Device");

		    filedev.add(eddev);
		    filedev.add(deldev);
		    
		    //vendor menu
		    JMenu fileven = new JMenu("Device Vendor");
		    
		    addven = new JMenuItem("Add Vendor");
		    edven = new JMenuItem("Edit Vendor");
		    delven = new JMenuItem("Delete Vendor");
		    
		    fileven.add(addven);
		    fileven.add(edven);
		    fileven.add(delven);
		    
		    //model menu

		    JMenu filemod = new JMenu("Device Model");

		    addmod = new JMenuItem("Add Device Model");
		    edmod = new JMenuItem("Edit Device Model");
		    delmod = new JMenuItem("Delete Device Model");

		    filemod.add(addmod);
		    filemod.add(edmod);
		    filemod.add(delmod);
		    
		    //os menu
		    
		    JMenu fileos = new JMenu("OS Version");

		    addos = new JMenuItem("Add OS Version");
		    edos = new JMenuItem("Edit OS Version");
		    delos = new JMenuItem("Delete OS Version");

		    fileos.add(addos);
		    fileos.add(edos);
		    fileos.add(delos);
		    
		    //user menu
		    
		    JMenu fileown = new JMenu("User");

		    addown = new JMenuItem("Add User");
		    edown = new JMenuItem("Edit User");
		    delown = new JMenuItem("Delete User");

		    fileown.add(addown);
		    fileown.add(edown);
		    fileown.add(delown);
		    
		    //function menu
		    
		    JMenu filefun = new JMenu("Function");

		    addfun = new JMenuItem("Add Function");
		    edfun = new JMenuItem("Edit Function");
		    delfun = new JMenuItem("Delete Function");

		    filefun.add(addfun);
		    filefun.add(edfun);
		    filefun.add(delfun);
		    
		    //location menu
		    
		    JMenu fileloc = new JMenu("Location");

		    addloc = new JMenuItem("Add Location");
		    edloc = new JMenuItem("Edit Location");
		    delloc = new JMenuItem("Delete Location");

		    fileloc.add(addloc);
		    fileloc.add(edloc);
		    fileloc.add(delloc);
		    
		    	   
		   //add menu items to bar 
		    menuBar.add(filedev);		
		    menuBar.add(fileven);
		    menuBar.add(filemod);
		    menuBar.add(fileos);
		    menuBar.add(fileown);
		    menuBar.add(filefun);
		    menuBar.add(fileloc);
		    
		    //device buttons
		    eddev.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Edit_Device.main(null); 
		    	}
			}); 
		    deldev.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        Del_Device.main(null); 
		    	}
			});
		    
		    //vendor buttons
		    addven.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Add_Vendor.main(null); 
		    	}
			});		    
		    edven.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Edit_Vendor.main(null); 
		    	}
			});		    
		    delven.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Del_Vendor.main(null); 
		    	}
			});
		    
		    //model buttons
		    addmod.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Add_Model.main(null); 
		    	}
			});		    
		    edmod.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Edit_Model.main(null); 
		    	}
			});		    
		    delmod.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Del_Model.main(null); 
		    	}
			});
		     
		    //os buttons
		    addos.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Add_Os.main(null); 
		    	}
			});		    
		    edos.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Edit_Os.main(null); 
		    	}
			});	     
		    delos.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Del_Os.main(null); 
		    	}
			});
		    
		    //owner buttons
		    addown.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Add_Owner.main(null); 
		    	}
			});	    
		    edown.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Edit_Owner.main(null); 
		    	}
			});    
		    delown.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        Del_Owner.main(null); 
		    	}
			});	
		    
		    //funtion buttons
		    addfun.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Add_Function.main(null); 
		    	}
			});	    
		    edfun.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Edit_Function.main(null); 
		    	}
			});	    
		    delfun.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Del_Function.main(null); 
		    	}
			});
		   	   
		    //location buttons
		    addloc.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Add_Location.main(null); 
		    	}
			});	    
		    edloc.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Edit_Location.main(null); 
		    	}
			});	    
		   delloc.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		       	Del_Location.main(null); 
		    	}
			});
		   		   
		   //main menu bar
		    JMenuBar menuBarmain = new JMenuBar();
		    menuBarmain.setBounds(25, 16, 102, 22);
		    contentPane.add(menuBarmain);
			
		    JMenuItem view, aud, com, rep, menu, add;
		    
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
		    
		    add.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	dispose();
					AddDevice.main(null);
		    	}
			});
		    
		    aud.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	dispose();
					Audit.main(null);
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
		    
		    JButton reset = new JButton("Reset");
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText(null);
					list.clearSelection();
					list_1.clearSelection();
					list_2.clearSelection();
					list_3.clearSelection();
					list_4.clearSelection();
					list_5.clearSelection();
					list_6.clearSelection();
					
				}
			});
			reset.setFont(new Font("Serif", Font.BOLD, 20));
			reset.setBounds(570, 504, 121, 41);
			contentPane.add(reset);
		    
	}
}