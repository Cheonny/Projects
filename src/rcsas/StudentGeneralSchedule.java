/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.io.PrintWriter;
import java.time.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author User
 */
public class StudentGeneralSchedule extends javax.swing.JFrame {


    public ArrayList<Schedule> scheduleWithFees2 = new ArrayList<>();  
    public String scheduleID,scheduleDate,sportCenter,
            sport,location,start,end,coach,fees,availability; 
    public ArrayList<Register> registered = new ArrayList<>();
    public ArrayList<Schedule> registeredSchedule = new ArrayList<>();
    public ArrayList<Schedule> scheduleAvailable = new ArrayList<>();
    
    /**
     * Creates new form StudentGeneralSchedule
     */    
    public StudentGeneralSchedule() {
        initComponents();
    }
    
    public void Update(){
        lblUsername.setText(RCSAS.current.getUsername());
        SportFees();
        Availability();
        updateDate();
        updateSport();
        
    }
    
    public void updateDate(){
        comboDate.removeAllItems();
        comboDate.addItem("All Dates");
        LocalDate currentDate = LocalDate.now();
        LocalDate monday = currentDate;
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) { //get the current week's monday date
          monday = monday.minusDays(1);
        }
        LocalDate nextMonday = monday.plusWeeks(1);
        
        String currentMonday2 = DateTime.LocalDateToString(monday);
        String nextMonday2 = DateTime.LocalDateToString(nextMonday);
        comboDate.addItem(currentMonday2);
        comboDate.addItem(nextMonday2);
    }
    
    public void updateSport(){
        comboSport.removeAllItems();
        comboSport.addItem("All Sports");
        Set<String> allSports = new HashSet<>(); 
        for(int i=0; i<RCSAS.allSport.size();i++){
            Sport s = RCSAS.allSport.get(i);
            String sportName = s.getSportName();
            allSports.add(sportName);
        }
        for(String s : allSports){
            comboSport.addItem(s);
        }
    }
    
    public void SportFees(){
        scheduleWithFees2 = new ArrayList<>();
        ArrayList<Schedule> scheduleWithFees = new ArrayList<>();
        
        for(int i=0;i<RCSAS.allSchedule.size();i++){
            Schedule sch = RCSAS.allSchedule.get(i);
            for(int a=0; a<RCSAS.allSport.size();a++){
                Sport s = RCSAS.allSport.get(a);
                if(sch.getSportCenterName().equals(s.getSportCenterName()) && sch.getSportName().equals(s.getSportName())){
                    Schedule sch2 = new Schedule(sch.getID(),sch.getScheduleDate(),sch.getSportCenterName(),
                    sch.getSportName(),sch.getSportLocation(),sch.getStartTime(),sch.getEndTime(),
                    sch.getCoachName(),sch.getSportPax(),s.getSportFee(),sch.getAvailability());
                    scheduleWithFees.add(sch2);
                }
            }
        }
        
        for(Schedule i : scheduleWithFees){
            for(int a=0;a<RCSAS.allCoach.size();a++){
                Coach b = RCSAS.allCoach.get(a);
                if(i.getCoachName().equals(b.getName())){
                    if(!(b.isIsTerminated())){
                        scheduleWithFees2.add(i);
                    }
                }
            }
        }
        
    }
    
    public void Availability(){
        //set availability
        int cnt = 0;
        for(int i=0;i<scheduleWithFees2.size();i++){
            Schedule a = scheduleWithFees2.get(i);
            for(int b=0; b<RCSAS.allRegister.size(); b++){
                Register c = RCSAS.allRegister.get(b);
                if(a.getID().equals(c.getScheduleID().getID())){
                    cnt=cnt+1;
                }
            }
            if(cnt>=a.getSportPax()){
                a.setAvailability("Full");
            }else{
                a.setAvailability("Available");
            }
            cnt=0;
        }
        
        //update all schedule
        for(int i=0;i<RCSAS.allSchedule.size();i++){
            Schedule a = RCSAS.allSchedule.get(i);
            for(int b=0;b<scheduleWithFees2.size();b++){
                Schedule c = scheduleWithFees2.get(b);
                if(a.getID().equals(c.getID())){
                    a.setAvailability(c.getAvailability());
                }
            }
        }
        
        updateTxtFile();
        
    }
    
    public void updateTxtFile(){
        try{
          PrintWriter p = new PrintWriter("schedule.txt");
          for(int i=0;i<RCSAS.allSchedule.size();i++){
              Schedule sch = RCSAS.allSchedule.get(i);
              p.println(sch.getID());
              p.println(sch.getCreatedDate());
              p.println(sch.getModifiedDate());
              p.println(sch.getScheduleDate());
              p.println(sch.getSportCenterName());
              p.println(sch.getSportName());
              p.println(sch.getSportLocation());
              p.println(sch.getStartTime());
              p.println(sch.getEndTime());
              p.println(sch.getCoachName());
              p.println(sch.getSportPax());
              p.println(sch.getAvailability());
              p.println();
          }
          p.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!");
        }
        
    }
    
    private ArrayList<Schedule> Filter1(){
        ArrayList<Schedule> table = new ArrayList<>();
        if(comboDate.getSelectedItem()==null){
            return table;
        }
        String date = comboDate.getSelectedItem().toString();
        
        LocalDate currentDate = LocalDate.now();
        LocalDate monday = currentDate;
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) { //get the current week's monday date
          monday = monday.minusDays(1);
        }
        LocalDate endDateOf2Weeks = monday.plusWeeks(2); //get the end of 2 weeks monday date
        
        for(int i=0; i<scheduleWithFees2.size();i++){
            Schedule sch = scheduleWithFees2.get(i); 
            LocalDate schDate = DateTime.StringToLocalDate(sch.getScheduleDate()); //schedule date

            if(date=="All Dates"){
                if((schDate.isEqual(currentDate) || schDate.isAfter(currentDate)) &&  //display range from currentDate to end of 2 weeks
                        schDate.isBefore(endDateOf2Weeks)){
                    table.add(sch);
                }
            }else{
                LocalDate date2 = DateTime.StringToLocalDate(date); //combobox date
                LocalDate endDate = date2.plusWeeks(1); //end of week
                if((schDate.equals(currentDate) || schDate.isAfter(currentDate)) && //display range from currentDate based on the selected comboDate to end of 1 week
                        (schDate.equals(date2) || schDate.isAfter(date2)) && 
                        (schDate.isBefore(endDate))){
                    table.add(sch);
                }
            }
            
        }
        return table;
    }
       
    private ArrayList<Schedule> Filter2 (ArrayList<Schedule> table){
        ArrayList<Schedule> table2 = new ArrayList<>();
        if(comboDate.getSelectedItem()==null){
            return table;
        }
        if(comboSport.getSelectedItem()==null){
            return table;
        }

        String sport2 = comboSport.getSelectedItem().toString();
        for(int i=0; i<table.size();i++){
            Schedule sch = table.get(i);
            if(sport2=="All Sports"){
                return table;
            }else if(sport2.equals(sch.getSportName())){
                table2.add(sch);
            }
        }
        return table2;
    }
    
    private void Filter(){
        ArrayList<Schedule> finalTable = Filter2(Filter1());
        DefaultTableModel model = (DefaultTableModel) tbGeneralSchedule.getModel();
        model.setRowCount(0);
        for(int i=0; i<finalTable.size();i++){
            Schedule s = finalTable.get(i);
            Object rowData[] = new Object[]{s.getID(),s.getScheduleDate(),s.getSportCenterName(),
                s.getSportName(), s.getSportLocation(),s.getStartTime(),
                s.getEndTime(),s.getCoachName(),s.getSportPax(),s.getSportFees(),s.getAvailability()};
            model.addRow(rowData);
        }
        
        RowSorter<TableModel> sorter = new TableRowSorter<>(tbGeneralSchedule.getModel());
        tbGeneralSchedule.setRowSorter(sorter);
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
    
    public void register(){
        boolean duplicate=false;
        boolean overlap=false;
        
        int selectedRowIndex = tbGeneralSchedule.getSelectedRow();
        
        if(tbGeneralSchedule.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"Please select a row to register!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            scheduleID = tbGeneralSchedule.getValueAt(selectedRowIndex,0).toString();
            scheduleDate = tbGeneralSchedule.getValueAt(selectedRowIndex,1).toString();
            sportCenter = tbGeneralSchedule.getValueAt(selectedRowIndex,2).toString();
            sport = tbGeneralSchedule.getValueAt(selectedRowIndex,3).toString();
            location = tbGeneralSchedule.getValueAt(selectedRowIndex,4).toString();
            start = tbGeneralSchedule.getValueAt(selectedRowIndex,5).toString();
            end = tbGeneralSchedule.getValueAt(selectedRowIndex,6).toString();
            coach = tbGeneralSchedule.getValueAt(selectedRowIndex,7).toString();
            fees = tbGeneralSchedule.getValueAt(selectedRowIndex,9).toString();
            availability = tbGeneralSchedule.getValueAt(selectedRowIndex,10).toString();
            
            if(!(availability=="Full")){
                //the registered schedule not being registered again
                String studentID = RCSAS.current.getID();
                for(int i=0; i<RCSAS.allRegister.size();i++){
                Register r = RCSAS.allRegister.get(i);
                if(scheduleID.equals(r.getScheduleID().getID()) && studentID.equals(r.getStudentID().getID())){
                    JOptionPane.showMessageDialog(null, "The selected schedule has been registered before!","Error",JOptionPane.ERROR_MESSAGE);
                    duplicate=true;
                }
                }

                //check overlap registered schedule
                MyRegister();

                for(int i=0; i<registeredSchedule.size();i++){
                    Schedule sch = registeredSchedule.get(i);
                    LocalTime startTime = LocalTime.parse(start);
                    LocalTime scheduleStart = LocalTime.parse(sch.getStartTime());
                    LocalTime scheduleEnd = LocalTime.parse(sch.getEndTime());
                    if(scheduleDate.equals(sch.getScheduleDate()) && 
                            (startTime.isAfter(scheduleStart) && startTime.isBefore(scheduleEnd))){
                        JOptionPane.showMessageDialog(null, "The selected schedule overlaps with schedule registered before!","Error",JOptionPane.ERROR_MESSAGE);
                        overlap=true;
                    }
                }

                //make payment
                if(duplicate==false && overlap==false){
                    PaymentDialog p = new PaymentDialog();
                    p.Update();
                    p.setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(null, "The selected schedule is FULL!","Error",JOptionPane.ERROR_MESSAGE);
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
        lblUsername = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        comboDate = new javax.swing.JComboBox<>();
        comboSport = new javax.swing.JComboBox<>();
        btnRegister = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGeneralSchedule = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(252, 252, 252)
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("General Schedule");

        comboDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDateActionPerformed(evt);
            }
        });

        comboSport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSportActionPerformed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(172, 3, 24));
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        tbGeneralSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Schedule Date", "Sport Center", "Sport", "Sport Location", "Start Time", "End Time", "Coach", "Sport Pax", "Sport Fees", "Availability"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGeneralSchedule.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbGeneralSchedule.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbGeneralSchedule.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbGeneralSchedule);
        if (tbGeneralSchedule.getColumnModel().getColumnCount() > 0) {
            tbGeneralSchedule.getColumnModel().getColumn(0).setMinWidth(100);
            tbGeneralSchedule.getColumnModel().getColumn(1).setMinWidth(150);
            tbGeneralSchedule.getColumnModel().getColumn(2).setMinWidth(150);
            tbGeneralSchedule.getColumnModel().getColumn(3).setMinWidth(150);
            tbGeneralSchedule.getColumnModel().getColumn(4).setMinWidth(150);
            tbGeneralSchedule.getColumnModel().getColumn(7).setMinWidth(150);
            tbGeneralSchedule.getColumnModel().getColumn(9).setMinWidth(150);
        }

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("<HTML>*Only 2 weeks schedule shown (from today onwards)<BR>*Tap column header to sort table</HTML> ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 817, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRegister)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(comboDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addComponent(comboSport, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister)
                    .addComponent(btnBack))
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDateActionPerformed
        Filter();
    }//GEN-LAST:event_comboDateActionPerformed

    private void comboSportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSportActionPerformed
        Filter();
    }//GEN-LAST:event_comboSportActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        register();
    }//GEN-LAST:event_btnRegisterActionPerformed

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
            java.util.logging.Logger.getLogger(StudentGeneralSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentGeneralSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentGeneralSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentGeneralSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentGeneralSchedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox<String> comboDate;
    private javax.swing.JComboBox<String> comboSport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tbGeneralSchedule;
    // End of variables declaration//GEN-END:variables
}
