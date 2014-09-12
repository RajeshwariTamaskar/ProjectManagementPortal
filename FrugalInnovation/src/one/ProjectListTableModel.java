package one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import javax.swing.text.DateFormatter;

public class ProjectListTableModel extends AbstractTableModel {
	  List<ProjectList> projectListResultList;   // stores the model data in a List collection of type ProjectList
	  private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
	  private EntityManager manager;				// JPA 
	  private ProjectList projectlist;			    // represents the entity projectlist
	  private ProjectListService projectlistService;
	  // This field contains additional information about the results   
	  int numcols, numrows;           // number of rows and columns
	  ProjectList newRecord;  

        /*Constructors*/  
	 ProjectListTableModel() {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                manager = factory.createEntityManager();
                projectlist = new ProjectList();
                projectlistService = new ProjectListService(manager);	    
                // read all the records from projectlist
                projectListResultList = projectlistService.readAll();	    
                // update the number of rows and columns in the model
                numrows = projectListResultList.size();
                numcols = projectlist.getNumberOfColumns();
         }
	
	 ProjectListTableModel(List<ProjectList> projectList) {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                manager = factory.createEntityManager();
                projectlist = new ProjectList();
                projectlistService = new ProjectListService(manager);	    
                // read all the records from projectlist
                projectListResultList = projectList;	    
                // update the number of rows and columns in the model
                numrows = projectListResultList.size();
                numcols = projectlist.getNumberOfColumns();
	 } 

	 // returns a count of the number of rows
	 public int getRowCount() {
		return numrows;
	 }
	
	 public void doPersist(ProjectList j) {
		 EntityTransaction userTransaction = manager.getTransaction();
		 userTransaction.begin();
		 manager.merge(j);
		 userTransaction.commit();
	 }
	 
