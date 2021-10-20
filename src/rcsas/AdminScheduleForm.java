/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.io.PrintWriter;
import java.text.ParseException;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class AdminScheduleForm extends javax.swing.JFrame {

    /**
     * Creates new form AdminScheduleForm2
     */
    public ArrayList<LocalTime> timeList = new ArrayList<>();
    public LocalTime sportCenterOpenTime;
    public LocalTime sportCenterCloseTime;
    public ArrayList<LocalDate> Mondays = new ArrayList<>();
    public LocalDate comboRealDate;
    public String editID, editDateCreated, editScheduleDate, editSportCenter,
            editSport, editLocation, editStart, editCoach, editPax;
    public Date editDate;

    
    public AdminScheduleForm() {
        initComponents();
    }
    
    public void Update(){
        lblUsername.setText(RCSAS.current.getUsername());
        dayAfter2();
        dcDate.setCalendar(null);
        TimeInterval();
        insertToJtable();
        updateSportCenterInfo();
        updateFilterSportCenter();
        updateSportCenterOpen();
        updateSportCenterClose();
        updateScheduleID();
        updateMonday();
        updateFilterDate();
        updateFilterSport();
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
    
    public void updateSportCenterInfo(){ //sportcenter combobox
        comboSportCenter.removeAllItems();

        if(RCSAS.loginpage.isAdmin==true){
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin a = RCSAS.allAdmin.get(i);
                if(RCSAS.current.getID().equals(a.getID())){
                    comboSportCenter.addItem(a.getSportCenterName());
                    comboSportCenter.setEnabled(false);
                    break;
                }
            }
        }else if(RCSAS.loginpage.isHQAdmin==true){
            for(int i=0; i<RCSAS.allSportCenter.size();i++){
                SportCenter c = RCSAS.allSportCenter.get(i);
                String name = c.getName();
                comboSportCenter.addItem(name);
            }
        }
    }
    
    public void updateFilterSportCenter(){
        comboFilterSportCenter.removeAllItems();
        
        if(RCSAS.loginpage.isAdmin==true){
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin a = RCSAS.allAdmin.get(i);
                if(RCSAS.current.getID().equals(a.getID())){
                    comboFilterSportCenter.addItem(a.getSportCenterName());
                    comboFilterSportCenter.setEnabled(false);
                    break;
                }
            }
        }else if(RCSAS.loginpage.isHQAdmin==true){
            comboFilterSportCenter.addItem("All Sport Centers");
            for(int i=0; i<RCSAS.allSportCenter.size();i++){
                SportCenter c = RCSAS.allSportCenter.get(i);
                String name = c.getName();
                comboFilterSportCenter.addItem(name);
            }
        }
    }
    
    public void updateScheduleID(){  
        int size = RCSAS.allSchedule.size();
        if(size==0){
            lblScheduleID.setText("SCH00001");
        }else{
            int ID = RCSAS.allSchedule.size()+1;
            String format = String.format("%s%05d","SCH",ID);
            lblScheduleID.setText(format);
        }
    }
    
    public void updateSportName(){
        comboSport.removeAllItems();
        if(comboSportCenter.getSelectedItem() == null){
            return;
        }
        
        String sportCenterName = comboSportCenter.getSelectedItem().toString();
        for(int i=0; i<RCSAS.allSport.size();i++){
            Sport s = RCSAS.allSport.get(i);
            if(sportCenterName.equals(s.getSportCenterName())){
                String sport = s.getSportName();
                comboSport.addItem(sport);
            }
        }
        updateCoachName();
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
            if((i.equals(sportCenterOpenTime) || i.isAfter(sportCenterOpenTime)) && i.isBefore(sportCenterCloseTime)){ 
                String availableTime = DateTime.Time(i);
                comboStart.addItem(availableTime);
            }
        }
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
    
    //weekly for comboDate values  
    private static LocalDate firstDayOfMonth(){
        YearMonth now = YearMonth.now();
        return now.atDay(1).with(DayOfWeek.MONDAY);
    }
    
    private static boolean lastDayOfMonth(YearMonth yearMonth, LocalDate day){
        return !day.isAfter(yearMonth.atEndOfMonth());
    }
    
    public void updateMonday(){ //put the mondays of the month to an arraylist
        Mondays.clear();
        for(LocalDate day = firstDayOfMonth(); lastDayOfMonth(YearMonth.now(), day); day = day.plusWeeks(1)){
            Mondays.add(day);
        }
    }
    
    public void updateFilterDate(){
        comboDate.removeAllItems();
        comboDate.addItem("All Dates");
        

        for(LocalDate i : Mondays){
            String mondays = DateTime.LocalDateToString(i);
            comboDate.addItem(mondays);
        }
    }
    
    public void updateFilterSport(){ //3
        comboFilterSport.removeAllItems();
        if(comboDate.getSelectedItem()==null){
            return;
        }
        if(comboFilterSportCenter.getSelectedItem()==null){
            return;
        }
        comboFilterSport.addItem("All Sports"); //for all sports
        Set<String> sport = new HashSet<>();
        String date = comboDate.getSelectedItem().toString();
        String sportCenterName = comboFilterSportCenter.getSelectedItem().toString();
        
        for(int i=0; i<RCSAS.allSchedule.size(); i++){
            Schedule sch = RCSAS.allSchedule.get(i);
            String schDate = sch.getScheduleDate();
            
            if(!(date=="All Dates")){
                LocalDate date2 = DateTime.StringToLocalDate(date); //comboDate to LocalDate
                LocalDate schDate2 = DateTime.StringToLocalDate(schDate); //scheduleDate to LocalDate
                LocalDate date3 = date2.plusDays(7); //end day of the week

                if((schDate2.isAfter(date2) || schDate2.equals(date2)) && schDate2.isBefore(date3)){
                    if(sportCenterName.equals(sch.getSportCenterName())){ //1 2 3
                        String scheduleSport = sch.getSportName();
                        sport.add(scheduleSport);
                    }else if (sportCenterName == "All Sport Centers"){ //2 3
                        String scheduleSport = sch.getSportName();
                        sport.add(scheduleSport);
                    }
                }
            }else if(date =="All Dates" && sportCenterName == "All Sport Centers"){ //3
                String scheduleSport = sch.getSportName();
                sport.add(scheduleSport);
            }else if(date == "All Dates" && sportCenterName.equals(sch.getSportCenterName())){ //1 3
                String scheduleSport = sch.getSportName();
                sport.add(scheduleSport);
            } 

        }
        for(String i : sport){
            comboFilterSport.addItem(i);
        }
    }
    
    
    public void updateFilterCoach(){ //4 
        comboFilterCoach.removeAllItems();
        if(comboFilterSportCenter.getSelectedItem()==null){
            return;
        }
        if(comboFilterSport.getSelectedItem()==null){
            return;
        }
        if(comboDate.getSelectedItem()==null){
            return;
        }
        comboFilterCoach.addItem("All Coaches"); //for all coach
        Set<String> coach = new HashSet<>();
        String date = comboDate.getSelectedItem().toString();
        String sportCenterName = comboFilterSportCenter.getSelectedItem().toString();
        String sport = comboFilterSport.getSelectedItem().toString();
        
        for(int i=0; i<RCSAS.allSchedule.size(); i++){
            Schedule sch = RCSAS.allSchedule.get(i);
            String schDate = sch.getScheduleDate();
            
            if(!(date=="All Dates")){ //2
                LocalDate date2 = DateTime.StringToLocalDate(date); //comboDate to LocalDate
                LocalDate schDate2 = DateTime.StringToLocalDate(schDate); //scheduleDate to LocalDate
                LocalDate date3 = date2.plusDays(7); //end day of the week

                if((schDate2.isAfter(date2) || schDate2.equals(date2)) && schDate2.isBefore(date3)){
                    if(sportCenterName=="All Sport Centers" && sport.equals(sch.getSportName())){ //2 3 4
                        String scheduleCoach = sch.getCoachName();
                        coach.add(scheduleCoach);
                    }else if(sport=="All Sports" && sportCenterName.equals(sch.getSportCenterName())){ //1 2 4
                        String scheduleCoach = sch.getCoachName();
                        coach.add(scheduleCoach);
                    }else if(sport=="All Sports" && sportCenterName=="All Sport Centers"){ //2 4
                        String scheduleCoach = sch.getCoachName();
                        coach.add(scheduleCoach);
                    }else if(sportCenterName.equals(sch.getSportCenterName()) && sport.equals(sch.getSportName())){ //1 2 3 4
                        String scheduleCoach = sch.getCoachName();
                        coach.add(scheduleCoach);
                    }
                }
            }else if(sportCenterName=="All Sport Centers" && sport=="All Sports" && date=="All Dates" ){ //4
                String scheduleCoach = sch.getCoachName();
                coach.add(scheduleCoach);
            }else if(date=="All Dates" && sportCenterName.equals(sch.getSportCenterName()) && sport.equals(sch.getSportName())){ //1 3 4
                String scheduleCoach = sch.getCoachName();
                coach.add(scheduleCoach);
            }else if(sportCenterName.equals(sch.getSportCenterName())){ //1 4
                String scheduleCoach = sch.getCoachName();
                coach.add(scheduleCoach);
            }else if(sport.equals(sch.getSportName())){ //3 4
                String scheduleCoach = sch.getCoachName();
                coach.add(scheduleCoach);
            }
            
        }
        for(int i=0;i<RCSAS.allCoach.size();i++){
            Coach c = RCSAS.allCoach.get(i);
            for (String i2 : coach){
                if(i2.equals(c.getName())){
                    if(!(c.isIsTerminated())){
                        comboFilterCoach.addItem(i2);
                    }
                }
            }
        }


    }
    
    private ArrayList<Schedule> Filter1(){
        ArrayList<Schedule> table = new ArrayList<>();
        if(comboFilterSportCenter.getSelectedItem()==null){ //sportcenter filter
            return table;
        }
        String sportCenterName = comboFilterSportCenter.getSelectedItem().toString();
        for(int i = 0; i<RCSAS.allSchedule.size();i++){
            Schedule s = RCSAS.allSchedule.get(i);
            if(sportCenterName == "All Sport Centers"){
                for(int a=0;a<RCSAS.allCoach.size();a++){
                    Coach b = RCSAS.allCoach.get(a);
                    if(s.getCoachName().equals(b.getName())){
                        if(b.isIsTerminated()){
                            LocalDate schDate = DateTime.StringToLocalDate(s.getScheduleDate());
                            if(schDate.isBefore(LocalDate.now())){
                                table.add(s);
                            }
                        }else if(!(b.isIsTerminated())){
                            table.add(s);
                        }
                    }
                }
            }else if(sportCenterName.equals(s.getSportCenterName())){
                for(int a=0;a<RCSAS.allCoach.size();a++){
                    Coach b = RCSAS.allCoach.get(a);
                    if(s.getCoachName().equals(b.getName())){
                        if(b.isIsTerminated()){
                            LocalDate schDate = DateTime.StringToLocalDate(s.getScheduleDate());
                            if(schDate.isBefore(LocalDate.now())){
                                table.add(s);
                            }
                        }else if(!(b.isIsTerminated())){
                            table.add(s);
                        }
                    }
                }
            }
        }
        return table;
    }
    
    private ArrayList<Schedule> Filter2(ArrayList<Schedule> table){ //date filter
        if(comboDate.getSelectedItem()==null){
            return table;
        }
        ArrayList<Schedule> table2 = new ArrayList<>();
        String date = comboDate.getSelectedItem().toString();
        
        for(int i=0; i<table.size();i++){
            Schedule s = table.get(i);
            if(date=="All Dates"){
                return table;
            }else{
                LocalDate date2 = DateTime.StringToLocalDate(date);
                LocalDate schDate = DateTime.StringToLocalDate(s.getScheduleDate());
                LocalDate endDate = date2.plusDays(7);
                if((schDate.isAfter(date2) || schDate.equals(date2)) && schDate.isBefore(endDate)){
                    table2.add(s);
                }
            }
        }
        return table2;
    }
    
    private ArrayList<Schedule> Filter3(ArrayList<Schedule> table2){ //sport filter
        ArrayList<Schedule> table3 = new ArrayList<>();
        if(comboFilterSport.getSelectedItem()==null){
            return table2;
        }
        String sportName = comboFilterSport.getSelectedItem().toString();
        
        for(int i=0; i<table2.size();i++){
            Schedule s = table2.get(i);
            if(sportName == "All Sports"){
                return table2;
            }else if(sportName.equals(s.getSportName())){
                table3.add(s);
            }
        }
        return table3;
    }
    
    private ArrayList<Schedule> Filter4(ArrayList<Schedule> table3){ //coach filter
        ArrayList<Schedule> table4 = new ArrayList<>();
        if(comboFilterCoach.getSelectedItem()==null){
            return table3;
        }
        String coachName = comboFilterCoach.getSelectedItem().toString();
        
        for(int i=0; i<table3.size();i++){
            Schedule s = table3.get(i);
            if(coachName == "All Coaches"){
                return table3;
            }else if(coachName.equals(s.getCoachName())){
                table4.add(s);
            }
        }
        return table4;
    }
    
    private void Filter(){
        ArrayList<Schedule> finalTable = Filter4(Filter3(Filter2(Filter1())));
        DefaultTableModel model = (DefaultTableModel) tbSportSchedule.getModel();
        model.setRowCount(0);
        for(int i=0; i<finalTable.size();i++){
            Schedule s = finalTable.get(i);
            Object rowData[] = new Object[]{s.getID(),s.getCreatedDate(),s.getModifiedDate(),
                    s.getScheduleDate(),s.getSportCenterName(),s.getSportName(), s.getSportLocation(),
                    s.getStartTime(),s.getEndTime(),s.getCoachName(),s.getSportPax()};
            model.addRow(rowData);
        }    
    }  
    
    public void insertToJtable(){
        DefaultTableModel model = (DefaultTableModel) tbSportSchedule.getModel();
        model.setRowCount(0);
        if(RCSAS.loginpage.isHQAdmin==true){
            for(int i=0; i<RCSAS.allSchedule.size();i++){
                Schedule s = RCSAS.allSchedule.get(i);
                for(int a=0; a<RCSAS.allCoach.size();a++){
                    Coach c = RCSAS.allCoach.get(a);
                    if(s.getCoachName().equals(c.getName())){
                        if(c.isIsTerminated()){
                            LocalDate schDate = DateTime.StringToLocalDate(s.getScheduleDate());
                            if(schDate.isBefore(LocalDate.now())){
                                Object rowData[] = new Object[]{s.getID(),s.getCreatedDate(),s.getModifiedDate(),
                                    s.getScheduleDate(),s.getSportCenterName(),s.getSportName(), s.getSportLocation(),
                                    s.getStartTime(),s.getEndTime(),s.getCoachName(),s.getSportPax()};
                                model.addRow(rowData);
                            }
                        }else if(!(c.isIsTerminated())){
                            Object rowData[] = new Object[]{s.getID(),s.getCreatedDate(),s.getModifiedDate(),
                                s.getScheduleDate(),s.getSportCenterName(),s.getSportName(), s.getSportLocation(),
                                s.getStartTime(),s.getEndTime(),s.getCoachName(),s.getSportPax()};
                            model.addRow(rowData);
                        }
                    }
                }
                
            }
        }else if(RCSAS.loginpage.isAdmin==true){
            String adminID = RCSAS.current.getID();
            for(int i=0; i<RCSAS.allAdmin.size(); i++){
                Admin a = RCSAS.allAdmin.get(i);
                if(adminID.equals(a.getID())){
                    String sportCenter = a.getSportCenterName();
                    for(int ii=0; ii<RCSAS.allSchedule.size(); ii++){
                        Schedule s = RCSAS.allSchedule.get(ii);
                        if(sportCenter.equals(s.getSportCenterName())){
                            for(int b=0; b<RCSAS.allCoach.size();b++){
                                Coach c = RCSAS.allCoach.get(b);
                                if(s.getCoachName().equals(c.getName())){
                                    if(c.isIsTerminated()){
                                        LocalDate schDate = DateTime.StringToLocalDate(s.getScheduleDate());
                                        if(schDate.isBefore(LocalDate.now())){
                                            Object rowData[] = new Object[]{s.getID(),s.getCreatedDate(),s.getModifiedDate(),
                                                s.getScheduleDate(),s.getSportCenterName(),s.getSportName(), s.getSportLocation(),
                                                s.getStartTime(),s.getEndTime(),s.getCoachName(),s.getSportPax()};
                                            model.addRow(rowData);
                                        }
                                    }else if(!(c.isIsTerminated())){
                                        Object rowData[] = new Object[]{s.getID(),s.getCreatedDate(),s.getModifiedDate(),
                                                s.getScheduleDate(),s.getSportCenterName(),s.getSportName(), s.getSportLocation(),
                                                s.getStartTime(),s.getEndTime(),s.getCoachName(),s.getSportPax()};
                                        model.addRow(rowData);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        RowSorter<TableModel> sorter = new TableRowSorter<>(tbSportSchedule.getModel());
        tbSportSchedule.setRowSorter(sorter);
    }    
    
    public void addSchedule(){
        boolean overlap = false;
        boolean occupied = false;
        if(dcDate.getDate()==null || comboSportCenter.getSelectedItem()==null || comboSport.getSelectedItem()==null ||
                comboLocation.getSelectedItem()==null || comboCoach.getSelectedItem()==null){
            JOptionPane.showMessageDialog(null, "Empty input(s)!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            String scheduleID = lblScheduleID.getText();
            String dateCreated = DateTime.DateTime();
            String dateModified = DateTime.DateTime();
            String date = DateTime.DateToString(dcDate.getDate());
            String sportCenter = comboSportCenter.getSelectedItem().toString();
            String sportName = comboSport.getSelectedItem().toString();
            String sportLocation = comboLocation.getSelectedItem().toString();
            String startTime = comboStart.getSelectedItem().toString();
            String endTime = lblEndTime.getText(); 
            String coachName = comboCoach.getSelectedItem().toString();
            String sportPax = comboPax.getSelectedItem().toString();
            
            LocalTime startTime2 = LocalTime.parse(startTime);
            
            for(int i=0; i<RCSAS.allSchedule.size(); i++){
                Schedule sch = RCSAS.allSchedule.get(i);
                LocalTime startTime3 = LocalTime.parse(sch.getStartTime());
                LocalTime endTime3 = LocalTime.parse(sch.getEndTime());
                if(date.equals(sch.getScheduleDate()) && 
                        sportCenter.equals(sch.getSportCenterName()) && 
                        sportName.equals(sch.getSportName()) &&  
                        startTime.equals(sch.getStartTime()) && 
                        coachName.equals(sch.getCoachName())){
                    JOptionPane.showMessageDialog(null,"Selected coach is occupied for this time! / Duplicated Schedule!","Error",JOptionPane.ERROR_MESSAGE);
                    occupied = true;
                }else if(date.equals(sch.getScheduleDate()) &&
                        sportCenter.equals(sch.getSportCenterName()) && 
                        sportName.equals(sch.getSportName()) &&  
                        coachName.equals(sch.getCoachName()) &&
                        (startTime2.isAfter(startTime3) && startTime2.isBefore(endTime3))){
                    JOptionPane.showMessageDialog(null,"Selected time is overlapped with other class of this coach!","Error",JOptionPane.ERROR_MESSAGE);
                    overlap=true;
                }
            }
            
            if(overlap ==false && occupied == false){
                Schedule sch2 = new Schedule(scheduleID,dateCreated,dateModified,date,sportCenter,sportName,
                        sportLocation,startTime,endTime,coachName,Integer.parseInt(sportPax),"Available");
                JOptionPane.showMessageDialog(null, "New schedule added!");
                
                RCSAS.allSchedule.add(sch2);
                updateFilterSportCenter();
                updateFilterDate();
                updateFilterSport();
                updateFilterCoach();
                updateSportCenterInfo();
                dcDate.setCalendar(null);
                insertToJtable();
                updateTxtFile();
                updateScheduleID();
                RCSAS.mainpage.insertSchedule();
            }
        }
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
    
    public void EditSchedule() throws ParseException{
        int selectedRowIndex = tbSportSchedule.getSelectedRow();
        
        if(tbSportSchedule.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"Please select a row to edit!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            
            editID = tbSportSchedule.getValueAt(selectedRowIndex,0).toString();
            editDateCreated = tbSportSchedule.getValueAt(selectedRowIndex,1).toString();
            editScheduleDate = tbSportSchedule.getValueAt(selectedRowIndex,3).toString();
            editSportCenter = tbSportSchedule.getValueAt(selectedRowIndex,4).toString();
            editSport = tbSportSchedule.getValueAt(selectedRowIndex,5).toString();
            editLocation = tbSportSchedule.getValueAt(selectedRowIndex,6).toString();
            editStart = tbSportSchedule.getValueAt(selectedRowIndex,7).toString();
            editCoach = tbSportSchedule.getValueAt(selectedRowIndex,9).toString();
            editPax = tbSportSchedule.getValueAt(selectedRowIndex,10).toString();


            LocalDate realScheduleDate = DateTime.StringToLocalDate(editScheduleDate);
            LocalDate currentDate = LocalDate.now();
            editDate = DateTime.StringToDate(editScheduleDate);

            if(realScheduleDate.isBefore(currentDate)){
                JOptionPane.showMessageDialog(null,"Past Schedule cannot be edited!","Error",JOptionPane.ERROR_MESSAGE);
            }else if(realScheduleDate.equals(currentDate)){
                JOptionPane.showMessageDialog(null,"Today's Schedule cannot be edited!","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                this.setEnabled(false);
                RCSAS.adminEditSchedule.Update();
                RCSAS.adminEditSchedule.setVisible(true);
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
        jLabel3 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        comboFilterCoach = new javax.swing.JComboBox<>();
        comboFilterSport = new javax.swing.JComboBox<>();
        comboFilterSportCenter = new javax.swing.JComboBox<>();
        comboDate = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSportSchedule = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblScheduleID = new javax.swing.JLabel();
        comboLocation = new javax.swing.JComboBox<>();
        comboStart = new javax.swing.JComboBox<>();
        comboCoach = new javax.swing.JComboBox<>();
        comboSportCenter = new javax.swing.JComboBox<>();
        comboSport = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        comboPax = new javax.swing.JComboBox<>();
        lblEndTime = new javax.swing.JLabel();
        dcDate = new com.toedter.calendar.JDateChooser();
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Current Login:");

        lblUsername.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsername.setText("123456789");
        lblUsername.setFocusable(false);
        lblUsername.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

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
                .addGap(252, 252, 252)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
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
                            .addComponent(jLabel3)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogout))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(700, 851));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Schedule");

        comboFilterCoach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFilterCoachActionPerformed(evt);
            }
        });

        comboFilterSport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFilterSportItemStateChanged(evt);
            }
        });
        comboFilterSport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFilterSportActionPerformed(evt);
            }
        });

        comboFilterSportCenter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFilterSportCenterItemStateChanged(evt);
            }
        });
        comboFilterSportCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFilterSportCenterActionPerformed(evt);
            }
        });

        comboDate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDateItemStateChanged(evt);
            }
        });
        comboDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDateActionPerformed(evt);
            }
        });

        tbSportSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schedule ID", "Date Created", "Date Modified", "Schedule Date", "Sport Center Name", "Sport Name", "Location", "Start Time", "End Time", "Coach Name", "Sport Pax"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSportSchedule.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbSportSchedule.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSportSchedule.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbSportSchedule);
        if (tbSportSchedule.getColumnModel().getColumnCount() > 0) {
            tbSportSchedule.getColumnModel().getColumn(0).setMinWidth(100);
            tbSportSchedule.getColumnModel().getColumn(1).setMinWidth(200);
            tbSportSchedule.getColumnModel().getColumn(2).setMinWidth(200);
            tbSportSchedule.getColumnModel().getColumn(3).setMinWidth(100);
            tbSportSchedule.getColumnModel().getColumn(4).setMinWidth(200);
            tbSportSchedule.getColumnModel().getColumn(5).setMinWidth(100);
            tbSportSchedule.getColumnModel().getColumn(6).setMinWidth(200);
            tbSportSchedule.getColumnModel().getColumn(7).setMinWidth(100);
            tbSportSchedule.getColumnModel().getColumn(8).setMinWidth(100);
            tbSportSchedule.getColumnModel().getColumn(9).setMinWidth(100);
            tbSportSchedule.getColumnModel().getColumn(10).setMinWidth(100);
        }

        btnEdit.setBackground(new java.awt.Color(172, 3, 24));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit Selected");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Schedule ID :");

        jLabel7.setText("Date : ");

        jLabel8.setText("Start Time :");

        jLabel9.setText("End Time :");

        jLabel10.setText("Sport Center Name :");

        jLabel11.setText("Sport Name :");

        jLabel12.setText("Location :");

        jLabel13.setText("Coach Name :");

        jLabel14.setText("Sport Pax : ");

        lblScheduleID.setText("SCH00001");

        comboStart.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboStartItemStateChanged(evt);
            }
        });

        comboSportCenter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSportCenterItemStateChanged(evt);
            }
        });

        comboSport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSportItemStateChanged(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(172, 3, 24));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        comboPax.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20" }));

        lblEndTime.setText("08:00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(342, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboLocation, 0, 225, Short.MAX_VALUE)
                            .addComponent(comboStart, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboCoach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboSportCenter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboSport, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblScheduleID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboPax, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblScheduleID))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(dcDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblEndTime))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(comboCoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(comboPax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("* Weekly (by this month)");

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("*Tap column header to sort table");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator1)
                .addGap(46, 46, 46))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboFilterSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboDate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addComponent(comboFilterSport, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(comboFilterCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFilterSport, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFilterSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFilterCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addGap(13, 13, 13))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addSchedule();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        if(RCSAS.loginpage.isHQAdmin==true){
            RCSAS.adminMain.setVisible(true);
        }else{
            RCSAS.adminMain2.setVisible(true);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            EditSchedule();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnEditActionPerformed
    
    private void comboSportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSportItemStateChanged
        updateSportLocation();
        updateCoachName();        
    }//GEN-LAST:event_comboSportItemStateChanged

    private void comboSportCenterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSportCenterItemStateChanged
        updateSportName();
        updateSportCenterOpen();
    }//GEN-LAST:event_comboSportCenterItemStateChanged

    private void comboStartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboStartItemStateChanged
        updateSportCenterClose();
    }//GEN-LAST:event_comboStartItemStateChanged

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        RCSAS.mainpage.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void comboFilterSportCenterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFilterSportCenterItemStateChanged
        updateFilterSport();
    }//GEN-LAST:event_comboFilterSportCenterItemStateChanged

    private void comboDateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDateItemStateChanged
        updateFilterSport();
    }//GEN-LAST:event_comboDateItemStateChanged

    private void comboFilterSportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFilterSportItemStateChanged
        updateFilterCoach();
    }//GEN-LAST:event_comboFilterSportItemStateChanged

    private void comboFilterSportCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFilterSportCenterActionPerformed
        Filter();
    }//GEN-LAST:event_comboFilterSportCenterActionPerformed

    private void comboDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDateActionPerformed
        Filter();
    }//GEN-LAST:event_comboDateActionPerformed

    private void comboFilterSportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFilterSportActionPerformed
        Filter();
    }//GEN-LAST:event_comboFilterSportActionPerformed

    private void comboFilterCoachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFilterCoachActionPerformed
        Filter();
    }//GEN-LAST:event_comboFilterCoachActionPerformed

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
            java.util.logging.Logger.getLogger(AdminScheduleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminScheduleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminScheduleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminScheduleForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminScheduleForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JComboBox<String> comboCoach;
    private javax.swing.JComboBox<String> comboDate;
    private javax.swing.JComboBox<String> comboFilterCoach;
    private javax.swing.JComboBox<String> comboFilterSport;
    private javax.swing.JComboBox<String> comboFilterSportCenter;
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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblEndTime;
    private javax.swing.JLabel lblScheduleID;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tbSportSchedule;
    // End of variables declaration//GEN-END:variables
}
