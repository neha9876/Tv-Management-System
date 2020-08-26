import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
class Add
{
	String name;
	String cat;
	String sponsor;
	String year;
	boolean seen;
	Add(String str,String a,String spo,String year)
	{
		this.name=str;
		this.cat=a;
		this.sponsor=spo;
		this.year=year;
	}	
}
class slist{
    String name;
    String interval;
    String type;
    slist(String n,String i,String t)
    {
    	name=n;
    	interval=i;
    	type=t;
    }
	
}
public class weekdaySchedule extends JFrame implements ActionListener{
	JTable j1;
    JButton b1;
    String h[]={"Event name","Time interval","Type"};
    String d[][]=new String[100][3];  
    int i=0,j=0;

	String t="01:00";
	String AddTime(String tr,int inc)
	{
		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime lt = LocalTime.parse(tr);
        return  (df.format(lt.plusMinutes(inc)));
	}
	ArrayList<Add> createList()
	{
		ArrayList<Add> arr=new ArrayList<>();
		try{        
				cone c1 = new cone();
				String q="SELECT * FROM `advertise`";
				ResultSet rs = c1.s.executeQuery(q);
				while(rs.next())
				{
		              Add ob=new Add(rs.getString("name"),rs.getString("category"),rs.getString("sponsor"),rs.getString("year"));
		              arr.add(ob);		  
				}
    	 }
		catch(Exception e){
        System.out.println("error: "+e);
		}
		return arr;
	}
	int countchannel(ArrayList<Add> arr)
	{
		int count=0;
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i).cat.equals("channel"))
			{
				count++;
			}
		}
		return count;
	}
	int countshow(ArrayList<Add> arr)
	{
		int count=0;
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i).cat.equals("program"))
				count++;	
		}
		return count;
	} 
