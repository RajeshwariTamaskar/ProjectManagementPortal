package one;

import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
* Glue between the view (DemoGUI) and the model (DemoTableModel).
* No database specific code. Aware of both the TableModel and the graphical user interface
* @author rgrover
*/

public class MediaTableController implements ListSelectionListener, TableModelListener{
	private MediaTableModel tableModel;
	private AddPanel addGUI;
	private EditFormPanel editGUI;
	private ProjectDetailsPanel detailsGUI;
	private int selectedRow = 0;

	public MediaTableController(AddPanel addGUI) {
		this.addGUI = addGUI;
                // create the tableModel 
		tableModel = new MediaTableModel();
		tableModel.addTableModelListener(this);
	}
	
	public MediaTableController(EditFormPanel editGUI) {
		this.editGUI = editGUI;
                // create the tableModel 
		tableModel = new MediaTableModel();
		tableModel.addTableModelListener(this);
	}
	
	public MediaTableController(ProjectDetailsPanel detailsGUI) {
		this.detailsGUI = detailsGUI;
                // create the tableModel 
		tableModel = new MediaTableModel();
		tableModel.addTableModelListener(this);
	}
	
	public MediaTableController(MediaTableModel tableModel, AddPanel addGUI) {
		/*create the tableModel using the data */
		this.addGUI = addGUI; 
		this.tableModel = tableModel; 
                tableModel.addTableModelListener(this);
	}
	
	public MediaTableController(MediaTableModel tableModel, ProjectDetailsPanel detailsGUI) {
		/*create the tableModel using the data */
		this.detailsGUI = detailsGUI; 
		this.tableModel = tableModel; 
                tableModel.addTableModelListener(this);
	}
	
	public MediaTableController(MediaTableModel tableModel, EditFormPanel editGUI) {
		/*create the tableModel using the data */
		this.editGUI = editGUI; 
		this.tableModel = tableModel; 
                tableModel.addTableModelListener(this);
	}


	// Getter methods
	public TableModel getTableModel() {
		tableModel.update();
		return this.tableModel;
	}
	
	public Media getMedia(int mediaID) {
		return tableModel.getMedia(mediaID);
	}
	
	public void doPersist(Media m) {
		tableModel.doPersist(m);
	}
	
	public void doRemove(Media m) {
		tableModel.doRemove(m);
	}

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
		if((String) tableModel.getValueAt(firstIndex, 0) != null) {
			selectedRow = Integer.parseInt((String) tableModel.getValueAt(firstIndex, 0));
		}
	}

	public void tableChanged(TableModelEvent e)
	{
	   try {
	    	// get the index of the inserted row
	    	int firstIndex =  e.getFirstRow();
	    	// create a new table model with the new data
	        tableModel = new MediaTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	//addGUI.updateTable();
	   	} catch(Exception exp) {
			exp.getMessage();
			exp.printStackTrace();
	   	}
	}
	
	public List<Media> getMediaJtable(int projectSelectedID) {
		return tableModel.getMediaJtable(projectSelectedID);
	}
	
	public int rowNumberSelected() {
		return selectedRow;
	}
	
// 	pass from gui to table model both the remove and add functions
	public int addRow(String[] array) {
		return tableModel.addRow(array);
	}

	public void removeRow(int id){
		tableModel.removeRow(id);
//		gui.updateTable();
	}
}
