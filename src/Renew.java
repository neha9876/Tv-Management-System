import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Renew {
  
    private String name;
    private String category;
    private String year;
    private String sponsor;
   
    public Renew(){}
    public Renew(String _name,String _category,String _year,String _sponsor){
    	
        this.name = _name;
        this.category = _category;
        this.year = _year;
        this.sponsor = _sponsor;
        
    }
    
    public String getName(){
     return this.name;
    }
    public String getCategory(){
        return this.category;
    }   
    public String getYear(){
        return this.year;
    }
     public String getSponsor(){
        return this.sponsor;
    }
}

/*

JButton btnRenewal = new JButton("Renewal");
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
btnRenewal.setFont(new Font("Arial", Font.PLAIN, 12));
btnRenewal.setBounds(362, 216, 128, 22);
contentPane.add(btnRenewal);*/