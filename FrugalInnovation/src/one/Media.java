package one;

import javax.persistence.*;

import java.io.*;
import java.util.Set;


@Entity(name="MEDIA")
public class Media implements Serializable{
		
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Column(name="media_id")
		private int media_id;
		
		@Column(name="type")		
		private String type;
		
		@Column(name="file_path")
		private String file_path;
		
		@ManyToMany(cascade=CascadeType.PERSIST, mappedBy="mediaSet")
		private Set<ProjectList> projectSet;		
		
		int getMedia_id() {
		return media_id;
		}
				
		public void setMedia_id(int media_id) {
		this.media_id = media_id;
		}
		
		public String getType() {
		return type;
		}
		
		public void setType(String type) {
		this.type = type;
		}
		
		public String getFile_path() {
		return file_path;
		}
		
		
		public void setFile_path(String file_path) {
		this.file_path = file_path;
		}
		
		public int getNumberOfColumns() {
			return 3;
		}
		
		public Set<ProjectList> getRecords() {
			return projectSet;
		}
		
		public void setRecords(Set<ProjectList> rec) {
			   projectSet = rec;
		}
		// return the data in column i
		 public String getColumnData(int i) throws Exception {
			   if(i == 0)
				   return Integer.toString(getMedia_id());
			   else if (i == 1)
				   return getType();
			   else if (i == 2)
				   return getFile_path();
			   else
				   throw new Exception("Error: invalid column index in media table");    
		   }
		   
		   // return the name of column i
		   public String getColumnName(int i) throws Exception {
			   String colName = null;
			   if (i == 0) 
				   colName = "Media ID";
			   else if (i == 1)
				   colName = "Name";
			   else if (i == 2)
				   colName = "File Path";
			   else 
				   throw new Exception("Access to invalid column number in media table");
			   
			   return colName;
		   }
		   
		   // set data column i to value
		   public void setColumnData(int i, Object value) throws Exception {
			   if (i == 0) 
				   media_id = Integer.parseInt((String) value);
			   else if (i == 1) 
				   type = (String) value;
			   else if (i == 2) 
				   file_path =  (String) value;
			   else
				   throw new Exception("Error: invalid column index in media table");    
		   }	
		
		@Override
		public String toString() {
				
			String toReturn = "Media [ID = " + media_id + ", File Path = " + file_path + ", Type = " + type;  
			return toReturn;
		}

}



