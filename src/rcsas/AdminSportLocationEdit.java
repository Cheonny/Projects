/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yu Jue Cheon
 */
public class AdminSportLocationEdit extends javax.swing.JFrame {
    
    private String dateTime;
    private String beforeEditedSportCenter;
    private String beforeEditedSport;
    private String beforeEditedLocation;
    private String sportCenter;
    private String sport;
    private String location;
    private String firstSport;
    private boolean registeredName;
    /**
     * Creates new form AdminSportLocationEdit
     */
    public AdminSportLocationEdit() {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                RCSAS.adminSportCenter.setEnabled(true);
            }
        });
    }
    
    public void setTextField(){
        comboSportName.removeAllItems();
        comboSportCenterName.removeAllItems();
        for(int z=0;z<RCSAS.allSportCenter.size();z++){
            SportCenter sc = RCSAS.allSportCenter.get(z);
            comboSportCenterName.addItem(sc.getName());
            for(int k=0;k<RCSAS.allSport.size();k++){
                Sport sp = RCSAS.allSport.get(k);
                if(sp.getSportCenterName().equals(sc.getName()) ){
                    comboSportName.addItem(sp.getSportName());
                }
            }
        }
        for(int i=0;i<RCSAS.allLocation.size();i++){
            Location l = RCSAS.allLocation.get(i);
            if(RCSAS.adminSportCenter.selectedSportLocationID.equals(l.getLocationID())){
                lblLocationID.setText(l.getLocationID());
                lblDateCreated.setText(l.getDateCreated());
                txtLocationName.setText(l.getSportLocationName());
                //comboSportName.setSelectedItem(l.getSportName());
                comboSportCenterName.setSelectedItem(l.getSportCenterName());
                beforeEditedSport = l.getSportName();
            }
        }
        updateFirstSport();
        beforeEditedSportCenter =  comboSportCenterName.getSelectedItem().toString();
        
        beforeEditedLocation = txtLocationName.getText();
    }
    
    public void updateFirstSport(){
        for(int i=0; i<comboSportName.getItemCount();i++){
            String comboItem = comboSportName.getItemAt(i);
            for(int k=0;k<RCSAS.allLocation.size();k++){
                Location l = RCSAS.allLocation.get(k);
                if(RCSAS.adminSportCenter.selectedSportLocationID.equals(l.getLocationID())){
                    if(l.getSportName().equals(comboItem)){                       
                        firstSport = comboItem;
                        break;
                    }
                }
            }
        }
        comboSportName.setSelectedItem(firstSport);
        
    }
    
    public void UpdateScheduleFile(){
        for(int i=0; i<RCSAS.allSchedule.size();i++){
            Schedule ss = RCSAS.allSchedule.get(i);
            if((ss.getSportCenterName().equals(beforeEditedSportCenter)) &&  
                    (ss.getSportName().equals(beforeEditedSport)) 
                    && (ss.getSportLocation().equals(beforeEditedLocation))){
                ss.setSportCenterName(sportCenter);
                ss.setSportName(sport);
                ss.setSportLocation(location);
            }
        }
        RCSAS.adminSchedule.updateTxtFile();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        c = new javax.swing.JPanel();
        lblDateCreated = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtLocationName = new javax.swing.JTextField();
        comboSportName = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        lblLocationID = new javax.swing.JLabel();
        comboSportCenterName = new javax.swing.JComboBox<>();
        btnDone = new javax.swing.JButton();

        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(172, 3, 24));
        jPanel3.setPreferredSize(new java.awt.Dimension(574, 85));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Real Champions Sport Academy ");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel6.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(38, 38, 38))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        c.setBackground(new java.awt.Color(255, 255, 255));

        lblDateCreated.setText("SP002");
        lblDateCreated.setEnabled(false);

        jLabel23.setText("Date Created:");

        jLabel26.setText("Sport Name :");

        jLabel4.setText("Sport Center Name :");

        jLabel28.setText("Location Name :");

        jLabel24.setText("Location ID :");

        lblLocationID.setText("SP002");
        lblLocationID.setEnabled(false);

        comboSportCenterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSportCenterNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cLayout = new javax.swing.GroupLayout(c);
        c.setLayout(cLayout);
        cLayout.setHorizontalGroup(
            cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel4)
                    .addComponent(jLabel28)
                    .addComponent(jLabel26))
                .addGap(19, 19, 19)
                .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cLayout.createSequentialGroup()
                        .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDateCreated)
                            .addComponent(lblLocationID))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(cLayout.createSequentialGroup()
                        .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSportName, 0, 188, Short.MAX_VALUE)
                            .addComponent(txtLocationName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboSportCenterName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        cLayout.setVerticalGroup(
            cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblLocationID))
                .addGap(18, 18, 18)
                .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblDateCreated))
                .addGap(18, 18, 18)
                .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboSportCenterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(cLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(comboSportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        btnDone.setBackground(new java.awt.Color(172, 3, 24));
        btnDone.setForeground(new java.awt.Color(255, 255, 255));
        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDone)
                    .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDone)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        dateTime = DateTime.DateTime();
        location = txtLocationName.getText();
        sportCenter = comboSportCenterName.getSelectedItem().toString();
        sport = comboSportName.getSelectedItem().toString();
        
        if(location.equals("")){
            JOptionPane.showMessageDialog(null, "Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                location = RCSAS.validation.CapitalizeLetter(location);
                if(RCSAS.validation.ValidateName(location)==false || location.length() > 20){
                    throw new Exception();
                }else{
                    for(int i=0;i<RCSAS.allLocation.size();i++){
                        Location l = RCSAS.allLocation.get(i);
                    if(location.equals(l.getSportLocationName()) && sportCenter.equals(l.getSportCenterName())){
                        registeredName = true;
                        throw new Exception();
                    }   
                }
                    for(int i=0;i<RCSAS.allLocation.size();i++){
                        Location l = RCSAS.allLocation.get(i);
                        if(RCSAS.adminSportCenter.selectedSportLocationID.equals(l.getLocationID())){
                            l.setSportLocationName(location);
                            l.setSportCenterName(sportCenter);
                            l.setSportName(sport);

                            //update
                            JOptionPane.showMessageDialog(null,"Edit successfully");
                            RCSAS.adminSportCenter.UpdateSportCenterFile();
                            if(RCSAS.adminSportCenter.isSportLocation){
                                RCSAS.adminSportCenter.initiateSportCenterForm2();
                                RCSAS.adminSportCenter.addRowToJTable2();
                                RCSAS.adminSportCenter.UpdateSportLocationFile();
                                UpdateScheduleFile();
                                RCSAS.adminSportCenter.setEnabled(true);
                                this.setVisible(false);
                            }
                        }
                    }
                }
            }catch(Exception ex){
                if(registeredName){
                    JOptionPane.showMessageDialog(
                            null,
                            "Registered sport location name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null, "Uhh-Ohh!"
                            + "\n*Sport location name must <= 20 characters and \n  not contain symbols(except '.' and '/') and digits",
                            "Error Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDoneActionPerformed

    private void comboSportCenterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSportCenterNameActionPerformed
        comboSportName.removeAllItems();
        for(int k=0; k<RCSAS.allSport.size();k++){
            Sport sp = RCSAS.allSport.get(k);
            if(sp.getSportCenterName().equals(comboSportCenterName.getSelectedItem())){
                comboSportName.addItem(sp.getSportName());
            }
        }
    }//GEN-LAST:event_comboSportCenterNameActionPerformed

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
            java.util.logging.Logger.getLogger(AdminSportLocationEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminSportLocationEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminSportLocationEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminSportLocationEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminSportLocationEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    private javax.swing.JPanel c;
    private javax.swing.JComboBox<String> comboSportCenterName;
    private javax.swing.JComboBox<String> comboSportName;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDateCreated;
    private javax.swing.JLabel lblLocationID;
    private javax.swing.JTextField txtLocationName;
    // End of variables declaration//GEN-END:variables
}