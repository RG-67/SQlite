package com.example.sqlite;

public class DataModal {

    //  Creating variables for "User_ID", "User_Name", "User_number", "User_Email".
    private String id, personName, personNumber, personEmail;

    //  Creating getter method for "User_ID".
    public String getId() {
        return id;
    }

    //  Creating setter method for "User_ID".
    public void setId(String id) {
        this.id = id;
    }

    //  Creating getter method for "User_Name".
    public String getPersonName() {
        return personName;
    }

    //  Creating setter method for "User_Name".
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    //  Creating getter method for "User_Number".
    public String getPersonNumber() {
        return personNumber;
    }

    //  Creating setter method for "User_Number".
    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    //  Creating getter method for "User_Email".
    public String getPersonEmail() {
        return personEmail;
    }

    //  Creating setter method for "User_Email".
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    //  Creating constructor of "DataModel".
    public DataModal(String id, String personName, String personNumber, String personEmail) {
        this.id = id;
        this.personName = personName;
        this.personNumber = personNumber;
        this.personEmail = personEmail;
    }
}