	 public void doRemove(ProjectList j) {
		 EntityTransaction userTransaction = manager.getTransaction();
		 userTransaction.begin();
		 manager.remove(j);
		 userTransaction.commit();
	 }
	 /*Query execution*/
	 public List<ProjectList> searchQueryAdmin(java.sql.Date startDate, java.sql.Date endDate, 
                String nameProject, String categoryProject, String statusField) {
                EntityTransaction transaction = manager.getTransaction();		
                transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p WHERE p.name LIKE :nameProject AND p.status = :statusField "
                                + "AND p.category LIKE :categoryProject AND p.startDate >= :startDate AND p.endDate <= :endDate");
                query.setParameter("nameProject", "%" + nameProject + "%");
                query.setParameter("statusField", statusField);
                query.setParameter("startDate", startDate);
                query.setParameter("endDate", endDate);
                query.setParameter("categoryProject", "%" + categoryProject + "%");
                List<ProjectList> result = query.getResultList();
                transaction.commit();
                return result;
	}
         
         public List<ProjectList> searchQueryViewerDSP(java.sql.Date startDate, java.sql.Date endDate, 
		String nameProject, String categoryProject, String statusField, String personName) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p INNER JOIN p.peopleSet q INNER JOIN PEOPLE r "
						+ "WHERE p.name LIKE :nameProject "
						+ "AND p.status = :statusField "
						+ "AND p.category LIKE :categoryProject "
						+ "AND (p.startDate >= :startDate AND p.endDate <= :endDate) "
                                                + "AND (q.people_id = r.people_id "
						+ "AND r.first_name LIKE :firstName) ");
                query.setParameter("nameProject", "%" + nameProject + "%");
                query.setParameter("statusField", statusField);
                query.setParameter("startDate", startDate);
                query.setParameter("endDate", endDate);
                query.setParameter("firstName", "%" +personName+ "%");
                query.setParameter("categoryProject", "%" + categoryProject + "%");
                List<ProjectList> result = query.getResultList();
		transaction.commit();
                return result;
         } 
         
         public List<ProjectList> searchQueryViewerS(String nameProject, String categoryProject, String statusField) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p "
						+ "WHERE p.name LIKE :nameProject "
						+ "AND p.status = :statusField "
						+ "AND p.category LIKE :categoryProject ");
                query.setParameter("nameProject", "%" + nameProject + "%");
                query.setParameter("statusField", statusField);
                query.setParameter("categoryProject", "%" + categoryProject + "%");
                List<ProjectList> result = query.getResultList();
		transaction.commit();
                return result;
         }
         
         public List<ProjectList> searchQueryViewerD(java.sql.Date startDate, java.sql.Date endDate, 
		String nameProject, String categoryProject) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p WHERE p.name LIKE :nameProject "
						+ "AND p.category LIKE :categoryProject AND (p.startDate >= :startDate AND p.endDate <= :endDate) ");
				query.setParameter("nameProject", "%" + nameProject + "%");
				query.setParameter("startDate", startDate);
				query.setParameter("endDate", endDate);
				query.setParameter("categoryProject", "%" + categoryProject + "%");
                List<ProjectList> result = query.getResultList();
		transaction.commit();
                return result;
         }
         
         public List<ProjectList> searchQueryViewerDS(java.sql.Date startDate, java.sql.Date endDate, 
			String nameProject, String categoryProject, String statusField) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p WHERE p.name LIKE :nameProject "
						+ "AND p.category LIKE :categoryProject "
                                                + "AND (p.startDate >= :endDate AND p.endDate <= :startDate) "
                                                + "AND p.status = :statusField ");
                query.setParameter("nameProject", "%" + nameProject + "%");               
                query.setParameter("startDate", startDate, TemporalType.DATE);
                query.setParameter("endDate", endDate, TemporalType.DATE);
                query.setParameter("categoryProject", "%" + categoryProject + "%");
                query.setParameter("statusField", statusField);
                
                List<ProjectList> result = query.getResultList();
                transaction.commit();
                return result;              
         }
         
         public List<ProjectList> searchQueryViewerDP(java.sql.Date startDate, java.sql.Date endDate, 
			String nameProject, String categoryProject, String personName) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p INNER JOIN p.peopleSet q INNER JOIN PEOPLE r "
						+ "WHERE p.name LIKE :nameProject "
						+ "AND p.category LIKE :categoryProject "
						+ "AND (p.startDate <= :startDate AND p.endDate >= :endDate) "
                                                + "AND q.people_id = r.people_id "
						+ "AND r.first_name LIKE :firstName");
                query.setParameter("nameProject", "%" + nameProject + "%");
                query.setParameter("startDate", startDate);
                query.setParameter("endDate", endDate);
                query.setParameter("firstName", "%" +personName+ "%");
                query.setParameter("categoryProject", "%" + categoryProject + "%");
                List<ProjectList> result = query.getResultList();
		transaction.commit();
                return result;  
         } 
         
         public List<ProjectList> searchQueryViewerSP(String nameProject, String categoryProject, String statusField, String personName) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p INNER JOIN p.peopleSet q INNER JOIN PEOPLE r "
						+ "WHERE p.name LIKE :nameProject "
						+ "AND p.status = :statusField "
						+ "AND p.category LIKE :categoryProject "
                                                + "AND q.people_id = r.people_id "
						+ "AND r.first_name LIKE :firstName");
                query.setParameter("nameProject", "%" + nameProject + "%");
                query.setParameter("statusField", statusField);
                query.setParameter("firstName", "%" +personName+ "%");
                query.setParameter("categoryProject", "%" + categoryProject + "%");
                List<ProjectList> result = query.getResultList();
		transaction.commit();
                return result; 
         } 
         
         public List<ProjectList> searchQueryViewerP(String nameProject, String categoryProject, String personName) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p INNER JOIN p.peopleSet q INNER JOIN PEOPLE r "
						+ "WHERE p.name LIKE :nameProject "
						+ "AND p.category LIKE :categoryProject "
                                                + "AND q.people_id = r.people_id "
						+ "AND r.first_name LIKE :firstName");
                query.setParameter("nameProject", "%" + nameProject + "%");
                query.setParameter("firstName", "%" +personName+ "%");
                query.setParameter("categoryProject", "%" + categoryProject + "%");
                List<ProjectList> result = query.getResultList();
		transaction.commit();
                return result;
         } 
         
         public List<ProjectList> searchQueryViewer(String nameProject, String categoryProject) {
                EntityTransaction transaction = manager.getTransaction();		
		transaction.begin();
                Query query = manager.createQuery("SELECT p FROM PROJECT p WHERE p.name LIKE :nameProject AND p.category LIKE :categoryProject");
                query.setParameter("nameProject", "%" + nameProject + "%");				
                query.setParameter("categoryProject", "%" + categoryProject + "%");				
                List<ProjectList> result = query.getResultList();
		transaction.commit();
                return result;
         }       
	 
	 // returns a count of the number of columns
	 public int getColumnCount() {
		return numcols;
	 }
	
	 // returns the data at the given row and column number
	 public Object getValueAt(int row, int col) {
		try {
		  return projectListResultList.get(row).getColumnData(col);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	 }
	
	 // table cells are not editable
	 public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	 }
	
	 public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	 }
	
	 // returns the name of the column
	 public String getColumnName(int col) {
		   try {
			return projectlist.getColumnName(col);
		   } catch (Exception err) {
	             return err.toString();
	       }             
	 }
	
	 // update the data in the given row and column to aValue
	 public void setValueAt(Object aValue, int row, int col) {
		//data[rowIndex][columnIndex] = (String) aValue;
		try {
                    ProjectList element = projectListResultList.get(row);
                    element.setColumnData(col, aValue); 
                    fireTableCellUpdated(row, col);
		} catch(Exception err) {
			err.toString();
		}	
	 }
	
	 public List<ProjectList> getList() {
		 return projectListResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }

	 // create a new table model using the existing data in list
	 public ProjectListTableModel(List<ProjectList> list, EntityManager em)  {
                projectListResultList = list;
                numrows = projectListResultList.size();
                projectlist = new ProjectList();
	   	numcols = projectlist.getNumberOfColumns();     
		manager = em;  
		projectlistService = new ProjectListService(manager);
	 }
         
          	 
	 // In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	 // The argument to this method is an array containing the data in the textfields of the new row.
	 public int addRow(Object[] array) {
                // add row to database
                EntityTransaction userTransaction = manager.getTransaction();  
                userTransaction.begin();
                newRecord = projectlistService.createProject((String) array[0], (String) array[1],(String) array[2], (String) array[3], (String) array[4], (String) array[5]);
                userTransaction.commit();
                // set the current row to rowIndex
                projectListResultList.add(newRecord);
                int row = projectListResultList.size();  
                int col = 0;
                // update the data in the model to the entries in array
                for (Object data : array) {
                         setValueAt((String) data, row-1, col++);
                }       
                numrows++;
                return newRecord.getProjectID();
	  }	 
	  
	 public void updateRow(Object[] array) {
                // add row to database
                EntityTransaction userTransaction = manager.getTransaction();  
                userTransaction.begin();
                projectlistService.updateProject((String)array[0], (String) array[1],(String) array[2], (String) array[3], (String) array[4], (String) array[5], (String) array[6]);
                userTransaction.commit();			
        }
	 
	 
        public void removeRow(int id){
                // create the remove query
                EntityTransaction tx = manager.getTransaction();
                tx.begin();
                projectlistService.deleteProject(id);
                tx.commit();
                for (Iterator<ProjectList> iter = projectListResultList.listIterator(); iter.hasNext();) {
                        int projectID = iter.next().getProjectID();
                        if(projectID == id) {
                                iter.remove();
                        }
                }
        }
		 
        public ProjectList getProject(int id) {
                return projectlistService.readProject(id);
        }
	
        public void update(){
                // update the number of rows and columns in the model
                numrows = projectListResultList.size();
                numcols = projectlist.getNumberOfColumns();
	 }
}


