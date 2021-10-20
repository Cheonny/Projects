/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PaymentDialog extends javax.swing.JFrame {

    /**
     * Creates new form PaymentDialog
     */
    
    public String scheduleID = RCSAS.studentGeneralSchedule.scheduleID;
    public String fees = RCSAS.studentGeneralSchedule.fees; //sport fees(==payment amount) from schedule table
    public double fees2 = Double.parseDouble(fees); //payment amount (double)
    public double fees3 = Double.parseDouble(fees);
    public Student s2;
    
    public PaymentDialog() {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                RCSAS.studentGeneralSchedule.setEnabled(true);
            }
        });
    }
    
    public void Update(){
        PaymentInfo();
    }
    
    public void PaymentInfo(){
        //update register ID
        int size = RCSAS.allRegister.size();
        if(size==0){
            lblRegisterID.setText("R000001");
        }else{
            int ID = RCSAS.allRegister.size()+1;
            String format = String.format("%s%06d","R",ID);
            lblRegisterID.setText(format);
        }       

        String scheduleDate = RCSAS.studentGeneralSchedule.scheduleDate;
        String sportCenter = RCSAS.studentGeneralSchedule.sportCenter;
        String sport = RCSAS.studentGeneralSchedule.sport;
        String location = RCSAS.studentGeneralSchedule.location;
        String start = RCSAS.studentGeneralSchedule.start;
        String end = RCSAS.studentGeneralSchedule.end;
        String coach = RCSAS.studentGeneralSchedule.coach;
        
        
        txtareaScheduleInfo.setText("Schedule ID : "+scheduleID+
                    "\nSchedule Date : "+scheduleDate+"\nSport Center : "+sportCenter+
                    "\nSport : "+sport+"\nLocation : "+location+"\nStart Time : "+start+
                    "\nEnd Time : "+end+"\nCoach : "+coach+"\nFees Amount : "+fees);
        lblPaymentInfo.setText("Total Payment Amount : "+fees);
        
        

    }
        
    public void PaymentCal(){

        String payment = txtPayment.getText();
        Pattern pattern = Pattern.compile("^\\d{0,3}(\\.\\d{0,2})?$");
        Matcher match = pattern.matcher(payment);
        
        try{
            if(payment==""){
                JOptionPane.showMessageDialog(null,"Please enter a payment amount!");
            }else{
                if(match.matches()){
                    double amount = Double.parseDouble(payment);
                    
                    if(amount<1){
                        throw new Exception();
                    }else if(amount>=fees2){ //successful
                        

                        //generate paymentID
                        String paymentID;
                        int size2 = RCSAS.allPayment.size();
                        if(size2==0){
                            paymentID ="P000001";
                        }else{
                            int ID = RCSAS.allPayment.size()+1;
                            String format = String.format("%s%06d","P",ID);
                            paymentID = format;
                        }
                        
                        //subtract amount using fees for balance
                        BigDecimal amount2 = BigDecimal.valueOf(amount);
                        BigDecimal fees4 = BigDecimal.valueOf(fees2);
                        BigDecimal balance2 = amount2.subtract(fees4);

                        JOptionPane.showMessageDialog(null,"Payment ID : "+paymentID+"\nPayment done!"+"\nYou registered successfully!"+
                            "\nYour changes is RM"+balance2.setScale(2)+".");

                        //update allRegister, myRegister, scheduleRegister, allPayment, myPayment
                        String registerID = lblRegisterID.getText();
                        String feedback ="null";
                        int rating = 0;
                        String currentID = RCSAS.current.getID();
                        String paymentDate = DateTime.DateTime();
                        double paymentAmount = balance2.setScale(2).doubleValue()+fees3;

                        for(int i=0; i<RCSAS.allStudent.size();i++){
                            Student s = RCSAS.allStudent.get(i);
                            if(currentID.equals(s.getID())){
                                s2 = s;
                            }
                        }

                        for(int a=0; a<RCSAS.allSchedule.size();a++){
                            Schedule sch = RCSAS.allSchedule.get(a);
                            if(scheduleID.equals(sch.getID())){
                                Register r = new Register(registerID, feedback, rating, sch, s2);
                                s2.getMyRegister().add(r);
                                RCSAS.allRegister.add(r);
                                Payment p1 = new Payment(paymentID, paymentDate, fees2, paymentAmount, balance2.setScale(2).doubleValue(), r, s2);
                                RCSAS.allPayment.add(p1);
                                s2.getMyPayment().add(p1);
                            }
                        }
                        updateRegisterTxt();
                        updatePaymentTxt();
                        this.setVisible(false);

                    }else{ //alert user remaining amount
                        fees2 = fees2-amount;
                        txtPayment.setText("");
                        lblRemaining.setText("You need to pay RM"+fees2+".");
                        JOptionPane.showMessageDialog(null, "You must make full payment to register!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"The amount only accepts 2 decimal places or below 999");
                } 
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Wrong input!");
        }
    }
    
    public void CancelPayment(){
        JOptionPane.showMessageDialog(null,"You need to make payment to register!"+"\nRegistration unsuccessfull!");
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
    
    public void updatePaymentTxt(){
        try{
            PrintWriter p = new PrintWriter("payment.txt");
            for(int i=0; i<RCSAS.allPayment.size();i++){
                Payment p1 = RCSAS.allPayment.get(i);
                p.println(p1.getID());
                p.println(p1.getPaymentDate());
                p.println(p1.getSportFees());
                p.println(p1.getPaymentAmount());
                p.println(p1.getPaymentBalance());
                p.println(p1.getRegisterID().getRegisterID());
                p.println(p1.getStudentID().getID());
                p.println();
            }
            p.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!");
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

        txtPayment = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblPaymentInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaScheduleInfo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblRemaining = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblRegisterID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setResizable(false);

        btnOk.setText("Done");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblPaymentInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPaymentInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaymentInfo.setText("Payment");

        txtareaScheduleInfo.setColumns(20);
        txtareaScheduleInfo.setRows(5);
        txtareaScheduleInfo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtareaScheduleInfo.setEnabled(false);
        jScrollPane1.setViewportView(txtareaScheduleInfo);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Enter your amount :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Schedule Information :");

        lblRemaining.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRemaining.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel1.setBackground(new java.awt.Color(172, 3, 24));
        jPanel1.setForeground(new java.awt.Color(172, 3, 24));

        lblRegisterID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRegisterID.setForeground(new java.awt.Color(255, 255, 255));
        lblRegisterID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegisterID.setText("Register ID : R000001");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegisterID, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegisterID)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("RM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPaymentInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRemaining, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPaymentInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addGap(33, 33, 33))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        PaymentCal();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        CancelPayment();
        this.setVisible(false);
        
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPaymentInfo;
    private javax.swing.JLabel lblRegisterID;
    private javax.swing.JLabel lblRemaining;
    private javax.swing.JTextField txtPayment;
    private javax.swing.JTextArea txtareaScheduleInfo;
    // End of variables declaration//GEN-END:variables
}
