//export CLASSPATH=$CLASSPATH:/usr/share/java/mysql-connector-java.jar
import java.sql.*;
import java.util.*;
class Link
{
	Scanner sc=new Scanner(System.in);
	String create_table()
	{
		System.out.println("CREATE\n");
		System.out.println("Enter table name :");
		String name=sc.nextLine();
		
		System.out.println("Enter the number of columns :");
		int column=sc.nextInt();
		
		System.out.println("Enter the columns or 0 to exit");

		String query="";

		String arr[]=new String[column+1];
			for(int i=0;i<arr.length;i++)
			{
				arr[i]=sc.nextLine();	
				if(query=="")	
					query=arr[i];
				else if(i<arr.length-1)
					query+=arr[i]+",";
				else
					query+=arr[i];
				
			}
		return ("CREATE TABLE "+name+"("+query+")");
	}
	String insert_table()
	{
		System.out.println("INSERT\n");
		System.out.println("Enter table name :");
		String name=sc.nextLine();
		
		System.out.println("How many entries ? -");
		int ent=sc.nextInt();
		
		String arr1[]=new String[ent+1];
		String que="";
		System.out.println("Enter the data :");
		for(int i=0;i<arr1.length;i++)
		{
			arr1[i]=sc.nextLine();
				if(que=="")	
					que=arr1[i];
				else if(i<arr1.length-1)
					que+="'"+arr1[i]+"'"+",";
				else
					que+="'"+arr1[i]+"'";		
		}
		
		return ("INSERT INTO "+name+" VALUES"+"("+que+")");		
	
		
	}
}
class Database
{
	private static final String url = "jdbc:mysql://localhost/javac?autoReconnect=true&useSSL=false";
	private static final String username = "root";
	private static final String pass = "password";	
	public static void main(String []args)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Scanner sc=new Scanner(System.in);
			
			Connection con = DriverManager.getConnection(url,username,pass);
			System.out.println("Sucess!");
			Statement stmt = con.createStatement();
			
			String str="use javac";
			stmt.executeUpdate(str);
			
			Link link=new Link();
			String choice;
			do
			{
				System.out.print("\n<=MYSQL-JAVA=> \nCREATE\nINSERT\nDISPLAY\nEXIT\nType your choice :");
				choice=sc.next();
				switch(choice)
				{
					case "CREATE":
						str=link.create_table();
						System.out.println(str);
						stmt.executeUpdate(str);
						break;
					case "INSERT":
						str=link.insert_table();
						System.out.println(str);
						stmt.executeUpdate(str);
						break; 
					case "DISPLAY":
						System.out.println("Enter the name of table which you want to display :");
						String t_name=sc.next();
						str="SELECT * FROM "+t_name;
						ResultSet rs=stmt.executeQuery(str);
						System.out.println(str);
						while(rs.next())
						{
							String n=rs.getString(1);
							int roll=rs.getInt(2);
							String div=rs.getString(3);
							System.out.println(n+"	"+roll+"	"+div);
						}
						break;
					case "EXIT":
						return;
					default:
						System.out.println("Invalid Input!!!");
						
				}
			}while(choice!="EXIT");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
}
