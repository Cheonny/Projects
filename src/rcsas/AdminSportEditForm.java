
package rcsas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AdminSportEditForm extends javax.swing.JFrame {
    private String dateTime;
    private String sportCenter;
    private String sportName;
    private String beforeEditedSportCenter;
    private String beforeEditedSport;
    private boolean registeredName = false;
    
    public AdminSportEditForm() {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                RCSAS.adminSport.setEnabled(true);
            }
        });
    }

   public void setTextField(){
        comboSportCenter.removeAllItems();
        for(int k=0;k<RCSAS.allSportCenter.size();k++){
            SportCenter sc = RCSAS.allSportCenter.get(k);
            comboSportCenter.addItem(sc.getName());
        }
        for(int i=0;i<RCSAS.allSport.size();i++){
            Sport sp = RCSAS.allSport.get(i);
            if(RCSAS.adminSport.selectedSportID.equals(sp.getID())){
                lblSportID.setText(sp.getID());
                lblDateCreated.setText(sp.getDateCreated());
                txtSportName.setText(sp.getSportName());
                txtSportFee.setText(String.valueOf(sp.getSportFee()));
                txtSportFee.setToolTipText("Must more than 0");
                comboSportCenter.setSelectedItem(sp.getSportCenterName());
                beforeEditedSportCenter = sp.getSportCenterName();
                beforeEditedSport = sp.getSportName();
            }
        }   
   }
   
   public void UpdateScheduleFile(){
        for(int i=0; i<RCSAS.allSchedule.size();i++){
            Schedule ss = RCSAS.allSchedule.get(i);
            //replace the edited sport name in schedule file
            if( (ss.getSportCenterName().equals(beforeEditedSportCenter)) && (ss.getSportName().equals(beforeEditedSport)) ) {
                ss.setSportCenterName(sportCenter);
                ss.setSportName(sportName);
            }
        }
        RCSAS.adminSchedule.updateTxtFile();
   }
   public void UpdateCoachFile(){
        for(int i=0; i<RCSAS.allCoach.size();i++){
            Coach c = RCSAS.allCoach.get(i);
            //replace the edited sport name in coach file
            if((c.getSportCenterName().equals(beforeEditedSportCenter)) && (c.getSportName().equals(beforeEditedSport)) ){
                c.setSportCenterName(sportCenter);
                c.setSportName(sportName);
            }
        }
        RCSAS.adminCoach.UpdateCoachFile();
   }
   public void UpdateLocationFile(){
        for(int i=0; i<RCSAS.allLocation.size();i++){
            Location l = RCSAS.allLocation.get(i);
            //replace the edited sport name in location file
            if((l.getSportCenterName().equals(beforeEditedSportCenter)) && (l.getSportName().equals(beforeEditedSport)) ) {
                l.setSportCenterName(sportCenter);
                l.setSportName(sportName);
            }
        }
        RCSAS.adminSportCenter.UpdateSportLocationFile();
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDone = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        txtSportFee = new javax.swing.JTextField();
        lblDateCreated = new javax.swing.JLabel();
        lblSportID = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        comboSportCenter = new javax.swing.JComboBox<>();
        lbl1 = new javax.swing.JLabel();
        txtSportName = new javax.swing.JTextField();

        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(172, 3, 24));
        jPanel1.setPreferredSize(new java.awt.Dimension(574, 85));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Real Champions Sport Academy ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDone.setBackground(new java.awt.Color(172, 3, 24));
        btnDone.setForeground(new java.awt.Color(255, 255, 255));
        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblDateCreated.setText("jLabel3");
        lblDateCreated.setEnabled(false);

        lblSportID.setText("SP002");
        lblSportID.setEnabled(false);

        jLabel23.setText("Sport ID :");

        jLabel24.setText("Date Created :");

        jLabel25.setText("Sport Name :");

        jLabel26.setText("Sport Center Name :");

        lbl1.setText("Sport Fee :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(lbl1))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSportFee, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSportID)
                    .addComponent(lblDateCreated)
                    .addComponent(txtSportName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblSportID))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblDateCreated))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSportFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jScrollPane1.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDone))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDone)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        dateTime = DateTime.DateTime();
        sportName = txtSportName.getText();
        sportCenter = comboSportCenter.getSelectedItem().toString();
        
        
        //empty input
        if(txtSportFee.getText().equals("") || sportName.equals("")){
            JOptionPane.showMessageDialog(null,"Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            //validate sport fee
            try{
                sportName = RCSAS.validation.CapitalizeLetter(sportName);
                if(RCSAS.validation.ValidateName(sportName)==false){
                    throw new Exception();
                }
                
                
                for (int z=0;z<RCSAS.allSportCenter.size();z++){
                    SportCenter sc = RCSAS.allSportCenter.get(z);
                    for(int i=0;i<RCSAS.allSport.size();i++){
                        Sport sp = RCSAS.allSport.get(i);
                        //if registered sport name
                        if(RCSAS.adminSport.selectedSportID.equals(sp.getID())){
                            //if no edit own's sport name and click done
                            sp.setSportCenterName(sportCenter);
                            if(sportName.equals(sp.getSportName())){
                                sp.setSportName(sportName);
                            }else{
                                sp.setSportName(sportName);
                            }
                            Pattern pattern = Pattern.compile("^\\d{0,3}(\\.\\d{0,2})?$");
                            Matcher match = pattern.matcher(txtSportFee.getText());
                            if(match.matches()){
                                double sportFee = Double.parseDouble(txtSportFee.getText());
                                if(sportFee>1 && sportFee <999){
                                    sp.setSportFee(sportFee);
                                }else{
                                    throw new Exception();
                                }
                            }else{
                                throw new Exception();
                            }
                        //does the other registered the username or not
                        }else{ 
                            if(sportName.equals(sp.getSportName()) && sportCenter.equals(sp.getSportCenterName())){
                                registeredName = true;
                                throw new Exception();
                            }
                        }
                    }
                }

                //update
                JOptionPane.showMessageDialog(null,"Edit successfully");
                RCSAS.adminSport.UpdateSportFile();
                RCSAS.adminSport.addRowToJTable();
                UpdateScheduleFile();
                UpdateCoachFile();
                UpdateLocationFile();
                RCSAS.adminSport.setEnabled(true);
                this.setVisible(false);
                
            }catch (Exception e){
                if(registeredName){
                    JOptionPane.showMessageDialog(null,"Uhh-ohh\n*Registered Name",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                        null,
                        "Uhh-ohh"
                        + "\n*Name must <= 50 characters and not contain symbols (except '.' and '/') and digits"
                        + "\n*Sport Fee must be in digits (1.00-999.99).",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDoneActionPerformed

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
            java.util.logging.Logger.getLogger(AdminSportEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminSportEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminSportEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminSportEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminSportEditForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    private javax.swing.JComboBox<String> comboSportCenter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lblDateCreated;
    private javax.swing.JLabel lblSportID;
    private javax.swing.JTextField txtSportFee;
    private javax.swing.JTextField txtSportName;
    // End of variables declaration//GEN-END:variables

}
