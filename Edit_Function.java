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
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Edit_Function extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldname;
	private JTextField textFielddesc;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit_Function frame = new Edit_Function();
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

	public Edit_Function() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Function");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(226, 16, 168, 62);
		contentPane.add(lblNewLabel);
		
		JLabel labelname = new JLabel("Function Name");
		labelname.setFont(new Font("Serif", Font.BOLD, 16));
		labelname.setBounds(86, 267, 140, 20);
		contentPane.add(labelname);
		
		JLabel labeldesc = new JLabel("Description");
		labeldesc.setFont(new Font("Serif", Font.BOLD, 16));
		labeldesc.setBounds(128, 317, 74, 26);
		contentPane.add(labeldesc);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(211, 267, 183, 20);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		textFielddesc = new JTextField();
		textFielddesc.setBounds(214, 320, 183, 20);
		contentPane.add(textFielddesc);
		textFielddesc.setColumns(10);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();	
				String tblname = tblmodel.getValueAt(table.getSelectedRow(), 1).toString();	
				String tbldesc = tblmodel.getValueAt(table.getSelectedRow(), 2).toString();	
				
				textFieldname.setText(tblname);
				textFielddesc.setText(tbldesc);
				
			}
		});
		table.setBounds(102, 72, 403, 176);
		contentPane.add(table);
	  	
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
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
        
           int cols = rsmd.getColumnCount();
           String[] colName = new String[cols];
           for (int i=0; i<cols;i++)
           	colName[i] = rsmd.getColumnName(i+1);
           tm.setColumnIdentifiers(colName);
           
          while (rs.next()) {
       	   
       	    String id = rs.getString("id");
               String name = rs.getString("function_name");
               String desc = rs.getString("description");	   
                
               String[] data = {id, name, desc} ;	         
           
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
		
        //update button add to table
        JButton add_location = new JButton("Update");
		add_location.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		  int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
			        if (result == JOptionPane.YES_OPTION) {
			            
			        	Connection conn = null;	
						try {
					    // db parameters
							 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
					    // create a connection to the database
					    conn = DriverManager.getConnection(url);
					            
						DefaultTableModel tblmodel = (DefaultTableModel) table.getModel();		
							
						if(table.getSelectedRowCount() == 1) {
							
							String name = textFieldname.getText();
							String desc = textFielddesc.getText();
							
							Statement stmt = conn.createStatement();
							PreparedStatement ps = conn.prepareStatement("SELECT * FROM Function");
							
							String idrow = tblmodel.getValueAt(table.getSelectedRow(), 0).toString();
							
							Statement stmt2 = conn.createStatement();
							PreparedStatement ps2 = conn.prepareStatement("UPDATE Function SET function_name=?, description=? WHERE id= '"+idrow+"'");
				        	ps2.setString(1, name);
				        	ps2.setString(2, desc);
				      
				        	ps2.executeUpdate();
				        	
							tblmodel.setValueAt(name, table.getSelectedRow(), 1);
							tblmodel.setValueAt(desc, table.getSelectedRow(), 2);
							
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
						
			}
		});
		add_location.setFont(new Font("Serif", Font.BOLD, 18));
		add_location.setBounds(232, 363, 140, 41);
		contentPane.add(add_location);	
        
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 18));
		btnNewButton.setBounds(0, 0, 117, 48);
		contentPane.add(btnNewButton);
    }
} 
