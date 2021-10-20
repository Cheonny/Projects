/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author User
 */
public class StudentRegisteredSchedule extends javax.swing.JFrame {


    
    public ArrayList<Register> registered = new ArrayList<>();
    public ArrayList<Schedule> registeredSchedule = new ArrayList<>();
    public LocalDate currentDate = LocalDate.now();
    public String schDate, coachName, sportName, registerID, schID;
    /**
     * Creates new form StudentRegisteredSchedule
     */    
    public StudentRegisteredSchedule(){
        initComponents();
        ButtonGroup g = new ButtonGroup();
        g.add(rdbtnAll);
        g.add(rdbtnRegistered);
        g.add(rdbtnJoined);
        btnFeedback.setEnabled(false);
        btnRemove.setEnabled(false);
    }
    
    public void Update(){
        lblUsername.setText(RCSAS.current.getUsername());
        rdbtnAll.setSelected(true);
        MyRegister();
        UpdateAll();
    }
    
    public void MyRegister(){
        registered = new ArrayList<>();
        registeredSchedule = new ArrayList<>();

        String studentID = RCSAS.current.getID();
        
        //get the MyRegister info        
        for(int i=0; i<RCSAS.allStudent.size(); i++){
            Student s = RCSAS.allStudent.get(i);
            if(studentID.equals(s.getID())){
                for(int a=0; a<s.getMyRegister().size(); a++){
                    Register b = s.getMyRegister().get(a);
                    registered.add(b);
                }
            }
        }
        
        //get the students's registered schedule info
        for(int i=0; i<registered.size(); i++){
            Register r = registered.get(i);
            Schedule s = r.getScheduleID();
            registeredSchedule.add(s);
        }
    }
    
    public void UpdateAll(){
        DefaultTableModel model = (DefaultTableModel) tbMySchedule.getModel();
        model.setRowCount(0);
                       
        //add into the table
        for(int i=0; i<registeredSchedule.size(); i++){
            Schedule sch = registeredSchedule.get(i);
            for(int c=0; c<registered.size();c++){
                Register d = registered.get(c);
                if(d.getScheduleID().getID().equals(sch.getID())){
                    for(int a=0; a<RCSAS.allSport.size();a++){
                        Sport s = RCSAS.allSport.get(a);
                        if(sch.getSportCenterName().equals(s.getSportCenterName()) && sch.getSportName().equals(s.getSportName())){
                            Object rowData[] = new Object[]{d.getRegisterID(),sch.getID(),sch.getScheduleDate(),sch.getSportCenterName(),
                                sch.getSportName(),sch.getSportLocation(),sch.getStartTime(),sch.getEndTime(),sch.getCoachName(),
                                s.getSportFee()};
                            model.addRow(rowData);
                        }
                    }
                }
                
            }
        }
        
        RowSorter<TableModel> sorter = new TableRowSorter<>(tbMySchedule.getModel());
        tbMySchedule.setRowSorter(sorter); 
        
    }
    
    public void UpdateRegistered(){ //current day and after
        DefaultTableModel model = (DefaultTableModel) tbMySchedule.getModel();
        model.setRowCount(0);
        
        for(int i=0; i<registeredSchedule.size(); i++){
            Schedule sch = registeredSchedule.get(i);
            LocalDate schDate2 = DateTime.StringToLocalDate(sch.getScheduleDate());
            if(schDate2.isAfter(currentDate) || schDate2.isEqual(currentDate)){
                for(int b=0; b<registered.size();b++){
                    Register c = registered.get(b);
                    if(c.getScheduleID().getID().equals(sch.getID())){
                        for(int a=0; a<RCSAS.allSport.size();a++){
                            Sport s = RCSAS.allSport.get(a);
                            if(sch.getSportCenterName().equals(s.getSportCenterName()) && sch.getSportName().equals(s.getSportName())){
                                Object rowData[] = new Object[]{c.getRegisterID(),sch.getID(),sch.getScheduleDate(),sch.getSportCenterName(),
                                    sch.getSportName(),sch.getSportLocation(),sch.getStartTime(),sch.getEndTime(),sch.getCoachName(),
                                    s.getSportFee()};
                                model.addRow(rowData);
                            }
                        }
                    }
                }
            }
        }

               
    }
    
