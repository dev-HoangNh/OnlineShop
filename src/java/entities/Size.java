/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class Size {
    private int sizeId;
    private int productId;
    private float price;
    private String sizeName;

    public Size() {
    }

    public Size(int sizeId, int productId, float price, String sizeName) {
        this.sizeId = sizeId;
        this.productId = productId;
        this.price = price;
        this.sizeName = sizeName;
    }
    
    public Size(int productId, float price, String sizeName) {
        this.productId = productId;
        this.price = price;
        this.sizeName = sizeName;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
    
    
}
