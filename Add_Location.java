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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_Location extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldname;
	private JTextField textFieldbuilding;
	private JTextField textFieldroom;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Location frame = new Add_Location();
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

	public Add_Location() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add A Location");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
		lblNewLabel.setBounds(185, 15, 168, 62);
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
		
		JButton add_location = new JButton("Add Location");
		add_location.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Connection conn = null;
		        try {
		            // db parameters
		        	 String url = "jdbc:sqlite:C:/NETWORK-AUDITING-TOOL/database.db";
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);	                
	       	 
		       	String name = textFieldname.getText();
		        String building = textFieldbuilding.getText();
		        String room = textFieldroom.getText();
		          
		        Statement stmt = conn.createStatement();
		        String query = "SELECT MAX(id) FROM Location";
		       	 ResultSet rs = stmt.executeQuery(query);
		     		  
		        int highestID = 0;

		        if (rs.next()) {
		          highestID = rs.getInt(1);
		        }

		        int newID = highestID + 1;
		       	 
		        if (name != null && !name.isEmpty() && building != null && !building.isEmpty() && room != null && !room.isEmpty()) {
		      
		        	PreparedStatement stmt2 = conn.prepareStatement("insert into location (id,location_name,building,room) values(?,?,?,?) ");
		        	stmt2.setInt(1, newID);
		        	stmt2.setString(2, name);
		        	stmt2.setString(3, building);
		        	stmt2.setString(4, room);
		        	
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
		add_location.setFont(new Font("Serif", Font.BOLD, 18));
		add_location.setBounds(105, 235, 140, 41);
		contentPane.add(add_location);
		
		JLabel labelname = new JLabel("Location Name");
		labelname.setFont(new Font("Serif", Font.BOLD, 16));
		labelname.setBounds(45, 104, 140, 20);
		contentPane.add(labelname);
		
		JLabel labelbuilding = new JLabel("Building");
		labelbuilding.setFont(new Font("Serif", Font.BOLD, 16));
		labelbuilding.setBounds(45, 135, 102, 26);
		contentPane.add(labelbuilding);
		
		JLabel labelroom = new JLabel("Room");
		labelroom.setFont(new Font("Serif", Font.BOLD, 16));
		labelroom.setBounds(45, 172, 102, 26);
		contentPane.add(labelroom);
		
		textFieldname = new JTextField();
		textFieldname.setBounds(195, 106, 183, 20);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		textFieldbuilding = new JTextField();
		textFieldbuilding.setBounds(195, 134, 183, 20);
		contentPane.add(textFieldbuilding);
		textFieldbuilding.setColumns(10);
		
		textFieldroom = new JTextField();
		textFieldroom.setBounds(195, 177, 183, 20);
		contentPane.add(textFieldroom);
		textFieldroom.setColumns(10);
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldname.setText(null);
				textFieldbuilding.setText(null);
				textFieldroom.setText(null);
				
			}
		});
		reset.setFont(new Font("Serif", Font.BOLD, 20));
		reset.setBounds(257, 235, 121, 41);
		contentPane.add(reset);
	  
         }
	} 
