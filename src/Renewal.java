
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
    
public class Renewal extends JFrame{

	JFrame frame;
	JLabel JL_name,JL_category,JL_year,JL_sponsor;
	JTextField JT_name,JT_category,JT_year,JT_sponsor;
	JButton btn_next,btn_previous,b1,b2;
	int pos = 0;
    public static Connection getConnection()      
    {
    	Connection cn;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
        	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tvmanagement","root","");
            return cn;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    // bind a list with ResultSet
    public static List<Renew> BindList(){
        try{
        	Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from advertise where year < 2019  and year !='null'");
            List<Renew> list = new ArrayList<Renew>();
            while(rs.next()){
            	Renew u = new Renew(rs.getString("name"),
                                    rs.getString("category"),
                                    rs.getString("year"),
                                    rs.getString("sponsor")
                 );
                list.add(u);
            }
            return list;
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
   //Showing the Users Info in jtexfields
    public void ShowPosInfo(int index){

    //	JT_rollno.setText(BindList().get(index).getRollno());
        JT_name.setText(BindList().get(index).getName());
        JT_category.setText(BindList().get(index).getCategory());
        JT_year.setText(BindList().get(index).getYear());
        JT_sponsor.setText(BindList().get(index).getSponsor());
        //JT_mob.setText(BindList().get(index).getMob());
        //JT_emailid.setText(BindList().get(index).getEmailid());
    }
    	    
    public Renewal(){
    
    //	JL_rollno = new JLabel("Roll No.:");
    	JL_name = new JLabel("Advertisment name :");
    	JL_category = new JLabel("Category :");
    	JL_year = new JLabel("Year :");
    	JL_sponsor = new JLabel("Sponsor :");
 
    	JL_name.setBounds(10, 50, 150, 20);
    	JL_category.setBounds(10, 80, 150, 20);
    	JL_year.setBounds(10, 110, 150, 20);
    	JL_sponsor.setBounds(10, 140, 150, 20);
    
    	//JT_rollno = new JTextField(20);
    	JT_name = new JTextField(20);
    	JT_category = new JTextField(20);
    	JT_year = new JTextField(20);
    	JT_sponsor = new JTextField(20);
    	
    	JT_name.setBounds(140, 50, 150, 20);
    	JT_category.setBounds(140, 80, 150, 20);
    	JT_year.setBounds(140, 110, 150, 20);
    	JT_sponsor.setBounds(140, 140, 150, 20);
    	btn_next = new JButton("Next");
    	btn_previous = new JButton("Previous");
    	b1 = new JButton("Accept");
    	b2 = new JButton("Reject");
    	btn_next.setBounds(310, 50, 100, 20);
    	btn_previous.setBounds(310, 80, 100, 20);
    	b1.setBounds(310, 140, 100, 20);
    	b2.setBounds(310, 170, 100, 20);
        // Button to show the Next user from the List
        btn_next.addActionListener(new ActionListener() {    
         @Override
         public void actionPerformed(ActionEvent e) {
        	 pos++;
        	 if(pos < BindList().size()){
        		 ShowPosInfo(pos);
        	 }
        	 else{
        		 pos = BindList().size() - 1;
        		 ShowPosInfo(pos);
        		 JOptionPane.showMessageDialog(null, "END");
             }
         }
         });
         //Button to show the Previous user from the List
        btn_previous.addActionListener(new ActionListener(){    
        @Override
        public void actionPerformed(ActionEvent e){  
        	pos--;
        	if(pos > 0){
        		ShowPosInfo(pos);                
        	}
            else{
                pos = 0;
                ShowPosInfo(pos);
                JOptionPane.showMessageDialog(null, "END");
            }
        }
        });
        
        b1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	try{
        		Connection con = getConnection();
        		String sn1=(String)JT_name.getText();
        		PreparedStatement pst=con.prepareStatement("update advertise set year = year+1 where name='"+sn1+"'");
        		pst.executeUpdate();
        		JOptionPane.showMessageDialog(null, "Verified");
        	}
        	catch(Exception ex){
        		ex.printStackTrace();
        	}
        }
        });
        
        b2.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	    	try{
	    		Connection con = getConnection();
	    		String sn1=(String)JT_name.getText();
	    		PreparedStatement pst=con.prepareStatement("delete from advertise where name = '"+sn1+"'");
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Rejected");
	    	}
	    	catch(Exception ex){
	              ex.printStackTrace();
	    	}
	    }
	    });
        
        ShowPosInfo(pos);
        getContentPane().add(btn_previous);
        getContentPane().add(btn_next);
        getContentPane().add(b1);
        getContentPane().add(b2);
      //  add(JT_rollno);
        getContentPane().add(JT_name);
        getContentPane().add(JT_category);
        getContentPane().add(JT_year);
        getContentPane().add(JT_sponsor);
        getContentPane().add(JL_name);
        getContentPane().add(JL_category);
        getContentPane().add(JL_year);
        getContentPane().add(JL_sponsor);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.PINK);
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dispose();
        //new Administration();
        setLocationRelativeTo(null);
        setSize(500,300);
        setLocation(400,200);
        setVisible(true);
    }     
    public static void main(String[] args) {
    	new Renewal();
    }
}
