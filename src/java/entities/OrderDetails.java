/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class OrderDetails {
    private int orderDetailsId;
    private int orderId;
    private int productId;
    private int sizeId;
    private int quantity;
    private float unitPrice;
    private Product product;
    private Size size;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailsId, int orderId, int productId, int sizeId, int quantity, float unitPrice) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    public OrderDetails(int orderId, int productId, int sizeId, int quantity, float unitPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    
    

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    
    
}
