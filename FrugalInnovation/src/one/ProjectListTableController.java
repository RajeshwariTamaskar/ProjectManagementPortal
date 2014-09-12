package one;

import javax.sql.RowSetListener;
import javax.sql.RowSetEvent;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.*;

public class ProjectListTableController implements ListSelectionListener, TableModelListener {
	private ProjectListTableModel tableModel;
	private AddPanel addGUI;
	private SearchPanelAdmin searchAdminGUI;
	private SearchPanelViewer searchViewerGUI;
	private ProjectDetailsPanel projectDetailsPanel;
	private EditFormPanel editFormPanel;
	private PeopleViewGUI peopleGUI;
        private SearchPanelViewer viewerGUI;
	private int selectedRow = 0;
	
        /*Constructors*/
	public ProjectListTableController(AddPanel addGUI) {
		this.addGUI = addGUI;   
                tableModel = new ProjectListTableModel(); 
                tableModel.addTableModelListener(this);
	}
	
	public ProjectListTableController(ProjectListTableModel tableModel, SearchPanelAdmin searchAdminGUI) {
		this.searchAdminGUI = searchAdminGUI; 
		this.tableModel = tableModel; 
                tableModel.addTableModelListener(this);
	}
	
	public ProjectListTableController(ProjectListTableModel tableModel, SearchPanelViewer searchViewerGUI) {
		this.searchViewerGUI = searchViewerGUI; 
		this.tableModel = tableModel; 
                tableModel.addTableModelListener(this);
	}
	
	public ProjectListTableController(SearchPanelAdmin searchAdminGUI) {
		this.searchAdminGUI = searchAdminGUI; 
                tableModel = new ProjectListTableModel(); 
                tableModel.addTableModelListener(this);
	}
	
	public ProjectListTableController(ProjectDetailsPanel projectDetailsPanel) {
		this.projectDetailsPanel = projectDetailsPanel; 
                tableModel = new ProjectListTableModel(); 
                tableModel.addTableModelListener(this);
	}
	
	public ProjectListTableController(EditFormPanel editFormPanel) {
		this.editFormPanel = editFormPanel; 
                tableModel = new ProjectListTableModel(); 
                tableModel.addTableModelListener(this);
	}
	
	public ProjectListTableController(PeopleViewGUI peopleGUI) {
		this.peopleGUI = peopleGUI; 
                tableModel = new ProjectListTableModel(); 
                tableModel.addTableModelListener(this);
	}
        
        public ProjectListTableController(SearchPanelViewer viewerGUI) {
		this.viewerGUI = viewerGUI; 
                tableModel = new ProjectListTableModel(); 
                tableModel.addTableModelListener(this);
	}
		
	/*Get table model*/
	public ProjectListTableModel getTableModel() {
		tableModel.update();
		return this.tableModel;
	}
        
	/*Implemented when values the table are modified*/
	public void valueChanged(ListSelectionEvent e) {		
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
		if(this.searchAdminGUI != null) {
			searchAdminGUI.setEditDeleteVisible();
		}	
		if((String) tableModel.getValueAt(firstIndex, 0) != null) {
			selectedRow = Integer.parseInt((String) tableModel.getValueAt(firstIndex, 0));
		}	
	}
	public int rowNumberSelected() {
		return selectedRow;
	}
	
	public void doPersist(ProjectList j) {
		tableModel.doPersist(j);
	}
	
	public void doRemove(ProjectList j) {
		tableModel.doRemove(j);
	}
	
        /*Search queries*/
	public List<ProjectList> searchQueryAdmin(java.sql.Date startDate, java.sql.Date endDate, 
			String nameProject, String categoryProject, String statusField) {
			return tableModel.searchQueryAdmin(startDate, endDate, nameProject, categoryProject, statusField);
	}
        
        public List<ProjectList> searchQueryViewerDSP(java.sql.Date startDate, java.sql.Date endDate, 
			String nameProject, String categoryProject, String statusField, String personName) {
			return tableModel.searchQueryViewerDSP(startDate, endDate, nameProject, categoryProject, statusField, personName);
	}
        
        public List<ProjectList> searchQueryViewerS(String nameProject, String categoryProject, String statusField) {
			return tableModel.searchQueryViewerS(nameProject, categoryProject, statusField);
	}
        
        public List<ProjectList> searchQueryViewerD(java.sql.Date startDate, java.sql.Date endDate, 
			String nameProject, String categoryProject) {
			return tableModel.searchQueryViewerD(startDate, endDate, nameProject, categoryProject);
	}
        
        public List<ProjectList> searchQueryViewerDS(java.sql.Date startDate, java.sql.Date endDate, 
			String nameProject, String categoryProject, String statusField) {
			return tableModel.searchQueryViewerDS(startDate, endDate, nameProject, categoryProject, statusField);
	}
        
        public List<ProjectList> searchQueryViewerDP(java.sql.Date startDate, java.sql.Date endDate, 
			String nameProject, String categoryProject,  String personName) {
			return tableModel.searchQueryViewerDP(startDate, endDate, nameProject, categoryProject, personName);
	}
        
        public List<ProjectList> searchQueryViewerSP(String nameProject, String categoryProject, String statusField, String personName) {
			return tableModel.searchQueryViewerSP(nameProject, categoryProject, statusField, personName);
	}
        
        public List<ProjectList> searchQueryViewerP(String nameProject, String categoryProject, String personName) {
			return tableModel.searchQueryViewerP(nameProject, categoryProject, personName);
	}
        
        public List<ProjectList> searchQueryViewer(String nameProject, String categoryProject) {
			return tableModel.searchQueryViewer(nameProject, categoryProject);
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {		
		try 
		{
                    // get the index of the inserted row
                    int firstIndex =  e.getFirstRow();
                    // create a new table model with the new data
                    tableModel = new ProjectListTableModel(tableModel.getList(), tableModel.getEntityManager());
                    tableModel.addTableModelListener(this);	        
	        
                } catch(Exception exp) {
			exp.getMessage();
			exp.printStackTrace();
                }
   }

	public int addRow(String[] array) {
		return tableModel.addRow(array);			
	}
	
	public void updateRow(String[] array) {
		tableModel.updateRow(array);			
	}
	

	public void removeRow(int projectID){
		tableModel.removeRow(projectID);
		searchAdminGUI.updateTable();
	}
	
	public ProjectList getProject(int id) {
		 return tableModel.getProject(id);
	}	
		
}

