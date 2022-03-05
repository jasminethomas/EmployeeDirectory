package com.jasmine.employeedirectory.model;

public class EmployeeModel {
    // variables for Employee details
    private int employeeId;
    private String employeeName;
    private String employeeUserName;
    private String employeeEmail;
    private String employeeProfileImage;
    private String employeeStreet;
    private String employeeSuite;
    private String employeeCity;
    private String employeeZipCode;
    private String employeeLatitude;
    private String employeeLongitude;
    private String employeePhone;
    private String employeeWebsite;
    private String employeeCompanyName;
    private String employeeCompanyCatchPhrase;
    private String employeeCompanyBs;

    //Constructor
    public EmployeeModel(int employeeId, String employeeName, String employeeUserName, String employeeEmail, String employeeProfileImage, String employeeStreet, String employeeSuite, String employeeCity, String employeeZipCode, String employeeLatitude, String employeeLongitude, String employeePhone, String employeeWebsite, String employeeCompanyName, String employeeCompanyCatchPhrase, String employeeCompanyBs) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeUserName = employeeUserName;
        this.employeeEmail = employeeEmail;
        this.employeeProfileImage = employeeProfileImage;
        this.employeeStreet = employeeStreet;
        this.employeeSuite = employeeSuite;
        this.employeeCity = employeeCity;
        this.employeeZipCode = employeeZipCode;
        this.employeeLatitude = employeeLatitude;
        this.employeeLongitude = employeeLongitude;
        this.employeePhone = employeePhone;
        this.employeeWebsite = employeeWebsite;
        this.employeeCompanyName = employeeCompanyName;
        this.employeeCompanyCatchPhrase = employeeCompanyCatchPhrase;
        this.employeeCompanyBs = employeeCompanyBs;
    }

    public EmployeeModel() {

    }

    //Getter and Setter methods
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeProfileImage() {
        return employeeProfileImage;
    }

    public void setEmployeeProfileImage(String employeeProfileImage) {
        this.employeeProfileImage = employeeProfileImage;
    }

    public String getEmployeeStreet() {
        return employeeStreet;
    }

    public void setEmployeeStreet(String employeeStreet) {
        this.employeeStreet = employeeStreet;
    }

    public String getEmployeeSuite() {
        return employeeSuite;
    }

    public void setEmployeeSuite(String employeeSuite) {
        this.employeeSuite = employeeSuite;
    }

    public String getEmployeeCity() {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        this.employeeCity = employeeCity;
    }

    public String getEmployeeZipCode() {
        return employeeZipCode;
    }

    public void setEmployeeZipCode(String employeeZipCode) {
        this.employeeZipCode = employeeZipCode;
    }

    public String getEmployeeLatitude() {
        return employeeLatitude;
    }

    public void setEmployeeLatitude(String employeeLatitude) {
        this.employeeLatitude = employeeLatitude;
    }

    public String getEmployeeLongitude() {
        return employeeLongitude;
    }

    public void setEmployeeLongitude(String employeeLongitude) {
        this.employeeLongitude = employeeLongitude;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeWebsite() {
        return employeeWebsite;
    }

    public void setEmployeeWebsite(String employeeWebsite) {
        this.employeeWebsite = employeeWebsite;
    }

    public String getEmployeeCompanyName() {
        return employeeCompanyName;
    }

    public void setEmployeeCompanyName(String employeeCompanyName) {
        this.employeeCompanyName = employeeCompanyName;
    }

    public String getEmployeeCompanyCatchPhrase() {
        return employeeCompanyCatchPhrase;
    }

    public void setEmployeeCompanyCatchPhrase(String employeeCompanyCatchPhrase) {
        this.employeeCompanyCatchPhrase = employeeCompanyCatchPhrase;
    }

    public String getEmployeeCompanyBs() {
        return employeeCompanyBs;
    }

    public void setEmployeeCompanyBs(String employeeCompanyBs) {
        this.employeeCompanyBs = employeeCompanyBs;
    }
}

