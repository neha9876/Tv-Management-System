import java.sql.*;
 
public class cone{
    Connection c;
    Statement s;
    public cone(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");   
            c =DriverManager.getConnection("jdbc:mysql://localhost:3306/tvmanagement","root","");
            s =c.createStatement();  
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
}  