    public void UpdateJoined(){ //before currentDay
        DefaultTableModel model = (DefaultTableModel) tbMySchedule.getModel();
        model.setRowCount(0);
        
        for(int i=0; i<registeredSchedule.size(); i++){
            Schedule sch = registeredSchedule.get(i);
            LocalDate schDate2 = DateTime.StringToLocalDate(sch.getScheduleDate());
            if(schDate2.isBefore(currentDate)){
                for(int b=0; b<registered.size();b++){
                    Register c = registered.get(b);
                    if(c.getScheduleID().getID().equals(sch.getID())){
                        for(int a=0; a<RCSAS.allSport.size();a++){
                            Sport s = RCSAS.allSport.get(a);
                            if(sch.getSportCenterName().equals(s.getSportCenterName()) && sch.getSportName().equals(s.getSportName())){
                                Object rowData[] = new Object[]{c.getRegisterID(),sch.getID(),sch.getScheduleDate(),sch.getSportCenterName(),
                                    sch.getSportName(),sch.getSportLocation(),sch.getStartTime(),sch.getEndTime(),sch.getCoachName(),
                                    s.getSportFee()};
                                model.addRow(rowData);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void Remove(){
        int selectedRowIndex = tbMySchedule.getSelectedRow();
        if(tbMySchedule.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"Please select a row to remove!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            Double sportFees = Double.valueOf(tbMySchedule.getValueAt(selectedRowIndex,9).toString());
            String sportFees2 = String.format("%.2f",sportFees);
            JOptionPane.showMessageDialog(null,"The selected schedule is sucessfully removed!"+
                    "\nRM"+sportFees2+" will be refunded to your bank account.");
            String selectedID = tbMySchedule.getValueAt(selectedRowIndex,1).toString();
            
            for(int i=0; i<registered.size();i++){
                Register r = registered.get(i);
                if(r.getScheduleID().getID().equals(selectedID)){
                    registered.remove(i);
                }
            }
            
            //update registeredSchedule
            registeredSchedule.clear();
            for(int i=0; i<registered.size(); i++){
                Register r = registered.get(i);
                Schedule s = r.getScheduleID();
                registeredSchedule.add(s);
            }
            
            for(int i=0; i<RCSAS.allRegister.size();i++){
                Register r = RCSAS.allRegister.get(i);
                if(r.getScheduleID().getID().equals(selectedID)){
                    Schedule sch2 = new Schedule();
                    sch2.setID("null");
                    r.setScheduleID(sch2);
                    Student stu = new Student();
                    stu.setID("null");
                    r.setStudentID(stu);
                }
            }
                              
            //update myRegister in student class
            String studentID = RCSAS.current.getID();
            for(int i=0; i<RCSAS.allStudent.size(); i++){
                Student s = RCSAS.allStudent.get(i);
                if(studentID.equals(s.getID())){
                    s.setMyRegister(registered);
                }
            }
            
            
            UpdateRegistered(); //update Registered table
            updateRegisterTxt(); //update register txt file
        }
    }
    
    public void Feedback(){ //check feedback before or not 
        int selectedRowIndex = tbMySchedule.getSelectedRow();
        if(tbMySchedule.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"Please select a row to provide feedback!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            schDate = tbMySchedule.getValueAt(selectedRowIndex,2).toString();
            coachName = tbMySchedule.getValueAt(selectedRowIndex,8).toString();
            sportName = tbMySchedule.getValueAt(selectedRowIndex,4).toString();
            registerID = tbMySchedule.getValueAt(selectedRowIndex,0).toString();
            schID = tbMySchedule.getValueAt(selectedRowIndex,1).toString();
            
            boolean feedback = false;
            
            for(int i=0; i<RCSAS.allRegister.size();i++){
                Register r = RCSAS.allRegister.get(i);
                if(r.getRegisterID().equals(registerID)){
                    if(r.getFeedback().equals("null")){
                        feedback=true;
                    }else{
                        JOptionPane.showMessageDialog(null,"The selected schedule have been given feedback before!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            if(feedback==true){
                this.setEnabled(false);
                RCSAS.studentFeedback.Update();
                RCSAS.studentFeedback.setVisible(true);
            }

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
        lblUsername = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        rdbtnAll = new javax.swing.JRadioButton();
        rdbtnRegistered = new javax.swing.JRadioButton();
        rdbtnJoined = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMySchedule = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnFeedback = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(754, 426));

        jPanel2.setBackground(new java.awt.Color(172, 3, 24));
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 85));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Real Champion Sport Academy");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        lblUsername.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsername.setText("1234567891");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Current Login:");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(220, 220, 220)
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

        rdbtnAll.setSelected(true);
        rdbtnAll.setText("All");
        rdbtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnAllActionPerformed(evt);
            }
        });

        rdbtnRegistered.setText("Registered");
        rdbtnRegistered.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnRegisteredActionPerformed(evt);
            }
        });

        rdbtnJoined.setText("Joined");
        rdbtnJoined.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnJoinedActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("My Class");

        tbMySchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Register ID", "Schedule ID", "Schedule Date", "Sport Center", "Sport Name", "Location", "Start Time", "End Time", "Coach Name", "Sport Fees"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMySchedule.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbMySchedule.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbMySchedule.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbMySchedule);
        if (tbMySchedule.getColumnModel().getColumnCount() > 0) {
            tbMySchedule.getColumnModel().getColumn(0).setMinWidth(100);
            tbMySchedule.getColumnModel().getColumn(1).setMinWidth(100);
            tbMySchedule.getColumnModel().getColumn(2).setMinWidth(150);
            tbMySchedule.getColumnModel().getColumn(3).setMinWidth(200);
            tbMySchedule.getColumnModel().getColumn(4).setMinWidth(200);
            tbMySchedule.getColumnModel().getColumn(5).setMinWidth(200);
            tbMySchedule.getColumnModel().getColumn(8).setMinWidth(200);
        }

        btnRemove.setBackground(new java.awt.Color(172, 3, 24));
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnFeedback.setBackground(new java.awt.Color(172, 3, 24));
        btnFeedback.setForeground(new java.awt.Color(255, 255, 255));
        btnFeedback.setText("Feedback");
        btnFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbackActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("*Tap column header to sort table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 805, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rdbtnAll)
                        .addGap(123, 123, 123)
                        .addComponent(rdbtnRegistered)
                        .addGap(123, 123, 123)
                        .addComponent(rdbtnJoined)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnFeedback)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemove)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnAll)
                    .addComponent(rdbtnRegistered)
                    .addComponent(rdbtnJoined))
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnRemove)
                    .addComponent(btnFeedback))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rdbtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnAllActionPerformed
        UpdateAll();
        btnFeedback.setEnabled(false);
        btnRemove.setEnabled(false);
    }//GEN-LAST:event_rdbtnAllActionPerformed

    private void rdbtnRegisteredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnRegisteredActionPerformed
        UpdateRegistered();
        btnRemove.setEnabled(true);
        btnFeedback.setEnabled(false);
    }//GEN-LAST:event_rdbtnRegisteredActionPerformed

    private void rdbtnJoinedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnJoinedActionPerformed
        UpdateJoined();
        btnFeedback.setEnabled(true);
        btnRemove.setEnabled(false);
    }//GEN-LAST:event_rdbtnJoinedActionPerformed

    private void btnFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbackActionPerformed
        Feedback();
    }//GEN-LAST:event_btnFeedbackActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        Remove();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        RCSAS.studentMain.setVisible(true);
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
            java.util.logging.Logger.getLogger(StudentRegisteredSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentRegisteredSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentRegisteredSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentRegisteredSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentRegisteredSchedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFeedback;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JRadioButton rdbtnAll;
    private javax.swing.JRadioButton rdbtnJoined;
    private javax.swing.JRadioButton rdbtnRegistered;
    private javax.swing.JTable tbMySchedule;
    // End of variables declaration//GEN-END:variables
}
