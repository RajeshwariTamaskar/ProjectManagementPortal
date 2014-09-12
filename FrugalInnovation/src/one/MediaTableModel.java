package one;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.*;
import javax.persistence.*;
import java.io.File;
import java.util.*;

/**
*
* @author rgrover
*/
public class MediaTableModel extends AbstractTableModel {

	  List<Media> mediaResultList;   // stores the model data in a List collection of type Media
	  private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA
	  private static EntityManager manager;				// JPA
	  private Media media;			    // represents the entity demoList
	  private MediaService mediaService;
	   // This field contains additional information about the results
	  int numcols, numrows;           // number of rows and columns
	 
        MediaTableModel() {
             factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
             manager = factory.createEntityManager();
             media = new Media();
             mediaService = new MediaService(manager);
             // read all the records from media
             mediaResultList = mediaService.readAll();
             // update the number of rows and columns in the model
             numrows = mediaResultList.size();
             numcols = media.getNumberOfColumns();
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
		  return mediaResultList.get(row).getColumnData(col);
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
			return media.getColumnName(col);
		    } catch (Exception err) {
	            return err.toString();
	       }
	 }

	 // update the data in the given row and column to aValue
	 public void setValueAt(Object aValue, int row, int col) {
		//data[rowIndex][columnIndex] = (String) aValue;
		try {
                    Media element = mediaResultList.get(row);
                    element.setColumnData(col, aValue);
                    fireTableCellUpdated(row, col);
		} catch(Exception err) {
			err.toString();
		}
	 }
	 
	 public List<Media> getMediaJtable(int projectSelectedID) {
		 	EntityTransaction userTransaction = manager.getTransaction();
			userTransaction.begin();
			Query query = manager.createQuery("SELECT m FROM MEDIA m INNER JOIN m.projectSet project WHERE project.projectID = :projectID");
			query.setParameter("projectID", projectSelectedID);
			List<Media> result = query.getResultList();
			userTransaction.commit();
			return result;		 
	 }
	 
	 public void doPersist(Media m) {
		 EntityTransaction userTransaction = manager.getTransaction();
		 userTransaction.begin();
		 manager.merge(m);
		 userTransaction.commit();
	 }
	 
	 public void doRemove(Media m) {
		 EntityTransaction userTransaction = manager.getTransaction();
		 userTransaction.begin();
		 manager.remove(m);
		 userTransaction.commit();
	 }
	 
	 public Media getMedia(int id) {
		 return mediaService.readMedia(id);
	 }

	 public List<Media> getList() {
		 return mediaResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }

	 // create a new table model using the existing data in list
	 public MediaTableModel(List<Media> list, EntityManager em)  {
                mediaResultList = list;
                numrows = mediaResultList.size();
                media = new Media();
	   	numcols = media.getNumberOfColumns();
		manager = em;
		mediaService = new MediaService(manager);
	 }
	 
	 public MediaTableModel(List<Media> list)  {
                mediaResultList = list;
                numrows = mediaResultList.size();
                media = new Media();
                numcols = media.getNumberOfColumns();
                //manager = em;
                mediaService = new MediaService(manager);
	  }

	 // In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	 // The argument to this method is an array containing the data in the textfields of the new row.
	 public int addRow(Object[] array) {
		//data[rowIndex][columnIndex] = (String) aValue;
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();
		userTransaction.begin();
		Media newRecord = mediaService.createMedia((String) array[0], (String) array[1]);
		userTransaction.commit();
		// set the current row to rowIndex
                mediaResultList.add(newRecord);
                int row = mediaResultList.size();
                int col = 0;
                // update the data in the model to the entries in array
                for (Object data : array) {
                         setValueAt((String) data, row-1, col++);
                }
                numrows++;
                return newRecord.getMedia_id();
          }

	 public void removeRow(int id){
                // create the remove query
                EntityTransaction tx = manager.getTransaction();
                tx.begin();
                mediaService.deleteMedia(id);
                tx.commit();
	 }

	 public void update(){
                // read all the records from courselist
                mediaResultList = mediaService.readAll();
                // update the number of rows and columns in the model
                numrows = mediaResultList.size();
                numcols = media.getNumberOfColumns();
	 }
}
