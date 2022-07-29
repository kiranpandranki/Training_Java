package Models;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Contact {
    private String name;
    private String phoneNum;
    private String birthDate;
    private int age;

    public Contact(String name,String phoneNum,String birthDate) throws ParseException{
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        if(phoneNum==null || phoneNum.isBlank()){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        if(phoneNum.length()>10){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        if(birthDate==null || birthDate.isBlank()){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        this.name = name;
        this.phoneNum = phoneNum;
        this.birthDate = birthDate;
        this.age = toAge(birthDate);
    }

    public Contact(Contact source){
        this.name = source.name;
        this.age = source.age;
        this.birthDate = source.birthDate;
        this.phoneNum = source.phoneNum;
    }

    public int getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        if(birthDate==null || birthDate.isBlank()){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        this.birthDate = birthDate;
        setAge(toAge(birthDate));
    }

    public void setName(String name) {
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        if(phoneNum==null || phoneNum.isBlank()){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        if(phoneNum.length()>10){
            throw new IllegalArgumentException("Birthdate cannot be null/Blank");
        }
        this.phoneNum = phoneNum;
    }

    public int toAge(String birthDate) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setLenient(false);
        Date toDate = formatter.parse(birthDate);
        long toMilli = toDate.getTime();
        long diff = new Date().getTime()-toMilli;
        return (int)TimeUnit.MILLISECONDS.toDays(diff)/365;
    }

    public String toString(){
        return "name : "+this.name+"\n"+
                "Phone number : "+this.phoneNum+"\n"+
                "Birth Date : "+this.birthDate+"\n"+
                "Age : "+this.age+"\n";
    }

}
