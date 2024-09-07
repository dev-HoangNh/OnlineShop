/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private float totalPrice;
    private Date orderDate;
    private Time orderTime;
    private int accountId;
    private int addressId;
    private String description;
    private AddressShip addressShip;
    private List<OrderDetails> orderDetails;
    private Account account;

    public Order() {
    }

    public Order(int orderId, float totalPrice, Date orderDate, Time orderTime, int accountId, int addressId, String description) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.accountId = accountId;
        this.addressId = addressId;
        this.description = description;
    }
    
    public Order(float totalPrice, Date orderDate, Time orderTime, int accountId, int addressId, String description) {
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.accountId = accountId;
        this.addressId = addressId;
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAddressShip(AddressShip addressShip) {
        this.addressShip = addressShip;
    }

    public AddressShip getAddressShip() {
        return addressShip;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