weekdaySchedule() throws SQLException
	{
	 cone c1 = new cone();
	  ArrayList<Add> arr4=new ArrayList<>();
	    arr4=createList();
	    HashMap<String,Integer> map = new HashMap<String,Integer>(); 
	    for(int i=0;i<arr4.size();i++)
	    {
	    	if(arr4.get(i).cat.equals("program") && !map.containsKey(arr4.get(i).sponsor))
	    	{
	    		map.put(arr4.get(i).sponsor,1);
	    	}
	    }
	  String qr="SELECT * FROM `weekday`";
    ResultSet rs1 = c1.s.executeQuery(qr);
 while(rs1.next())
 {
 	if(!map.containsKey(rs1.getString("name")))
 	{
 		 JOptionPane.showMessageDialog(null, "Error:Program Does not Exist:");
         setVisible(false);
       //System.out.println("Error:Program Does not Exist:");
       return;
 	}
 	for(int i=0;i<arr4.size();i++)
 	{
 		if(arr4.get(i).cat.equals("channel") && Integer.parseInt(arr4.get(i).year)<2019)
 		{
 			JOptionPane.showMessageDialog(null, arr4.get(i).name + "advertisement is expire:");
 	         setVisible(false);
 			//System.out.println(arr4.get(i).name + "addvertisement is expire:");
 			return;
 		}
 	}
 }
    //*********************************************************************start********************************************************	
 
    ArrayList<slist> slist=new ArrayList<>();
   while(!t.equals("06:00"))
		{
			
			try {
		            String q="SELECT * FROM `weekday` WHERE start='"+t+"'";
		            ResultSet rs = c1.s.executeQuery(q);
		            if(rs.next())
		            {
		               if(rs.getString("type").equals("show") || rs.getString("type").equals("music") || rs.getString("type").equals("news"))
		            	 {
		            		ArrayList<Add> arr1=new ArrayList<>();
			            	arr1=createList();
		            		int durr=0;
		            		int chan=0;
		            		int totalchannel=countchannel(arr1);
		            		 int totalshow=countshow(arr1);
		            		int counts=0;
		            		int countc=0;
		            		int min=2;
		            		int flag=0;
		            		while(durr<=60)
		            		{
		            		   if(durr>=54)
		            		   {
		            			   String curr=t;
			            		   t=AddTime(t,6);
			            		   slist.add(new slist(rs.getString("name"),curr+" to " +t,rs.getString("type")));
			            		   System.out.println(rs.getString("name")+" " + rs.getString("type")  + " " + curr + "-"+ t);
			            		   break;
		            		   }
		            		   String curr=t;
		            		   t=AddTime(t,15);
		            		   durr=durr+15;
		            		   if(counts>=totalshow)
		            		   {
		            			   min=totalchannel;
		            			   arr1=createList();
		            		   }
		            		   slist.add(new slist(rs.getString("name"),curr+" to "+t,rs.getString("type")));
		            		   System.out.println(rs.getString("name")+" "+ rs.getString("type") + " " + curr + "-"+ t);
		            		   if(chan<min)
		            		   {
		            		        for(int i=0;i<arr1.size();i++)
		            		          {
		            			          if(arr1.get(i).cat.equals("channel") && arr1.get(i).seen==false)
		            			          {
		            			        	  String c=t;
		            			        	  t=AddTime(t,3);
		            			        	  durr=durr+3;
		            			        	  arr1.get(i).seen=true;
		            			        	  slist.add(new slist(arr1.get(i).name,c+" to " +t,"channel sponsor"));
		            			        	  System.out.println(arr1.get(i).name+" "+ "channel sponsor" + " " + c + "-"+ t);
		            			        	  break;
		            			          }
		            		          }
		            		        chan++;
		            		   }
		            		   else
		            		   {
		            			  if(flag==0)
		            			  {
		            			    for(int i=0;i<arr1.size();i++)
		            		          {
		            			          if(arr1.get(i).cat.equals("program") && arr1.get(i).seen==false && (arr1.get(i).sponsor.equals(rs.getString("name"))))
		            			          {
		            			        	  String c=t;
		            			        	  t=AddTime(t,3);
		            			        	  durr=durr+3;
		            			        	  arr1.get(i).seen=true;
		            			        	  slist.add(new slist(arr1.get(i).name,c+" to " +t,arr1.get(i).sponsor));
		            			        	  System.out.println(arr1.get(i).name+" show sponsor = "+ arr1.get(i).sponsor + " " + c + "-"+ t);
		            			        	  counts++;
		            			        	  flag=1;
		            			        	  break;
		            			          }
		            		          }
		            			  }
		            			  else
		            			  {
		            				  for(int i=0;i<arr1.size();i++)
		            		          {
		            			          if(arr1.get(i).cat.equals("program") && arr1.get(i).seen==false && !(arr1.get(i).sponsor.equals(rs.getString("name"))))
		            			          {
		            			        	  String c=t;
		            			        	  t=AddTime(t,3);
		            			        	  durr=durr+3;
		            			        	  arr1.get(i).seen=true;
		            			        	  slist.add(new slist(arr1.get(i).name,c+" to "+t,arr1.get(i).sponsor));
		            			        	  System.out.println(arr1.get(i).name+" show sponsor = "+ arr1.get(i).sponsor + " " + c + "-"+ t);
		            			        	  counts++;
		            			        	  break;
		            			          }
		            		          }
		            			  }
		            		   }
		            		   
		            		}
		            	}
	
		 //************************************ Movie***********************************
		             
		            }
			   }catch(Exception e){
			        System.out.println("error: "+e);
				}
			
		  
		}
		
   setSize(800,300);
   setLocation(450,150);

 
	   int k=0;
       while(slist.size()>k){
           d[i][j++]=slist.get(k).name;
           d[i][j++]=slist.get(k).interval;
           d[i][j++]=slist.get(k).type;
           i++;
           j=0;
           k++;
       }
   
       j1=new JTable(d,h);
      // b1=new JButton("Print");
      // add(b1,"South");
       JScrollPane s1=new JScrollPane(j1);
       add(s1);
       
     //  b1.addActionListener((ActionListener) this);
   
   }
   public void actionPerformed(ActionEvent ae){
       try{
           j1.print();
       }catch(Exception e){}
   }
   
   
	
	
    

	
	public static void main(String[] args) throws SQLException {
        new weekdaySchedule().setVisible(true); 
	}


}
