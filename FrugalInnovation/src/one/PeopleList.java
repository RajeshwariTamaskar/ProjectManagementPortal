package one;

import javax.persistence.*;

import java.io.*;
import java.util.Set;

@Entity(name = "PEOPLE")
public class PeopleList implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "people_id")
	private int people_id;	
	
	
	@Column(name = "first_name")
	private String first_name;
	
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "company_name")
	private String company_name;
	
	
	@Column(name = "email_address")
	private String email_address;
	
	@ManyToMany(cascade=CascadeType.PERSIST, mappedBy="peopleSet")
	private Set<ProjectList> projectSet;
	
	public String getFirstName() {
		return first_name;
	}
	
	public void setFirstName(String name) {
		this.first_name = name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public void setLastName(String name) {
		this.last_name = name;
	}
	
	public String getCompanyName() {
		return company_name;
	}
	
	public void setCompanyName(String name) {
	this.company_name = name;
	}
	
	public String getEmailAddress() {
		return email_address;
	}
	
	public void setEmailAddress(String email) {
		this.email_address = email;
	}
	
	public int getPeopleID() {
		return people_id;
	}
	
//	public void setID(int num) {
//		this.people_id = num;
//	}
	
	public void setRecords(Set<ProjectList> rec) {
		   projectSet = rec;
	}
	
	public Set<ProjectList> getRecords() {
			return projectSet;
	}
	   
	
	// return number of columns in the table
	public int getNumberOfColumns() {
		return 5;
	}
	
	// return the data in column i
		 public String getColumnData(int i) throws Exception {
			   if(i == 0)
				   return Integer.toString(getPeopleID());
			   else if (i == 1)
				   return getFirstName();
			   else if (i == 2)
				   return getLastName();
			   else if (i == 3)
				   return getCompanyName();
			   else if (i == 4)
				   return getEmailAddress();
			   else
				   throw new Exception("Error: invalid column index in media table");    
		   }
		   
		   // return the name of column i
		   public String getColumnName(int i) throws Exception {
			   String colName = null;
			   if (i == 0) 
				   colName = "People ID";
			   else if (i == 1)
				   colName = "First Name";
			   else if (i == 2)
				   colName = "Last Name";
			   else if (i == 3)
				   colName = "Comapany Name";
			   else if (i == 4)
				   colName = "Email Address";
			   else 
				   throw new Exception("Access to invalid column number in media table");			   
			   return colName;
		   }
		   
		   // set data column i to value
		   public void setColumnData(int i, Object value) throws Exception {
			   if (i == 0) 
				   people_id = Integer.parseInt((String) value);
			   else if (i == 1) 
				   first_name = (String) value;
			   else if (i == 2) 
				   last_name =  (String) value;
			   else if (i == 3) 
				   company_name =  (String) value;
			   else if (i == 4) 
				   email_address =  (String) value;
			   else
				   throw new Exception("Error: invalid column index in media table");    
		   }	
	
	@Override
	public String toString() {
		return "PeopleList [First Name =" + first_name + ", "
		+ " Last Name =" + last_name + ","
		+ " Company Name =" + company_name + ","
		+ " Email Address =" + email_address + ","
		+ "]";
	}
}
