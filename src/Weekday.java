import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Weekday extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField progname;
	private JTextField starttime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Weekday frame = new Weekday();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Weekday() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Program Name");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setBounds(30, 25, 113, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblAiringTime = new JLabel("Start Time");
		lblAiringTime.setForeground(Color.BLUE);
		lblAiringTime.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblAiringTime.setBounds(30, 126, 113, 22);
		contentPane.add(lblAiringTime);
		
		JLabel lblNewLabel_1 = new JLabel("Category of program");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel_1.setBounds(30, 72, 119, 22);
		contentPane.add(lblNewLabel_1);
		
		progname = new JTextField();
		progname.setFont(new Font("Arial", Font.PLAIN, 12));
		progname.setBounds(206, 27, 149, 20);
		contentPane.add(progname);
		progname.setColumns(10);
		
		starttime = new JTextField();
		starttime.setFont(new Font("Arial", Font.PLAIN, 12));
		starttime.setText("");
		starttime.setBounds(206, 126, 149, 22);
		contentPane.add(starttime);
		starttime.setColumns(10);
		
		JComboBox progcate = new JComboBox();
		progcate.setModel(new DefaultComboBoxModel(new String[] {"", "news", "show", "music"}));
		progcate.setFont(new Font("Arial Black", Font.BOLD, 12));
		progcate.setBounds(206, 72, 149, 22);
		contentPane.add(progcate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/tvmanagement","root","");
				PreparedStatement stat3=con.prepareStatement("insert into weekday(name,type,start) values(?,?,?)");
				stat3.setString(1,progname.getText());
				stat3.setString(2,(String)progcate.getSelectedItem());
				stat3.setString(3,starttime.getText());
				//stat3.setString(4,allowtime.getText());
				stat3.executeUpdate();
				JOptionPane.showMessageDialog(frame,new String("Your program have been added...!!!"));
				con.close();
				}
				catch(Exception ae)
				{
					JOptionPane.showMessageDialog(null, ae);
				}
				}
			
		});
		btnAdd.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnAdd.setBounds(10, 185, 113, 22);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean x=false;
				String prog;
				prog = JOptionPane.showInputDialog("Enter Program Name : ");
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con;
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvmanagement","root","");
				Statement st=con.createStatement();	
				st.executeUpdate("delete from weekday where name='"+prog+"'");
				st.executeUpdate("delete from advertise where sponsor='"+prog+"'");
					if(!x)
					{
						JOptionPane.showMessageDialog(frame,new String("Program deleted successfully"));
					}
				
					}
				
				catch(Exception exception)
			     {      
					//JOptionPane.showMessageDialog(frame,new String("Error encountered while entering data in the database: "+exception));
					exception.printStackTrace();
			     }
			}
		});
		btnDelete.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnDelete.setBounds(149, 185, 113, 22);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProgramManager().setVisible(true);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnCancel.setBounds(292, 186, 113, 21);
		contentPane.add(btnCancel);
	}

}
