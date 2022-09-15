import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

class  Employee {
    private String employeeId;
    private String employeeName;
    private String employeeEmailId;
    private String employeeDob;
    private String employeeDoj;
    
    Employee(String employeeId,String employeeName,String employeeEmailId,String employeeDob,String employeeDoj)
    {
  	  this.employeeId=employeeId;
  	  this.employeeName=employeeName;
  	  this.employeeEmailId=employeeEmailId;
  	  this.employeeDob=employeeDob;
  	  this.employeeDoj=employeeDoj;
    }
	public String getEmployeeId() {
		return employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public String getEmployeeEmailId() {
		return employeeEmailId;
	}
	public String getEmployeeDob() {
		return employeeDob;
	}
	public String getEmployeeDoj() {
		return employeeDoj;
	}
	public String toString()
	{
		return employeeId+" "+employeeName+" "+employeeEmailId+" "+employeeDob+" "+employeeDoj;
	}
	public String validateId()
	{
		return employeeId;
	}
	public String validateName()
	{
		return employeeName;
	}
	public String validateEmailId()
	{
		return employeeEmailId;
	}
	public String validateDob()
	{
		return employeeDob;
	}
	public String validateDoj()
	{
		return employeeDoj;
	}
}
public class EmployeeDetails extends Employee{

	EmployeeDetails(String employeeId, String employeeName, String employeeEmailId, String employeeDob,
			String employeeDoj) {
		super(employeeId, employeeName, employeeEmailId, employeeDob, employeeDoj);
	}

	Scanner sc=new Scanner(System.in);
	Scanner scan =new Scanner(System.in);
public String validateId()
{
	System.out.println("Enter the Employee id");
	String employeeId;
	employeeId=scan.nextLine();
	if((employeeId.matches("^[A][C][E][0-9]{4}")))
	{
		return employeeId;
	}
	else
	{
		System.out.println("Employee ID should begin with ACE follwed by Four Digit");	
	}
	return validateId();
}
public String validateName()                   
{
	System.out.println("Enter the Employee Name");
	String employeeName=scan.nextLine();
	if((employeeName.matches("^[a-zA-Z]*$")))
	{
		return employeeName;
	}
	else
	{
		System.out.println("No special charaters or numeric allowed Employee Name should be in uppercase or lowercase letter");
	}
	return validateName();
}
public String validateEmailId()
{
	System.out.println("Enter the Employee Email id");
	String employeeEmailId=scan.nextLine();
	Pattern pattern=Pattern.compile("^[a-zA-z0-9]+@+[a-zA-Z]+(.com)$");
	Matcher match=pattern.matcher(employeeEmailId);
	boolean valid=match.find();
	if((valid))
	{
		return employeeEmailId;
	}
	else
	{
		System.out.println("Please enter valid mailid like mail@domain.com Domain name must contain only alphabets and no special charaters is allowed");
	}
	return validateEmailId();
}
public String validateDob()
{
    System.out.println("Enter the Employee DOB");
	String employeeDob=scan.nextLine();
	SimpleDateFormat dob=new SimpleDateFormat("dd/MM/yyyy");
    dob.setLenient(false);
    try
    {
    	Date valid=dob.parse(employeeDob);
    	long calculate=System.currentTimeMillis()-valid.getTime();
    	long age=(long)((long)calculate/(1000.0*60*60*24*365));
    	if(age>=18&&age<=60)
    		return employeeDob;
    	else
    	{
    		System.out.println("Please enter the valid date of birth with dd/mm/yyyy format and should be between 18 to 60");
    	}
    }
    catch( ParseException e)
    {
    	System.out.println("Please enter the valid date of birth with dd/mm/yyyy format and should be between 18 to 60");
    }
    return validateDob();
}
public String validateDoj()
{
	System.out.println("Enter the Employee DOJ");
	String employeeDoj=scan.nextLine();
	SimpleDateFormat doj=new SimpleDateFormat("dd/MM/yyyy");
    doj.setLenient(false);
    try
    {
    	Date valid=doj.parse(employeeDoj);
    	long calculate=System.currentTimeMillis()-valid.getTime();
    	if(calculate>=0)
    		return employeeDoj;
    	else
    	{
    		System.out.println("Please enter the valid date of joining with dd/mm/yyyy format and should not be future date");
	    	validateDoj();
    	}
    	
    }
    catch( ParseException e)
    {
    	System.out.println("Please enter the valid date of joining with dd/mm/yyyy format and should not be future date");
    	validateDoj();
    }
    return validateDoj();
}

