package one;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.*;
import javax.persistence.*;

import java.util.*;

/**
*
* @author rgrover
*/
public class PeopleTableModel extends AbstractTableModel {

	  List<PeopleList> peopleResultList;   // stores the model data in a List collection of type CourseList
	  private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA
	  private EntityManager manager;				// JPA
	  private PeopleList people;			    // represents the entity demoList
	  private PeopleListService peopleListService;
	   // This field contains additional information about the results
          int numcols, numrows;           // number of rows and columns

         /*Constructor*/ 
	 PeopleTableModel() {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                manager = factory.createEntityManager();
		people = new PeopleList();
		peopleListService = new PeopleListService(manager);
                // read all the records from courselist
                peopleResultList = peopleListService.readAll();
                // update the number of rows and columns in the model
                numrows = peopleResultList.size();
                numcols = people.getNumberOfColumns();
          }

	 // returns a count of the number of rows
	 public int getRowCount() {
		return numrows;
	 }

	 // returns a count of the number of columns
	 public int getColumnCount() {
		return numcols;
	 }

	 // returns the data at the given row and column number
	 public Object getValueAt(int row, int col) {
		try {
		  return peopleResultList.get(row).getColumnData(col);
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
		if(getValueAt(0, col).getClass() != null) {
			return getValueAt(0, col).getClass();
		}
		return null;
	 }

	 // returns the name of the column
	 public String getColumnName(int col) {
		   try {
				return people.getColumnName(col);
			} catch (Exception err) {
	             return err.toString();
	       }
	 }

	 // update the data in the given row and column to aValue
	 public void setValueAt(Object aValue, int row, int col) {
		//data[rowIndex][columnIndex] = (String) aValue;
		try {
                    PeopleList element = peopleResultList.get(row);
                    element.setColumnData(col, aValue);
                    fireTableCellUpdated(row, col);
		} catch(Exception err) {
			err.toString();
		}
	 }
	 /*Implements manager.remove in tableModel*/
	 public void doRemove(PeopleList p) {
		EntityTransaction userTransaction = manager.getTransaction();
		userTransaction.begin();
                manager.remove(p);
                userTransaction.commit();
	}
	/*Get ID of people*/ 	 
	public PeopleList getPeople(int id) {
		return peopleListService.readPeople(id);
	}

        public List<PeopleList> getList() {
                return peopleResultList;
        }

        public EntityManager getEntityManager() {
                return manager;
        }
	 
        public List<PeopleList> getPeopleJtable(int projectSelectedID) {
                EntityTransaction userTransaction = manager.getTransaction();
                userTransaction.begin();
                Query query = manager.createQuery("SELECT m FROM PEOPLE m INNER JOIN m.projectSet project WHERE project.projectID = :projectID");
                query.setParameter("projectID", projectSelectedID);
                List<PeopleList> result = query.getResultList();
                userTransaction.commit();
                return result;
        }

	 // create a new table model using the existing data in list
	 public PeopleTableModel(List<PeopleList> list, EntityManager em)  {
                peopleResultList = list;
                numrows = peopleResultList.size();
                people = new PeopleList();
	   	numcols = people.getNumberOfColumns();
		manager = em;
		peopleListService = new PeopleListService(manager);
	 }
	 
	 public PeopleTableModel(List<PeopleList> list)  {
                peopleResultList = list;
                numrows = peopleResultList.size();
                people = new PeopleList();
                numcols = people.getNumberOfColumns();
                peopleListService = new PeopleListService(manager);
	  }

	 // In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	 // The argument to this method is an array containing the data in the textfields of the new row.
	 public int addRow(Object[] array) {
	       // add row to database
		EntityTransaction userTransaction = manager.getTransaction();
		userTransaction.begin();
		PeopleList newRecord = peopleListService.createPerson((String) array[0], (String) array[1], (String) array[2], (String) array[3]);
		userTransaction.commit();
		// set the current row to rowIndex
                peopleResultList.add(newRecord);
                int row = peopleResultList.size();
                int col = 0;
                // update the data in the model to the entries in array
                for (Object data : array) {
                         setValueAt((String) data, row-1, col++);
                }
                numrows++;
		return newRecord.getPeopleID();
	 }

	 public void removeRow(String title){
		 // create the remove query
		 EntityTransaction tx = manager.getTransaction();
		 tx.begin();
		 peopleListService.deletePerson(title);
		 tx.commit();
	 }

	 public void update(){
                // read all the records from courselist
                peopleResultList = peopleListService.readAll();
                // update the number of rows and columns in the model
                numrows = peopleResultList.size();
                numcols = people.getNumberOfColumns();
	 }
}
