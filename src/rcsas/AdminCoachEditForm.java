package rcsas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Yu Jue Cheon
 */
public class AdminCoachEditForm extends javax.swing.JFrame {
    public String dateTime;
    private String gender;
    private String Name;
    private String beforeEdited;
    private String coach;
    
    public AdminCoachEditForm() {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                RCSAS.adminCoach.setEnabled(true);
            }
        });
    }
    public void setTextField(){
        rbtnMale.setSelected(false);
        rbtnFemale.setSelected(false);
        rbtnMale.setEnabled(false);
        rbtnFemale.setEnabled(false);
        comboSportCenter.removeAllItems();
        comboSportName.removeAllItems();
        for(int i=0; i<RCSAS.allSportCenter.size();i++){
            SportCenter sc = RCSAS.allSportCenter.get(i);
            comboSportCenter.addItem(sc.getName());   
        }
        
        for(int i=0; i<RCSAS.allCoach.size(); i++){
            Coach c = RCSAS.allCoach.get(i);
            if(RCSAS.adminCoach.selectedCoachID.equals(c.getID())){
                lblCoachID.setText(c.getID());
                lblDateCreated.setText(c.getDateCreated());
                txtUsername.setText(c.getUsername());
                txtPassword.setText(c.getPassword());
                txtName.setText(c.getName());
                txtAge.setText(String.valueOf(c.getAge()));
                txtPhone.setText(c.getPhoneNo());
                txtAddress.setText(c.getAddress());
                txtEmail.setText(c.getEmail());
                comboSportCenter.setSelectedItem(c.getSportCenterName());
                comboSportName.setSelectedItem(c.getSportName());
                txtHourlyRate.setText(String.valueOf(c.getHrRate()));
                lblRating.setText(String.valueOf(c.getAvgRating()));
                if(c.getGender().equals("M")){
                    rbtnMale.setSelected(true);
                }else if(c.getGender().equals("F")){
                    rbtnFemale.setSelected(true);
                }
            }
            beforeEdited = txtName.getText();
        }
    }
    
    public void UpdateScheduleFile(){
        for(int i=0; i<RCSAS.allSchedule.size();i++){
            Schedule ss = RCSAS.allSchedule.get(i);
            if (ss.getCoachName().equals(beforeEdited)){
                ss.setCoachName(coach);
            }
        }
        RCSAS.adminSchedule.updateTxtFile();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDone = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        lblDateCreated = new javax.swing.JLabel();
        lblCoachID = new javax.swing.JLabel();
        lblCoach = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        txtHourlyRate = new javax.swing.JTextField();
        comboSportCenter = new javax.swing.JComboBox<>();
        lblRating = new javax.swing.JLabel();
        comboSportName = new javax.swing.JComboBox<>();
        lblGender9 = new javax.swing.JLabel();
        rbtnMale = new javax.swing.JRadioButton();
        rbtnFemale = new javax.swing.JRadioButton();
        txtPhone = new javax.swing.JTextField();
        jLabel160 = new javax.swing.JLabel();

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
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        btnDone.setBackground(new java.awt.Color(172, 3, 24));
        btnDone.setForeground(new java.awt.Color(255, 255, 255));
        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        lblDateCreated.setText("jLabel3");
        lblDateCreated.setEnabled(false);

        lblCoachID.setText("C002");
        lblCoachID.setEnabled(false);

        lblCoach.setText("Coach ID :");

        jLabel149.setText("Date Created :");

        lblUsername.setText("Username :");

        jLabel151.setText("Password :");

        jLabel152.setText("Name :");

        jLabel153.setText("Address :");

        jLabel154.setText("Email :");

        jLabel155.setText("Gender :");

        jLabel156.setText("Sport Name :");

        jLabel157.setText("Sport Center Name :");

        jLabel158.setText("Rating :");

        jLabel159.setText("Hourly Rate :");

        comboSportCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSportCenterActionPerformed(evt);
            }
        });

        lblRating.setText("0");

        comboSportName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblGender9.setText("Phone :");

        rbtnMale.setText("Male");
        rbtnMale.setEnabled(false);

        rbtnFemale.setText("Female");
        rbtnFemale.setEnabled(false);

        jLabel160.setText("Age :");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCoach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lblGender9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(65, 65, 65))
                    .addComponent(jLabel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel152, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboSportCenter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAge, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboSportName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHourlyRate)
                            .addComponent(lblRating, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(rbtnMale, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtnFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDateCreated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCoachID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lblCoachID)
                        .addGap(18, 18, 18)
                        .addComponent(lblDateCreated))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lblCoach)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel149)))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel151))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel152))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel160))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnFemale)
                    .addComponent(rbtnMale)
                    .addComponent(jLabel155))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGender9))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel154))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel157))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel156))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel159))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRating)
                    .addComponent(jLabel158))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDone)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDone)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        boolean registeredUN = false;
        dateTime = DateTime.DateTime();
        
        //read text field
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        coach = txtName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        
        //Validate empty input
        if( (username.equals("")) || (coach.equals("")) || (password.equals("")) || (txtAge.getText().equals("")) 
            || (phone.equals("")) || (address.equals(""))  || (email.equals(""))
            || (txtHourlyRate.getText().equals("")) ){
                JOptionPane.showMessageDialog(null,"Empty input");
        }else{
            coach = RCSAS.validation.CapitalizeLetter(coach);
            try{
                //error input validations
                int age = Integer.parseInt(txtAge.getText());
                double hrRate = Double.parseDouble(txtHourlyRate.getText());
                if( (username.length()>20) || 
                    (RCSAS.validation.ValidatePassword(password)==false) ||
                    (RCSAS.validation.ValidateName(coach)==false) || 
                    (RCSAS.validation.ValidateAge(age)==false) ||
                    (RCSAS.validation.ValidatePhoneNo(phone)==false) ||
                    (RCSAS.validation.ValidateEmail(email)==false) ||
                    ((hrRate < 100) && (hrRate > 500) ) ){
                    throw new Exception();
                }
                //Username validation
                for(int z=0; z < RCSAS.allSportCenter.size(); z++){
                    SportCenter sc = RCSAS.allSportCenter.get(z);
                    for(int i=0; i < RCSAS.allCoach.size(); i++){
                        Coach c = RCSAS.allCoach.get(i);
                        //if username no edit his username
                        if(RCSAS.adminCoach.selectedCoachID.equals(c.getID())){
                            if(username.equals(c.getUsername())||coach.equals(c.getName())){
                                c.setUsername(username);
                                c.setName(coach);
                            }else{
                                c.setUsername(username);
                                c.setName(coach);
                            }
                        //does the other registered the username or not
                        }else{ 
                            if(username.equals(c.getUsername())||coach.equals(c.getName())){
                                if(c.getSportCenterName().equals(sc.getName())){
                                    registeredUN = true;
                                    throw new Exception();
                                }
                            }
                        }
                    }
                }

                //Edit selected coach info in the arraylist
                for(int i=0; i < RCSAS.allCoach.size(); i++){
                    Coach C = RCSAS.allCoach.get(i);
                    if (RCSAS.adminCoach.selectedCoachID.equals(C.getID())){
                        C.setDateModified(dateTime);
                        C.setPassword(password);
                        C.setAge(age);
                        C.setName(coach);
                        C.setPhoneNo(phone);
                        C.setAddress(address);
                        C.setEmail(email);
                        C.setSportCenterName(comboSportCenter.getSelectedItem().toString());
                        C.setSportName(comboSportName.getSelectedItem().toString());
                        C.setHrRate(hrRate);
                        //no need set isTerminated
                    }
                }
                //update
                JOptionPane.showMessageDialog(null,"Edited successfully");
                RCSAS.adminCoach.OrderedTable();
                RCSAS.adminCoach.UpdateCoachFile();
                UpdateScheduleFile();
                RCSAS.adminCoach.setEnabled(true);
                this.setVisible(false);
                
                
            }catch(Exception e){
                if(registeredUN){
                    JOptionPane.showMessageDialog(null,"Registered username/name","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Uhh Ohh!"
                            + "\n*Username must be <= 20 characters"
                            + "\n*Name must <= 50 characters and not contain symbols (except '.' and '/') and digits"
                            + "\n*Username and Password must not contain spaces and symbols (except '.', '@', '/ ', '_', '*', '#', '!') )"
                            + "\n*Only accept digits for age (1-99)"
                            + "\n*Phone (e.g. __-_____ 012-3456789)"
                            + "\n*Email (e.g. __@__.__)"
                            + "\n*Only accept digits for hourly rate (100-500)",
                            "Error input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }//GEN-LAST:event_btnDoneActionPerformed

    private void comboSportCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSportCenterActionPerformed
        comboSportName.removeAllItems();
        for(int k=0; k<RCSAS.allSport.size();k++){
            Sport sp = RCSAS.allSport.get(k);
            if(sp.getSportCenterName().equals(comboSportCenter.getSelectedItem())){
                comboSportName.addItem(sp.getSportName());
            }
        }
    }//GEN-LAST:event_comboSportCenterActionPerformed

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
            java.util.logging.Logger.getLogger(AdminCoachEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminCoachEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminCoachEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminCoachEditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminCoachEditForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    private javax.swing.JComboBox<String> comboSportCenter;
    private javax.swing.JComboBox<String> comboSportName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCoach;
    private javax.swing.JLabel lblCoachID;
    private javax.swing.JLabel lblDateCreated;
    private javax.swing.JLabel lblGender9;
    private javax.swing.JLabel lblRating;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JRadioButton rbtnFemale;
    private javax.swing.JRadioButton rbtnMale;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHourlyRate;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