	public void decision()
	{
	ArrayList<Employee> list=new ArrayList<Employee>();
	int choice;
		do { 
		System.out.println("1.Add new employee details\n2.Update employee details\n3.Delete employee details\n4.Display employee details\n5.Exit");
		System.out.println("Enter your choice:");
		choice=sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			String employeeId=validateId();
			String employeeName=validateName();
			String employeeEmailId=validateEmailId();
			String employeeDob=validateDob();
			String employeeDoj=validateDoj();
			try {
			Connection connect =DriverManager.getConnection("jdbc:mysql://localhost:3306/emp","root","Aspire@123");

				PreparedStatement prepared=connect.prepareStatement("INSERT INTO employee(Id,Name,EmailId,Dob,Doj) VALUES(?,?,?,?,?)");
				prepared.setString(1, employeeId);
				prepared.setString(2, employeeName);
				prepared.setString(3, employeeEmailId);
				prepared.setString(4, employeeDob);
				prepared.setString(5, employeeDoj);
				prepared.executeUpdate();
				connect.close();
				}
				catch(Exception exception)
				{
					System.out.println(exception);
				}
			list.add(new Employee(employeeId,employeeName,employeeEmailId,employeeDob,employeeDoj));
			break;
		}
		case 2:
		{
			System.out.println("Update employee details");
			int flag=0;
			String empid=scan.next();
			ListIterator<Employee> line=list.listIterator();
			while(line.hasNext())
			{
				Employee e=line.next();
					if(e.getEmployeeId().equals(empid))
					{
						System.out.println("Add new employee details");
						String employeeId=validateId();
						String employeeName=validateName();
						String employeeEmailId=validateEmailId();
						String employeeDob=validateDob();
						String employeeDoj=validateDoj();
						try {
							Connection connetion =DriverManager.getConnection("jdbc:mysql://localhost:3306/emp","root","Aspire@123");
							PreparedStatement preparedStatements=connetion.prepareStatement("UPDATE employee SET Id=?,Name=?,EmailId=?,Dob=?,Doj=? WHERE=?");
							preparedStatements.setString(1, employeeId);
							preparedStatements.setString(2, employeeName);
							preparedStatements.setString(3, employeeEmailId);
							preparedStatements.setString(4, employeeDob);
							preparedStatements.setString(5, employeeDoj);
							preparedStatements.setString(6, empid);
							preparedStatements.executeUpdate();
							connetion.close();
							}
							catch(Exception e1)
							{
								System.out.println(e1);
							}
						line.set(new Employee(employeeId,employeeName,employeeEmailId,employeeDob,employeeDoj));
						flag=0;
						break;
					}
				
					else
						flag=1;
				}
				 if(flag==1)
					 System.out.println("Record not found");
				 else
					 System.out.println("Record updated sucessfully");
			break;
		}
		case 3:
		{
			int flag=0;
			System.out.println("Delete employee details");
			System.out.println("Enter the Employee Id to delete:");
			String empid=scan.next();
			Iterator<Employee> iterate=list.iterator();
			while(iterate.hasNext())
			{
				    Employee e=iterate.next();
					if(e.getEmployeeId().equals(empid))
					{
						iterate.remove();
						flag=0;
						break;
					}
				
					else
						flag=1;
				}
				 if(flag==1)
					 System.out.println("Record not found");
				 else
					 System.out.println("Record delete sucessfully");
			break;
		}
		case 4:
		{
			System.out.println("Display employee details");
			System.out.println("Enter the Employee Id to display:");
			String empid=scan.next();
			String url1="jdbc:mysql://127.0.0.1:3306/emp";
			String username1="root";
			String password1="Aspire@123";
			
			try {
				Connection connection= DriverManager.getConnection(url1,username1,password1);
				Statement statement=connection.createStatement();
				String sql1="SELECT * FROM employee WHERE ID='"+empid+"'";
				ResultSet rs=statement.executeQuery(sql1);
				if(rs.next()) {
					
						Thread.sleep(500);
						System.out.println("Employee ID:"+rs.getString(1));
						Thread.sleep(500);
						System.out.println("Employee Name:"+rs.getString(2));
						Thread.sleep(500);
						System.out.println("Employee Email:"+rs.getString(3));
						Thread.sleep(500);
						System.out.println("Employee DOB:"+rs.getString(4));
						Thread.sleep(500);
						System.out.println("Employee DOJ:"+rs.getString(5));
						Thread.sleep(500);
						System.out.println("Employee Number:"+rs.getString(6));
						System.out.println("--------------------------------------------------------------");
						Thread.sleep(500);
					
				}else {
					System.out.println(">>Record not found...");
					Thread.sleep(500);
					System.out.println("--------------------------------------------------------------");
					Thread.sleep(500);
				}
				
				
			} catch (Exception e) {
				System.out.println("Please check the employee ID for displaying the details.");
			}				
			break;
		}
		default:
		{
			System.out.println("please Enter the given choice");
			break;
		}
		}
		}
		while(choice!=5);
		}
}

