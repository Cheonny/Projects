/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author User
 */
public class DateTime {
        public static String DateTime(){
	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	LocalDateTime currentDateTime = LocalDateTime.now();
	String dateCreated = pattern.format(currentDateTime);
        return dateCreated;
    }
    
    public static String DateToString(Date var){
        SimpleDateFormat pattern = new SimpleDateFormat("dd-MMM-yyyy");
        String date = pattern.format(var);
        return date;
    }
    
    public static Date StringToDate(String var) throws ParseException{
        SimpleDateFormat pattern = new SimpleDateFormat("dd-MMM-yyyy");
        Date finalDate = pattern.parse(var);
        return finalDate;     
    }
    
    public static String Time(LocalTime var){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("HH:mm");
	String time = pattern.format(var);
        return time;
    }
    
    public static String LocalDateToString(LocalDate var){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	String date = pattern.format(var);
        return date;
    }
    
    public static LocalDate StringToLocalDate(String date){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate date2 = LocalDate.parse(date,pattern);
        return date2;
    }
}
