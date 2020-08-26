import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ProgramManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramManager frame = new ProgramManager();
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
	public ProgramManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnWeekday = new JButton("Weekday");
		btnWeekday.setBackground(Color.WHITE);
		btnWeekday.setForeground(Color.BLUE);
		btnWeekday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Weekday pg=new Weekday();
				dispose();
				pg.setVisible(true);
			}
		});
		btnWeekday.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnWeekday.setBounds(41, 72, 167, 36);
		contentPane.add(btnWeekday);

		JButton btnWeekend = new JButton("Weekend");
		btnWeekend.setForeground(Color.BLUE);
		btnWeekend.setBackground(Color.WHITE);
		btnWeekend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Weekend pg=new Weekend();
				dispose();
				pg.setVisible(true);
			}
		});
		btnWeekend.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnWeekend.setBounds(248, 72, 167, 36);
		contentPane.add(btnWeekend);

		JButton btnWeekdayschedule = new JButton("Weekday\r\nSchedule");
		btnWeekdayschedule.setBackground(Color.WHITE);
		btnWeekdayschedule.setForeground(Color.BLUE);
		btnWeekdayschedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new weekdaySchedule().setVisible(true);
				}
				catch(Exception ae){
					//JOptionPane.showMessageDialog(frame,new String("Error encountered while entering data in the database: "+exception));
					ae.printStackTrace();
				}
			}	
		});
		btnWeekdayschedule.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnWeekdayschedule.setBounds(41, 156, 167, 36);
		contentPane.add(btnWeekdayschedule);

		JButton btnWeekendSchedule = new JButton("Weekend Schedule");
		btnWeekendSchedule.setForeground(Color.BLUE);
		btnWeekendSchedule.setBackground(Color.WHITE);
		btnWeekendSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new weekendSchedule().setVisible(true);
				}
				catch(Exception ae){
					//JOptionPane.showMessageDialog(frame,new String("Error encountered while entering data in the database: "+exception));
					ae.printStackTrace();
				}
			}
		});
		btnWeekendSchedule.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnWeekendSchedule.setBounds(248, 156, 167, 36);
		contentPane.add(btnWeekendSchedule);

		JButton btnLogout = new JButton("Cancel");
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setForeground(Color.BLUE);
		btnLogout.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new Login().frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(169, 222, 100, 31);
		contentPane.add(btnLogout);
	}
}
