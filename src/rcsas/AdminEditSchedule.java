
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class AdminEditSchedule extends javax.swing.JFrame {

    /**
     * Creates new form AdminEditSchedule
     */
    
    public ArrayList<LocalTime> timeList = new ArrayList<>();
    public LocalTime sportCenterOpenTime;
    public LocalTime sportCenterCloseTime;
    private String firstLocation, firstStart, firstCoach, firstPax;
    
    public AdminEditSchedule() {
        initComponents();
        dayAfter2();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e){
                RCSAS.adminSchedule.setEnabled(true);
            }
        });
    }
    
    public void Update(){
        retrieveInfo();
        updateComboBox();
        TimeInterval();
        updateSportLocation();
        updateFirstLocation();
        updateSportCenterOpen();
        updateFirstStart();
        updateCoachName();
        updateFirstCoach();
        updatePax();
    }
    
    public void dayAfter2(){
        Date CurrentDate = new Date();
        Calendar cal = Calendar.getInstance(); //return the calendar
        cal.setTime(CurrentDate); //set the calendar time to current date
        cal.add(Calendar.DATE,1); //add one day after the current date
        Date dayAfter1 = cal.getTime(); //store the date modified above
        dcDate.setDateFormatString("dd-MMM-yyyy");
        dcDate.getJCalendar().setMinSelectableDate(dayAfter1);
        dcDate.setMinSelectableDate(dayAfter1);
    }
    
    public void retrieveInfo(){  
        this.lblScheduleID.setText(RCSAS.adminSchedule.editID);
        this.lblDateCreated.setText(RCSAS.adminSchedule.editDateCreated);
        this.dcDate.setDate(RCSAS.adminSchedule.editDate);  
    }
    
    public void updateComboBox(){
        comboSportCenter.removeAllItems();
        this.comboSportCenter.addItem(RCSAS.adminSchedule.editSportCenter);
        this.comboSportCenter.setEnabled(false);
        comboSport.removeAllItems();
        this.comboSport.addItem(RCSAS.adminSchedule.editSport);
        this.comboSport.setEnabled(false);
    }
    
    public void updateSportLocation(){
        comboLocation.removeAllItems();
        if(comboSportCenter.getSelectedItem() == null){
            return;
        }
        if(comboSport.getSelectedItem() == null){
            return;
        }
        String sportCenterName = comboSportCenter.getSelectedItem().toString();
        String sportName = comboSport.getSelectedItem().toString();
        for(int i=0; i<RCSAS.allLocation.size();i++){
            Location l = RCSAS.allLocation.get(i);
            if(sportCenterName.equals(l.getSportCenterName()) && sportName.equals(l.getSportName())){
                String location = l.getSportLocationName();
                comboLocation.addItem(location);
            }
        }
    }
    
    public void updateFirstLocation(){
        String locationSelected = RCSAS.adminSchedule.editLocation;
        for(int i=0; i<comboLocation.getItemCount();i++){
            String comboItem = comboLocation.getItemAt(i);
            if(locationSelected.equals(comboItem)){
                firstLocation = comboItem;
                break;
            }
        }
        comboLocation.setSelectedItem(firstLocation);
    }
    
    public void TimeInterval(){ //create lists of time intervals in LocalTime
        int interval = 30;
        String format;
        for (int h=8;h<23;h++){
            for(int m=0; m<60;){
                format = String.format("%02d:%02d", h,m);
                m=m+interval;
                LocalTime time = LocalTime.parse(format);
                timeList.add(time);
            }     
        }
    }
    
    public void updateSportCenterOpen(){ 
        comboStart.removeAllItems();
        if(comboSportCenter.getSelectedItem()==null){
            return;
        }
        String sportCenterName = comboSportCenter.getSelectedItem().toString();
        for(int i=0; i<RCSAS.allSportCenter.size();i++){
            SportCenter s = RCSAS.allSportCenter.get(i);
            if(sportCenterName.equals(s.getName())){
                String openTime = s.getOpenTime();
                String closeTime = s.getCloseTime();
                LocalTime openTime2 = LocalTime.parse(openTime);
                LocalTime closeTime2 = LocalTime.parse(closeTime);
                sportCenterOpenTime = openTime2;
                sportCenterCloseTime = closeTime2.minusHours(2);
                break;
            }
        }
        for(LocalTime i : timeList){
            if((i.equals(sportCenterOpenTime) || i.isAfter(sportCenterOpenTime)) && i.isBefore(sportCenterCloseTime)){ //0 means equal time, >0 means larger, <0 means smaller
                String availableTime = DateTime.Time(i);
                comboStart.addItem(availableTime);
            }
        }
    }
    
    public void updateFirstStart(){
        String startSelected = RCSAS.adminSchedule.editStart;
        for(int i=0; i<comboStart.getItemCount();i++){
            String comboItem = comboStart.getItemAt(i);
            if(startSelected.equals(comboItem)){
                firstStart = comboItem;
                break;
            }
        }
        comboStart.setSelectedItem(firstStart);
    }
    
    public void updateSportCenterClose(){
        if(comboStart.getSelectedItem()==null){
            return;
        }
        String sportCenterOpen = comboStart.getSelectedItem().toString();
        LocalTime openTime = LocalTime.parse(sportCenterOpen);
        LocalTime closeTime = openTime.plusHours(2);
        String closeTime2 = DateTime.Time(closeTime);
        lblEndTime.setText(closeTime2);
    }
    
    public void updateCoachName(){
        comboCoach.removeAllItems();
        if(comboSportCenter.getSelectedItem() == null){
            return;
        }
        if(comboSport.getSelectedItem() == null){
            return;
        }
        String sportCenterName = comboSportCenter.getSelectedItem().toString();
        String sportName = comboSport.getSelectedItem().toString();

        for(int i=0; i<RCSAS.allCoach.size(); i++){
            Coach c = RCSAS.allCoach.get(i);
            if(!(c.isIsTerminated())){
                if(sportCenterName.equals(c.getSportCenterName()) && sportName.equals(c.getSportName())){
                    String coach = c.getName();
                    comboCoach.addItem(coach);
                }
            }
        }

    }
    
    public void updateFirstCoach(){
        String coachSelected = RCSAS.adminSchedule.editCoach;
        for(int i=0; i<comboCoach.getItemCount();i++){
            String comboItem = comboCoach.getItemAt(i);
            if(coachSelected.equals(comboItem)){
                firstCoach = comboItem;
                break;
            }
        }
        comboCoach.setSelectedItem(firstCoach);
    }
    
    public void updatePax(){
        String paxSelected = RCSAS.adminSchedule.editPax;
        for(int i=0; i<comboPax.getItemCount();i++){
            String comboItem = comboPax.getItemAt(i);
            if(paxSelected.equals(comboItem)){
                firstPax = comboItem;
                break;
            }
        }
        comboPax.setSelectedItem(firstPax);
    }
    
    public void updateEditedInfo(){
        String scheduleID = lblScheduleID.getText();
        String date = DateTime.DateToString(dcDate.getDate());
        String sportCenter = comboSportCenter.getSelectedItem().toString();
        String sport = comboSport.getSelectedItem().toString();
        String location = comboLocation.getSelectedItem().toString();
        String startTime = comboStart.getSelectedItem().toString();
        String endTime = lblEndTime.getText();
        String coach = comboCoach.getSelectedItem().toString();
        String pax = comboPax.getSelectedItem().toString();
        
        LocalTime startTime2 = LocalTime.parse(startTime);
        
        boolean duplicate = false;
        boolean occupied = false;
        boolean overlap =false;
        for(int i=0; i<RCSAS.allSchedule.size();i++){
            Schedule s = RCSAS.allSchedule.get(i);
            LocalTime startTime3 = LocalTime.parse(s.getStartTime());
            LocalTime endTime3 = LocalTime.parse(s.getEndTime());
            if(scheduleID.equals(s.getID()) && //if the user edit nothing 
                    date.equals(s.getScheduleDate()) &&
                    sportCenter.equals(s.getSportCenterName()) &&
                    sport.equals(s.getSportName()) &&
                    location.equals(s.getSportLocation()) &&
                    startTime.equals(s.getStartTime()) &&
                    coach.equals(s.getCoachName())){
                duplicate = false;
            }else if(date.equals(s.getScheduleDate()) &&
                    sportCenter.equals(s.getSportCenterName()) &&
                    sport.equals(s.getSportName()) &&
                    startTime.equals(s.getStartTime()) &&
                    coach.equals(s.getCoachName())){
                JOptionPane.showMessageDialog(null,"Selected coach is occupied for this time! / Duplicated schedule!","Error",JOptionPane.ERROR_MESSAGE);
                occupied = true;
            }else if(date.equals(s.getScheduleDate()) &&
                    sportCenter.equals(s.getSportCenterName()) && 
                    coach.equals(s.getCoachName()) &&
                    (startTime2.isAfter(startTime3) && startTime2.isBefore(endTime3))){
                JOptionPane.showMessageDialog(null,"Selected time is overlapped with other class of this coach!","Error",JOptionPane.ERROR_MESSAGE);
                overlap=true;
            }
        }
        if(duplicate==false && occupied==false && overlap==false){
            String modifiedTime = DateTime.DateTime();
            for(int i=0; i<RCSAS.allSchedule.size();i++){
                Schedule s = RCSAS.allSchedule.get(i);
                if(scheduleID.equals(s.getID())){
                    s.setModifiedDate(modifiedTime);
                    s.setScheduleDate(date);
                    s.setSportLocation(location);
                    s.setStartTime(startTime);
                    s.setEndTime(endTime);
                    s.setCoachName(coach);
                    s.setSportPax(Integer.valueOf(pax));
                    JOptionPane.showMessageDialog(null,"Edit schedule successfully!");
                }
            }
            
            RCSAS.adminSchedule.Update();
            RCSAS.mainpage.insertSchedule();
            UpdateTxtFile();
            RCSAS.adminSchedule.setEnabled(true);
            this.setVisible(false);
        }
    }
          
    
    public void UpdateTxtFile(){
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
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblScheduleID = new javax.swing.JLabel();
        dcDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboSportCenter = new javax.swing.JComboBox<>();
        comboSport = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboLocation = new javax.swing.JComboBox<>();
        comboStart = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comboCoach = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDateCreated = new javax.swing.JLabel();
        lblEndTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboPax = new javax.swing.JComboBox<>();
        btnDone = new javax.swing.JButton();

        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(172, 3, 24));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Real Champion Sport Academy");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(93, 93, 93))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Schedule ID :");

        lblScheduleID.setText("SCH00001");

        jLabel7.setText("Date : ");

        jLabel8.setText("Start Time :");

        jLabel9.setText("End Time :");

        jLabel10.setText("Sport Center Name :");

        comboStart.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboStartItemStateChanged(evt);
            }
        });

        jLabel11.setText("Sport Name :");

        jLabel12.setText("Location :");

        jLabel13.setText("Coach Name :");

        jLabel3.setText("Date Created :");

        lblDateCreated.setText("jLabel4");

        lblEndTime.setText("jLabel5");

        jLabel4.setText("Sport Pax :");

        comboPax.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboSportCenter, 0, 225, Short.MAX_VALUE)
                    .addComponent(comboSport, 0, 225, Short.MAX_VALUE)
                    .addComponent(comboStart, 0, 225, Short.MAX_VALUE)
                    .addComponent(dcDate, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(comboCoach, 0, 225, Short.MAX_VALUE)
                    .addComponent(comboLocation, 0, 225, Short.MAX_VALUE)
                    .addComponent(lblScheduleID)
                    .addComponent(lblDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEndTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboPax, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblScheduleID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblDateCreated))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(comboSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(comboLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblEndTime))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(comboCoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboPax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDone)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnDone)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        updateEditedInfo();
    }//GEN-LAST:event_btnDoneActionPerformed

    private void comboStartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboStartItemStateChanged
        updateSportCenterClose();
    }//GEN-LAST:event_comboStartItemStateChanged

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
            java.util.logging.Logger.getLogger(AdminEditSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminEditSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminEditSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminEditSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminEditSchedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    private javax.swing.JComboBox<String> comboCoach;
    private javax.swing.JComboBox<String> comboLocation;
    private javax.swing.JComboBox<String> comboPax;
    private javax.swing.JComboBox<String> comboSport;
    private javax.swing.JComboBox<String> comboSportCenter;
    private javax.swing.JComboBox<String> comboStart;
    private com.toedter.calendar.JDateChooser dcDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDateCreated;
    private javax.swing.JLabel lblEndTime;
    private javax.swing.JLabel lblScheduleID;
    // End of variables declaration//GEN-END:variables
}
