package com.code.competition_project;

public class SendToFirebase {
    String grade,class1,number,name,seatNumber,time;
    //여러 값을 Value로 넘기기

    public SendToFirebase(){}

    public String getGrade(){

        return grade;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }
    public String getClass1(){

        return class1;
    }
    public void setClass1(String class1){
        this.class1 = class1;
    }
    public String getSeatNumber(){

        return seatNumber;
    }
    public void setSeatNumber(String seatNumber){
        this.seatNumber = seatNumber;
    }
    public String getName(String name){

        return name;
    }
    public void setNumber(String name){
        this.name = name;
    }

    public SendToFirebase(String grade, String class1, String seatNumber, String name){
        this.grade = grade;
        this.class1 = class1;
        this.seatNumber = seatNumber;
        this.name = name;
    }
}
