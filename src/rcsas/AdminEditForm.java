package rcsas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Yu Jue Cheon
 */
public class AdminEditForm extends javax.swing.JFrame {
    private String dateTime;
    
    public AdminEditForm() {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                RCSAS.admin.setEnabled(true);
            }
        });
    }
    public void setTextField(){
        setComboBox();
        for(int i=0; i<RCSAS.allAdmin.size(); i++){
            Admin a = RCSAS.allAdmin.get(i);
            if(RCSAS.admin.selectedAdminID.equals(a.getID())){
                lblID.setText(a.getID());
                lblDateCreated.setText(a.getDateCreated());
                txtUsername.setText(a.getUsername());
                txtPassword.setText(a.getPassword());
                txtName.setText(a.getName());
                txtPhone.setText(a.getPhoneNo());
                txtAddress.setText(a.getAddress());
                txtEmail.setText(a.getEmail());
                comboSportCenter.addItem(a.getSportCenterName());
                comboSportCenter.setSelectedItem(a.getSportCenterName());
                
                if(a.getIsHQ()){
                    cbIsHQ.setSelected(true);
                    comboSportCenter.setEnabled(false);
                }else if(a.getIsHQ()==false){
                    cbIsHQ.setSelected(false);
                    comboSportCenter.setEnabled(true);
                }
            } 
        }
        
    }
    public void setComboBox(){
        comboSportCenter.removeAllItems();
        ArrayList<String> firstList = new ArrayList<String>();
        ArrayList<String> secondList = new ArrayList<String>();
        for(int i=0; i<RCSAS.allSportCenter.size();i++){
            SportCenter sc = RCSAS.allSportCenter.get(i);
            firstList.add(sc.getName());
        }
        for(int k=0;k<RCSAS.allAdmin.size();k++){
            Admin a = RCSAS.allAdmin.get(k);
            secondList.add(a.getSportCenterName());
        }
        //no extra sport center
        if(firstList.equals(secondList)==true){
            for(int i=0; i<RCSAS.allAdmin.size(); i++){
                Admin a = RCSAS.allAdmin.get(i);
                if(RCSAS.admin.selectedAdminID.equals(a.getID())){
                    comboSportCenter.addItem(a.getSportCenterName());
                }
            }
        //gt new sport center
        }else{
            for(String item: firstList){
                if(secondList.contains(item)==false){
                    comboSportCenter.addItem(item);
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDone = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        lblDateCreated = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        comboSportCenter = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        cbIsHQ = new javax.swing.JCheckBox();

        setResizable(false);

        btnDone.setBackground(new java.awt.Color(172, 3, 24));
        btnDone.setForeground(new java.awt.Color(255, 255, 255));
        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

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
                .addContainerGap(88, Short.MAX_VALUE))
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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblDateCreated.setText("jLabel3");
        lblDateCreated.setEnabled(false);

        lblID.setText("A002");
        lblID.setEnabled(false);

        jLabel23.setText("Admin ID :");

        jLabel24.setText("Date Created :");

        jLabel25.setText("Username :");

        jLabel26.setText("Password :");

        jLabel27.setText("Name :");

        jLabel28.setText("Address :");

        jLabel29.setText("Email :");

        jLabel30.setText("Phone :");

        jLabel32.setText("Sport Center Name:");

        cbIsHQ.setText("isHQ");
        cbIsHQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIsHQActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel27)
                        .addComponent(jLabel30)))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress)
                    .addComponent(txtEmail)
                    .addComponent(comboSportCenter, 0, 215, Short.MAX_VALUE)
                    .addComponent(cbIsHQ)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword)
                            .addComponent(txtUsername)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtName)
                            .addComponent(lblDateCreated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblID))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblDateCreated))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbIsHQ)
                .addGap(33, 33, 33))
        );

        jScrollPane1.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDone)
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        dateTime = DateTime.DateTime();
        String password = txtPassword.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String username = txtUsername.getText();
        String name = txtName.getText();
        String sportCenter = comboSportCenter.getSelectedItem().toString();
        boolean registeredUN = false;
        
        if( (username.equals("")) || (password.equals("")) || (name.equals("")) ||
                (phone.equals("")) || (address.equals("")) || (email.equals("")) ){
            JOptionPane.showMessageDialog(null,"Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                
                name = RCSAS.validation.CapitalizeLetter(name);
                //Username validation
                for(int i=0; i < RCSAS.allAdmin.size(); i++){
                    Admin a = RCSAS.allAdmin.get(i);
                    //if username no edit his username
                    if(RCSAS.admin.selectedAdminID.equals(a.getID())){
                        if(username.equals(a.getUsername())||name.equals(a.getName())){
                            a.setUsername(username);
                            a.setName(name);
                        }else{
                            a.setUsername(username);
                            a.setName(name);
                        }
                    //does the other registered the username or not
                    }else{ 
                        if(username.equals(a.getUsername())||name.equals(a.getName())){
                            registeredUN = true;
                            throw new Exception();
                        }
                    }
                }
                //validation
                for(int z=0;z<RCSAS.allAdmin.size();z++){
                    Admin ab = RCSAS.allAdmin.get(z);
                    if((RCSAS.validation.ValidatePassword(password) == false) || 
                            (RCSAS.validation.ValidateEmail(email) == false) || 
                            (RCSAS.validation.ValidatePhoneNo(phone) == false) ||
                            (username.length()>20) ){
                       throw new Exception();
                    }else{
                        if(RCSAS.admin.selectedAdminID.equals(ab.getID())){
                            ab.setDateModified(dateTime);
                            ab.setName(name);
                            ab.setPhoneNo(phone);
                            ab.setAddress(address);
                            ab.setEmail(email);
                            ab.setPassword(txtPassword.getText());
                            if(cbIsHQ.isSelected()){
                                ab.setSportCenterName("null");
                                ab.setIsHQ(true);
                            //For HQ
                            }else if(cbIsHQ.isSelected()==false && comboSportCenter.getSelectedItem().equals("null")){
                                ab.setIsHQ(true);
                                ab.setSportCenterName(sportCenter);
                            }else{
                                ab.setIsHQ(false);
                                ab.setSportCenterName(sportCenter);
                            }
                        }
                    }
                }
                //update
                JOptionPane.showMessageDialog(null,"Information is edited successfully");
                RCSAS.admin.setEnabled(true);
                RCSAS.admin.addRowToJTable();
                RCSAS.admin.UpdateAdminFile();
                this.setVisible(false);
                
            }catch (Exception ex){
                    if(registeredUN){
                        JOptionPane.showMessageDialog(null,"Registered username/name","Error",JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(
                            null,
                            "Uhh-Ohh:"
                            + "\n*Username <= 20 characters"
                            + "\n*Name must <= 50 characters and not contain symbols (except '.' and '/') and digits"
                            + "\n*Password must not contain spaces and symbols '~','$','%','^','&','(',')','-'.'=','\n  [',']',';',':','?','<','>','+',',' )"
                            + "\n*Phone (e.g. ___-______ 012-3456789)"
                            + "\n*Email (e.g. __@__.__)",
                            "Error Input",
                            JOptionPane.ERROR_MESSAGE);
                    }
            }
            
        }
        
        
    }//GEN-LAST:event_btnDoneActionPerformed

    private void cbIsHQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIsHQActionPerformed
        if(cbIsHQ.isSelected()){
            comboSportCenter.setEnabled(false);
            comboSportCenter.addItem("null");
            comboSportCenter.setSelectedItem("null");
        }else{
            comboSportCenter.setEnabled(true);
            comboSportCenter.removeAllItems();
            setComboBox();
            for(int i=0; i<RCSAS.allAdmin.size(); i++){
                Admin a = RCSAS.allAdmin.get(i);
                if(RCSAS.admin.selectedAdminID.equals(a.getID())){
                    comboSportCenter.addItem(a.getSportCenterName());
                    comboSportCenter.setSelectedItem(a.getSportCenterName());
                }
            }
        }
    }//GEN-LAST:event_cbIsHQActionPerformed

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
            java.util.logging.Logger.getLogger(AdminEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminEditForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    private javax.swing.JCheckBox cbIsHQ;
    private javax.swing.JComboBox<String> comboSportCenter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDateCreated;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
