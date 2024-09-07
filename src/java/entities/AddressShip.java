/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class AddressShip {
    private int addressId;
    private int accountId;
    private String addressName;

    public AddressShip() {
    }

    public AddressShip(int addressId, int accountId, String addressName) {
        this.addressId = addressId;
        this.accountId = accountId;
        this.addressName = addressName;
    }
    
    public AddressShip(int accountId, String addressName) {
        this.accountId = accountId;
        this.addressName = addressName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    
}
