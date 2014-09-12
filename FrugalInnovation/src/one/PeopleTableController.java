package one;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
* Glue between the view and the model.
* No database specific code. Aware of both the TableModel and the graphical user interface
* @author rgrover
*/

public class PeopleTableController implements ListSelectionListener, TableModelListener{
	private PeopleTableModel tableModel;
	private AddPanel addGUI;
	private PeopleViewGUI viewGUI;
	private ProjectDetailsPanel detailsGUI;
	private EditFormPanel editGUI;
	private int selectedRow = 0;

	/*Constructors*/
        public PeopleTableController(AddPanel addGUI) {
		this.addGUI = addGUI;
         	tableModel = new PeopleTableModel();
		tableModel.addTableModelListener(this);
	}
	
	public PeopleTableController(PeopleViewGUI viewGUI) {
		this.viewGUI = viewGUI;
        	tableModel = new PeopleTableModel();
		tableModel.addTableModelListener(this);
	}
	
	public PeopleTableController(EditFormPanel editGUI) {
		this.editGUI = editGUI;
         	tableModel = new PeopleTableModel();
		tableModel.addTableModelListener(this);
	}
	
	public PeopleTableController(ProjectDetailsPanel detailsGUI) {
		this.detailsGUI = detailsGUI;
         	tableModel = new PeopleTableModel();
		tableModel.addTableModelListener(this);
	}
	
	public PeopleTableController(PeopleTableModel tableModel, AddPanel addGUI) {
		/*create the tableModel using the data */
		this.addGUI = addGUI; 
		this.tableModel = tableModel; 
                tableModel.addTableModelListener(this);
	}


	// Getter Method
	public TableModel getTableModel() {
		tableModel.update();
		return this.tableModel;
	}

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
                if(viewGUI != null) {
                    // read the data in each column using getValueAt and display it on corresponding textfield
                    viewGUI.setFirstNameTextField( (String) tableModel.getValueAt(firstIndex, 1));
                    viewGUI.setLastNameTextField( (String) tableModel.getValueAt(firstIndex, 2));
                    viewGUI.setCompanayNameTextField( (String) tableModel.getValueAt(firstIndex, 3));
                    viewGUI.setEmailAddressTextField( (String) tableModel.getValueAt(firstIndex, 4));
                }    
		if((String) tableModel.getValueAt(firstIndex, 0) != null) {
			selectedRow = Integer.parseInt((String) tableModel.getValueAt(firstIndex, 0));
		}
	}
	
	public int rowNumberSelected() {
		return selectedRow;
	}
		
	public void tableChanged(TableModelEvent e)
	{
	   try {
	    	// get the index of the inserted row
	    	int firstIndex =  e.getFirstRow();
	    	// create a new table model with the new data
	        tableModel = new PeopleTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	        viewGUI.updateTable();
			
            } catch(Exception exp) {
                exp.getMessage();
                exp.printStackTrace();
	    }
	}
	
	public PeopleList getPeople(int peopleSelectedID) {
		return tableModel.getPeople(peopleSelectedID);
	}
	
	public void doRemove(PeopleList p) {
		tableModel.doRemove(p);
	}
	
	public List<PeopleList> getPeopleJtable(int projectSelectedID) {
		return tableModel.getPeopleJtable(projectSelectedID);
	}
        // 	pass from viewGUI to table model both the remove and add functions
	public int addRow(String[] array) {
		return tableModel.addRow(array);
	}

	public void removeRow(String title){
		tableModel.removeRow(title);
		//gui.updateTable();
	}
}
