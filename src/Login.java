import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
public class Login {

	public JFrame frame;
	private JTextField uname;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//String a;
		JLabel lblTypeOfManager = new JLabel("Type of manager");
		lblTypeOfManager.setForeground(Color.BLUE);
		lblTypeOfManager.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblTypeOfManager.setBounds(21, 23, 161, 26);
		frame.getContentPane().add(lblTypeOfManager);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
			}
		});
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Advertisment manager", "Program manager"}));
		comboBox.setBounds(171, 26, 192, 21);
		frame.getContentPane().add(comboBox);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblUsername.setBounds(21, 81, 161, 26);
		frame.getContentPane().add(lblUsername);
		
		uname = new JTextField();
		uname.setBounds(171, 81, 192, 19);
		frame.getContentPane().add(uname);
		uname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblPassword.setBounds(21, 117, 161, 26);
		frame.getContentPane().add(lblPassword);
		
		JButton btnlog = new JButton("Login");
		btnlog.setBackground(Color.WHITE);
		btnlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String a =(String)comboBox.getSelectedItem();
				String name = uname.getText();
				   String passs  = pass.getText();  
				Object obj = e.getSource();
				  
				  if (obj==btnlog)
				{
				   if (a.equals("Advertisment manager") && name.equals("neha") && passs.equals("neha"))
				      
				 {
				JOptionPane.showMessageDialog(frame,new String("Login Successful"));
				AdManager ad=new AdManager();
				frame.dispose();
				ad.setVisible(true);
				}
				else  if (a.equals("Program manager") && name.equals("neha") && passs.equals("neha"))
				{
					JOptionPane.showMessageDialog(frame,new String("Login Successful"));
					// new MenuBar();
					ProgramManager pg=new ProgramManager();
					frame.dispose();
					pg.setVisible(true);
				}
				else   
				{
				JOptionPane.showMessageDialog(frame,new String("errorLogin "));

				}	
				}	}
		});
		
		btnlog.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnlog.setBounds(86, 170, 96, 26);
		frame.getContentPane().add(btnlog);
		
		JButton btncnl = new JButton("Cancel");
		btncnl.setBackground(Color.WHITE);
		btncnl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btncnl.setFont(new Font("Arial Black", Font.BOLD, 12));
		btncnl.setBounds(217, 170, 96, 26);
		frame.getContentPane().add(btncnl);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Arial", Font.PLAIN, 12));
		pass.setBounds(171, 121, 192, 19);
		frame.getContentPane().add(pass);
	}
}
