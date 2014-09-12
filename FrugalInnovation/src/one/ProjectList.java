package one;

import javax.persistence.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PROJECT")
public class ProjectList implements Serializable {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Column(name = "project_id")
		private int projectID;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "status")
		private String status;
		
		@Column(name = "start_date")
		private java.sql.Date startDate;
		
		@Column(name = "end_date")
		private java.sql.Date endDate;
		
		@Column(name = "category")
		private String category;
		
		@Column(name = "result")
		private String result;
		
		@ManyToMany(cascade=CascadeType.PERSIST)
		@JoinTable(name="PROJECTPEOPLE", 
		   joinColumns = @JoinColumn(name="project_id"),
		   inverseJoinColumns = @JoinColumn(name="people_id"))
		private Set<PeopleList> peopleSet;
		
		@ManyToMany(cascade=CascadeType.PERSIST)
		@JoinTable(name="PROJECTMEDIA", 
		   joinColumns = @JoinColumn(name="project_id"),
		   inverseJoinColumns = @JoinColumn(name="media_id"))
		private Set<Media> mediaSet;
		
		public int getProjectID() {
			return projectID;
		}
		
		public void setProjectID(int p) {
			this.projectID = p;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String n) {
			this.name = n;
		}
		
		public String getStatus() {
			if (status.equals("0"))
				return "Active";
			else if(status.equals("1"))
				return "Complete";
			else if(status.equals("2"))
				return "Suspended";
			else
				return "Aborted";
		}
		
		public void setStatus(String s) {
			this.status = s;
		}
		
		public Date getStartDate() {
			return startDate;
		}
		
		public void setStartDate(java.sql.Date s) {
			this.startDate = s;
		}
		
		public Date getEndDate() {
			return endDate;
		}
		
		public void setEndDate(java.sql.Date e) {
			this.endDate = e;
		}
		
		public String getCategory() {
				return category;
		}
		
		public void setCategory(String c) {
			this.category = c;
		}
		
		public String getResult(){
			return result;
		}
		
		public String setResult(String r){
			return this.result = r;
		}
		
		// return number of columns in the table
		public int getNumberOfColumns() {
			return 6;
		}
		
		// return the data in column i
		 public String getColumnData(int i) throws Exception {
			 DateFormat df = new SimpleDateFormat("MM-dd-yy");
			   if (i == 0)
			   		return Integer.toString(getProjectID());
			   else if(i == 1)
				   return getName();
			   else if (i == 2)
				   return getStatus();
			   else if (i == 3)
				   return df.format(getStartDate());
			   else if (i == 4)
				   return df.format(getEndDate());
			   else if (i == 5)
				   return getCategory();
			   else
				   throw new Exception("Error: invalid column index in courselist table");    
		   }
		   
		   // return the name of column i
		   public String getColumnName(int i) throws Exception {
			   String colName = null;
			   if (i == 0)
				   colName = "Project ID";
			   else if (i == 1) 
				   colName = "Name";
			   else if (i == 2)
				   colName = "Status";
			   else if (i == 3)
				   colName = "Start Date";
			   else if (i == 4)
				   colName = "End Date";
			   else if (i == 5)
				   colName = "Category";
			   else 
				   throw new Exception("Access to invalid column number in courselist table");
			   
			   return colName;
		   }
		   
		   // set data column i to value
		   public void setColumnData(int i, Object value) throws Exception {
			   if (i == 0) 
				   projectID = Integer.parseInt((String) value);
			   else if (i == 1) 
				   name = (String) value;
			   else if (i == 2) 
				   status =  (String) value;
			   else if (i == 3)
				   startDate = (java.sql.Date) value;
			   else if (i == 4)
				  endDate = (java.sql.Date) value;
			   else if (i == 5)
					  category = (String) value;
			   else if (i == 6)
					  result = (String) value;
			   else
				   throw new Exception("Error: invalid column index in courselist table");    
		   }
		
		
		@Override
		public String toString() {
			return "ProjectList [ID =" + projectID + ","
			+"Name =" + name + ", "
			+ " Status =" + status + ","
			+ " Start Date =" + startDate + ","
			+ " End Date =" + endDate + ","
			+ " Category =" + category + ","
			+ " Result =" + result + ","
			+ "]";
		}
		
		public void setRecordsPeople(Set<PeopleList> rec) {
			   peopleSet = rec;
		   }
		
		public Set<PeopleList> getRecordsPeople() {
			return peopleSet;
		}
		
		public void setRecordsMedia(Set<Media> rec) {
			   mediaSet = rec;
		   }
		
		public Set<Media> getRecordsMedia() {
			return mediaSet;
		}

		public String getStatusNum() {
			return status;
		}
		


}
