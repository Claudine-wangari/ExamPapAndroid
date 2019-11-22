package com.example.exampap.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String studentNumber;
    private String password;


    public LoggedInUser(String studentNumber, String password){
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoggedInUser(){

    }
    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
