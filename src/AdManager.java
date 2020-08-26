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

public class AdManager extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField adname;
	private JTextField sponsor;
	private JTextField year;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdManager frame = new AdManager();
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
	public AdManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Advertisement name");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setBounds(32, 26, 136, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdvertismentDuration = new JLabel("Show Name ");
		lblAdvertismentDuration.setForeground(Color.BLUE);
		lblAdvertismentDuration.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblAdvertismentDuration.setBounds(32, 113, 136, 22);
		contentPane.add(lblAdvertismentDuration);
		
		JLabel lblNewLabel_1 = new JLabel("Advertisment type");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel_1.setBounds(32, 69, 136, 22);
		contentPane.add(lblNewLabel_1);
		
		adname = new JTextField();
		adname.setFont(new Font("Arial", Font.PLAIN, 12));
		adname.setBounds(216, 28, 152, 22);
		contentPane.add(adname);
		adname.setColumns(10);
		
		sponsor = new JTextField();
		sponsor.setFont(new Font("Arial", Font.PLAIN, 12));
		sponsor.setBounds(216, 114, 152, 20);
		contentPane.add(sponsor);
		sponsor.setColumns(10);
		
		JComboBox adtype = new JComboBox();
		adtype.setModel(new DefaultComboBoxModel(new String[] {"", "channel", "program"}));
		adtype.setFont(new Font("Arial Black", Font.BOLD, 12));
		adtype.setBounds(216, 69, 152, 22);
		contentPane.add(adtype);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=(String)adtype.getSelectedItem();
				if(s.equals("channel"))
				{
					try
					{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/tvmanagement","root","");
					PreparedStatement stat2=con.prepareStatement("insert into advertise(name,category,year,sponsor) values(?,?,?,?)");
	
	
					//Fill up the parameter values from the controls
	
					stat2.setString(1,adname.getText());
					stat2.setString(2,(String)adtype.getSelectedItem());
					stat2.setString(4,sponsor.getText());
					stat2.setString(3,year.getText());
					//stat2.setString(4,t23.getText());
					stat2.executeUpdate();
					JOptionPane.showMessageDialog(frame,new String("Your advertisment have been added....!!!"));
					con.close();
				}
				catch(Exception ae)
				{
					JOptionPane.showMessageDialog(null, ae);
				}
				}
				else
				{
					year.setEnabled(false);
					try
					{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/tvmanagement","root","");
					PreparedStatement stat2=con.prepareStatement("insert into advertise(name,category,year,sponsor) values(?,?,?,?)");
	
	
					//Fill up the parameter values from the controls
	
					stat2.setString(1,adname.getText());
					stat2.setString(2,(String)adtype.getSelectedItem());
					stat2.setString(4,sponsor.getText());
					stat2.setString(3,"null");
					//stat2.setString(4,t23.getText());
					stat2.executeUpdate();
					JOptionPane.showMessageDialog(frame,new String("Your advertisment have been added....!!!"));
					con.close();
				}
				catch(Exception ae)
				{
					JOptionPane.showMessageDialog(null, ae);
				}
				}
			}
		});
		btnAdd.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnAdd.setBounds(10, 216, 136, 22);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel_2 = new JLabel("Year of expire ");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel_2.setBounds(32, 152, 136, 22);
		contentPane.add(lblNewLabel_2);
		
		year = new JTextField();
		year.setFont(new Font("Arial", Font.PLAIN, 12));
		year.setBounds(216, 154, 152, 22);
		contentPane.add(year);
		year.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewButton.setBounds(187, 216, 136, 22);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=(String)adtype.getSelectedItem();
				if(s.equals("program"))
				{
					year.setEnabled(false);
				}
				else if(s.equals("channel"))
					year.setEnabled(true);
			}
		});
		button.setBounds(156, 217, 21, 21);
		contentPane.add(button);
		JButton btnRenewal = new JButton("Renewal");
		btnRenewal.setBackground(Color.WHITE);
		btnRenewal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvmanagement","root","");
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from advertise where year < 2019 and year!='null'");
						if (rs.next() == false)
						{
							JOptionPane.showMessageDialog(null, "No new advertisment left for renewal");
				        }
				        else {
				        	Renewal rn=new Renewal();//.frame.setVisible(true);
							dispose();
							rn.setVisible(true);
				        }	
				}
				catch(Exception ex){
			         ex.printStackTrace();
			    }
			
			}
		});
		btnRenewal.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnRenewal.setBounds(362, 216, 128, 22);
		contentPane.add(btnRenewal);
	}
}
