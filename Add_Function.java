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

public class Add_Function extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldname;
	private JTextField textFielddesc;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Function frame = new Add_Function();
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
	   
	public Add_Function() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		     	
		JLabel lblNewLabel = new JLabel("Add A Function");
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
		btnNewButton.setBounds(0, 0, 96, 62);
		contentPane.add(btnNewButton);
		
		JButton add_function = new JButton("Add Function");
		add_function.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);	                
	       	 
		       	String name = textFieldname.getText();
		        String desc = textFielddesc.getText();
		          
		        Statement stmt = conn.createStatement();
		        String query = "SELECT MAX(id) FROM Function";
		       	 ResultSet rs = stmt.executeQuery(query);
		     		  
		        int highestID = 0;

		        if (rs.next()) {
		          highestID = rs.getInt(1);
		        }

		        int newID = highestID + 1;
		       	 
		        if (name != null && !name.isEmpty() && desc != null && !desc.isEmpty()) {
		      
		        	PreparedStatement stmt2 = conn.prepareStatement("insert into Function (id,function_name,description) values(?,?,?) ");
		        	stmt2.setInt(1, newID);
		        	stmt2.setString(2, name);
		        	stmt2.setString(3, desc);
		        	
		        	int rs2 = stmt2.executeUpdate();
		        	
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
		add_function.setFont(new Font("Serif", Font.BOLD, 18));
		add_function.setBounds(124, 235, 121, 41);
		contentPane.add(add_function);
		
		JLabel labelname = new JLabel("Function Name");
		labelname.setFont(new Font("Serif", Font.BOLD, 16));
		labelname.setBounds(45, 114, 140, 20);
		contentPane.add(labelname);
		
		JLabel labeldesc = new JLabel("Description");
		labeldesc.setFont(new Font("Serif", Font.BOLD, 16));
		labeldesc.setBounds(83, 162, 102, 26);
		contentPane.add(labeldesc);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(195, 116, 183, 20);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		textFielddesc = new JTextField();
		textFielddesc.setBounds(195, 167, 183, 32);
		contentPane.add(textFielddesc);
		textFielddesc.setColumns(10);	
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldname.setText(null);
				textFielddesc.setText(null);
				
			}
		});
		reset.setFont(new Font("Serif", Font.BOLD, 20));
		reset.setBounds(257, 235, 121, 41);
		contentPane.add(reset);
         }
	} 
