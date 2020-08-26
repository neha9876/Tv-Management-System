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
class List
{
	String name;
	String type;
	String time;
	String sponsor;
	boolean seen;
	List(String str,String t,String ti)
	{
		this.name=str;
		this.time=ti;
		this.type=t;
	}
}
class slist1{
    String name;
    String interval;
    String type;
    slist1(String n,String i,String t)
    {
    	name=n;
    	interval=i;
    	type=t;
    }
	
}
class Add1
{
	String name;
	String cat;
	String sponsor;
	boolean seen;
	String year;
	Add1(String str,String a,String spo,String year)
	{
		this.name=str;
		this.cat=a;
		this.sponsor=spo;
		this.year=year;
	}	
}

public class weekendSchedule extends JFrame implements ActionListener
{
	JTable j1;
    JButton b1;
    String h[]={"Event name","Time interval","Type"};
    String d[][]=new String[100][3];  
    int i=0,j=0;
	JFrame frame;
	static ArrayList<slist1> slist=new ArrayList<>();
	
	String AddTime(String tr,int inc)
	{
		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime lt = LocalTime.parse(tr);
        return  (df.format(lt.plusMinutes(inc)));
	}
	ArrayList<Add1> addcreateList()
	{
		ArrayList<Add1> arr=new ArrayList<>();
		try{        
				cone c1 = new cone();
				String q="SELECT * FROM `advertise`";
				ResultSet rs = c1.s.executeQuery(q);
				while(rs.next())
				{
		              Add1 ob=new Add1(rs.getString("name"),rs.getString("category"),rs.getString("sponsor"),rs.getString("year"));
		              arr.add(ob);		  
				}
    	 }
		catch(Exception e){
        System.out.println("error: "+e);
		}
		return arr;
	}
	ArrayList<List> createList()
	{
		ArrayList<List> arr=new ArrayList<>();
		try{        
				cone c1 = new cone();
				String q="SELECT * FROM `weekends`";
				ResultSet rs = c1.s.executeQuery(q);
				while(rs.next())
				{
		              List ob=new List(rs.getString("name"),rs.getString("type"),rs.getString("start").substring(0,5));
		              arr.add(ob);		  
				}
    	 }
		catch(Exception e){
        System.out.println("error: "+e);
		}
		return arr;
	}
	int countchannel(ArrayList<Add1> arr)
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
	int countshow(ArrayList<Add1> arr)
	{
		int count=0;
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i).cat.equals("program"))
				count++;	
		}
		return count;
	}
  String show(String t,String showname,String showtype)
  {  
	  
	  if(showtype.equals("show") || showtype.equals("music") || showtype.equals("news"))
 	 {
 		ArrayList<Add1> arr1=new ArrayList<>();
     	arr1=addcreateList();
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
 		   if(durr==54)
 		   {
 			   String curr=t;
     		   t=AddTime(t,6);
     		  slist.add(new slist1(showname,curr+" to " +t,showtype));
     		  // System.out.println(showname+" " + showtype  + " " + curr + "-"+ t);
     		   break;
 		   }
 		   String curr=t;
 		   t=AddTime(t,15);
 		   durr=durr+15;
 		   if(counts>=totalshow)
 		   {
 			   min=totalchannel;
 			   arr1=addcreateList();
 		   }
 		   slist.add(new slist1(showname,curr+" to " +t,showtype));
 		  // System.out.println(showname+" "+ showtype + " " + curr + "-"+ t);
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
 			        	  slist.add(new slist1(arr1.get(i).name,c+" to " +t,"channel sponsor"));
 			        	  //System.out.println(arr1.get(i).name+" "+ "channel sponsor" + " " + c + "-"+ t);
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
 			          if(arr1.get(i).cat.equals("program") && arr1.get(i).seen==false && (arr1.get(i).sponsor.equals(showname)))
 			          {
 			        	  String c=t;
 			        	  t=AddTime(t,3);
 			        	  durr=durr+3;
 			        	  arr1.get(i).seen=true;
 			        	  slist.add(new slist1(arr1.get(i).name,c+" to " +t,arr1.get(i).sponsor));
 			        	  // System.out.println(arr1.get(i).name+" show sponsor = "+ arr1.get(i).sponsor + " " + c + "-"+ t);
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
 			          if(arr1.get(i).cat.equals("program") && arr1.get(i).seen==false && !(arr1.get(i).sponsor.equals(showname)))
 			          {
 			        	  String c=t;
 			        	  t=AddTime(t,3);
 			        	  durr=durr+3;
 			        	  arr1.get(i).seen=true;
 			        	  slist.add(new slist1(arr1.get(i).name,c+" to " +t,arr1.get(i).sponsor));
 			        	  //System.out.println(arr1.get(i).name+" show sponsor = "+ arr1.get(i).sponsor + " " + c + "-"+ t);
 			        	  counts++;
 			        	  break;
 			          }
 		          }
 			  }
 		   }
 		   
 		}
 	}	
	  return t;
		   
  }
   String movie(String t,String moviename)
   {
	   
    	    ArrayList<Add1> arr1=new ArrayList<>();
        	arr1=addcreateList();
    		int durr=0;
    		int chan=0;
    		int totalchannel=countchannel(arr1);
    		int totalshow=countshow(arr1);
    		int counts=0;
    		int countc=0;
    		int flag=0;
    		int min=2;
    		while(durr<=210)
    		{
    		      if(durr==198)
    		      {
    			      String curr=t;
        		      t=AddTime(t,12);
        		      slist.add(new slist1(moviename,curr+" to " +t,"movie"));
        		      //System.out.println(moviename+" "+ "movie" + " " + curr + "-"+ t);
        		      break;
    		      }
    		      if(counts>=totalshow)
    	 		   {
    	 			   min=totalchannel;
    	 			   arr1=addcreateList();
    	 		   }
    		   String curr=t;
    		   t=AddTime(t,15);
    		   durr=durr+15;
    		   slist.add(new slist1(moviename,curr+" to " +t,"movie"));
    		  // System.out.println(moviename+" "+ "movie" + " " + curr + "-"+ t);
    		   if(arr1.size()==0)
    		   {
    			   JOptionPane.showMessageDialog(null, "There is no advertisement available:");
    		        setVisible(false);
    			   System.exit(0);
    		   }
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
    			        	  //slist.add(new slist1(moviename,curr+" to " +t,arr1.get(i).sponsor));
    			        	  slist.add(new slist1(arr1.get(i).name,c+" to " +t,"channel sponsor"));
    			        	 // System.out.println(arr1.get(i).name+" "+ "channel sponsor" + " " + c + "-"+ t);
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
    	 			          if(arr1.get(i).cat.equals("program") && arr1.get(i).seen==false && (arr1.get(i).sponsor.equals(moviename)))
    	 			          {
    	 			        	  String c=t;
    	 			        	  t=AddTime(t,3);
    	 			        	  durr=durr+3;
    	 			        	  arr1.get(i).seen=true;
    	 			        	 slist.add(new slist1(arr1.get(i).name,c+" to " +t,arr1.get(i).sponsor));
    	 			        	  //System.out.println(arr1.get(i).name+" movie sponsor = "+ arr1.get(i).sponsor + " " + c + "-"+ t);
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
    	 			          if(arr1.get(i).cat.equals("program") && arr1.get(i).seen==false && !(arr1.get(i).sponsor.equals(moviename)))
    	 			          {
    	 			        	  String c=t;
    	 			        	  t=AddTime(t,3);
    	 			        	  durr=durr+3;
    	 			        	  arr1.get(i).seen=true;
    	 			        	 slist.add(new slist1(arr1.get(i).name,c+" to " +t,arr1.get(i).sponsor));
    	 			        	  //System.out.println(arr1.get(i).name+" show sponsor = "+ arr1.get(i).sponsor + " " + c + "-"+ t);
    	 			        	  counts++;
    	 			        	  break;
    	 			          }
    	 		          }
    	 			  }
    		   }
    		   
    		}   
	   return t;
   }
   String cheack(String t) throws SQLException
   {
	  if(t.equals("05:30"))
	   {
		  //System.out.println("hjgsj");
		   cone c1 = new cone();
		   ArrayList<Add1> arr2=new ArrayList<>();
		   arr2=addcreateList();
		   int durr=0;
		   String qr="SELECT * FROM `weekends` WHERE type='news'or type='show'";
		   ResultSet rs1 = c1.s.executeQuery(qr);
			  while(!t.equals("06:00") && rs1.next())
			  {
			  	  if(rs1.getString("type").equals("news")||rs1.getString("type").equals("show"))
			  	  {
			  		  
			  		   while(durr<30)
			  		   {
			  			 if(t.equals("05:48"))
				  		  {
				  			   String curr=t;
		            		   t=AddTime(t,12);
		            		   durr=durr+12; 
		            		   slist.add(new slist1(rs1.getString("name"),curr+" to " +t,rs1.getString("type")));
				  			   //System.out.println(rs1.getString("name")+" " + rs1.getString("type")  + " " + curr + "-"+ t);
				  			  break;
				  		  }
			  			   String curr=t;
	            		   t=AddTime(t,15);
	            		   durr=durr+15; 
	            		   slist.add(new slist1(rs1.getString("name"),curr+" to " +t,rs1.getString("type")));
			  			   //System.out.println(rs1.getString("name")+" " + rs1.getString("type")  + " " + curr + "-"+ t);
			  			   for(int i=0;i<arr2.size();i++)
						     {
			        		     if(t.equals("06:00"))
			        		     {
			        		    	 break;
			        		     }
			        		     else
			        		     {
							        String c=t;
					        	    t=AddTime(t,3);
					        	    durr=durr+3;
					        	    slist.add(new slist1(arr2.get(i).name,c+" to " +t,arr2.get(i).cat));
					        	  //  System.out.println(arr2.get(i).name+" "+ arr2.get(i).cat + " " + c + "-"+ t);
					        	    break;
						        }
						     }
			  		   }
			  		   break;
			  	  }
			  	  break;
			  }
				 
	        	 
		   }
	  if(t.equals("04:30"))
	   {
		  System.out.println("hjgsj");
		   cone c1 = new cone();
		   ArrayList<Add1> arr2=new ArrayList<>();
		   arr2=addcreateList();
		   int durr=0;
		   String qr="SELECT * FROM `weekends` WHERE type='news'";
		   ResultSet rs1 = c1.s.executeQuery(qr);
			  while(!t.equals("05:00") && rs1.next())
			  {
				  System.out.println("under");
			  	  if(rs1.getString("type").equals("news")||(rs1.getString("type").equals("show")))
			  	  {
			  		  
			  		   while(durr<30)
			  		   {
			  			 if(t.equals("04:48"))
				  		  {
				  			   String curr=t;
		            		   t=AddTime(t,12);
		            		   durr=durr+12; 
		            		   slist.add(new slist1(rs1.getString("name"),curr+" to " +t,rs1.getString("type")));
				  			   //System.out.println(rs1.getString("name")+" " + rs1.getString("type")  + " " + curr + "-"+ t);
				  			  break;
				  		  }
			  			   String curr=t;
	            		   t=AddTime(t,15);
	            		   durr=durr+15; 
	            		   slist.add(new slist1(rs1.getString("name"),curr+" to " +t,rs1.getString("type")));
			  			   //System.out.println(rs1.getString("name")+" " + rs1.getString("type")  + " " + curr + "-"+ t);
			  			   for(int i=0;i<arr2.size();i++)
						     {
			        		     if(t.equals("05:00"))
			        		     {
			        		    	 break;
			        		     }
			        		     else
			        		     {
							        String c=t;
					        	    t=AddTime(t,3);
					        	    durr=durr+3;
					        	    slist.add(new slist1(arr2.get(i).name,c+" to " +t,arr2.get(i).cat));
					        	  //  System.out.println(arr2.get(i).name+" "+ arr2.get(i).cat + " " + c + "-"+ t);
					        	    break;
						        }
						     }
			  		   }
			  		   break;
			  	  }
			  	  break;
			  }
				 
	        	 
		   }
		   
	   
	  return t;
   }
  weekendSchedule() throws SQLException
	{   
	  cone c1 = new cone();
	  ArrayList<Add1> arr4=new ArrayList<>();
	    arr4=addcreateList();
	    HashMap<String,Integer> map = new HashMap<String,Integer>(); 
	    for(int i=0;i<arr4.size();i++)
	    {
	    	if(arr4.get(i).cat.equals("program") && !map.containsKey(arr4.get(i).sponsor))
	    	{
	    		map.put(arr4.get(i).sponsor,1);
	    	}
	    }
	  String qr="SELECT * FROM `weekends`";
     ResultSet rs1 = c1.s.executeQuery(qr);
  while(rs1.next())
  {
  	if(!map.containsKey(rs1.getString("name")))
  	{
  		JOptionPane.showMessageDialog(null, "Error:Program Does not Exist:");
        setVisible(false);
        return;
  	}
  }
 for(int i=0;i<arr4.size();i++)
 	{
 		if(arr4.get(i).cat.equals("channel") && Integer.parseInt(arr4.get(i).year)<2019)
 		{
 			JOptionPane.showMessageDialog(null, arr4.get(i).name + "addvertisement is expire:");
 	         setVisible(false);
 			return;
 			}
 	}
	 
 
//*********************************************************************start********************************************************	    
 	
	    String t="01:00";
	    ArrayList<List> arr1=new ArrayList<List>();
	    arr1=createList();
	    while(!t.equals("06:00"))
	    {
	    	  ArrayList<List> temp=new ArrayList<List>();
              for(int i=0;i<arr1.size();i++)
              {
            	  if(arr1.get(i).time.equals(t) && arr1.get(i).seen==false)
            	  {
            		    temp.add(arr1.get(i));  
            	  }
              }
              if(temp.size()==1)
              {
            	  if(temp.get(0).type.equals("movie") || temp.get(0).type.equals("Movie"))
            	  {
            		  t=movie(t,temp.get(0).name);
            		  t=cheack(t);
            	  }
            	  else
            	  {
            		  t=show(t,temp.get(0).name,temp.get(0).type);
            		  t=cheack(t);  
            	  }
              }
              else if(temp.size()>1)
              {
            	  int flag=0;
            	  for(int i=0;i<temp.size();i++)
            	  {
            		  if(!temp.get(i).type.equals("news"))
            		  {
            			  if(temp.get(i).type.equals("movie") || temp.get(i).type.equals("Movie"))
            			  {
            				  t=movie(t,temp.get(0).name);
                    		  t=cheack(t);
                    		  flag=1;
            			  }
            			  else
            			  { 
            				  //System.out.println("hii");
            				  t=show(t,temp.get(i).name,temp.get(i).type);
                    		  t=cheack(t); 
                    		  flag=1;
            			  }
            			  break;
            		  }
            	  }
            	  if(flag==0)
            	  {
            		  t=show(t,temp.get(0).name,temp.get(0).type);
            		  t=cheack(t);  
            	  }
              }
              t=cheack(t);
            //  System.out.println( "hii" +slist.size());

	    }
	   // System.out.println( "hii" +slist.size());
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
	        JScrollPane s1=new JScrollPane(j1);
	        add(s1); 
	        //System.out.println(slist.size());
}
	public static void main(String[] args) throws SQLException {
		new weekendSchedule().setVisible(true); 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
