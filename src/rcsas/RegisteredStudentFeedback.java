/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class RegisteredStudentFeedback extends javax.swing.JFrame {

    /**
     * Creates new form RegisteredStudentFeedback
     */
    public RegisteredStudentFeedback() {
        initComponents();
        ButtonGroup g = new ButtonGroup();
        g.add(rdbtnRate1);
        g.add(rdbtnRate2);
        g.add(rdbtnRate3);
        g.add(rdbtnRate4);
        g.add(rdbtnRate5);
        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e){
                RCSAS.studentOwnSchedule.setEnabled(true);
            }
        });
        
    }
    
    public void Update(){
        lblUsername.setText(RCSAS.current.getUsername());
        FeedbackInfo();
    }
    
    public void FeedbackInfo(){
        lblDate.setText(RCSAS.studentOwnSchedule.schDate);
        lblCoachName.setText(RCSAS.studentOwnSchedule.coachName);
        lblSportName.setText(RCSAS.studentOwnSchedule.sportName);
        txtarFeedback.setText("write your feedback here...");
        txtarFeedback.setForeground(new Color(102,102,102));
        rdbtnRate1.setSelected(true);
    }
    
    public void Submit(){
        if(txtarFeedback.getText().equals("write your feedback here...") || 
                txtarFeedback.getText().trim().length()==0){ //make sure that there is texts written
            JOptionPane.showMessageDialog(null,"Please type in your feedback!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            
            JOptionPane.showMessageDialog(null,"You have sucessfully provide feedback for the selected schedule!");
            //get the feedback info
            String[] feedback = txtarFeedback.getText().split("\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<feedback.length;i++){
                sb.append(feedback[i]+" ");
            }
            String joinedFB = sb.toString();
            
            //get rating
            int rating;
            if(rdbtnRate1.isSelected()){
                rating = 1;
            }else if(rdbtnRate2.isSelected()){
                rating = 2;
            }else if(rdbtnRate3.isSelected()){
                rating = 3;
            }else if(rdbtnRate4.isSelected()){
                rating = 4;
            }else{
                rating = 5;
            }
            
            String registerID = RCSAS.studentOwnSchedule.registerID;            
            //update allRegisters
            for(int i=0; i<RCSAS.allRegister.size();i++){
                Register r2 = RCSAS.allRegister.get(i);
                if(registerID.equals(r2.getRegisterID())){
                    r2.setFeedback(joinedFB);
                    r2.setRating(rating);
                }
            }
            
            updateRegisterTxt();
            updateCoachRating();
            RCSAS.studentOwnSchedule.setEnabled(true);
            this.setVisible(false);
            
        }
    }
    
    public void updateRegisterTxt(){
        try{
          PrintWriter p = new PrintWriter("register.txt");
          for(int i=0;i<RCSAS.allRegister.size();i++){
              Register r = RCSAS.allRegister.get(i);
              p.println(r.getRegisterID());
              p.println(r.getFeedback());
              p.println(r.getRating());
              p.println(r.getScheduleID().getID());
              p.println(r.getStudentID().getID());
              p.println();
          }
          p.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!");
        }
    }
    
    public void updateCoachRating(){
        int rating = 0; 
        int cnt = 0;
        double avgRating = 0;
        for(int i=0; i<RCSAS.allCoach.size();i++){
            Coach c = RCSAS.allCoach.get(i);
            for(int a=0; a<RCSAS.allRegister.size();a++){
                Register r = RCSAS.allRegister.get(a);
                if(r.getScheduleID().getCoachName().equals(c.getName())){
                    if(r.getRating()!=0){
                        rating = rating+r.getRating();
                        cnt = cnt+1;
                    }
                }
            }
            if(cnt>0){
                avgRating = (double)rating/cnt;
                c.setAvgRating(avgRating);
            }
            cnt=0;
            rating=0;
        }
        UpdateCoachFile();
    }
    
    public void UpdateCoachFile(){
        try{
            PrintWriter p = new PrintWriter("coach.txt");
            for(int i =0;i<RCSAS.allCoach.size();i++){
                Coach c = RCSAS.allCoach.get(i);
                p.println(c.getID());
                p.println(c.getDateCreated());
                p.println(c.getDateModified());
                p.println(c.getUsername());
                p.println(c.getPassword());
                p.println(c.getName());
                p.println(c.getAge());
                p.println(c.getGender());
                p.println(c.getPhoneNo());
                p.println(c.getAddress());
                p.println(c.getEmail());
                p.println(c.getSportCenterName());
                p.println(c.getSportName());
                p.println(c.getHrRate());
                p.println(c.getAvgRating());
                p.println(c.isIsTerminated());
                p.println();
            }p.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Update failed","Error",JOptionPane.ERROR_MESSAGE);
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
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rdbtnRate3 = new javax.swing.JRadioButton();
        rdbtnRate4 = new javax.swing.JRadioButton();
        rdbtnRate5 = new javax.swing.JRadioButton();
        rdbtnRate2 = new javax.swing.JRadioButton();
        rdbtnRate1 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarFeedback = new javax.swing.JTextArea();
        btnSubmit = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        lblCoachName = new javax.swing.JLabel();
        lblSportName = new javax.swing.JLabel();

        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(172, 3, 24));
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 85));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Real Champion Sport Academy");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsername.setText("1234567891");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Current Login:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(180, 180, 180)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLogout))
                .addGap(64, 64, 64))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogout))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Feedback for Below Class");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        rdbtnRate1.setSelected(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(rdbtnRate1)
                .addGap(18, 18, 18)
                .addComponent(rdbtnRate2)
                .addGap(18, 18, 18)
                .addComponent(rdbtnRate3)
                .addGap(18, 18, 18)
                .addComponent(rdbtnRate4)
                .addGap(18, 18, 18)
                .addComponent(rdbtnRate5)
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rdbtnRate1)
                    .addComponent(rdbtnRate2)
                    .addComponent(rdbtnRate3)
                    .addComponent(rdbtnRate4)
                    .addComponent(rdbtnRate5))
                .addContainerGap())
        );

        jLabel12.setText("1");

        jLabel14.setText("3");

        jLabel15.setText("4");

        jLabel16.setText("5");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("How would you rate this class ?");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/star.jpg"))); // NOI18N
        jLabel19.setText("jLabel18");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/star.jpg"))); // NOI18N
        jLabel20.setText("jLabel18");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/star.jpg"))); // NOI18N
        jLabel21.setText("jLabel18");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/star.jpg"))); // NOI18N
        jLabel22.setText("jLabel18");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/star.jpg"))); // NOI18N
        jLabel23.setText("jLabel18");

        jLabel18.setText("2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Schedule Date :");

        jLabel7.setText("Coach Name :");

        jLabel8.setText("Sport Name :");

        txtarFeedback.setColumns(20);
        txtarFeedback.setForeground(new java.awt.Color(102, 102, 102));
        txtarFeedback.setLineWrap(true);
        txtarFeedback.setRows(5);
        txtarFeedback.setText("write your feedback here...");
        txtarFeedback.setToolTipText("");
        txtarFeedback.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtarFeedbackFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(txtarFeedback);

        btnSubmit.setBackground(new java.awt.Color(172, 3, 24));
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        lblDate.setText("16/4/2021");

        lblCoachName.setText("Meow");

        lblSportName.setText("Badminton");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                    .addComponent(lblCoachName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSportName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblDate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblCoachName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblSportName))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(btnSubmit)
                .addGap(31, 31, 31))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtarFeedbackFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtarFeedbackFocusGained
        if(txtarFeedback.getText().equals("write your feedback here...")){
            txtarFeedback.setText("");
            txtarFeedback.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtarFeedbackFocusGained

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        Submit();
    }//GEN-LAST:event_btnSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(RegisteredStudentFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisteredStudentFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisteredStudentFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisteredStudentFeedback.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisteredStudentFeedback().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCoachName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblSportName;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JRadioButton rdbtnRate1;
    private javax.swing.JRadioButton rdbtnRate2;
    private javax.swing.JRadioButton rdbtnRate3;
    private javax.swing.JRadioButton rdbtnRate4;
    private javax.swing.JRadioButton rdbtnRate5;
    private javax.swing.JTextArea txtarFeedback;
    // End of variables declaration//GEN-END:variables
}
