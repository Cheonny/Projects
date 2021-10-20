/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author User
 */
public class AdminViewFeedback extends javax.swing.JFrame {
    
    String sportCenter;

    /**
     * Creates new form AdminViewFeedback
     */
    public AdminViewFeedback() {
        initComponents();
    }
    
    public void Update(){
        lblUsername.setText(RCSAS.current.getUsername());
        filterCoach();
        updateJtable();
        displayFeedback();
    }
    
    public void filterCoach(){
        comboCoach.removeAllItems();
        comboCoach.addItem("All Coaches");
        String userID = RCSAS.current.getID();
        sportCenter = null;
        
        if(RCSAS.loginpage.isAdmin==true){
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin a = RCSAS.allAdmin.get(i);
                if(userID.equals(a.getID())){
                    sportCenter = a.getSportCenterName();
                }
            }
            
            for(int i=0; i<RCSAS.allCoach.size();i++){
                Coach c = RCSAS.allCoach.get(i);
                if(!(c.isIsTerminated())){
                    if(sportCenter.equals(c.getSportCenterName())){
                        String coachName = c.getName();
                        comboCoach.addItem(coachName);
                    }
                }

            }
            
        }else if(RCSAS.loginpage.isHQAdmin==true){
            for(int i=0; i<RCSAS.allCoach.size();i++){
                Coach c = RCSAS.allCoach.get(i);
                if(!c.isIsTerminated()){
                    String coachName = c.getName();
                    comboCoach.addItem(coachName);
                }
            }
        }
    }
    
    public void updateJtable(){
        if(comboCoach.getSelectedItem()==null){
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) tbFeedback.getModel();
        model.setRowCount(0);
        
        String coachName = comboCoach.getSelectedItem().toString();
        
        if(RCSAS.loginpage.isAdmin==true){
            for(int i=0; i<RCSAS.allRegister.size();i++){
                Register r = RCSAS.allRegister.get(i);
                if(!(r.getFeedback().equals("null"))){
                    if(comboCoach.getSelectedItem().equals("All Coaches")){
                        if(r.getScheduleID().getSportCenterName().equals(sportCenter)){
                            Object rowData[] = new Object[]{r.getRegisterID(),r.getRating(),r.getScheduleID().getID(),
                                r.getScheduleID().getCoachName()};
                            model.addRow(rowData);
                        }     
                    }else if(r.getScheduleID().getCoachName().equals(coachName)){
                        Object rowData[] = new Object[]{r.getRegisterID(),r.getRating(),r.getScheduleID().getID(),
                            r.getScheduleID().getCoachName()};
                        model.addRow(rowData);
                    }
                }
            }
        }else if(RCSAS.loginpage.isHQAdmin==true){
            for(int i=0; i<RCSAS.allRegister.size();i++){
                Register r = RCSAS.allRegister.get(i);
                if(!(r.getFeedback().equals("null"))){
                    if(comboCoach.getSelectedItem().equals("All Coaches")){
                        Object rowData[] = new Object[]{r.getRegisterID(),r.getRating(),r.getScheduleID().getID(),
                                r.getScheduleID().getCoachName()};
                        model.addRow(rowData);
                    }else if(r.getScheduleID().getCoachName().equals(coachName)){
                        Object rowData[] = new Object[]{r.getRegisterID(),r.getRating(),r.getScheduleID().getID(),
                            r.getScheduleID().getCoachName()};
                        model.addRow(rowData);
                    }
                }
            }
        }
        
        
        RowSorter<TableModel> sorter = new TableRowSorter<>(tbFeedback.getModel());
        tbFeedback.setRowSorter(sorter);
    }
    
    public void displayFeedback(){
        txtarFeedback.setText("Select row to view feedback.");
        int selectedRowIndex = tbFeedback.getSelectedRow();
        if(!(tbFeedback.getSelectionModel().isSelectionEmpty())){

            String registerID = tbFeedback.getValueAt(selectedRowIndex,0).toString();
            for(int i=0;i<RCSAS.allRegister.size();i++){
                Register r = RCSAS.allRegister.get(i);
                if(r.getRegisterID().equals(registerID)){
                    String feedback = r.getFeedback();
                    txtarFeedback.setText(feedback);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFeedback = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtarFeedback = new javax.swing.JTextArea();
        comboCoach = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(172, 3, 24));
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 85));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Real Champion Sport Academy");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Current Login:");

        lblUsername.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsername.setText("1234567891");

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(218, 218, 218)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogout))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Feedback for Coaches");
        jLabel3.setToolTipText("");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tbFeedback.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Register ID", "Rating", "Schedule ID", "Coach Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFeedback.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbFeedback.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbFeedback.getTableHeader().setReorderingAllowed(false);
        tbFeedback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFeedbackMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbFeedback);
        if (tbFeedback.getColumnModel().getColumnCount() > 0) {
            tbFeedback.getColumnModel().getColumn(0).setMinWidth(50);
            tbFeedback.getColumnModel().getColumn(1).setMinWidth(50);
            tbFeedback.getColumnModel().getColumn(2).setMinWidth(100);
            tbFeedback.getColumnModel().getColumn(3).setMinWidth(200);
        }

        txtarFeedback.setColumns(20);
        txtarFeedback.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtarFeedback.setRows(5);
        txtarFeedback.setText("Select row to view feedback.");
        txtarFeedback.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtarFeedback.setEnabled(false);
        jScrollPane2.setViewportView(txtarFeedback);

        comboCoach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCoachActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Feedback :");

        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("*Tap column header to sort table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 815, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(comboCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addComponent(comboCoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(btnBack)
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboCoachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCoachActionPerformed
        updateJtable();
        displayFeedback();
    }//GEN-LAST:event_comboCoachActionPerformed

    private void tbFeedbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFeedbackMouseClicked
        displayFeedback();
    }//GEN-LAST:event_tbFeedbackMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        if(RCSAS.loginpage.isHQAdmin==true){
            RCSAS.adminMain.setVisible(true);
        }else if(RCSAS.loginpage.isAdmin==true){
            RCSAS.adminMain2.setVisible(true);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        RCSAS.mainpage.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminViewFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminViewFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminViewFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminViewFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminViewFeedback().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private javax.swing.JComboBox<String> comboCoach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tbFeedback;
    private javax.swing.JTextArea txtarFeedback;
    // End of variables declaration//GEN-END:variables
}
