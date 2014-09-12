package one;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectListService {
	 private EntityManager manager;
//	 private Set <ProjectList> projectSet = new HashSet <ProjectList>();
	 /*Constructor with Entity manager*/
	 public ProjectListService(EntityManager manager) {
		 this.manager = manager;
	 }
	 
	/* Method to create a new project*/
     public ProjectList createProject(String projectName, String status, String startDateString, String endDateString, String category, String result) {
    	 ProjectList project = new ProjectList();
    	 if(startDateString != null) {
		    	 try {
		    		 Date startDateTemp = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH).parse(startDateString);
		    		 Date endDateTemp = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH).parse(endDateString);
		    		 java.sql.Date startDate = new java.sql.Date(startDateTemp.getTime());
		    		 java.sql.Date endDate = new java.sql.Date(endDateTemp.getTime());
		    		 project.setName(projectName);
		    		 project.setCategory(category);
		    		 project.setStatus(status);
		    		 project.setStartDate(startDate);
		    		 project.setEndDate(endDate);
		    		 project.setResult(result);
		//    		 projectSet.add(project);
		    		 Set <Media> mediaSet = project.getRecordsMedia();
		    		 project.setRecordsMedia(mediaSet);
		     	 }catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
    	 }    	 
    	manager.persist(project);
    	return project;
     }
     
     /* Method to read a record*/
     public ProjectList readProject(int projectID) {
    	 ProjectList project = manager.find(ProjectList.class, projectID);
    	 return project;   	 
     }
     
    /* Method to read all records*/
     public List<ProjectList> readAll() {
    	 TypedQuery<ProjectList> query = manager.createQuery("SELECT e FROM PROJECT e", ProjectList.class);
    	 List<ProjectList> result =  query.getResultList();

    	 return result;   	 
     }
     
     /* Method to update a record */
     public ProjectList updateProject(String projectID, String projectName, String status, String startDateString, String endDateString, String category, String result) {
    	 int id = Integer.parseInt(projectID);
    	 ProjectList project = manager.find(ProjectList.class, id);
    	 if (project != null) {
    		 try {
    			 	Date startDateTemp = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH).parse(startDateString);
    			 	Date endDateTemp = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH).parse(endDateString);
    			 	java.sql.Date startDate = new java.sql.Date(startDateTemp.getTime());
    			 	java.sql.Date endDate = new java.sql.Date(endDateTemp.getTime());
    	    		project.setName(projectName);
    	        	project.setCategory(category);
    	        	project.setStatus(status);
    	        	project.setStartDate(startDate);
    	        	project.setEndDate(endDate);
    	        	project.setResult(result);
    	     	    manager.persist(project);
    	     	 }catch (java.text.ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} 
    	 }
    	 return project;
     }
     
    /* Method to delete a record */
     public void deleteProject(int projectID) {
    	 ProjectList project = manager.find(ProjectList.class, projectID);
     	 if (project != null) {
     		 manager.remove(project);
     	 }
     }
     


}
