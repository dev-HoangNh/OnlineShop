/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

public class Account {

    private int accountId;
    private String email;
    private String userName;
    private String password;
    private String address;
    private String fullName;
    private String phoneNumber;
    private String role;
    private String gender;
    private Date registerDate;

    public Account() {
    }

    public Account(int accountId, String email, String userName, String password, String address, String fullName, String phoneNumber, String role, String gender, Date registerDate) {
        this.accountId = accountId;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.gender = gender;
        this.registerDate = registerDate;
    }

    public Account(String email, String userName, String password, String address, String fullName, String phoneNumber, String role, String gender, Date registerDate) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.gender = gender;
        this.registerDate = registerDate;
    }

    public Account(String email, String username, String password) {
        this.email = email;
        this.userName = username;
        this.password = password;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

}
