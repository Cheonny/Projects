package rcsas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public Validation() {
    }
    
    public boolean ValidatePhoneNo(String phoneNo) {
        //phoneNo = 012-3456789
        if (phoneNo.matches("\\d{3}[-\\\\.\\\\s]\\d{8}")) {  //11 digit phoneNo
            return true;
        }else if (phoneNo.matches("\\d{3}[-\\\\.\\\\s]\\d{7}")) { //10 digit phoneNo
            return true;
        }else if (phoneNo.matches("\\d{2}[-\\\\.\\\\s]\\d{7}")) { //9 digit phoneNo
            return true;
        }else if (phoneNo.matches("\\d{2}[-\\\\.\\\\s]\\d{8}")) { //10 digit phoneNo
            return true;
        }else{
            return false;
        }
    }
    public boolean ValidateEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(email.matches(regex)){
            return true;
        }else{     
            return false;
        }
    }
    public boolean ValidateAge(int age){
        if(age>1 && age<100){
            return true;
        }else{
            return false;
        }   
    }
    public boolean ValidateName(String name){
        String regex = "^[a-zA-Z ./]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean ValidatePassword(String password){
        String regex = "^[a-zA-Z0-9.@/_*#!]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
    
    public String CapitalizeLetter(String str){
        StringBuilder result = new StringBuilder(str.length());
        String words[] = str.split(" ");
        for (int i = 0; i < words.length; i++){
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");
        }
        return result.toString().trim();
    }
    
    public boolean ValidateOpen(String str){
        String regex = "^(2[0-1]|[0]?[7-9]|[1]?[0-9]):([0-5]?[0-9])$"; //07:00 - 21:00
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public boolean ValidateClose(String str){
        String regex = "^(2[0-3]|[0]?[9]|[1]?[0-9]):([0-5]?[0-9])$"; //09:01 - 23:59
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
}




//[\\w-_\\.+]should start with (a-z 0-9) or hyphen, underscore, dot or a plus symbol
//* symbol means match the preceding zero or more times.
//\@([\w]+\.)+.  should contains the @ symbol followed by one or more word separated by the dot symbol
//[\w]+[\w] check that after the last period there should be another word for the domain suffix such as the co.uk or co.id.
//$ ends by a word character