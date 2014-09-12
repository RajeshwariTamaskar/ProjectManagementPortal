/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package one;

import java.awt.CardLayout;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 

/**
 *
 * @author sagarnaware
 */
public class WelcomePanel extends javax.swing.JPanel {

    /**
     * Creates new form WelcomePanel1
     */
    public WelcomePanel() {
        initComponents();
    }
    
 // Getter Functions:
    public String getUsernameTextField(){
        return usernameTextField.getText();
    }

    @SuppressWarnings("deprecation")
	public String getPasswordTextField(){
        return passwordField.getText();
    }

    // Setter Functions:
    public void setUsernameTextField(String s){
        usernameTextField.setText(s);
    }

    public void setPasswordTextField(String s){
        passwordField.setText(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        usernameTextField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(228, 221, 251));
        setAlignmentX(10.0F);
        setAlignmentY(10.0F);
        setAutoscrolls(true);

        jPanel3.setBackground(new java.awt.Color(228, 221, 251));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel6.setText("     VIEWER");

        searchButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        searchButton.setText("Search Project");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(228, 221, 251));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), null));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setText("     ADMIN");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setText("  User Name");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel4.setText("   Password");

        usernameTextField.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addComponent(passwordField)))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
    	String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    	EntityManager manager = factory.createEntityManager();
    	LoginController loginController = new LoginController(this, manager);
    	EntityTransaction transaction = manager.getTransaction();
    	 //start transaction
       	transaction.begin();
	   	if (loginController.verifyLogin()) {
			this.setVisible(false);
			JPanel mainPanel = (JPanel) this.getParent();
			CardLayout layout = (CardLayout) mainPanel.getLayout();
			layout.show(mainPanel, "SearchAdmin");
		}
		else {
			//custom title, error icon
			JOptionPane.showMessageDialog(jPanel1, "Invalid username or password.","Login error",
			    JOptionPane.ERROR_MESSAGE);
		}
       	// commit a transaction
       	transaction.commit();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    	this.setVisible(false);
		JPanel mainPanel = (JPanel) this.getParent();
		CardLayout layout = (CardLayout) mainPanel.getLayout();
		layout.show(mainPanel, "SearchViewer");
    }//GEN-LAST:event_searchButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}